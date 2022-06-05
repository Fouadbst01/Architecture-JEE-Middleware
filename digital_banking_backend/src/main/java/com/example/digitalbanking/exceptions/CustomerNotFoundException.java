package com.example.digitalbanking.exceptions;

import java.util.function.Supplier;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
