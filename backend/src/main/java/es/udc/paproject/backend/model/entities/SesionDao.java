package es.udc.paproject.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface SesionDao extends PagingAndSortingRepository<Sesion,Long> {

    List<Sesion> findAllByFechaBetweenOrderByPeliculaTituloAscFechaAsc(LocalDateTime date1, LocalDateTime date2);
}
