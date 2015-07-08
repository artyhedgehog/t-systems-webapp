package com.tsystems.logiweb.ui.viewmodels;

import java.util.Arrays;
import java.util.List;

import com.tsystems.logiweb.persistence.entities.Town;
import com.tsystems.logiweb.persistence.entities.TruckCondition;


public class TruckForm extends Form {

    private static List<FormField> buildEmptyFields(
            final List<TruckCondition> conditions,
            final List<Town> towns) {

        final FormField[] fields = {
                new InputFormField("regnumber-input", "Registration number",
                                   "Truck[registrationNumber]", "",
                                   "License plate number", FieldType.TEXT),
                new InputFormField("driversnum-input", "Drivers quantity",
                                   "Truck[driversQuantity]", "",
                                   "Number of drivers in shift",
                                   FieldType.NUMBER),
                new InputFormField("capacity", "Capacity (tons)",
                                   "Truck[capacityInTons]", "",
                                   "Truck cargo capacity", FieldType.NUMBER),
                new SelectFormField<TruckCondition>("condition-select",
                                                    "Condition",
                                                    "Truck[conditionId]", "",
                                                    conditions),
                new SelectFormField<Town>("town-select", "Town",
                                          "Truck[townId]", "", towns),
                new InputFormField("truck-submit", "", "add", "Add truck", "",
                                   FieldType.SUBMIT)

        };
        return Arrays.asList(fields);
    }

    public TruckForm(final List<TruckCondition> conditions,
                     final List<Town> towns) {
        super(buildEmptyFields(conditions, towns));
    }

}
