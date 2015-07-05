package com.tsystems.logiweb.ui;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet for trucks managing.
 */
public class TrucksPageServlet extends BasePageServlet {
    static final String PAGE_TITLE = "Trucks";
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrucksPageServlet() {
        super();
    }

    @Override
    protected void setupRequestAttributes(final HttpServletRequest request) {
        super.setupRequestAttributes(request);
        request.setAttribute("pageTitle", PAGE_TITLE);
    }

    @Override
    protected String processGetRequestForLayout(final HttpServletRequest request) {
        return null;
    }

    @Override
    protected String processPostRequestForLayout(final HttpServletRequest request) {
        return null;
    }

}
