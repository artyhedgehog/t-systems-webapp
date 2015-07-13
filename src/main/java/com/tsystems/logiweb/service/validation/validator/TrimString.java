package com.tsystems.logiweb.service.validation.validator;

import com.tsystems.logiweb.service.validation.ValidationException;

public class TrimString extends BaseValidator<String> {

    private static final String ERROR = "Failed trimming given string: '%s'.";

    public TrimString(final String propertyName) {
        super(propertyName);
    }

    @Override
    public String validate(final String value) throws ValidationException {
        if (null == value) {
            return null;
        }
        try {
            return value.trim();
        } catch (final Exception cause) {
            throw new ValidationException(propertyName,
                                          String.format(ERROR, value),
                                          cause);
        }
    }

}
