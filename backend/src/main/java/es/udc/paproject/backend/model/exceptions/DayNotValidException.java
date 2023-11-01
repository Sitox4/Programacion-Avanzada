package es.udc.paproject.backend.model.exceptions;

import java.time.LocalDate;

public class DayNotValidException extends Exception{
    private LocalDate date;
    public DayNotValidException(LocalDate date){
        super("The date: " + date + "is not valid");
    }

    public LocalDate getDate() {
        return date;
    }
}
