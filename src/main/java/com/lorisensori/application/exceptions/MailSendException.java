package com.lorisensori.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class MailSendException extends RuntimeException {
    private String recipientAddress;
    private String message;

    public MailSendException(String recipientAddress, String message) {
        super(String.format("%Error sending [%s] for user [%s]", message, recipientAddress));
        this.recipientAddress = recipientAddress;
        this.message = message;
    }
}