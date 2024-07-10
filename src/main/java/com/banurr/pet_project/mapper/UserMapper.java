package com.banurr.pet_project.mapper;

import com.banurr.pet_project.dto.UserRegisterDto;
import com.banurr.pet_project.model.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface UserMapper
{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRegisterDto userRegisterDto);
}
