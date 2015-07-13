package com.tsystems.logiweb.service.validation;

import com.tsystems.logiweb.service.validation.validator.PatternString;
import com.tsystems.logiweb.service.validation.validator.TrimString;

public class StringValidation extends Validation<String> {

    public StringValidation(final String propertyName) {
        super(propertyName);
    }

    public StringValidation trim() {
        validators.add(new TrimString(propertyName));
        return this;
    }

    public StringValidation pattern(final String regExp) {
        validators.add(new PatternString(regExp, propertyName));
        return this;
    }
}
