package com.abc.domain.sub.time;


public class WorldClock implements Clock {

    public TimePoint now() {
        return TimePoint.now();
    }
}