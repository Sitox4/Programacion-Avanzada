package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GiveTicketParamsDto {
    private String creditCard;

    public GiveTicketParamsDto() {
    }

    public GiveTicketParamsDto(String creditCard) {
        this.creditCard = creditCard;
    }

    @NotNull
    @Size(min=16,max=16)
    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
