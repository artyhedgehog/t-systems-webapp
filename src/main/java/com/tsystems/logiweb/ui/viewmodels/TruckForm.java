package com.tsystems.logiweb.ui.viewmodels;

import java.util.Arrays;
import java.util.List;

import com.tsystems.logiweb.entities.LabeledFieldsEntity;
import com.tsystems.logiweb.entities.Town;
import com.tsystems.logiweb.entities.TruckCondition;


public class TruckForm extends Form {

    public TruckForm(final LabeledFieldsEntity entity,
                     final List<TruckCondition> conditions,
                     final List<Town> towns) {
        super(buildFields(entity, conditions, towns));
    }

    private static List<FormField> buildFields(final LabeledFieldsEntity entity,
            final List<TruckCondition> conditions, final List<Town> towns) {
        final FormField[] fields = {
                new InputFormField("regnumber-input", entity,
                                   "regNumber", "License plate number",
                                   FieldType.TEXT),
                new InputFormField("driversnum-input", entity,
                                   "driversQuantity",
                                   "Number of drivers in shift",
                                   FieldType.NUMBER),
                new InputFormField("capacity-input", entity,
                                   "capacityTons",
                                   "Truck cargo capacity", FieldType.NUMBER),
                new SelectFormField<TruckCondition>("condition-select", entity,
                                                    "conditionId",
                                                    conditions),
                new SelectFormField<Town>("town-select", entity, "townId",
                                          towns),
                new InputFormField("truck-form-submit", "", "", "Save", "",
                                   FieldType.SUBMIT)
        };

        return Arrays.asList(fields);
    }
}
