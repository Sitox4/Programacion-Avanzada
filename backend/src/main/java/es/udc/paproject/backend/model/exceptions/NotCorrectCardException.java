package es.udc.paproject.backend.model.exceptions;

public class NotCorrectCardException extends Exception{
    public NotCorrectCardException(Long compraId, Long userId){
        super("The credit card from order:" + compraId + " and user:" + userId + "is not the correct one");
    }
}
