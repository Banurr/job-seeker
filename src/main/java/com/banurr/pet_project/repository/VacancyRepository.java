package com.banurr.pet_project.repository;

import com.banurr.pet_project.model.Company;
import com.banurr.pet_project.model.Vacancy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long>
{
    @Query("select v from Vacancy v order by v.id asc")
    List<Vacancy> findAll();

    @EntityGraph(attributePaths = "applications")
    Optional<Vacancy> findById(Long id);
}
