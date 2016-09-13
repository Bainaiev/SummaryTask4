package ua.nure.bainaiev.SummaryTask4.servlet.user;

import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.ServletPaths.User.TEST)
public class TestServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(Attributes.TEST, getTestService().get(id));
        getServletContext().setAttribute(Attributes.SERVLET_PATH, Constants.ServletPaths.User.TEST);

        forward(Constants.Pages.User.TEST, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = getIntParam(req, Constants.Parameters.ID);

        redirectToAction(Constants.ServletPaths.User.TEST, req, resp);
    }
}
