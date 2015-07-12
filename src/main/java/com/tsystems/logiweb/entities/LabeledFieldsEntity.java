package com.tsystems.logiweb.entities;

import java.util.Map;

public interface LabeledFieldsEntity {
    /**
     * Get fields with label.
     *
     * Keys are properties name, values are labels.
     * @return
     */
    Map<String, String> fieldsLabels();
}
