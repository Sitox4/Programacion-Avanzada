package es.udc.paproject.backend.model.exceptions;

import es.udc.paproject.backend.model.entities.Sesion;

public class SesionDateBeforeNowException extends  Exception{
    private final Long sesionId;

    public SesionDateBeforeNowException(Long sesionId) {
        super("Sesion with id: " + sesionId + " have already finished");
        this.sesionId = sesionId;
    }
}
