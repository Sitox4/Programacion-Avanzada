package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Sesion {

    private Long id;
    private LocalDateTime fecha;
    private BigDecimal precio;
    private Pelicula pelicula;
    private Sala sala;
    private int ticketsLibres;
    private long version;

    public Sesion() {
    }

    public Sesion(LocalDateTime fecha, BigDecimal precio, Pelicula pelicula, Sala sala, int ticketsLibres) {
        this.fecha = fecha;
        this.precio = precio;
        this.pelicula = pelicula;
        this.sala = sala;
        this.ticketsLibres = ticketsLibres;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="peliculaId")
    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="salaId")
    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public int getTicketsLibres() {
        return ticketsLibres;
    }

    public void setTicketsLibres(int ticketsLibres) {
        this.ticketsLibres = ticketsLibres;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}