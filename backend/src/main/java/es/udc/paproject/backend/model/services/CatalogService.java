package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.BillboardItem;
import es.udc.paproject.backend.model.entities.Pelicula;
import es.udc.paproject.backend.model.entities.Sesion;
import es.udc.paproject.backend.model.exceptions.DayNotValidException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.SesionDateBeforeNowException;

import java.time.LocalDate;
import java.util.List;

public interface CatalogService {
    List<BillboardItem> getBillboard(LocalDate date) throws DayNotValidException;
    Pelicula getPelicula(Long peliculaId) throws InstanceNotFoundException;
    Sesion getSesion(Long sesionId) throws InstanceNotFoundException, SesionDateBeforeNowException;
}
