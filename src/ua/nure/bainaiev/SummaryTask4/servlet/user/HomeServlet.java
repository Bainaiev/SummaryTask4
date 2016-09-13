package ua.nure.bainaiev.SummaryTask4.servlet.user;

import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({Constants.ServletPaths.User.HOME, Constants.ServletPaths.START_PATH})
public class HomeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().setAttribute(Attributes.SERVLET_PATH, Constants.ServletPaths.User.HOME);
        forward(Constants.Pages.User.HOME, req, resp);
    }
}
