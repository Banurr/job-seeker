package com.banurr.pet_project.exception;

public class TokenBlackListedException extends RuntimeException
{
    public TokenBlackListedException(String message)
    {
        super(message);
    }
}
