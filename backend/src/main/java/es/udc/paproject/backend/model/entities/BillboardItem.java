package es.udc.paproject.backend.model.entities;

import java.util.List;

public class BillboardItem {
    private Pelicula pelicula;
    private List<Sesion> listaSesiones;

    public BillboardItem() {
    }

    public BillboardItem(Pelicula pelicula, List<Sesion> listaSesiones) {
        this.pelicula = pelicula;
        this.listaSesiones = listaSesiones;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public List<Sesion> getListaSesiones() {
        return listaSesiones;
    }

    public void setListaSesiones(List<Sesion> listaSesiones) {
        this.listaSesiones = listaSesiones;
    }
}
