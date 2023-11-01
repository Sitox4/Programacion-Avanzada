package es.udc.paproject.backend.rest.controllers;

import es.udc.paproject.backend.model.entities.Compra;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.ShoppingService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.CompraParamsDto;
import es.udc.paproject.backend.rest.dtos.CompraSummaryDto;
import es.udc.paproject.backend.rest.dtos.GiveTicketParamsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static es.udc.paproject.backend.rest.dtos.CompraConversor.toCompraDto;
import static es.udc.paproject.backend.rest.dtos.CompraConversor.toCompraSummaryDtos;

import java.util.Locale;

@RestController
@RequestMapping("/shopping")
public class ShoppingServiceController {
    private final static String SESION_BEFORE_NOW = "project.exceptions.SesionDateBeforeNowException";
    private final static String NOT_ENOUGH_TICKETS = "project.exceptions.NotEnoughtTicketsException";
    private final static String ALREADY_TAKEN = "project.exceptions.TicketsAlreadyTakenException";
    private final static String INCORRECT_CARD = "project.exceptions.NotCorrectCardException";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ShoppingService shoppingService;

    @ExceptionHandler(NotEnoughtTicketsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleNotEnoughtTicketsException(NotEnoughtTicketsException exception, Locale locale){
        String errorMessage = messageSource.getMessage(NOT_ENOUGH_TICKETS,new Object[]{exception.getTicketsLibres()},NOT_ENOUGH_TICKETS,locale);
        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(SesionDateBeforeNowException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleSesionDateBeforeNowException(SesionDateBeforeNowException exception, Locale locale){
        String errorMessage = messageSource.getMessage(SESION_BEFORE_NOW,new Object[]{exception.getMessage()},SESION_BEFORE_NOW,locale);
        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(TicketsAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleTicketsAlreadyTakenException(TicketsAlreadyTakenException exception, Locale locale){
        String errorMessage = messageSource.getMessage(ALREADY_TAKEN,new Object[]{exception.getMessage()},ALREADY_TAKEN,locale);
        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(NotCorrectCardException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleNotCorrectCardException(NotCorrectCardException exception, Locale locale){
        String errorMessage = messageSource.getMessage(INCORRECT_CARD,new Object[]{exception.getMessage()},INCORRECT_CARD,locale);
        return new ErrorsDto(errorMessage);
    }

    @PostMapping("/buy")
    public Long buyTicket(@RequestAttribute Long userId,@Validated @RequestBody CompraParamsDto paramsDto)
            throws InstanceNotFoundException, SesionDateBeforeNowException, NotEnoughtTicketsException{
        return toCompraDto(shoppingService.buyTicket(userId,paramsDto.getCantidad(), paramsDto.getTarjeta(), paramsDto.getSesionId())).getId();

    }

    @GetMapping("/historial")
    public BlockDto<CompraSummaryDto> getCompras(@RequestAttribute Long userId, @RequestParam(defaultValue = "0") int page){
        Block<Compra> compraBlock = shoppingService.getCompras(userId,page,2);

        return new BlockDto<>(toCompraSummaryDtos(compraBlock.getItems()),compraBlock.getExistMoreItems());
    }

    @PostMapping("/give/{compraId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void giveTicket(@PathVariable Long compraId,@Validated @RequestBody GiveTicketParamsDto paramsDto)
            throws InstanceNotFoundException, SesionDateBeforeNowException, TicketsAlreadyTakenException, NotCorrectCardException {
        shoppingService.giveTicket(compraId, paramsDto.getCreditCard());

    }


}
