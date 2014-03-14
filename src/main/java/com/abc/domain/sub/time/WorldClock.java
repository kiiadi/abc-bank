/*
 * Copyright (c) 2012-2020  The Bank of New York Mellon, All Rights Reserved.
 */
package com.abc.domain.sub.time;


public class WorldClock implements Clock {

    public TimePoint now() {
        return TimePoint.now();
    }
}