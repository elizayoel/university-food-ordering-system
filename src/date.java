import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import java.io.Serializable;

public class date implements Serializable {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    public date(LocalDateTime localDateTime){
        this.day = localDateTime.getDayOfMonth();
        this.month = localDateTime.getMonthValue();
        this.year = localDateTime.getYear();
        this.hour = localDateTime.getHour();
        this.minute = localDateTime.getMinute();
    }
    public String getFormattedDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
        return dateFormatter.format(LocalDateTime.of(year, month, day, 0, 0));
    }

    public String getFormattedTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
        return timeFormatter.format(LocalDateTime.of(year, month, day, hour, minute));
    }


    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public int getMinute() {
        return minute;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

}