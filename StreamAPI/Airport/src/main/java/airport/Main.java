package airport;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static long findCountAircraftWithModelAirbus(Airport airport, String model) {
        int amount;
        amount = airport.getAllAircrafts()
                .stream()
                .filter(plane -> plane.getModel().contains(model))
                .toArray().length;

        //TODO Метод должен вернуть количество самолетов указанной модели.
        // подходят те самолеты, у которых name начинается со строки model
        return amount;
    }

    public static Map<String, Integer> findMapCountParkedAircraftByTerminalName(Airport airport) {
        Map<String, Integer> map;
        map = airport.getTerminals().stream()
            .filter(terminal -> terminal != null && !terminal.getParkedAircrafts().isEmpty())
            .collect(Collectors.toMap(Terminal::getName, terminal -> terminal.getParkedAircrafts().toArray().length, (a, b) -> b));
        
        //TODO Метод должен вернуть словарь с количеством припаркованных самолетов в каждом терминале.
        return map;
    }

    public static List<Flight> findFlightsLeavingInTheNextHours(Airport airport, int hours) {

        Instant date  = Instant.now();
        List<Flight> flights = new ArrayList<>();
        airport.getTerminals().stream()
                .filter(terminal -> !terminal.getFlights().isEmpty())
                .forEach(terminal -> terminal.getFlights().stream()
                        .filter(flight -> flight.getDate().isAfter(date) && flight.getDate().isBefore(date.plus(hours, ChronoUnit.HOURS)))
                        .filter(flight -> flight.getType()!= Flight.Type.ARRIVAL)
                        .forEach(flights::add));


        //TODO Метод должен вернуть список отправляющихся рейсов в ближайшее количество часов.
        return flights;
    }

    public static Optional<Flight> findFirstFlightArriveToTerminal(Airport airport, String terminalName) {
        Instant date  = Instant.now();
        Optional<Flight> flight = Optional.empty();
        Optional<Terminal> terminal = airport.getTerminals().stream()
                .filter(term -> term.getName().equals(terminalName)).findFirst();
        if (terminal.isPresent()) {
            flight = terminal.get().getFlights().stream()
                    .filter(tmpflight -> tmpflight.getDate().isAfter(date) && tmpflight.getType().equals(Flight.Type.ARRIVAL))
                    .min(Comparator.comparing(Flight::getDate));
        }
        //TODO Найти ближайший прилет в указанный терминал.
        return flight;
    }
}