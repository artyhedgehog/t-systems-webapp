package com.tsystems.logiweb.ui;

import javax.servlet.http.HttpServletRequest;

import com.tsystems.logiweb.entities.LabeledFieldsEntity;
import com.tsystems.logiweb.ui.viewmodels.FormField;

public class RequestParser {
    private final HttpServletRequest request;
    private final LabeledFieldsEntity entity;

    public RequestParser(final HttpServletRequest request,
                         final LabeledFieldsEntity entity) {
        super();
        this.request = request;
        this.entity = entity;
    }

    /**
     * @param property
     *            Name of field property.
     * @see LabeledFieldsEntity#fieldsLabels()
     * @return
     */
    public String getValue(final String property) {
        return request.getParameter(FormField.getName(entity, property));
    }

    public Byte getValueAsByte(final String property) {
        final String raw = getValue(property);
        if (null == raw) {
            return null;
        } else if ("" == raw) {
            return 0;
        }
        return Byte.parseByte(raw);
    }

    public Integer getValueAsInteger(final String property) {
        final String raw = getValue(property);
        if (null == raw) {
            return null;
        } else if ("" == raw) {
            return 0;
        }
        return Integer.parseInt(raw);
    }

    public Float getValueAsFloat(final String property) {
        final String raw = getValue(property);
        if (null == raw) {
            return null;
        } else if ("" == raw) {
            return 0f;
        }
        return Float.parseFloat(raw);
    }
}
