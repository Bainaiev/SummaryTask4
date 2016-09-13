package ua.nure.bainaiev.SummaryTask4.servlet.admin;


import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Subject;
import ua.nure.bainaiev.SummaryTask4.service.TestService;
import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.ServletPaths.Admin.UPDATE_TEST)
public class UpdateTestServlet extends BaseServlet {
    private Test test;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getAttribute(Attributes.TEST) != null){
            test = (Test) req.getAttribute(Attributes.TEST);
        }
        if (test != null) {
            req.setAttribute(Attributes.TEST, test);
            getServletContext().setAttribute(Attributes.SERVLET_PATH, Constants.ServletPaths.Admin.UPDATE_TEST);
            forward(Constants.Pages.Admin.EDIT_QUESTIONS, req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter(Attributes.TITLE);
        int complexity = getIntParam(req, Attributes.COMPLEXITY);
        int timePassing = getIntParam(req, Attributes.TIME_PASSING);
        Subject subject = Subject.valueOf(getStringParam(req, Attributes.SUBJECT));
        id = getIntParam(req, Attributes.ID);

        test = getTestService().update(new Test(title, complexity, timePassing, subject, id));

        redirectToAction(Constants.ServletPaths.Admin.UPDATE_TEST, req, resp);
    }
}
