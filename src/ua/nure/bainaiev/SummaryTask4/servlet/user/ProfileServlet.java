package ua.nure.bainaiev.SummaryTask4.servlet.user;

import ua.nure.bainaiev.SummaryTask4.entity.*;
import ua.nure.bainaiev.SummaryTask4.service.StorageService;
import ua.nure.bainaiev.SummaryTask4.service.TestService;
import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(Constants.ServletPaths.User.PROFILE)
public class ProfileServlet extends BaseServlet {
    private List<Test> listTests = new ArrayList<>();
    private List<Storage> storageList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getCurrentUser(req);

        userRating(getStorageService(), getTestService(), user);


        req.setAttribute(Attributes.LIST_STORAGE, storageList);
        req.setAttribute(Attributes.LIST_TESTS, listTests);
        getServletContext().setAttribute(Attributes.SERVLET_PATH, Constants.ServletPaths.User.PROFILE);

        forward(Constants.Pages.User.PROFILE, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getCurrentUser(req);

        id = getIntParam(req, Constants.Parameters.ID);

        Test test = getTestService().get(id);

        String result = getAnswerService().checkAnswers(test, req);

        getStorageService().save(new Storage(user.getId(), id, result));

        redirectToAction(Constants.ServletPaths.User.PROFILE, req, resp);
    }

    private void userRating(StorageService storageService, TestService testService, User user) {
        if(storageList != null){
            listTests.clear();
            storageList.clear();
        }
        storageList = storageService.getAll(user.getId());

        for (Storage s : storageList) {
            int id = s.getTestId();
            Test obj = testService.get(id);
            listTests.add(obj);
        }
    }
}
