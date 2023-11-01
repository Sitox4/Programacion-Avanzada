package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Pelicula;

public class PeliculaConversor {
    private PeliculaConversor() {}

    public final static PeliculaDto toPeliculaDto(Pelicula pelicula) {
        return new PeliculaDto(pelicula.getId(), pelicula.getTitulo(), pelicula.getResumen(), pelicula.getDuracion());
    }
}
