package com.tsystems.logiweb.ui.viewmodels;

import java.util.List;

import com.tsystems.logiweb.entities.LabeledFieldsEntity;

/**
 *
 * @param <O> Entity type for options.
 */
public class SelectFormField<O> extends FormField {
    private final List<O> options;

    public SelectFormField(final String id, final String label,
                           final String name, final String value,
                           final List<O> options) {
        super(id, label, name, value);
        this.options = options;
    }

    public SelectFormField(final String id, final LabeledFieldsEntity entity,
                           final String property, final List<O> options) {
        super(id, entity, property);
        this.options = options;
    }

    public List<O> getOptions() {
        return options;
    }

    @Override
    public FieldType getType() {
        return FieldType.SELECT;
    }
}
