package com.example.letsmeal;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Schedule implements Serializable {

    long id;
    long organizerId;
    String title;
    Calendar calendar;

    /**
     * These two coordinates should be written in GeoPoint for Firebase
     */
    double latitude;
    double longitude;
    String place;

    /**
     * We store participants as a list of Person.ID.
     */
    String participantsAsString;
    ArrayList<Long> participants;
    HashMap<Long, Boolean> participates;

    String description;

    /**
     * TODO:  Media fields.
     */

    Schedule(long organizerId) {
        this.organizerId = organizerId;
        this.calendar = Calendar.getInstance();
        this.description = "";
    }

    @Override
    public String toString() {
        String str;
        str = "Schedule ID : " + id;
        str += ", organizerID = " + organizerId;
        str += ", title = " + title;
        str += ", date = " + Schedule.getDateString(this.calendar);
        str += ", time = " + Schedule.getTimeString(this.calendar);
        str += ", description: \n";
        str += description;
        return str;
    }

    public void setTitle(String title) {this.title = title;}
    public String getTitle() {return this.title;}

    public void setCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        this.calendar.set(this.calendar.YEAR, year);
        this.calendar.set(this.calendar.MONTH, month);
        this.calendar.set(this.calendar.DAY_OF_MONTH, dayOfMonth);
        this.calendar.set(this.calendar.HOUR_OF_DAY, hourOfDay);
        this.calendar.set(this.calendar.MINUTE, minute);
    }

    public Calendar getCalendar() {return this.calendar;}
    public long getTimeInMills() {return this.calendar.getTimeInMillis();}


    public void setLatitude(double latitude) {this.latitude = latitude;}
    public void setLongitude(double longitude) {this.longitude = longitude;}

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setDescription(String description) {this.description = description;}
    public String getDescription() {return this.description;}

    /**
     * Pretty-print date
     * @param dateCalendar A Calendar instance which contains YEAR, MONTH, DAY_OF_MONTH
     * @return A formatted String of dateCalendar
     */
    public static String getDateString(Calendar dateCalendar) {
        return String.format(Locale.getDefault(),
                "%04d/%02d/%02d (%s)",
                dateCalendar.get(dateCalendar.YEAR),
                dateCalendar.get(dateCalendar.MONTH)+1,
                dateCalendar.get(dateCalendar.DAY_OF_MONTH),
                getKoreanDayOfWeek(dateCalendar));

    }
    private static String getKoreanDayOfWeek(Calendar cal) {
        String [] yoil_vector = {"ERROR", "일", "월", "화", "수", "목", "금", "토"};
        return yoil_vector[cal.DAY_OF_WEEK];
    }

    /**
     * Pretty-print date
     * @param timeCalendar A Calendar instance which contains HOUR_OF_DAY, MINUTE
     * @return A formatted String of timeCalendar
     */
    public static String getTimeString(Calendar timeCalendar) {
        return String.format(Locale.getDefault(),
                "%s %02d:%02d",
                getKoreanAmPm(timeCalendar),
                timeCalendar.get(timeCalendar.HOUR_OF_DAY),
                timeCalendar.get(timeCalendar.MINUTE));
    }
    private static String getKoreanAmPm(Calendar cal) {
        if (cal.get(cal.HOUR_OF_DAY)< 12)
            return "오전";
        return "오후";
    }

    /**
     * TODO: IMPLEMENT!!!
     * I am not even sure what is the right signature for this function.
     */
    public void setParticipants(String participantsString) {
        return;
    }

    public void setParticipantsAsString(String participantsAsString) {
        this.participantsAsString = participantsAsString;
    }

    public String getParticipantsAsString() {
        return participantsAsString;
    }
}
