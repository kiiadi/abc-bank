package com.abc.domain.sub.time;

import java.util.Calendar;


public class Duration {
    
    public static final long SECONDS_IN_MILLESECONDS = 1000;
    
    public static final long MINUTES_IN_MILLESECONDS = 60 * SECONDS_IN_MILLESECONDS;
    
    public static final long HOURS_IN_MILLESECONDS = 60 * MINUTES_IN_MILLESECONDS;
    
    public static final long DAYS_IN_MILLESECONDS = 24 * HOURS_IN_MILLESECONDS;
    
    private long milleseconds;
    
    public static Duration of(final long milleseconds) {
        return new Duration(milleseconds);
    }
    
    public static Duration between(DayOfYear dayFrom, DayOfYear dayTo) {
        Calendar fromCalendar = dayFrom.asTimePoint().asCalendar();
        int fromYear = fromCalendar.get(Calendar.YEAR);
        int fromDay = fromCalendar.get(Calendar.DAY_OF_YEAR);
        
        Calendar toCalendar = dayTo.asTimePoint().asCalendar();
        int toYear = toCalendar.get(Calendar.YEAR);
        int toDay = toCalendar.get(Calendar.DAY_OF_YEAR);
        
        if (fromYear == toYear) {
            return Duration.of((toDay-fromDay) * DAYS_IN_MILLESECONDS);
        }
        
        return Duration.of((365*(toYear-fromYear)+(toDay-fromDay)) * DAYS_IN_MILLESECONDS);
    }
    
    public Duration(final long milleseconds) {
        if (milleseconds < 0)
            throw new IllegalArgumentException("duration[" + milleseconds + "] cannot be negative value");
        
        this.milleseconds = milleseconds;
    }
    
    public long getMilleseconds() {
        return milleseconds;
    }
    
    public int asDays() {
        return (int) (milleseconds / DAYS_IN_MILLESECONDS);
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Duration)) {
            return false;
        }

        Duration other = (Duration) object;
        return getMilleseconds() == other.getMilleseconds();
    }

    @Override
    public int hashCode() {
        return (int) getMilleseconds();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return milleseconds + " milleseconds";
    }
}
