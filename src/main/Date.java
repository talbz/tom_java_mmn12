/**
 * Set finals.
 * Add doc.
 */

// Comments


public class Date {

    private int _day;
    private int _month;
    private int _year;

    final int DEFAULT_DAY = 1;
    final int DEFAULT_MONTH = 1;
    final int DEFAULT_YEAR = 2000;
    /********************************************************/
    final int FEB = 2;
    /********************************************************/

    /**
     * @param day
     * @param month
     * @param year
     */
    public Date(int day, int month, int year) {
        if (isLegalDate(day, month, year)) {
            _day = day;
            _month = month;
            _year = year;
        } else {
            _day = DEFAULT_DAY;
            _month = DEFAULT_MONTH;
            _year = DEFAULT_YEAR;
        }
    }

    /**
     * @param other
     */
    public Date(Date other) {
        _day = other.getDay();
        _month = other.getMonth();
        _year = other.getYear();
    }

    /**
     * Checks if given date is legal.
     *
     * @param day   Should be positive and not shall not exceed the amount of day in the given month.
     * @param month Should be between 1 and 12.
     * @param year  Should be a positive and with 4 digits (1000-9999).
     * @return Is the given date is legal.
     */
    private boolean isLegalDate(int day, int month, int year) {

        if (year < 1000 || year > 9999) {
            return false;
        }

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (day < 1 || day > 31) {
                    return false;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (day < 1 || day > 30) {
                    return false;
                }
                break;
            case 2:
                if (isLeapYear(year)) {
                    if (day < 1 || day > 29) {
                        return false;
                    }
                } else {
                    if (day < 1 || day > 28) {
                        return false;
                    }
                }
                break;
            // if the month is not anything between 1 and 12 it's not a legal month anyway.
            default:
                return false;
        }
// can separate the check if day > 1.
        return true;
    }

    public boolean equals(Date other) {
        return (_day == other.getDay() && _month == other.getMonth() && _year == other.getYear());
    }

    public boolean before(Date other) {
        return (dateInDays(this) < dateInDays(other));
    }

    public boolean after(Date other) {
        return other.before(this);
    }

    public int difference(Date other) {
        return Math.abs(dateInDays(this) - other.dateInDays(other));
    }

    public String toString() {
        String dayString = (_day < 10) ? "0" + _day : "" + _day;
        String monthString = (_month < 10) ? "0" + _month : "" + _month;
        String yearString = "" + _year;

        return dayString + "/" + monthString + "/" + yearString;
    }


    /**
     * Adds years to this date, if it's the last day of February the new date will be
     * the last day of February whatever if it's a leap year or not.
     *
     * @param num The amount of years that will be added to the given date.
     *
     * @return The new date after the amount of years added.
     * */
    public Date addYearsToDate(int num) {
        Date newDate = new Date(_day, _month, _year + num);
        if (_month == 2) {
            if ((isLeapYear(_year) && _day == 29) || (!isLeapYear(_year) && _day == 28)) {
                if (isLeapYear(_year + num)) {
                    newDate.setDay(29);
                } else {
                    newDate.setDay(28);
                }
            }
        }
        return newDate;
    }


    // computes the day number since the beginning of the Christian counting of years
    private int dateInDays(Date date) {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();

        if (month < 3) {
            year--;
            month = month + 12;
        }

        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }

    // checks if the year is a leap year
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
// return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0) ? true : false;


    public void setDay(int dayToSet) {
        if (isLegalDate(dayToSet, DEFAULT_MONTH, DEFAULT_YEAR)) {
            _day = dayToSet;
        }
    }
    public void setMonth(int monthToSet) {
        if (isLegalDate(DEFAULT_DAY, monthToSet, DEFAULT_YEAR)) {
            _month = monthToSet;
        }
    }
    public void setYear(int yearToSet) {
        if (isLegalDate(DEFAULT_DAY, DEFAULT_MONTH, yearToSet)) {
            _year = yearToSet;
        }
    }

    public int getDay() {
        return _day;
    }
    public int getMonth() {
        return _month;
    }
    public int getYear() {
        return _year;
    }

}
