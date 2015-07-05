package com.tsystems.logiweb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for trucks managing.
 */
public class TrucksServlet extends HttpServlet {
    private static final String APP_TITLE = "Logiweb";
    private static final String VIEWS_PATH = "/WEB-INF/jsp/";
    private static final String PAGE_TITLE = "Trucks";
    private static final long serialVersionUID = 1L;
    private final List<String> js = new ArrayList<>();
    private final List<String> css = new ArrayList<>();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrucksServlet() {
        super();
        initJS();
        initCSS();
    }

    protected void initJS() {
        addJS("https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js");
        addJS("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js");
    }

    protected void initCSS() {
        addCSS("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css");
        addCSS("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css");

        addCSS("/css/sticky-footer.css");
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
        renderView("layout.jsp", request, response);
    }

    protected void renderView(final String view,
                              final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServletException, IOException {

        final ServletContext context;
        final RequestDispatcher dispatcher;

        context = request.getServletContext();
        dispatcher = context.getRequestDispatcher(VIEWS_PATH + view);
        dispatcher.forward(request, response);
    }

    /**
     * Setup request attributes for usage in layout.jsp.
     * @param request
     */
    protected void setupRequestAttributes(final HttpServletRequest request) {
        request.setAttribute("appTitle", APP_TITLE);
        request.setAttribute("pageTitle", PAGE_TITLE);

        request.setAttribute("scripts", js);
        request.setAttribute("stylesheets", css);

        setView(request, "headerView", "common/header.jsp");
        setView(request, "footerView", "common/footer.jsp");

        setView(request, "pageView", "trucks/main.jsp");
    }

    /**
     * @param request
     * @param attributeName
     * @param viewRelativePath
     */
    protected void setView(final HttpServletRequest request,
                             final String attributeName,
                             final String viewRelativePath) {
        request.setAttribute(attributeName, VIEWS_PATH + viewRelativePath);
    }

    protected void addJS(final String url) {
        js.add(url);
    }

    protected void addCSS(final String url) {
        css.add(url);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException,
            IOException {
        // TODO Auto-generated method stub
    }

}
