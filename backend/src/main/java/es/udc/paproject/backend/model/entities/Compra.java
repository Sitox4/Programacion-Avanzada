package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Compra {

    private Long id;
    private int cantidad;
    private String tarjeta;
    private User user;
    private Sesion sesion;
    private LocalDateTime fecha;
    private boolean entregada;

    public Compra() {
    }

    public Compra(int cantidad, String tarjeta, User user, Sesion sesion, LocalDateTime fecha, boolean entregada) {
        this.cantidad = cantidad;
        this.tarjeta = tarjeta;
        this.user = user;
        this.sesion = sesion;
        this.fecha = fecha;
        this.entregada = entregada;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="sesionId")
    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public boolean isEntregada() {
        return entregada;
    }

    public void setEntregada(boolean entregada) {
        this.entregada = entregada;
    }
}
