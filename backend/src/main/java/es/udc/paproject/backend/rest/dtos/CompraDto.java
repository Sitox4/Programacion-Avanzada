package es.udc.paproject.backend.rest.dtos;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CompraDto {
    private Long id;
    private long fecha;
    private String tituloPelicula;
    private int cantidad;
    private BigDecimal precioTotal;
    private long fechaSesion;
    private boolean entregada;

    public CompraDto(){}

    public CompraDto(Long id, long fecha, String tituloPelicula, int cantidad, BigDecimal precioTotal, long fechaSesion, boolean entregada) {
        this.id = id;
        this.fecha = fecha;
        this.tituloPelicula = tituloPelicula;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.fechaSesion = fechaSesion;
        this.entregada = entregada;
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    @NotNull
    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    @NotNull
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @NotNull
    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    @NotNull
    public long getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(long fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    @NotNull
    public boolean isEntregada() {
        return entregada;
    }

    public void setEntregada(boolean entregada) {
        this.entregada = entregada;
    }
}