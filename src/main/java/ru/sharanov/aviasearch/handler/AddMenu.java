package ru.sharanov.aviasearch.handler;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddMenu {
    private final Data data;

    public AddMenu(Data data) {
        this.data = data;
    }

    public List<String> addFlight() {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            String value = getValue(i);
            if (value.equals("0")) {
                return new ArrayList<>();
            } else {
                result.add(value);
            }
        }
        return result;
    }

    private String getValue(int i) {
        String message = "";
        switch (i) {
            case 1 -> message = "Введите данные рейса:\n ХХХХ - номер рейса: ";
            case 2 -> message = "ДД/ММ/ГГГГ - дата рейса: ";
            case 3 -> message = "ЧЧ:ММ - время вылета: ";
            case 4 -> message = "ХХ.ХХ - длительность полёта: ";
            case 5 -> message = "ХХХ - аэропорт вылета: ";
            case 6 -> message = "ХХХ - аэропорт прилёта: ";
            case 7 -> message = ".ХХ - стоимость билета: ";
        }
        System.out.println(message);
        return data.getData(i).toUpperCase();
    }
}
