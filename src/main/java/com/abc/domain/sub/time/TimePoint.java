package com.abc.domain.sub.time;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimePoint implements Comparable<TimePoint> {
    
    private long miliseconds;

    public static TimePoint at(final int year, final int month, final int day, final int hour, final int minute, final int second) {
        return at(year, month, day, hour, minute, second, 0, TimeZone.getDefault());
    }
    
    public static TimePoint at(final int year, final int month, final int day, final int hour, final int minute) { 
        return at(year, month, day, hour, minute, 0, 0, TimeZone.getDefault());
    }
    
    static TimePoint at(final int year, final int month, final int day, 
                        final int hour, final int minute, final int second, final int milisecond, 
                        final TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milisecond);
        return new TimePoint(calendar.getTime().getTime());
    }

	public static TimePoint atMidnight(int year, int month, int day) {
		return at(year, month, day, 0, 0, 0);
	}
	
    public static TimePoint at(final long miliseconds) { 
        return new TimePoint(miliseconds);
    }
    
    TimePoint(final long miliseconds) { 
        this.miliseconds = miliseconds;
    }

    public boolean isBefore(final TimePoint other) { 
        return compareTo(other) < 0 ;
    }

    public boolean isAfter(final TimePoint other) { 
        return compareTo(other) > 0 ;
    }
    
    public long getMilliseconds() { 
        return miliseconds;
    }
    
    public DayOfYear asDayOfYear(final TimeZone timeZone) { 
        Calendar calendar = asCalendar(timeZone);
        return DayOfYear.at(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    public DayOfYear asDayOfYear() { 
        return asDayOfYear(TimeZone.getDefault());
    }

    public TimeOfDay asTimeOfDay(final TimeZone timeZone) { 
        Calendar calendar = asCalendar(timeZone);
        return TimeOfDay.at(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), 
                            calendar.get(Calendar.SECOND), calendar.get(Calendar.MILLISECOND));
    }

    public TimeOfDay asTimeOfDay() { 
        return asTimeOfDay(TimeZone.getDefault());
    }   
    
    public Calendar asCalendar(final TimeZone timeZone) { 
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTimeInMillis(miliseconds);
        return calendar;
    }
    
    public Calendar asCalendar() { 
        return asCalendar(TimeZone.getDefault());
    }
    
    public static TimePoint now() { 
        return new TimePoint(new Date().getTime());
    }
    
    public int compareTo(final TimePoint other) { 
        long difference = getMilliseconds() - other.getMilliseconds();

        if (difference < 0) {
            return -1;
        }

        if (difference > 0) {
            return 1;
        }

        return 0;
    }
    
    @Override
    public boolean equals(final Object object) { 
        if (this == object) {
            return true;
        }

        if (!(object instanceof TimePoint)) {
            return false;
        }

        return compareTo((TimePoint) object) == 0;
    }

    @Override
    public int hashCode() { 
        return (int) miliseconds;
    }
    
    @Override
    public String toString() { 
        return miliseconds + " ms" + ", " + asCalendar().getTime();
    }
}