package com.tsystems.logiweb.ui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tsystems.logiweb.persistence.entities.Truck;
import com.tsystems.logiweb.service.JPABasedTrucksService;

/**
 * Servlet for trucks managing.
 */
public class TrucksPageServlet extends BasePageServlet {
    public static final String PAGE_VIEW = "trucks/main.jsp";
    public static final String PAGE_TITLE = "Trucks";
    private static final long serialVersionUID = 1L;


    @Override
    protected String processGetRequestForAView(
            final HttpServletRequest request) {
        return listTrucks(request);
    }

    /**
     * @param request
     * @return
     */
    private String listTrucks(final HttpServletRequest request) {
        final List<Truck> trucks = new JPABasedTrucksService().getTrucksList();
        request.setAttribute("trucks", trucks);
        return PAGE_VIEW;
    }

    @Override
    protected String processPostRequestForAView(
            final HttpServletRequest request) {

        return listTrucks(request);
    }

    @Override
    protected String getPageTitle() {
        return PAGE_TITLE;
    }

}
