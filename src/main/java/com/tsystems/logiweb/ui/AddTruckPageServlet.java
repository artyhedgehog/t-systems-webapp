package com.tsystems.logiweb.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public static final String PAGE_VIEW = "trucks/add.jsp";

    private static final long serialVersionUID = 1L;

    @Override
    protected String processGetRequestForAView(
            final HttpServletRequest request) {
        final ServiceFactory serviceFactory = Logiweb.getContext()
                                                     .getServiceFactory();
        final List<TruckCondition> conditions = serviceFactory
                .getTrucksService().getConditionsList();

        final List<Town> towns = new ArrayList<>();
        final Truck sample = new Truck();
        final Form formViewModel = new TruckForm(sample, conditions, towns);
        request.setAttribute("form", formViewModel);
        return PAGE_VIEW;
    }

    @Override
    protected String processPostRequestForAView(
            final HttpServletRequest request) {

        return null;
    }


    @Override
    protected void doPost(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {
        setupRequestAttributes(request);

        final TrucksService service = Logiweb.getContext()
                                             .getServiceFactory()
                                             .getTrucksService();
        final RequestParser parser = new RequestParser(request, new Truck());

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

        forwardToPath("/trucks", request, response);
    }

    @Override
    protected String getPageTitle() {
        return PAGE_TITLE;
    }

}
