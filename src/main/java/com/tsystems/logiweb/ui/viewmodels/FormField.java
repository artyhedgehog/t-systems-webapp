package com.tsystems.logiweb.ui.viewmodels;

/**
 */
public abstract class FormField {
    private String id;
    private String label;
    private String name;
    private String value;

    public FormField(final String id, final String label,
                     final String name, final String value) {
        super();
        this.id = id;
        this.label = label;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public FormField setId(final String id) {
        this.id = id;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public FormField setLabel(final String label) {
        this.label = label;
        return this;
    }

    public String getName() {
        return name;
    }

    public FormField setName(final String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public FormField setValue(final String value) {
        this.value = value;
        return this;
    }

    public abstract FieldType getType();
}
