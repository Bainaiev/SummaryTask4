package ua.nure.bainaiev.SummaryTask4.servlet.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Role;
import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;
import ua.nure.bainaiev.SummaryTask4.util.validation.UserValidator;
import ua.nure.bainaiev.SummaryTask4.util.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(Constants.ServletPaths.Admin.LOGIN)
public class LoginServlet extends BaseServlet{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    private Map<String, String> messages;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getCurrentUser(req);

        if (messages == null) {
            messages = new HashMap<>();
        }

        req.setAttribute("loginInvalid", messages.get("loginInvalid"));
        req.setAttribute("passwordInvalid", messages.get("passwordInvalid"));
        req.setAttribute("status", messages.get("status"));
        req.setAttribute("rights", messages.get("rights"));
        getServletContext().setAttribute(Attributes.SERVLET_PATH, Constants.ServletPaths.Admin.LOGIN);

        if (messages != null) {
            messages.clear();
        }

        if(user!=null && user.getRoles().contains(Role.ADMIN)){
            redirectToAction(Constants.Pages.Admin.MAIN_PAGE, req, resp);
            return;
        }
        forward(Constants.Pages.Admin.LOGIN, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getCurrentUser(req);
        if (user!=null && user.getRoles().contains(Role.ADMIN)){
            LOGGER.error("Admin already logged in");
            redirectToAction(Constants.Pages.Admin.MAIN_PAGE, req, resp);
            return;
        }

        String login = getStringParam(req, Attributes.LOGIN);
        String password = getStringParam(req, Attributes.PASSWORD);

        Validator validator = new UserValidator(login, password, getLocale(req));

        if (validator.hasErrors()) {
            messages.put("loginInvalid", validator.getMessages().get("login"));
            messages.put("passwordInvalid", validator.getMessages().get("password"));

            redirectToAction(Constants.ServletPaths.Admin.LOGIN, req, resp);

            return;
        }

        user = getUserService().getByLogin(login);
        if (user == null || !user.getLogin().equals(login)) {
            validator.putIssue("login", "validator.invalidLogin");
            messages.put("loginInvalid", validator.getMessages().get("login"));

            redirectToAction(Constants.ServletPaths.Admin.LOGIN, req, resp);
            return;
        }

        LOGGER.debug("User found. The id is {} and login is {}", user.getId(), user.getLogin());

        if (!user.getPassword().equals(password)) {
            validator.putIssue("password", "validator.invalidPassword");
            messages.put("passwordInvalid", validator.getMessages().get("password"));

            redirectToAction(Constants.ServletPaths.Admin.LOGIN, req, resp);
            return;
        }

        if (!user.getRoles().contains(Role.ADMIN)) {
            validator.putIssue("rights", "validator.noRights");

            messages.put("rights", validator.getMessages().get("rights"));

            redirectToAction(Constants.ServletPaths.Admin.LOGIN, req, resp);
            return;
        }

        setCurrentUser(req, user);

        redirectToAction(Constants.ServletPaths.Admin.MAIN, req, resp);
    }
}
