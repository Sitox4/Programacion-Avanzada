package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class SesionDto {
    private Long peliculaId;
    private String tituloPelicula;
    private int duracion;
    private BigDecimal precio;
    private long date;
    private String nombreSala;
    private int ticketsLibres;

    public SesionDto() {
    }

    public SesionDto(Long peliculaId,String tituloPelicula, int duracion, BigDecimal precio, long date, String nombreSala, int ticketsLibres) {
        this.peliculaId = peliculaId;
        this.tituloPelicula = tituloPelicula;
        this.duracion = duracion;
        this.precio = precio;
        this.date = date;
        this.nombreSala = nombreSala;
        this.ticketsLibres = ticketsLibres;
    }

    @NotNull
    public Long getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(Long peliculaId) {
        this.peliculaId = peliculaId;
    }

    @NotNull
    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    @NotNull
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @NotNull
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @NotNull
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @NotNull
    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    @NotNull
    public int getTicketsLibres() {
        return ticketsLibres;
    }

    public void setTicketsLibres(int ticketsLibres) {
        this.ticketsLibres = ticketsLibres;
    }
}