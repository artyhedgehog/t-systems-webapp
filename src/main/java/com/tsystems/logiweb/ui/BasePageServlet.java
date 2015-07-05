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


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        setupRequestAttributes(request);
        final String view = processGetRequestForAView(request);
        renderPage(view, request, response);
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
    protected abstract String processGetRequestForAView(
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
        final String view = processPostRequestForAView(request);
        renderPage(view, request, response);
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
    protected abstract String processPostRequestForAView(
            HttpServletRequest request);

    /**
     * Setup request attributes for usage in layout.jsp.
     *
     * @param request
     */
    protected void setupRequestAttributes(final HttpServletRequest request) {
        request.setAttribute("appTitle", APP_TITLE);
        request.setAttribute("pageTitle", getPageTitle());

        request.setAttribute("scripts", getJavaScriptSourceURLs());
        request.setAttribute("stylesheets", getCSSStylesheetURLs());

        setView(request, "headerView", getHeaderView());
        setView(request, "footerView", getFooterView());
    }

    /**
     * Get title for the concrete page.
     * @return Human-readable title to show in browser title and on the page.
     */
    abstract protected String getPageTitle();

    /**
     * Get footer view.
     * @return View file name relative to {@link BasePageServlet.VIEWS_PATH}.
     */
    protected String getFooterView() {
        return "common/footer.jsp";
    }

    /**
     * Get header view.
     * @return View file name relative to {@link BasePageServlet.VIEWS_PATH}.
     */
    protected String getHeaderView() {
        return "common/header.jsp";
    }

    /**
     * Get CSS stylesheets for the web page.
     * @return List of stylesheets URL's.
     */
    protected List<String> getCSSStylesheetURLs() {
        final List<String> css = new ArrayList<>();

        css.add("https://maxcdn.bootstrapcdn.com"
                + "/bootstrap/3.3.5/css/bootstrap.min.css");
        css.add("https://maxcdn.bootstrapcdn.com"
                + "/bootstrap/3.3.5/css/bootstrap-theme.min.css");

        css.add("/css/sticky-footer.css");

        return css;
    }

    /**
     * Get JavaScript source files for the web page.
     * @return List of scripts URL's.
     */
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
     * @param getLayout(attributeName)
     * @param getLayout(viewRelativePath)
     */
    protected void setView(final HttpServletRequest request,
            final String attributeName, final String viewRelativePath) {
        request.setAttribute(attributeName, VIEWS_PATH + viewRelativePath);
    }

    /**
     * Render given view in the page layout.
     * @param pageView
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void renderPage(final String pageView,
                              final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServletException, IOException {
        final ServletContext context;
        final RequestDispatcher dispatcher;

        setView(request, "pageView", pageView);

        context = request.getServletContext();
        dispatcher = context.getRequestDispatcher(LAYOUTS_PATH + getLayout());
        dispatcher.forward(request, response);
    }


    /**
     * Get layout suitable for the page.
     * @param layout Layout name relative to
     *               {@link BasePageServlet.LAYOUTS_PATH}.
     * @return
     */
    protected String getLayout() {
        return DEFAULT_LAYOUT;
    }
}
