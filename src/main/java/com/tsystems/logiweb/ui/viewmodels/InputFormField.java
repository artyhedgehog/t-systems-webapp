package com.tsystems.logiweb.ui.viewmodels;

import com.tsystems.logiweb.entities.LabeledFieldsEntity;


public class InputFormField extends FormField {
    private final String placeholder;
    private final FieldType type;

    public InputFormField(final String id, final String label,
                          final String name, final String value,
                          final String placeholder, final FieldType type) {
        super(id, label, name, value);
        this.placeholder = placeholder;
        this.type = type;
    }

    public InputFormField(final String id, final LabeledFieldsEntity entity,
                          final String property, final String placeholder,
                          final FieldType type) {
        super(id, entity, property);
        this.placeholder = placeholder;
        this.type = type;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    public FieldType getType() {
        return type;
    }
}
