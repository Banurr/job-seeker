package com.banurr.pet_project.exception;

import com.banurr.pet_project.model.Application;

public class ApplicationNotFoundException extends RuntimeException
{
    public ApplicationNotFoundException(String message)
    {
        super(message);
    }
}
