package com.banurr.pet_project.exception;

public class CompanyNotFoundException extends RuntimeException
{
    public CompanyNotFoundException(String message)
    {
        super(message);
    }
}
