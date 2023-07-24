package ru.sharanov.aviasearch.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sharanov.aviasearch.repositories.AirportRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class Data {
    private final AirportRepository airportRepository;

    public String getData(int numberOfComponent) {
        String regex = "";
        String wrongAnswer = "";
        String result;
        boolean correctIata = true;
        switch (numberOfComponent) {
            case 1 -> regex = "[a-zA-Z0-9]{4}";
            case 2 -> regex = "\\d{2}/\\d{2}/\\d{4}";
            case 3 -> regex = "(([0-1][0-9])|(2[0-3])):[0-5][0-9]";
            case 4 -> regex = "\\d{2}\\.\\d{2}";
            case 5 -> regex = "[a-zA-ZА-я]{3}";
            case 6 -> regex = "[a-zA-ZА-я]{3}";
            case 7 -> regex = "[\\d]+\\.[\\d]{2}";
        }
        switch (numberOfComponent) {
            case 1 -> wrongAnswer = "Введён некорректный номер рейса\nВведите номер в формате ХХХХ, напрмиер 5B7N: ";
            case 2 -> wrongAnswer = "Введена некорректная дата вылета рейса" +
                    "\nВведите дату в формате ДД/ММ/ГГГГ, например 01/01/1970: ";
            case 3 -> wrongAnswer = "Введено некорректное время рейса\nВведите время в формате ЧЧ:ММ, например 06:00: ";
            case 4 -> wrongAnswer = "Введено некорректное время полёта\nВведите время в формате ЧЧ.ММ, например 07.30:";
            case 5 -> wrongAnswer = "Введён некорректный код аэропорта вылета" +
                    "\nВведите существующий код в формате ХХХ, например KUF: ";
            case 6 -> wrongAnswer = "Введён некорректный код аэропорта прилёта " +
                    "\nВведите существующий код в формате ХХХ, например KUF: ";
            case 7 -> wrongAnswer = "Введена некорректная стоимость билета" +
                    "\nВведите положительно число.Например 5000.00: ";
        }
        do {
            result = new Scanner(System.in).nextLine();
            if (result.equals("0")) {
                return "0";
            }
            if (numberOfComponent == 2) {
                result = correctingDate(result);
            }
            if (numberOfComponent == 3 && result.matches("^\\d:\\d{2}")) {
                result = "0" + result;
            }
            if (numberOfComponent == 4 && result.matches("^\\d\\.\\d{2}")) {
                result = "0" + result;
            }
            if (numberOfComponent == 5 || numberOfComponent == 6) {
                correctIata = checkAirport(result);
                if (!correctIata) {
                    System.out.println("введён некорректный код аэропорта. Введите существующий код: ");
                }
            }
            if (numberOfComponent == 7) {
                result = result.replaceAll(",", ".");
            }
            if (!result.matches(regex)) {
                System.out.println(wrongAnswer);
            }
        }
        while (!result.matches(regex) || !correctIata);
        return result;
    }

    private String correctingDate(String result) {
        result = result.replaceAll("\\.", "/").replaceAll("-", "/");
        try {
            String[] components = result.split("/");
            String dayOfMonth = components[0];
            String month = components[1];
            String year = components[2];
            if (dayOfMonth.matches("^\\d$")) {
                dayOfMonth = 0 + dayOfMonth;
            }
            if (month.matches("^\\d$")) {
                month = 0 + month;
            }
            result = dayOfMonth + "/" + month + "/" + year;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate departureTimeDate = LocalDate.parse(result, formatter);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            result = "XX.XX.XXXX";
            System.out.println("Некорректно введена дата");
        }
        return result;
    }

    private boolean checkAirport(String codeIata) {
        return airportRepository.findAll().stream().anyMatch(a -> a.getCodeIata().equals(codeIata));
    }
}