package com.banurr.pet_project.mapper;

import com.banurr.pet_project.dto.ApplicationView;
import com.banurr.pet_project.model.Application;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface ApplicationMapper
{
    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    ApplicationView toDto(Application application);
}
