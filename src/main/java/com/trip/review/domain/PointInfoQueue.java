package com.trip.review.domain;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class PointInfoQueue<T> {

    private LinkedBlockingQueue<T> pointInfoQ;

    public PointInfoQueue() {
        this.pointInfoQ = new LinkedBlockingQueue<>();
    }

    public void inQ(T pointInfo) {
        this.pointInfoQ.add(pointInfo);
    }

    public void inQ(List<T> pointInfos) {
        for (T pointInfo : pointInfos) {
            this.pointInfoQ.add(pointInfo);
        }
    }

    public T deQ(long timeout, TimeUnit unit) {
        try {
            return this.pointInfoQ.poll(timeout, unit);
        } catch (InterruptedException e) {
            return null;
        }
    }

    public int getCountOfQ() {
        return this.pointInfoQ.size();
    }
}

