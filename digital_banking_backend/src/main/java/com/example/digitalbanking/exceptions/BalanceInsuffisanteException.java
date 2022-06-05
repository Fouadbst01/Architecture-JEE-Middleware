package com.example.digitalbanking.exceptions;

public class BalanceInsuffisanteException extends Throwable {
    public BalanceInsuffisanteException(String message) {
        super(message);
    }
}
