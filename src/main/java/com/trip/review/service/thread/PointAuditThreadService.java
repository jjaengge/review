package com.trip.review.service.thread;

import com.trip.review.Configuration;
import com.trip.review.domain.PointInfoQueue;
import com.trip.review.entity.PointAudit;
import com.trip.review.service.repository.RepoService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@WebListener
public class PointAuditThreadService implements ServletContextListener, Runnable {

    @Autowired
    private Configuration config;

    @Autowired
    private RepoService repoService;

    @Autowired
    private PointInfoQueue<PointAudit> pointInfoQueue;

    private Thread thread;
    private boolean isShutdown = false;
    private ServletContext sc;

    public void startDaemon() {
        if (thread == null) {
            thread = new Thread(this, "Point Audit Service Daemon thread for background task");
            thread.setDaemon(true);
        }
        if (!thread.isAlive()) {
            thread.start();
        }
    }

    public void contextInitialized(ServletContextEvent event) {
        if (config.getThreadServiceFlag("pointAudit")) {
            System.out.println("[pointAudit] DaemonListener.contextInitialized has been called.");
            sc = event.getServletContext();
            startDaemon();
        } else {
            System.out.println("[pointAudit::contextInitialized()] pointAudit Flag=[false].");
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        this.isShutdown = true;
        try {
            thread.join();
            thread = null;
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void run() {
        Thread currentThread = Thread.currentThread();
        while (currentThread == thread && !this.isShutdown) {
            try {
                deQueueProc();
            } catch (Exception e) {
                System.out.println("[pointAudit::run()] occured exception.");
            }
        }
        System.out.println("[pointAudit::run()] DaemonListener end.");
    }

    private void deQueueProc() {
        PointAudit data;
        long procDt = System.currentTimeMillis();
        while (true) {
            data = pointInfoQueue.deQ(1000, TimeUnit.MILLISECONDS);

            if (Optional.ofNullable(data).isPresent()) {
                System.out.println(String.format("procDt[%d] userid[%s] placeId[%s] reviewId[%s] reason[%s] point[%d->%d]",
                        procDt, data.getUser().getUserId(), data.getPlaceId(), data.getReviewId(), data.getReviewId(), data.getAgoPoint(), data.getCurrentPoint()));

                repoService.savePointAudit(data);
            } else {
                break;
            }
        }
    }
}
