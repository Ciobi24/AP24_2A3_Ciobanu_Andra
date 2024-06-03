package org.example.com;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales {

    public static void execute(ResourceBundle bundle) {
        System.out.println(bundle.getString("locales"));
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            System.out.println(locale.toString() + " - " + locale.getDisplayName());
        }
    }
}
