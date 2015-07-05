package com.tsystems.logiweb.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BasePageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String APP_TITLE = "Logiweb";
    public static final String VIEWS_PATH = "/WEB-INF/views/";
    public static final String LAYOUTS_PATH = "/WEB-INF/layouts/";
    public static final String DEFAULT_LAYOUT = "default.jsp";


    public BasePageServlet() {
        super();
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        setupRequestAttributes(request);
        final String layout = processGetRequestForLayout(request);
        if (null == layout) {
            renderPage(request, response);
        } else {
            renderPage(layout, request, response);
        }
    }

    /**
     * Process GET request with necessary logic and get appropriate layout name.
     *
     * Method might set request attributes if needed.
     *
     * @param request Request to process and store attributes.
     * @return Layout file name relative to {@link BasePageServlet.LAYOUTS_PATH}
     *         to render or null to use {@link BasePageServlet.DEFAULT_LAYOUT}.
     *
     * @see BasePageServlet.processPostRequestForLayout
     */
    protected abstract String processGetRequestForLayout(
            HttpServletRequest request);

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {
        setupRequestAttributes(request);
        final String view = processPostRequestForLayout(request);
        setView(request, "pageView", "trucks/main.jsp");
        if (null == view) {
            renderPage(request, response);
        } else {
            renderPage(view, request, response);
        }
    }

    /**
     * Process POST request with necessary logic and get appropriate layout name.
     *
     * Method might set request attributes if needed.
     *
     * @param request Request to process and store attributes.
     * @return Layout file name relative to {@link BasePageServlet.LAYOUTS_PATH}
     *         to render or null to use {@link BasePageServlet.DEFAULT_LAYOUT}.
     *
     * @see BasePageServlet.processGetRequestForLayout
     */
    protected abstract String processPostRequestForLayout(
            HttpServletRequest request);

    /**
     * Setup request attributes for usage in layout.jsp.
     *
     * @param request
     */
    protected void setupRequestAttributes(final HttpServletRequest request) {
        request.setAttribute("appTitle", APP_TITLE);

        request.setAttribute("scripts", getJavaScriptSourceURLs());
        request.setAttribute("stylesheets", getCSSStylesheetURLs());

        setView(request, "headerView", getHeaderView());
        setView(request, "footerView", getFooterView());
    }


    protected String getFooterView() {
        return "common/footer.jsp";
    }


    protected String getHeaderView() {
        return "common/header.jsp";
    }

    protected List<String> getCSSStylesheetURLs() {
        final List<String> css = new ArrayList<>();

        css.add("https://maxcdn.bootstrapcdn.com"
                + "/bootstrap/3.3.5/css/bootstrap.min.css");
        css.add("https://maxcdn.bootstrapcdn.com"
                + "/bootstrap/3.3.5/css/bootstrap-theme.min.css");

        css.add("/css/sticky-footer.css");

        return css;
    }

    protected List<String> getJavaScriptSourceURLs() {
        final List<String> js = new ArrayList<>();

        js.add("https://ajax.googleapis.com"
               + "/ajax/libs/jquery/2.1.4/jquery.min.js");
        js.add("https://maxcdn.bootstrapcdn.com"
               + "/bootstrap/3.3.5/js/bootstrap.min.js");

        return js;
    }

    /**
     * @param request
     * @param attributeName
     * @param viewRelativePath
     */
    protected void setView(final HttpServletRequest request,
            final String attributeName, final String viewRelativePath) {
        request.setAttribute(attributeName, VIEWS_PATH + viewRelativePath);
    }

    protected void renderPage(final String layout,
                              final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServletException, IOException {
        final ServletContext context;
        final RequestDispatcher dispatcher;

        context = request.getServletContext();
        dispatcher = context.getRequestDispatcher(LAYOUTS_PATH + layout);
        dispatcher.forward(request, response);
    }


    protected void renderPage(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServletException, IOException {
        renderPage(DEFAULT_LAYOUT, request, response);
    }
}
