package es.udc.paproject.backend.model.exceptions;

public class TicketsAlreadyTakenException extends Exception{
    public TicketsAlreadyTakenException(Long compraId, Long userId) {
        super("The tickets from order:" + compraId + " and user:"+userId+" have been already taken");
    }
}
