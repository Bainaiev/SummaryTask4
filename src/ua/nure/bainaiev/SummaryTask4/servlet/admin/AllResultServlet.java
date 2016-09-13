package ua.nure.bainaiev.SummaryTask4.servlet.admin;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import ua.nure.bainaiev.SummaryTask4.bean.UserBean;
import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.service.UserService;
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
import java.util.Map;

@WebServlet(Constants.ServletPaths.Admin.GET_ALL_RESULT)
public class AllResultServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = getUserService();

        Map<UserBean, Integer> map = userService.getAllResult();

        List<UserBean> list = new ArrayList<>();
        for (Map.Entry<UserBean, Integer> entry : map.entrySet()) {
            UserBean userBean = entry.getKey();
            userBean.setResult(entry.getValue());
            list.add(userBean);
        }

        req.setAttribute("list", list);
        forward(Constants.Pages.Admin.RESULT, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
