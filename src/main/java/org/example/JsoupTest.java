package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JsoupTest {
    private static List<Holiday> getHolidays(Element table) {
        List<Holiday> holidays = new ArrayList<>();
        Elements rows = table.select("tr");
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            String date = cols.get(0).text();
            String day = cols.get(1).text();
            String holiday = cols.get(2).text();
            String state = cols.get(3).text();
            String id = date + holiday;
            holidays.add(new Holiday(date, day, holiday, state, id));
        }
        return holidays;
    }

    public static List<Holiday> loadedHolidays() {
        try {
            Document doc = Jsoup.connect("https://www.bankbazaar.com/indian-holiday-calendar.html").get();
            Elements tables = doc.select("table");
            Element table = tables.get(1);
            Elements rows = table.select("tr");
            List<Holiday> loadedholidays = getHolidays(table);
            return loadedholidays;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}

