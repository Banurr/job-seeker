package com.banurr.pet_project.exception;

public class VacancyNotFoundException extends RuntimeException
{
    public VacancyNotFoundException(String message)
    {
        super(message);
    }
}
