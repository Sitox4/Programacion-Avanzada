package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CompraSummaryDto {
    private Long compraId;
    private long date;
    private String tituloPelicula;
    private int entradas;
    private BigDecimal precioTotal;
    private long fechaSesion;
    private boolean entregadas;

    public CompraSummaryDto() {
    }

    public CompraSummaryDto(Long compraId, long date, String tituloPelicula, int entradas, BigDecimal precioTotal, long fechaSesion, boolean entregadas) {
        this.compraId = compraId;
        this.date = date;
        this.tituloPelicula = tituloPelicula;
        this.entradas = entradas;
        this.precioTotal = precioTotal;
        this.fechaSesion = fechaSesion;
        this.entregadas = entregadas;
    }

    @NotNull
    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }

    @NotNull
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @NotNull
    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    @NotNull
    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
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
    public boolean isEntregadas() {
        return entregadas;
    }

    public void setEntregadas(boolean entregadas) {
        this.entregadas = entregadas;
    }
}