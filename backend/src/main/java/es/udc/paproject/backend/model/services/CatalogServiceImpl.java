package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.DayNotValidException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.SesionDateBeforeNowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CatalogServiceImpl implements CatalogService{

    @Autowired
    private PeliculaDao peliculaDao;

    @Autowired
    private SesionDao sesionDao;


    @Override
    public List<BillboardItem> getBillboard(LocalDate date) throws DayNotValidException {

        List<Sesion> sesionList;

        if(date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusDays(6))){
            throw new DayNotValidException(date);
        }

        if(date.isEqual(LocalDate.now())){
            sesionList = sesionDao.findAllByFechaBetweenOrderByPeliculaTituloAscFechaAsc(LocalDateTime.now(),date.atTime(LocalTime.MAX));
        }else{
            sesionList = sesionDao.findAllByFechaBetweenOrderByPeliculaTituloAscFechaAsc(date.atStartOfDay(),date.atTime(LocalTime.MAX));
        }

        Sesion sesionActual, sesionAnterior;

        List<BillboardItem> billboardItemList = new ArrayList<>();

        sesionAnterior = sesionList.get(0);

        List<Sesion> listaSesiones = new ArrayList<>();

        listaSesiones.add(sesionAnterior);

        for (int i=1;i<sesionList.size();i++){
            sesionActual = sesionList.get(i);
            if(sesionActual.getPelicula().getTitulo()==sesionAnterior.getPelicula().getTitulo()){
                listaSesiones.add(sesionActual);
            }else{
                billboardItemList.add(new BillboardItem(sesionAnterior.getPelicula(),listaSesiones));
                listaSesiones = new ArrayList<>();
                listaSesiones.add(sesionActual);
            }
            sesionAnterior = sesionActual;
        }

        billboardItemList.add(new BillboardItem(sesionAnterior.getPelicula(),listaSesiones));

        return billboardItemList;
    }

    @Override
    public Pelicula getPelicula(Long peliculaId) throws InstanceNotFoundException {

        Optional<Pelicula> pelicula = peliculaDao.findById(peliculaId);

        if(pelicula.isEmpty()){
            throw new InstanceNotFoundException("peliculaId",peliculaId);
        }

        return pelicula.get();
    }

    @Override
    public Sesion getSesion(Long sesionId) throws InstanceNotFoundException, SesionDateBeforeNowException {
        Optional<Sesion> sesion = sesionDao.findById(sesionId);
        if(sesion.isEmpty()){
            throw new InstanceNotFoundException("sesionId",sesionId);
        }
        if(LocalDateTime.now().isAfter(sesion.get().getFecha())){
            throw new SesionDateBeforeNowException(sesionId);
        }
        return sesion.get();
    }
}
