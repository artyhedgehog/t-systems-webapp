package com.tsystems.logiweb.ui.viewmodels;

import java.util.List;

/**
 *
 * @param <E> Entity type for options.
 */
public class SelectFormField<E> extends FormField {
    private List<E> options;

    public SelectFormField(final String id, final String label,
                           final String name, final String value,
                           final List<E> options) {
        super(id, label, name, value);
        this.options = options;
    }

    public List<E> getOptions() {
        return options;
    }

    public SelectFormField<E> setOptions(final List<E> options) {
        this.options = options;
        return this;
    }

    @Override
    public FieldType getType() {
        return FieldType.SELECT;
    }
}
