package es.udc.paproject.backend.model.exceptions;

public class NotEnoughtTicketsException extends  Exception{
    private final Long sesionId;

    public int getTicketsLibres() {
        return ticketsLibres;
    }

    private final int ticketsLibres;

    public NotEnoughtTicketsException(Long sesionId, int ticketsLibres) {
        super("Sesion with id: " + sesionId + " have "+ticketsLibres+" tickets available");
        this.sesionId = sesionId;
        this.ticketsLibres = ticketsLibres;
    }
}
