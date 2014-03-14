package com.abc.domain.sub.time;

import java.util.Calendar;

/**
 * This represents the specific day of the year.
 */
public final class DayOfYear implements Comparable<DayOfYear> {

    private int year;
    
    /**
     * month (1 ~ 12)
     */
    private int month;
    
    private int day;

    public static DayOfYear at(final int year, final int month, final int day) {
        return new DayOfYear(year, month, day);
    }
    
    public static DayOfYear today() { 
        return TimePoint.now().asDayOfYear();
    }

    private DayOfYear(final int year, final int month, final int day) { 
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() { 
        return year;
    }

    /**
     * This returns month (1~12)
     */
    public int getMonth() { 
        return month;
    }

    public int getDay() { 
        return day;
    }
    
	public TimePoint asTimePoint() {
		return TimePoint.atMidnight(year, month, day);
	}
	
	public DayOfYear rollDay(int day) {
		if (day == 0) {
			return this;
		}
	
		Calendar calendar = asTimePoint().asCalendar();
		calendar.roll(Calendar.DAY_OF_YEAR, day);
		
		return at(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
	}
	
    public boolean isBefore(final DayOfYear other) { 
        if (year < other.year) {
            return true;
        }

        if (year > other.year) {
            return false;
        }

        if (month < other.month) {
            return true;
        }

        if (month > other.month) {
            return false;
        }

        if (day < other.day) {
            return true;
        }

        return false;
    }

    public boolean isAfter(final DayOfYear other) { 
        return !isBefore(other) && !equals(other);
    }

    public int compareTo(final DayOfYear other) { 
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

        if (!(object instanceof DayOfYear)) {
            return false;
        }

        DayOfYear other = (DayOfYear) object;
        return year == other.year && month == other.month && day == other.day;
    }
    
    @Override
    public int hashCode() { 
        int result = 17;
        
        result = 37 * result + year;
        result = 37 * result + month;
        result = 37 * result + day;

        return result;
    }
    
    @Override
    public String toString() { 
        return year + "-" + month + "-" + day;
    }
}
