package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Compra;
import es.udc.paproject.backend.model.entities.Sesion;
import es.udc.paproject.backend.model.exceptions.*;

import java.util.List;

public interface ShoppingService {
    Compra buyTicket(Long userId, int tickets, String card, Long sesionId)
            throws InstanceNotFoundException, SesionDateBeforeNowException, NotEnoughtTicketsException;

    Block<Compra> getCompras(Long userId,int page, int size);

    void giveTicket(Long compraId, String card)
            throws InstanceNotFoundException, SesionDateBeforeNowException, TicketsAlreadyTakenException, NotCorrectCardException;

}
