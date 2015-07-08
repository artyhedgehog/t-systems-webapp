package com.tsystems.logiweb.ui;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tsystems.logiweb.persistence.entities.Town;
import com.tsystems.logiweb.persistence.entities.TruckCondition;
import com.tsystems.logiweb.ui.viewmodels.Form;
import com.tsystems.logiweb.ui.viewmodels.TruckForm;

public class AddTruckPageServlet extends BasePageServlet {

    private static final long serialVersionUID = 1L;
    private static final String ADD_TRUCK_VIEW = "trucks/add.jsp";

    @Override
    protected String processGetRequestForAView(
            final HttpServletRequest request) {
        final List<TruckCondition> conditions = new ArrayList<>();
        final List<Town> towns = new ArrayList<>();
        final Form formViewModel = new TruckForm(conditions, towns);
        request.setAttribute("form", formViewModel);
        return ADD_TRUCK_VIEW;
    }

    @Override
    protected String processPostRequestForAView(
            final HttpServletRequest request) {
        return ADD_TRUCK_VIEW;
    }

    @Override
    protected String getPageTitle() {
        return "Add truck";
    }

}
