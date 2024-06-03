package org.example.app;

import org.example.com.DisplayLocales;
import org.example.com.Info;
import org.example.com.SetLocale;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ResourceBundle bundle = ResourceBundle.getBundle("res.Messages", Locale.getDefault());

        while (true) {
            System.out.print(bundle.getString("prompt"));
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("display")) {
                DisplayLocales.execute(bundle);
            } else if (command.startsWith("set ")) {
                String languageTag = command.substring(4);
                SetLocale.execute(languageTag, bundle);
                bundle = ResourceBundle.getBundle("res.Messages", Locale.getDefault());
            } else if (command.startsWith("info")) {
                Locale locale;
                if (command.length() > 5) {
                    String languageTag = command.substring(5);
                    locale = Locale.forLanguageTag(languageTag);
                } else {
                    locale = Locale.getDefault();
                }
                Info.execute(locale, bundle);
            } else {
                System.out.println(bundle.getString("invalid"));
            }
        }
    }
}
