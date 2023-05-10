package org.example;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class CreateCalendar {
    private String date;
    private String holiday;

    // public CreateCalendar(String date, String holiday){
    //     this.date = date;
    //     this.holiday = holiday;
    // }
    public static Calendar creatCalendar(){
        Calendar calendar =new Calendar();
        calendar.getProperties().add(new ProdId("-//Holiday Calendar//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        return calendar;
    }

    public static void addEvent(Calendar calendar,String date, String holiday){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Paris"));
        System.setProperty("net.fortuna.ical4j.timezone.date.floating", "true");
        DateTimeFormatter formatter;
        String dateString = date;
        String[] date2 = dateString.split(" ");
        if (date2[0].length() == 2){
            formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        }
        else{
            formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        }
        LocalDate startDate = LocalDate.parse(dateString, formatter);
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIDNIGHT);

        // Create a ZonedDateTime object from the LocalDateTime object with the system default time zone
        ZonedDateTime zonedDateTime = startDateTime.atZone(ZoneId.systemDefault());

        // Create a Date object from the ZonedDateTime object
        Date startDateObj = Date.from(zonedDateTime.toInstant());


        // Create a Date object from the ZonedDateTime object
        System.out.println(date); // Output: 2023-01-14
        Uid uid = new Uid(date+holiday);
        System.out.println("date: " + date);

        // System.out.println("date1: " + date1);
        // net.fortuna.ical4j.model.Date date3 = new net.fortuna.ical4j.model.Date(date1);
        // System.out.println("date2: " + date2);
        VEvent event = new VEvent();
        event.getProperties().add(new DtStart(new net.fortuna.ical4j.model.DateTime(startDateObj)));
        event.getProperties().add(new Summary(holiday));
        event.getProperties().add(uid);

        calendar.getComponents().add(event);
        // File file = new File("event.ics");
        // FileOutputStream fout = new FileOutputStream(file);
        // CalendarOutputter outputter = new CalendarOutputter();
        // outputter.output(calendar, fout);
    }
    public static void createICSFile(Calendar calendar) throws IOException{

        File file = new File("holiday-calendar.ics");
        FileOutputStream fout = new FileOutputStream(file);
        CalendarOutputter outputter = new CalendarOutputter();
        outputter.output(calendar, fout);

    }
    // public static void main(String[] args) throws IOException {
    //     TimeZone.setDefault(TimeZone.getTimeZone("Europe/Paris"));
    //     System.setProperty("net.fortuna.ical4j.timezone.date.floating", "true");
    //     Uid uid = new Uid("my-unique-id");
    //     Calendar calendar = new Calendar();
    //     calendar.getProperties().add(new ProdId("-//Holiday Calendar//iCal4j 1.0//EN"));
    //     calendar.getProperties().add(Version.VERSION_2_0);
    //     calendar.getProperties().add(CalScale.GREGORIAN);
    //     VEvent event = new VEvent(new DateTime(), "Event summary");
    //     event.getProperties().add(uid);

    //     calendar.getComponents().add(event);
    //     File file = new File("event.ics");
    //     FileOutputStream fout = new FileOutputStream(file);
    //     CalendarOutputter outputter = new CalendarOutputter();
    //     outputter.output(calendar, fout);
    // }

}
