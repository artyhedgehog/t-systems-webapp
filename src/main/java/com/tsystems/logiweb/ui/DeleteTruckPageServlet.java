package com.tsystems.logiweb.ui;

import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import com.tsystems.logiweb.Logiweb;
import com.tsystems.logiweb.entities.Truck;
import com.tsystems.logiweb.service.ServiceException;

/**
 * Servlet implementation class DeleteTruckPageServlet
 */
public class DeleteTruckPageServlet extends BasePageServlet {
    private static final String SUCCESS_MESSAGE = "Truck #%d has been successfully deleted.";
    private static final String CONFIRM_MESSAGE = "Are you sure you want to delete the following truck?";
    public static final String CONFIRM_VIEW = "trucks/confirm-delete.jsp";
    public static final String ALERT_VIEW = "common/alert.jsp";
    public static final String PAGE_TITLE = "Delete truck";
    public static final String
            ERROR_NOT_FOUND = "Could not find truck #%d for deletion.";

    private static final long serialVersionUID = 1L;

    @Override
    protected RequestDispatcher processGetRequestForDispatcher(
            final HttpServletRequest request) {
        final Integer truckId = getTruckId(request);
        try {
            final List<Truck> trucks = Arrays
                    .asList(new Truck[] {Logiweb.getContext()
                                                .getServiceFactory()
                                                .getTrucksService()
                                                .getTruck(truckId)});
            request.setAttribute("trucks", trucks);
        } catch (final ServiceException exception) {
            log.warn(String.format(ERROR_NOT_FOUND,
                                   truckId),
                     exception);
            setAlert(request, ALERT_VIEW, "danger", ERROR_NOT_FOUND);
            return dispatchPath(TrucksPageServlet.PATH, request);
        }
        request.setAttribute("cancelUrl", TrucksPageServlet.PATH);
        setAlert(request, ALERT_VIEW, "warning", CONFIRM_MESSAGE);
        return dispatchPage(CONFIRM_VIEW, request);
    }

    private Integer getTruckId(final HttpServletRequest request) {
        return new RequestParser(request, Truck.class).getPathPartAsInteger(-1);
    }

    @Override
    protected RequestDispatcher processPostRequestForDispatcher(
            final HttpServletRequest request) {
        String alertType, alertMessage;
        final Integer truckId = getTruckId(request);
        try {
            Logiweb.getContext().getServiceFactory().getTrucksService()
                   .removeTruck(truckId);
            alertType = "success";
            alertMessage = String.format(SUCCESS_MESSAGE, truckId);
        } catch (final ServiceException e) {
            alertType = "danger";
            alertMessage = e.getMessage();
            log.warn(alertMessage, e);
        }
        setAlert(request, ALERT_VIEW, alertType, alertMessage);
        return dispatchPath(TrucksPageServlet.PATH, request);
    }

    @Override
    protected String getPageTitle() {
        return PAGE_TITLE;
    }

}
