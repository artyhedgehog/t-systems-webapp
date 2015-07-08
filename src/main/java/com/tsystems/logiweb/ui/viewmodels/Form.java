package com.tsystems.logiweb.ui.viewmodels;

import java.util.ArrayList;
import java.util.List;

public class Form {

    private List<FormField> fields;

    public List<FormField> getFields() {
        return fields;
    }

    public void setFields(final List<FormField> fields) {
        this.fields = fields;
    }

    public Form(final List<FormField> fields) {
        super();
        this.fields = fields;
    }

    public Form() {
        super();
        fields = new ArrayList<>();
    }

}
