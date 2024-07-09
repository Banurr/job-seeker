package com.banurr.pet_project.mapper;

import com.banurr.pet_project.dto.VacancyCreate;
import com.banurr.pet_project.dto.VacancyResponse;
import com.banurr.pet_project.model.Vacancy;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface VacancyMapper
{
    VacancyMapper INSTANCE = Mappers.getMapper(VacancyMapper.class);

    @Mapping(source = "companyId", target = "company.id")
    Vacancy toEntity(VacancyCreate vacancyCreate);

    @Mapping(source = "company", target = "company")
    VacancyResponse toResponseDto(Vacancy vacancy);
}
