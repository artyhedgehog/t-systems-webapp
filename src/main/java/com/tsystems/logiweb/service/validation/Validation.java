package com.tsystems.logiweb.service.validation;

import java.util.ArrayList;
import java.util.List;

import com.tsystems.logiweb.service.validation.validator.NotNull;
import com.tsystems.logiweb.service.validation.validator.Validator;

/**
 * Validation manager for some property value.
 * @param <T> Validated value type.
 */
public class Validation<T> {

    protected final String propertyName;
    protected final List<Validator<T>> validators;

    public Validation(final String propertyName) {
        super();
        this.propertyName = propertyName;
        validators = new ArrayList<Validator<T>>();
    }

    public Validation<T> notNull() throws ValidationException {
        validators.add(new NotNull<T>(propertyName));
        return this;
    }

    public T validate(T propertyValue) throws ValidationException {
        for (final Validator<T> validator : validators) {
            propertyValue = validator.validate(propertyValue);
        }
        return propertyValue;
    }
}
