package com.banurr.pet_project.mapper;

import com.banurr.pet_project.dto.CompanyDto;
import com.banurr.pet_project.model.Company;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface CompanyMapper
{
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company toEntity(CompanyDto companyDto);

    CompanyDto toDto(Company company);
}
