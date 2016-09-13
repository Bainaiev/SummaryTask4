package ua.nure.bainaiev.SummaryTask4.listener;


import ua.nure.bainaiev.SummaryTask4.db.holder.ThreadLocalConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.db.manager.BoneCPManager;
import ua.nure.bainaiev.SummaryTask4.util.context.ContextLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {
    private ContextLoader loader;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        loader = new ContextLoader(
                sce.getServletContext(),
                new ThreadLocalConnectionHolder(),
                new BoneCPManager()
        );
        loader.load(
                "ua.nure.bainaiev.SummaryTask4.repository",
                "ua.nure.bainaiev.SummaryTask4.service"
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        loader.destroy(sce.getServletContext());
    }
}
