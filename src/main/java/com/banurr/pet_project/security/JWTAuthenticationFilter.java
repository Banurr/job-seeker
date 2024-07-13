package com.banurr.pet_project.security;

import com.banurr.pet_project.exception.InvalidTokenException;
import com.banurr.pet_project.exception.TokenBlackListedException;
import com.banurr.pet_project.service.TokenBlacklistService;
import com.banurr.pet_project.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter
{

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException
    {
        try
        {
            String token = jwtGenerator.getJWTFromRequest(request);
            if(StringUtils.hasText(token) && jwtGenerator.validateToken(token))
            {
                if(tokenBlacklistService.isTokenBlacklisted(token)) throw new TokenBlackListedException("Token is black listed");

                String email = jwtGenerator.getEmailFromJWT(token);
                UserDetails userDetails = userService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        catch (InvalidTokenException | TokenBlackListedException exception)
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(exception.getMessage());
            return;
        }
        filterChain.doFilter(request,response);
    }
}
