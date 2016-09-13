package ua.nure.bainaiev.SummaryTask4.servlet.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Role;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Status;
import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;
import ua.nure.bainaiev.SummaryTask4.util.validation.UserValidator;
import ua.nure.bainaiev.SummaryTask4.util.validation.Validator;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {ServletPaths.User.LOGIN})
public class LoginServlet extends BaseServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    private Map<String, String> messages;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (messages == null) {
            messages = new HashMap<>();
        }

        req.setAttribute(Attributes.LOGIN_INVALID, messages.get(Keys.LOGIN_INVALID));
        req.setAttribute(Attributes.PASSWORD_INVALID, messages.get(Keys.PASSWORD_INVALID));
        req.setAttribute(Attributes.STATUS, messages.get(Keys.STATUS));
        req.setAttribute(Attributes.RIGHTS, messages.get(Keys.RIGHTS));
        getServletContext().setAttribute(Attributes.SERVLET_PATH, ServletPaths.User.LOGIN);

        if (messages != null) {
            messages.clear();
        }


        forward(Pages.User.LOGIN, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = getStringParam(req, Parameters.LOGIN);
        String password = getStringParam(req, Parameters.PASSWORD);

        Validator validator = new UserValidator(login, password, getLocale(req));

        if (validator.hasErrors()) {
            messages.put(Keys.LOGIN_INVALID, validator.getMessages().get(Keys.LOGIN));
            messages.put(Keys.PASSWORD_INVALID, validator.getMessages().get(Keys.PASSWORD));

            redirectToAction(ServletPaths.User.LOGIN, req, resp);

            return;
        }

        User user = getUserService().getByLogin(login);

        if (user == null || !user.getLogin().equals(login)) {
            validator.putIssue(Keys.LOGIN, Validation.INVALID_LOGIN);
            messages.put(Keys.LOGIN_INVALID, validator.getMessages().get(Keys.LOGIN));

            redirectToAction(ServletPaths.User.LOGIN, req, resp);
            return;
        }

        LOGGER.debug("User found. The id is {} and login is {}", user.getId(), user.getLogin());

        if (!user.getPassword().equals(password)) {
            validator.putIssue(Keys.PASSWORD, Validation.INVALID_PASSWORD);
            messages.put(Keys.PASSWORD_INVALID, validator.getMessages().get(Keys.PASSWORD));

            redirectToAction(ServletPaths.User.LOGIN, req, resp);
            return;
        }

        if (user.getStatus().equals(Status.BANNED)) {
            validator.putIssue(Keys.STATUS, Validation.BANNED);

            messages.put(Keys.STATUS, validator.getMessages().get(Keys.STATUS));

            redirectToAction(ServletPaths.User.LOGIN, req, resp);
            return;
        }

        if (!user.getRoles().contains(Role.STUDENT)) {
            validator.putIssue(Keys.RIGHTS, Validation.NO_RIGHTS);

            messages.put(Keys.RIGHTS, validator.getMessages().get(Keys.RIGHTS));

            redirectToAction(Constants.ServletPaths.User.LOGIN, req, resp);
            return;
        }

        setCurrentUser(req, user);
        redirectToAction(ServletPaths.User.CATALOG, req, resp);

    }
}
