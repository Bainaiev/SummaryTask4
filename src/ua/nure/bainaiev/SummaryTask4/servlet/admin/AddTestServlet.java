package ua.nure.bainaiev.SummaryTask4.servlet.admin;

import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Subject;
import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;
import ua.nure.bainaiev.SummaryTask4.util.validation.TestValidator;
import ua.nure.bainaiev.SummaryTask4.util.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(Constants.ServletPaths.Admin.ADD_TEST)
public class AddTestServlet extends BaseServlet {
    private Map<String, String> messages;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().setAttribute(Attributes.SERVLET_PATH, Constants.ServletPaths.Admin.ADD_TEST);
        if (id != 0) {
            req.setAttribute(Attributes.ID, id);
            forward(Constants.Pages.Admin.ADD_QUESTION, req, resp);
            return;
        }

        if (messages == null) {
            messages = new HashMap<>();
        }
        req.setAttribute(Attributes.TITLE, messages.get(Constants.Keys.TITLE));
        req.setAttribute(Attributes.COMPLEXITY, messages.get(Constants.Keys.COMPLEXITY));
        req.setAttribute(Attributes.TIME_PASSING, messages.get(Constants.Keys.TIME));

        if (messages != null) {
            messages.clear();
        }

        req.setAttribute(Attributes.LIST_SUBJECT, getListSubject());
        forward(Constants.Pages.Admin.ADD_TEST, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(getStringParam(req, Attributes.ID) != null){
            id = getIntParam(req, Attributes.ID);
            redirectToAction(Constants.ServletPaths.Admin.ADD_TEST, req, resp);
            return;
        }
        if(getStringParam(req, Attributes.ADD) != null){
            id = 0;
            redirectToAction(Constants.ServletPaths.Admin.ADD_TEST, req, resp);
            return;
        }

        String title = req.getParameter(Attributes.TITLE);
        int complexity = getIntParam(req, Attributes.COMPLEXITY);
        int timePassing = getIntParam(req, Attributes.TIME_PASSING);
        Subject subject = Subject.valueOf(getStringParam(req, Attributes.SUBJECT));

        Test test = new Test(title, complexity, timePassing, subject);

        Validator validator = new TestValidator(test , getLocale(req));

        if (validator.hasErrors()) {
            messages.put(Constants.Keys.TITLE, validator.getMessages().get(Constants.Keys.TITLE));
            messages.put(Constants.Keys.COMPLEXITY, validator.getMessages().get(Constants.Keys.COMPLEXITY));
            messages.put(Constants.Keys.TIME, validator.getMessages().get(Constants.Keys.TIME));

            redirectToAction(Constants.ServletPaths.Admin.ADD_TEST, req, resp);

            return;
        }

        Test test1 = getTestService().save(test);
        id = test.getId();

        redirectToAction(Constants.ServletPaths.Admin.ADD_TEST, req, resp);
    }
}
