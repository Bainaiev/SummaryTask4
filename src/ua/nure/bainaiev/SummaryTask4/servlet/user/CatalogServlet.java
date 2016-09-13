package ua.nure.bainaiev.SummaryTask4.servlet.user;

import ua.nure.bainaiev.SummaryTask4.entity.Storage;
import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Subject;
import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(ServletPaths.User.CATALOG)
public class CatalogServlet extends BaseServlet {
    private List<Test> testList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Storage> storageList = null;
        User user = getCurrentUser(req);

        if (user != null) {
            storageList = getStorageService().getAll(user.getId());
        }

        if (testList == null) {
            testList = getTestService().getAll();
        }

        req.setAttribute(Attributes.LIST_SUBJECT, getListSubject());
        req.setAttribute(Attributes.LIST_TESTS, testList);
        req.setAttribute(Attributes.LIST_STORAGE, storageList);
        getServletContext().setAttribute(Attributes.SERVLET_PATH, ServletPaths.User.CATALOG);

        forward(Pages.User.CATALOG, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter(Parameters.TITLE);
        String sort = req.getParameter(Parameters.SORT);
        String order = req.getParameter(Parameters.ORDER);

        testList = getTestService().getSorted(title, sort, order);

        redirectToAction(ServletPaths.User.CATALOG, req, resp);

    }
}
