package com.tsystems.logiweb.service.validation;

import com.tsystems.logiweb.service.ServiceException;

public class ValidationException extends ServiceException {

    private static final long serialVersionUID = 1L;

    private final String property;


    public ValidationException(final String property,
                                      final String message,
                                      final Throwable cause,
                                      final boolean enableSuppression,
                                      final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.property = property;
    }

    public ValidationException(final String property,
                                      final String message,
                                      final Throwable cause) {
        super(message, cause);
        this.property = property;
    }

    public ValidationException(final String property,
                                      final String message) {
        super(message);
        this.property = property;
    }

    public ValidationException(final String property,
                                      final Throwable cause) {
        super(cause);
        this.property = property;
    }


    public String getProperty() {
        return property;
    }

}
