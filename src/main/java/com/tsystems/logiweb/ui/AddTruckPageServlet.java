package com.tsystems.logiweb.ui;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import com.tsystems.logiweb.Logiweb;
import com.tsystems.logiweb.entities.Town;
import com.tsystems.logiweb.entities.Truck;
import com.tsystems.logiweb.entities.TruckCondition;
import com.tsystems.logiweb.service.ServiceException;
import com.tsystems.logiweb.service.ServiceFactory;
import com.tsystems.logiweb.service.TrucksService;
import com.tsystems.logiweb.ui.viewmodels.Form;
import com.tsystems.logiweb.ui.viewmodels.TruckForm;

public class AddTruckPageServlet extends BasePageServlet {

    public static final String ALERT_VIEW = "common/alert.jsp";
    public static final String PAGE_TITLE = "Add truck";
    public static final String PAGE_VIEW = "trucks/form.jsp";

    private static final long serialVersionUID = 1L;

    @Override
    protected RequestDispatcher processGetRequestForDispatcher(
            final HttpServletRequest request) {
        final ServiceFactory serviceFactory = Logiweb.getContext()
                                                     .getServiceFactory();
        final List<TruckCondition> conditions = serviceFactory
                .getTrucksService().getConditionsList();

        final List<Town> towns = new ArrayList<>();
        final Truck sample = new Truck();
        final Form formViewModel = new TruckForm(sample, conditions, towns);
        request.setAttribute("form", formViewModel);
        return dispatchPage(PAGE_VIEW, request);
    }

    @Override
    protected RequestDispatcher processPostRequestForDispatcher(
            final HttpServletRequest request) {
        final TrucksService service = Logiweb.getContext()
                                             .getServiceFactory()
                                             .getTrucksService();
        final RequestParser parser = new RequestParser(request, Truck.class);

        final String registrationNumber = parser.getValue("regNumber");
        final Byte driversQuantity = parser.getValueAsByte("driversQuantity");
        final Float capacityTons = parser.getValueAsFloat("capacityTons");
        final Integer conditionId = parser.getValueAsInteger("conditionId");
        final Integer townId = parser.getValueAsInteger("townId");

        String alertType, alertMessage;
        try {
            service.addTruck(registrationNumber, driversQuantity, capacityTons,
                             conditionId, townId);
            alertType = "success";
            alertMessage = "Truck has been succesfully added.";
        } catch (final ServiceException exception) {
            alertType = "danger";
            alertMessage = exception.getMessage();
        }

        setView(request, "alertView", ALERT_VIEW);
        request.setAttribute("alertType", alertType);
        request.setAttribute("alertMessage", alertMessage);

        return dispatchPath("/trucks", request);
    }

    @Override
    protected String getPageTitle() {
        return PAGE_TITLE;
    }

}
