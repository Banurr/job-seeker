package com.banurr.pet_project.repository;

import com.banurr.pet_project.model.Application;
import com.banurr.pet_project.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>
{
    boolean existsApplicationByEmailAndVacancy(String email, Vacancy vacancy);
}
