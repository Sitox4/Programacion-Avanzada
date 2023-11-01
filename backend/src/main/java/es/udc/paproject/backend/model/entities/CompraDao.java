package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Slice;


public interface CompraDao extends PagingAndSortingRepository<Compra,Long>{
    Slice<Compra> findByUserIdOrderByFechaDesc(Long userId, Pageable pageable);
}
