package com.tsystems.logiweb.ui;

import javax.servlet.http.HttpServletRequest;

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
        return PAGE_VIEW;
    }

    @Override
    protected String processPostRequestForAView(
            final HttpServletRequest request) {
        return PAGE_VIEW;
    }

    @Override
    protected String getPageTitle() {
        return PAGE_TITLE;
    }

}
