package com.tsystems.logiweb.service.validation.validator;

public abstract class BaseValidator<T> implements Validator<T> {
    protected String propertyName;

    public BaseValidator(final String propertyName) {
        super();
        this.propertyName = propertyName;
    }
}
