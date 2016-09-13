package ua.nure.bainaiev.SummaryTask4.servlet.admin;


import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(Constants.ServletPaths.Admin.TEST)
public class TestServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Test> listTests = getTestService().getAll();
        req.setAttribute(Attributes.LIST_TESTS, listTests);
        getServletContext().setAttribute(Attributes.SERVLET_PATH, Constants.ServletPaths.Admin.TEST);

        forward(Constants.Pages.Admin.TEST, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        redirectToAction(Constants.ServletPaths.Admin.TEST, req, resp);
    }
}
