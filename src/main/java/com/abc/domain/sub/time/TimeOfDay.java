package com.abc.domain.sub.time;

import java.util.Calendar;

public final class TimeOfDay implements Comparable<TimeOfDay> { 
    
    /**
     * hour of day (0 ~ 23)
     */
    private int hour;

    private int minute;

    private int second;
    
    private int milisecond;

    public static TimeOfDay atMidnight() {
        return at(0);
    }

    public static TimeOfDay atNoon() { 
        return at(12);
    }
    
    public static TimeOfDay now() { 
        Calendar now = Calendar.getInstance();
        return at(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND), now.get(Calendar.MILLISECOND));
    }

    public static TimeOfDay at(final int hour) { 
        return at(hour, 0);
    }

    public static TimeOfDay at(final int hour, final int minute) { 
        return at(hour, minute, 0, 0);
    }

    public static TimeOfDay at(final int hour, final int minute, final int second) { 
        return at(hour, minute, second, 0);
    }
    
    public static TimeOfDay at(final int hour, final int minute, final int second, final int milisecond) { 
        return new TimeOfDay(hour, minute, second, milisecond);
    }

    private TimeOfDay(final int hour, final int minute, final int second, final int milisecond) { 
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.milisecond = milisecond;
    }

    public int getHour() { 
        return hour;
    }

    public int getMinute() { 
        return minute;
    }

    public int getSecond() { 
        return second;
    }

    public int getMilisecond() { 
        return milisecond;
    }

    public boolean isBefore(final TimeOfDay other) { 
        if (hour < other.hour) {
            return true;
        }

        if (hour > other.hour) {
            return false;
        }

        if (minute < other.minute) {
            return true;
        }

        if (minute > other.minute) {
            return false;
        }

        if (second < other.second) {
            return true;
        }

        return false;
    }

    public boolean isAfter(final TimeOfDay other) { 
        return !isBefore(other) && !equals(other);
    }

    public int compareTo(final TimeOfDay other) { 
        if (isBefore(other)) {
            return -1;
        }
            
        if (isAfter(other)) {
            return 1;
        }
            
        return 0;
    }

    @Override
    public boolean equals(final Object object) { 
        if (this == object) {
            return true;
        }

        if (!(object instanceof TimeOfDay)) {
            return false;
        }

        TimeOfDay other = (TimeOfDay)object;
        return hour == other.hour && minute == other.minute 
                        && second == other.second && milisecond == other.milisecond;
    }

    @Override
    public int hashCode() { 
        int result = 17;
        result = 37 * result + hour;
        result = 37 * result + minute;
        result = 37 * result + second;
        result = 37 * result + milisecond;
        
        return result;
    }   
    
    @Override
    public String toString() { 
        return  hour + ":" + minute + ":" + second + ":" + milisecond; 
    }   
}