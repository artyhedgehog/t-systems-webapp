package com.tsystems.logiweb.service.validation.validator;

import com.tsystems.logiweb.service.validation.ValidationException;

/**
 * Interface for property value validators.
 *
 * @param <T> Type of value to validate.
 */
public interface Validator<T> {
    /**
     * Validate given value.
     *
     * @param value Raw value.
     * @return Corrected value if applicable or raw value otherwise.
     * @throws ValidationException On invalid value.
     */
    T validate(T value) throws ValidationException;
}
