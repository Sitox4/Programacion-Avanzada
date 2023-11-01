package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Sesion;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class SesionConversor {
    private SesionConversor() {}

    public final static SesionDto toSesionDto(Sesion sesion) {
        return new SesionDto(sesion.getPelicula().getId(),sesion.getPelicula().getTitulo(),sesion.getPelicula().getDuracion(),
                sesion.getPrecio(),toMillis(sesion.getFecha()),sesion.getSala().getNombre(),sesion.getTicketsLibres());
    }

    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }
}