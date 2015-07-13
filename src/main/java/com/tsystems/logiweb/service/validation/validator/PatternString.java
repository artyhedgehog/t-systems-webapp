package com.tsystems.logiweb.service.validation.validator;

import java.util.regex.Pattern;

import com.tsystems.logiweb.service.validation.ValidationException;

public class PatternString extends BaseValidator<String> {

    private static final String
            ERROR = "String '%s' does not match given pattern: %s";
    private final Pattern pattern;

    public PatternString(final String regexp, final String propertyName) {
        super(propertyName);
        pattern = Pattern.compile(regexp);
    }

    @Override
    public String validate(final String value) throws ValidationException {
        if (null == value) {
            return null;
        }
        if (!pattern.matcher(value).matches()) {
            throw new ValidationException(propertyName,
                                          String.format(ERROR, value, pattern));
        }
        return value;
    }

}
