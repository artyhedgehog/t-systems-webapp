package com.tsystems.logiweb.ui;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import com.tsystems.logiweb.Logiweb;
import com.tsystems.logiweb.entities.Truck;

/**
 * Servlet for trucks managing.
 */
public class TrucksPageServlet extends BasePageServlet {
    public static final String PAGE_VIEW = "trucks/main.jsp";
    public static final String PAGE_TITLE = "Trucks";
    private static final long serialVersionUID = 1L;


    @Override
    protected RequestDispatcher processGetRequestForDispatcher(
            final HttpServletRequest request) {
        return listTrucks(request);
    }

    /**
     * @param request
     * @return
     */
    private RequestDispatcher listTrucks(final HttpServletRequest request) {
        final List<Truck> trucks = Logiweb.getContext().getServiceFactory()
                .getTrucksService().getTrucksList();
        request.setAttribute("trucks", trucks);
        return dispatchPage(PAGE_VIEW, request);
    }

    @Override
    protected RequestDispatcher processPostRequestForDispatcher(
            final HttpServletRequest request) {

        return listTrucks(request);
    }

    @Override
    protected String getPageTitle() {
        return PAGE_TITLE;
    }

}
