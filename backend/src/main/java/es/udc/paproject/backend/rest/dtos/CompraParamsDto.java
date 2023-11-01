package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompraParamsDto {

    private Long sesionId;
    private int cantidad;
    private String tarjeta;

    public CompraParamsDto(){}

    public CompraParamsDto(Long sesionId, int cantidad, String tarjeta) {
        this.sesionId = sesionId;
        this.cantidad = cantidad;
        this.tarjeta = tarjeta;
    }

    @NotNull
    public Long getSesionId() {
        return sesionId;
    }

    public void setSesionId(Long sesionId) {
        this.sesionId = sesionId;
    }

    @NotNull
    @Max(10)
    @Min(1)
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @NotNull
    @Size(min=16,max=16)
    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

}
