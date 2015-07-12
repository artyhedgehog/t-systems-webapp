package com.tsystems.logiweb.ui.viewmodels;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.tsystems.logiweb.entities.LabeledFieldsEntity;

public abstract class FormField {
    private static final String NAME_FORMAT = "%s[%s]";

    private final String id;
    private final String label;
    private final String name;
    private final String value;


    public FormField(final String id, final LabeledFieldsEntity entity,
                     final String property) {
        this(id, entity.fieldsLabels().get(property),
             getName(entity.getClass(), property), getValue(entity, property));
    }

    public static String getName(final Class<?> entityClass,
                                 final String property) {
        return String.format(NAME_FORMAT, entityClass.getSimpleName(),
                             property);
    }

    public static String getValue(final Object entity, final String property) {
        final String getterMethodName = String.format("get%s%s",
                                                      property.substring(0, 1)
                                                              .toUpperCase(),
                                                      property.substring(1));
        try {
            final Method getter = entity.getClass().getMethod(getterMethodName);
            return (String) getter.invoke(entity);
        } catch (NoSuchMethodException | SecurityException
                 | IllegalAccessException | IllegalArgumentException
                 | InvocationTargetException exception) {
            Logger.getLogger(FormField.class)
                  .warn(String.format("Failed getting value for a property %s.",
                                      property),
                        exception);
            return "";
        }
    }

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

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public abstract FieldType getType();
}
