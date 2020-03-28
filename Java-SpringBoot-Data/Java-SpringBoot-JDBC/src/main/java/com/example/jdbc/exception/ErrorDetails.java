package com.example.jdbc.exception;

import java.util.Date;

public final class ErrorDetails {

    private Date timestamp;
    private String details;
    private String cause;
    private String message;

    public ErrorDetails(Date timestamp, String message, String cause, String details) {
        super();
        this.timestamp = timestamp;
        this.details = details;
        this.cause = cause;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }

    public String getCause() {
        return cause;
    }

    public String getMessage() {
        return message;
    }

}
