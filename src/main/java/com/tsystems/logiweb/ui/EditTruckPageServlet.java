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

public class EditTruckPageServlet extends BasePageServlet {

    public static final String ALERT_VIEW = "common/alert.jsp";
    public static final String PAGE_TITLE = "Edit truck";
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
        final RequestParser parser = new RequestParser(request, Truck.class);
        Truck truck;
        try {
            truck = serviceFactory.getTrucksService()
                    .getTruck(parser.getPathPartAsInteger(-1));
        } catch (final ServiceException e) {
            log.warn(e.getMessage(), e);
            setAlert(request, ALERT_VIEW, "danger", e.getMessage());
            return dispatchPath("/trucks", request);
        }
        final Form formViewModel = new TruckForm(truck, conditions, towns);
        request.setAttribute("form", formViewModel);
        return dispatchPage(PAGE_VIEW, request);
    }

    @Override
    protected RequestDispatcher processPostRequestForDispatcher(
            final HttpServletRequest request) {
        setupRequestAttributes(request);

        final TrucksService service = Logiweb.getContext()
                                             .getServiceFactory()
                                             .getTrucksService();
        final RequestParser parser = new RequestParser(request, Truck.class);


        final Integer truckId = parser.getPathPartAsInteger(-1);
        final String registrationNumber = parser.getValue("regNumber");
        final Byte driversQuantity = parser.getValueAsByte("driversQuantity");
        final Float capacityTons = parser.getValueAsFloat("capacityTons");
        final Integer conditionId = parser.getValueAsInteger("conditionId");
        final Integer townId = parser.getValueAsInteger("townId");

        String alertType, alertMessage;
        try {
            service.modifyTruck(truckId, registrationNumber, driversQuantity,
                                capacityTons, conditionId, townId);

            alertType = "success";
            alertMessage = "Editions to the truck has been succesfully saved.";
        } catch (final ServiceException exception) {
            log.warn(exception.getMessage(), exception);
            alertType = "danger";
            alertMessage = exception.getMessage();
        }

        setAlert(request, ALERT_VIEW, alertType, alertMessage);

        return dispatchPath("/trucks", request);
    }

    @Override
    protected String getPageTitle() {
        return PAGE_TITLE;
    }
}
