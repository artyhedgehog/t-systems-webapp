package com.tsystems.logiweb.ui.viewmodels;


public class InputFormField extends FormField {
    private String placeholder;
    private FieldType type;

    public InputFormField(final String id, final String label,
                          final String name, final String value,
                          final String placeholder, final FieldType type) {
        super(id, label, name, value);
        this.placeholder = placeholder;
        this.type = type;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public InputFormField setPlaceholder(final String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public FieldType getType() {
        return type;
    }

    public InputFormField setType(final FieldType type) {
        this.type = type;
        return this;
    }
}
