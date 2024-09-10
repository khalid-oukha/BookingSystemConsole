package commons;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateInterval {
    private LocalDate startDate;
    private LocalDate endDate;

    public DateInterval(LocalDate startDate, LocalDate endDate) {
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date cannot be before today");
        } else if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        } else if (endDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("End date cannot be before today");
        } else {
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date cannot be before today");
        } else if (startDate.isAfter(this.endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        } else {
            this.startDate = startDate;
        }
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate.isBefore(this.startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        } else {
            this.endDate = endDate;
        }
    }

    public long getNumberOfDays(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}