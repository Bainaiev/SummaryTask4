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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegisterServlet", urlPatterns = {Constants.ServletPaths.User.REGISTER})
public class RegisterServlet extends BaseServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServlet.class);
    private Map<String, String> messages;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (getCurrentUser(req) != null) {
            redirectToAction(Constants.ServletPaths.User.PROFILE, req, resp);
            return;
        }

        if (messages == null) {
            messages = new HashMap<>();
        }

        req.setAttribute(Attributes.LOGIN_INVALID, messages.get(Constants.Keys.LOGIN_INVALID));
        req.setAttribute(Attributes.PASSWORD_INVALID, messages.get(Constants.Keys.PASSWORD_INVALID));
        req.setAttribute(Attributes.FIRST_NAME, messages.get(Constants.Keys.FIRST_NAME));
        req.setAttribute(Attributes.LAST_NAME, messages.get(Constants.Keys.LAST_NAME));
        req.setAttribute(Attributes.EMAIL, messages.get(Constants.Keys.EMAIL));
        req.setAttribute(Attributes.ERROR, messages.get(Constants.Keys.ERROR));
        getServletContext().setAttribute(Attributes.SERVLET_PATH, Constants.ServletPaths.User.REGISTER);



        if (messages != null) {
            messages.clear();
        }

        forward(Constants.Pages.User.REGISTRATION, req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(getStringParam(req, Constants.Parameters.FIRST_NAME));
        user.setLastName(getStringParam(req, Constants.Parameters.LAST_NAME));
        user.setEmail(getStringParam(req, Constants.Parameters.EMAIL));
        user.setLogin(getStringParam(req, Constants.Parameters.LOGIN));
        user.setPassword(getStringParam(req, Constants.Parameters.PASSWORD));
        String locale = getLocale(req);

        Validator validator = new UserValidator(user, locale);

        if (validator.hasErrors()) {
            messages.put(Constants.Keys.LOGIN_INVALID, validator.getMessages().get(Constants.Keys.LOGIN));
            messages.put(Constants.Keys.PASSWORD_INVALID, validator.getMessages().get(Constants.Keys.PASSWORD));
            messages.put(Constants.Keys.FIRST_NAME, validator.getMessages().get(Constants.Keys.FIRST_NAME));
            messages.put(Constants.Keys.LAST_NAME, validator.getMessages().get(Constants.Keys.LAST_NAME));
            messages.put(Constants.Keys.EMAIL, validator.getMessages().get(Constants.Keys.EMAIL));


            redirectToAction(Constants.ServletPaths.User.REGISTER, req, resp);

            return;
        }

        checkUserUniqueness(user, validator);

        if (validator.hasErrors()) {
            messages.put(Constants.Keys.LOGIN_INVALID, validator.getMessages().get(Constants.Keys.LOGIN));
            messages.put(Constants.Keys.EMAIL, validator.getMessages().get(Constants.Keys.EMAIL));


            redirectToAction(Constants.ServletPaths.User.REGISTER, req, resp);

            return;
        }

        user.addRole(Role.STUDENT);
        user.setStatus(Status.ACTIVE);

        User savedUser = saveUser(user);

        if (savedUser == null) {
            validator.putIssue(Constants.Keys.ERROR, Constants.Validation.ERROR_500);
            messages.put(Constants.Keys.ERROR, validator.getMessages().get(Constants.Keys.ERROR));
            return;
        }

        redirectToAction(Constants.ServletPaths.User.HOME, req, resp);
    }
}
