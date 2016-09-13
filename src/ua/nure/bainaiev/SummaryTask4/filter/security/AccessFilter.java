package ua.nure.bainaiev.SummaryTask4.filter.security;

import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Role;
import ua.nure.bainaiev.SummaryTask4.filter.BaseFilter;
import ua.nure.bainaiev.SummaryTask4.util.Tuple;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filters request and defines whether the current user can access specified path.
 * In case of denied access redirects to the path that can be accessed.
 */
public class AccessFilter extends BaseFilter {
    private static final AccessConfig[] CONFIGS = new AccessConfig[]{
            new ActionAccessConfig(
                    Role.STUDENT,
                    Constants.ServletPaths.User.LOGIN,
                    Constants.ServletPaths.User.PROFILE,
                    Constants.ServletPaths.User.CATALOG
            ),
            new ModuleAccessConfig(
                    Role.ADMIN,
                    Constants.ServletPaths.Admin.ADMIN,
                    Constants.ServletPaths.Admin.LOGIN,
                    Constants.ServletPaths.Admin.LOGIN
            )
    };

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String path = request.getServletPath();
        User user = (User) session.getAttribute(Attributes.CURRENT_USER);
        String redirect = isAllowed(path, user);
        if (redirect != null) {
            response.sendRedirect(redirect);
            return;
        }
        chain.doFilter(request, response);
    }

    private String isAllowed(String path, User user) {
        for (AccessConfig config : CONFIGS) {
            Tuple<Boolean, Boolean> t = config.isAllowed(path, user);
            if (t.getSecondEntity()) {
                continue;
            }
            return t.getFirstEntity() ? null : config.getRedirect();
        }
        return null;
    }

}
