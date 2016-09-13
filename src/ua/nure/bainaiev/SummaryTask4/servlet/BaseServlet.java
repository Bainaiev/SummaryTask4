package ua.nure.bainaiev.SummaryTask4.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;
import ua.nure.bainaiev.SummaryTask4.util.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Base servlet for module servlets.
 */
public abstract class BaseServlet extends AbstractServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServlet.class);
    protected int id;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(Attributes.CURRENT_USER);
        return obj == null ? null : (User) obj;
    }

    protected void setCurrentUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(Attributes.CURRENT_USER, user);
    }

    protected void unsetCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Attributes.CURRENT_USER);
    }

    protected void forward(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(page).forward(req, resp);
    }

    protected void redirectToAction(String uri, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath()+uri);
    }

    protected void sendError(HttpServletRequest request, HttpServletResponse response, Validator validator)
            throws ServletException, IOException {


        LOGGER.trace("Errors found while processing operation");

        StringBuilder error = new StringBuilder();

        for (Map.Entry<String, String> errorEntry :validator.getMessages().entrySet()) {
            error.append(errorEntry.getKey()).append(": ").append(errorEntry.getValue()).append("\n");
        }

        response.sendError(HttpServletResponse.SC_BAD_REQUEST,error.toString());

    }

    protected void checkUserUniqueness(User user, Validator validator) {
        User existingUser = getUserService().getByLogin(user.getLogin());
        if (existingUser != null) {
            validator.putIssue(Constants.Keys.LOGIN, Constants.Validation.LOGIN_TAKEN);
        }
        existingUser = getUserService().getByEmail(user.getEmail());
        if (existingUser != null) {
            validator.putIssue(Constants.Keys.EMAIL, Constants.Validation.EMAIL_TAKEN);
        }
    }

    protected User saveUser(User user) {
        user.setImage("noimage.jpg");
        return getUserService().save(user);
    }

}
