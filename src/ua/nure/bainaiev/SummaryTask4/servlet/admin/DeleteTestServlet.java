package ua.nure.bainaiev.SummaryTask4.servlet.admin;


import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.ServletPaths.Admin.DELETE_TEST)
public class DeleteTestServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getTestService().delete(id);

        forward(Constants.ServletPaths.Admin.TEST, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = getIntParam(req, "id");

        redirectToAction(Constants.ServletPaths.Admin.DELETE_TEST, req, resp);
    }
}
