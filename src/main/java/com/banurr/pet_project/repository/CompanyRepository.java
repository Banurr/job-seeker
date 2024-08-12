package com.banurr.pet_project.repository;

import com.banurr.pet_project.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>
{
    @Query("SELECT c FROM Company c ORDER BY c.id")
    List<Company> findAll();
}
