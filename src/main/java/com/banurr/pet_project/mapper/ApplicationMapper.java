package com.banurr.pet_project.mapper;

import com.banurr.pet_project.dto.ApplicationView;
import com.banurr.pet_project.model.Application;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true),uses = VacancyMapper.class)
public interface ApplicationMapper
{
    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    @Mapping(source = "vacancy", target = "vacancy")
    ApplicationView toDto(Application application);
}
