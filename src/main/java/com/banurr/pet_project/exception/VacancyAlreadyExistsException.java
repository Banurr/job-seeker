package com.banurr.pet_project.exception;

public class VacancyAlreadyExistsException extends RuntimeException
{
    public VacancyAlreadyExistsException(String message)
    {
        super(message);
    }
}
