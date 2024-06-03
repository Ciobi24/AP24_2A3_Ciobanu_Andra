package org.example.com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    public static void execute(Locale locale, ResourceBundle bundle) {
        System.out.println(bundle.getString("info").replace("{0}", locale.toString()));

        // Country
        System.out.println("Country: " + locale.getDisplayCountry() + " (" + locale.getDisplayCountry(locale) + ")");

        // Language
        System.out.println("Language: " + locale.getDisplayLanguage() + " (" + locale.getDisplayLanguage(locale) + ")");

        // Currency
        try {
            Currency currency = Currency.getInstance(locale);
            System.out.println("Currency: " + currency.getCurrencyCode() + " (" + currency.getDisplayName(locale) + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("Currency: Not available for this locale");
        }

        // Week Days
        DateFormatSymbols symbols = new DateFormatSymbols(locale);
        String[] weekDays = symbols.getWeekdays();
        System.out.println("Week Days: " + String.join(", ", weekDays));

        // Months
        String[] months = symbols.getMonths();
        System.out.println("Months: " + String.join(", ", months));

        // Today
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
        System.out.println("Today: " + dateFormat.format(new Date()));
    }
}
