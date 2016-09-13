package ua.nure.bainaiev.SummaryTask4.servlet.admin;

import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.service.TestService;
import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.ServletPaths.Admin.EDIT_TEST)
public class EditTestServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TestService testService = getTestService();
        Test test = testService.get(id);


        req.setAttribute(Attributes.TEST, test);
        req.setAttribute(Attributes.LIST_SUBJECT,getListSubject() );
        getServletContext().setAttribute(Attributes.SERVLET_PATH, Constants.ServletPaths.Admin.EDIT_TEST);
        forward(Constants.Pages.Admin.EDIT_TEST, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = getIntParam(req, "id");
        redirectToAction(Constants.ServletPaths.Admin.EDIT_TEST, req, resp);
    }
}
