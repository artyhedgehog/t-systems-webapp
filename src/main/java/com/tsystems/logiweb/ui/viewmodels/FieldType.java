package com.tsystems.logiweb.ui.viewmodels;

public enum FieldType {
    TEXT("text"),
    EMAIL("email"),
    PASSWORD("password"),
    NUMBER("number"),
    SUBMIT("submit"),
    TEXTAREA(null),
    SELECT(null);

    private String inputType;

    FieldType(final String inputType) {
        this.inputType = inputType;
    }

    public String getInputType() {
        return inputType;
    }
}
