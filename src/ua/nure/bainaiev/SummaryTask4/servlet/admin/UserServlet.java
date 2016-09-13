package ua.nure.bainaiev.SummaryTask4.servlet.admin;


import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Status;
import ua.nure.bainaiev.SummaryTask4.service.UserService;
import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(Constants.ServletPaths.Admin.USERS)
public class UserServlet extends BaseServlet {
    private String status;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = getUserService();
        if (status != null) {
            User user = userService.get(id);
            user.setStatus(Status.valueOf(status));
            userService.update(user);
        }


        List<User> listUsers = userService.getAllStudent();
        req.setAttribute(Attributes.LIST_USERS, listUsers);
        getServletContext().setAttribute(Attributes.SERVLET_PATH, Constants.ServletPaths.Admin.USERS);


        forward(Constants.Pages.Admin.USERS, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        status = req.getParameter("status");
        id = getIntParam(req, "id");
        redirectToAction(Constants.ServletPaths.Admin.USERS, req, resp);
    }
}
