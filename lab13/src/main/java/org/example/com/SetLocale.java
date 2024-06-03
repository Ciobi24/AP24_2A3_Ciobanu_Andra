package org.example.com;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {

    public static void execute(String languageTag, ResourceBundle bundle) {
        Locale locale = Locale.forLanguageTag(languageTag);
        Locale.setDefault(locale);
        System.out.println(String.format(bundle.getString("locale.set"), locale));
    }
}
