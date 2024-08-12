package com.banurr.pet_project.repository;

import com.banurr.pet_project.model.Vacancy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long>
{

    @EntityGraph(attributePaths = "company")
    @Query("select v from Vacancy v order by v.id asc")
    List<Vacancy> findAll();
}
