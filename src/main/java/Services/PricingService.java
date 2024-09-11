package Services;

import Entities.Reservation;
import Entities.Room;

import java.time.LocalDate;
import java.time.Month;

public class PricingService {

    public double seasonalPrice(Room room, Reservation reservation) {
        LocalDate startDate = reservation.getDate().getStartDate();
        double basePrice = room.getPrice();

        if (isInSummer(startDate)) {
            return basePrice * 1.2;
        } else if (isInNewYear(startDate)) {
            return basePrice * 1.5;
        }
        return basePrice;
    }

    public Double calculatePrice(Room room, Reservation reservation) {
        double seasonalPrice = seasonalPrice(room, reservation);
        double totalPrice = seasonalPrice * reservation.getNumberOfDays();

        if (reservation.getNumberOfDays() >= 10 && reservation.getNumberOfDays() <= 20) {
            return totalPrice - ((totalPrice * 10) / 100);
        } else if (reservation.getNumberOfDays() > 20) {
            return totalPrice - ((totalPrice * 20) / 100);
        }

        return totalPrice;
    }

    public Boolean isInSummer(LocalDate date) {
        Month month = date.getMonth();
        return month == Month.JUNE || month == Month.JULY || month == Month.AUGUST;
    }

    public Boolean isInNewYear(LocalDate date) {
        Month month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();

        if (month == Month.DECEMBER && dayOfMonth >= 15) {
            return true;
        } else if (month == Month.JANUARY && dayOfMonth <= 15) {
            return true;
        }

        return false;
    }
}
