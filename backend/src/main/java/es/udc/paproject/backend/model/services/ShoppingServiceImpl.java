package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ShoppingServiceImpl implements ShoppingService{

    @Autowired
    private PermissionChecker permissionChecker;
    @Autowired
    private CompraDao compraDao;

    @Override
    public Compra buyTicket(Long userId, int tickets, String card, Long sesionId)
            throws InstanceNotFoundException, SesionDateBeforeNowException, NotEnoughtTicketsException {

        User user = permissionChecker.checkUser(userId);
        Sesion sesion = permissionChecker.checkSesion(sesionId);

        if(LocalDateTime.now().isAfter(sesion.getFecha())){
            throw new SesionDateBeforeNowException(sesionId);
        }
        if(tickets > sesion.getTicketsLibres()){
            throw new NotEnoughtTicketsException(sesionId,sesion.getTicketsLibres());
        }

        Compra compra = new Compra(tickets,card,user,sesion, LocalDateTime.now(),false);
        compraDao.save(compra);
        sesion.setTicketsLibres(sesion.getTicketsLibres()-tickets);

        return compra;
    }

    @Override
    @Transactional(readOnly=true)
    public Block<Compra> getCompras(Long userId,int page, int size){
        Slice<Compra> slice = compraDao.findByUserIdOrderByFechaDesc(userId, PageRequest.of(page,size));
        return new Block<>(slice.getContent(), slice.hasNext());
    }

    @Override
    public void giveTicket(Long compraId, String card) throws InstanceNotFoundException, SesionDateBeforeNowException, TicketsAlreadyTakenException, NotCorrectCardException {
        Optional<Compra> compra = compraDao.findById(compraId);

        if(compra.isEmpty()){
            throw new InstanceNotFoundException("Id de compra", compraId);
        }
        if(compra.get().isEntregada()){
            throw new TicketsAlreadyTakenException(compraId,compra.get().getUser().getId());
        }
        if(compra.get().getSesion().getFecha().isBefore(LocalDateTime.now())){
            throw new SesionDateBeforeNowException(compra.get().getSesion().getId());
        }
        if(!(Objects.equals(compra.get().getTarjeta(), card))){
            throw new NotCorrectCardException(compraId,compra.get().getUser().getId());
        }

        compra.get().setEntregada(true);
    }
}