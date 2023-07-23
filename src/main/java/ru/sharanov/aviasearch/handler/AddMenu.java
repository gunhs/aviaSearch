package ru.sharanov.aviasearch.handler;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AddMenu {

    private final Data data;

    public AddMenu(Data data) {
        this.data = data;
    }

    public ArrayList<String> addFlight() {
        ArrayList<String> result = new ArrayList<>();
        System.out.println("Введите данные рейса:\n ХХХХ - номер рейса: ");
        String flightNumber = data.getData(1);
        if (flightNumber.equals("0")){
            return new ArrayList<>();
        }
        System.out.println("ДД/ММ/ГГГГ - дата рейса: ");
        String departureDate = data.getData(2);
        if (departureDate.equals("0")){
            return new ArrayList<>();
        }
        System.out.println("ЧЧ:ММ - время вылета: ");
        String departureTime = data.getData(3);
        if (departureTime.equals("0")){
            return new ArrayList<>();
        }
        System.out.println("ХХ.ХХ - длительность полёта: ");
        String durationFlight = data.getData(4);
        if (durationFlight.equals("0")){
            return new ArrayList<>();
        }
        System.out.println("ХХХ - аэропорт вылета: ");
        String departureAirport = data.getData(5);
        if (departureAirport.equals("0")){
            return new ArrayList<>();
        }
        System.out.println("ХХХ - аэропорт назначения: ");
        String arriveAirport = data.getData(5);
        if (arriveAirport.equals("0")){
            return new ArrayList<>();
        }
        System.out.println(".ХХ - стоимость билета: ");
        String price = data.getData(6);
        if (price.equals("0")){
            return new ArrayList<>();
        }

        result.add(flightNumber.toUpperCase());
        result.add(departureDate);
        result.add(departureTime);
        result.add(durationFlight);
        result.add(departureAirport.toUpperCase());
        result.add(arriveAirport.toUpperCase());
        result.add(price);
        return result;
    }
}
