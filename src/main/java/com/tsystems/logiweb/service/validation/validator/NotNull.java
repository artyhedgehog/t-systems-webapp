package com.tsystems.logiweb.service.validation.validator;

import com.tsystems.logiweb.service.validation.ValidationException;

public class NotNull<T> extends BaseValidator<T> {

    private static final String ERROR = "Property %s must not be null.";

    public NotNull(final String propertyName) {
        super(propertyName);
    }

    @Override
    public T validate(final T value) throws ValidationException {
        if (null == value) {
            throw new ValidationException(propertyName, ERROR);
        }
        return value;
    }

}
