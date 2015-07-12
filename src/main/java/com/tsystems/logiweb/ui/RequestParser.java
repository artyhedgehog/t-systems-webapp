package com.tsystems.logiweb.ui;

import javax.servlet.http.HttpServletRequest;

import com.tsystems.logiweb.entities.LabeledFieldsEntity;
import com.tsystems.logiweb.ui.viewmodels.FormField;

public class RequestParser {
    private final HttpServletRequest request;
    private final Class<?> entityClass;

    public RequestParser(final HttpServletRequest request,
                         final Class<?> entityClass) {
        super();
        this.request = request;
        this.entityClass = entityClass;
    }

    /**
     * @param property
     *            Name of field property.
     * @see LabeledFieldsEntity#fieldsLabels()
     * @return
     */
    public String getValue(final String property) {
        return request.getParameter(FormField.getName(entityClass, property));
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

    public String getPathPart(final int position) {
        final String pathInfo = request.getPathInfo();
        final String[] pathParts = pathInfo.split("/");

        return (position < 0) ? pathParts[pathParts.length + position]
                              : pathParts[position];
    }

    public Integer getPathPartAsInteger(final int position) {
        return Integer.parseInt(getPathPart(position));
    }
}
