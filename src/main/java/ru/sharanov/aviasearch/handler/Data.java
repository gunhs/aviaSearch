package ru.sharanov.aviasearch.handler;

import org.springframework.stereotype.Component;
import ru.sharanov.aviasearch.repositoris.AirportRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@Component
public class Data {
    private final AirportRepository airportRepository;

    public Data(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    String getData(int numberOfComponent) {
        String regex = "";
        String wrongAnswer = "";
        String result;
        String codeIATA;
        boolean correctIATA;
        switch (numberOfComponent) {
            case 1 -> regex = "[a-zA-Z0-9]{4}";
            case 2 -> regex = "\\d{2}\\/\\d{2}\\/\\d{4}";
            case 3 -> regex = "(([0-1][0-9])|(2[0-3])):[0-5][0-9]";
            case 4 -> regex = "\\d{2}\\.\\d{2}";
            case 5 -> regex = "[a-zA-Z]{3}";
            case 6 -> regex = "[\\d.]+";
        }
        switch (numberOfComponent) {
            case 1 -> wrongAnswer = "Введён некорректный номер рейса\nВведите номер в формате ХХХХ, напрмиер 5B7N: ";
            case 2 -> wrongAnswer = "Введена некорректная дата вылета рейса" +
                    "\nВведите дату в формате ДД/ММ/ГГГГ, например 01/01/1970: ";
            case 3 -> wrongAnswer = "Введено некорректное время рейса\nВведите время в формате ЧЧ:ММ, например 06:00: ";
            case 4 -> wrongAnswer = "Введено некорректное время полёта\nВведите время в формате ЧЧ.ММ, например 07.30: ";
            case 5 -> wrongAnswer = "Введён некорректный код аэропорта" +
                    "\nВведите существующий код в формате ХХХ, например KUF: ";
            case 6 -> wrongAnswer = "Введена некорректная стоимость билета" +
                    "\nВведите положительно число.Например 5000.00: ";
        }

        do {
            result = new Scanner(System.in).nextLine();
            if (numberOfComponent == 2) {
                result = correctingDate(result);
            }
            if (!result.matches(regex)) {
                System.out.println(wrongAnswer);
            }
            codeIATA = numberOfComponent == 5 ? result : "";
            correctIATA = checkAirport(codeIATA);
            if (numberOfComponent == 5 && !correctIATA) {
                System.out.println("введён некорректный код аэропорта. Введите существующий код: ");
            }
            if (numberOfComponent == 3 && result.matches("^\\d:\\d{2}")) {
                result = "0" + result;
            }
            if (numberOfComponent == 4 && result.matches("^\\d.\\d{2}")) {
                result = "0" + result;
            }
        }
        while (!result.matches(regex) || !correctIATA);
        return result;
    }

    private String correctingDate(String result) {
        result = result.replaceAll("\\.", "/").replaceAll("-", "/");
        try {
            String[] components = result.split("/");
            if (components[0].matches("^\\d$")) {
                components[0] = 0 + components[0];
            }
            if (components[1].matches("^\\d$")) {
                components[1] = 0 + components[1];
            }
            result = components[0] + "/" + components[1] + "/" + components[2];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate departureTimeDate = LocalDate.parse(result, formatter);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            result = "XX.XX.XXXX";
            System.out.println("Некорректно введена дата");
        }
        return result;
    }


    private boolean checkAirport(String codeIATA) {
        return airportRepository.findAll().stream().anyMatch(a -> a.getCodeIATA().equals(codeIATA))
                || codeIATA.isEmpty();
    }
}