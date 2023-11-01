package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.BillboardItemConversor.toBillboardItemDtos;
import static es.udc.paproject.backend.rest.dtos.PeliculaConversor.toPeliculaDto;
import static es.udc.paproject.backend.rest.dtos.SesionConversor.toSesionDto;

import es.udc.paproject.backend.model.exceptions.DayNotValidException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.SesionDateBeforeNowException;
import es.udc.paproject.backend.model.services.CatalogService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.BillboardItemDto;
import es.udc.paproject.backend.rest.dtos.PeliculaDto;
import es.udc.paproject.backend.rest.dtos.SesionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/catalog")

public class CatalogServiceController {
    private final static String DAY_NOT_VALID = "project.exceptions.DayNotValidException";
    private final static String SESION_BEFORE_NOW = "project.exceptions.SesionDateBeforeNowException";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CatalogService catalogService;

    @ExceptionHandler(DayNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleDayNotValidException(DayNotValidException exception, Locale locale){
        String errorMessage = messageSource.getMessage(DAY_NOT_VALID,new Object[]{exception.getMessage()},DAY_NOT_VALID,locale);
        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(SesionDateBeforeNowException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleSesionDateBeforeNowException(SesionDateBeforeNowException exception, Locale locale){
        String errorMessage = messageSource.getMessage(SESION_BEFORE_NOW,new Object[]{exception.getMessage()},SESION_BEFORE_NOW,locale);
        return new ErrorsDto(errorMessage);
    }

    @GetMapping("/billboard")
    public List<BillboardItemDto> getBillboard(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws DayNotValidException {
        return toBillboardItemDtos(catalogService.getBillboard(date));
    }

    @GetMapping("/peliculas/{id}")
    public PeliculaDto getPelicula(@PathVariable Long id) throws InstanceNotFoundException {
        return toPeliculaDto(catalogService.getPelicula(id));
    }

    @GetMapping("/sesiones/{id}")
    public SesionDto getSesion(@PathVariable Long id) throws InstanceNotFoundException, SesionDateBeforeNowException {
        return toSesionDto(catalogService.getSesion(id));
    }

}
