package com.banurr.pet_project.repository;

import com.banurr.pet_project.model.Company;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>
{
    @Query("select c from Company c order by c.id asc")
    List<Company> findAll();

    @EntityGraph(attributePaths = "vacancies")
    Optional<Company> findById(Long id);
}
