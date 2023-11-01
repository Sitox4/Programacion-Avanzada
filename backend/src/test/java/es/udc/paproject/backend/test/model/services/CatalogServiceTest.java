package es.udc.paproject.backend.test.model.services;

import   static org.junit.jupiter.api.Assertions.*;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.DayNotValidException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.SesionDateBeforeNowException;
import es.udc.paproject.backend.model.services.CatalogService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CatalogServiceTest {

    private final Long NON_EXISTENT_ID = (long) -1;

    @Autowired
    private PeliculaDao peliculaDao;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private SalaDao salaDao;
    @Autowired
    private SesionDao sesionDao;

    private Pelicula crearPelicula(String titulo) {
        return new Pelicula(titulo, "resumen de la pelicula", 140);
    }
    private Sesion crearSesion(Pelicula pelicula, LocalDateTime fecha, Sala sala){
        return new Sesion(fecha, new BigDecimal(5), pelicula, sala, 15);
    }

    private Sesion crearSesion(LocalDateTime fecha,Pelicula pelicula, Sala sala,int capacidad){
        return new Sesion(fecha, new BigDecimal(5), pelicula, sala, capacidad);
    }

    private Sala crearSala(String nombre, int capacity){
        return new Sala(nombre, capacity);
    }

    @Test
    public void getBillboardTest() throws DayNotValidException {
        Pelicula avatar = crearPelicula("Avatar");
        peliculaDao.save(avatar);
        Pelicula transformers = crearPelicula("Transformers");
        peliculaDao.save(transformers);
        Sala sala1 = crearSala("sala1",100);
        salaDao.save(sala1);
        Sala sala2 = crearSala("sala2",100);
        salaDao.save(sala2);
        Sesion sesion1 = crearSesion(LocalDateTime.now().plusMinutes(2),avatar,sala1,sala1.getCapacidad());
        sesionDao.save(sesion1);
        Sesion sesion2 = crearSesion(LocalDateTime.now().plusMinutes(3),avatar,sala1,sala1.getCapacidad());
        sesionDao.save(sesion2);
        Sesion sesion3 = crearSesion(LocalDateTime.now().plusMinutes(1),transformers,sala2, sala2.getCapacidad());
        sesionDao.save(sesion3);
        Sesion sesion4 = crearSesion(LocalDateTime.now().plusHours(24),transformers,sala2, sala2.getCapacidad()); //Sesion mañana, no se tiene en cuenta en la busqueda de las peliculas de hoy
        sesionDao.save(sesion4);
        Sesion sesion5 = crearSesion(LocalDateTime.now().minusHours(1),transformers,sala2, sala2.getCapacidad());
        sesionDao.save(sesion5);

        List<BillboardItem> billboardItemList = new ArrayList<>();
        List<Sesion> sesionList1 = new ArrayList<>();
        List<Sesion> sesionList2 = new ArrayList<>();
        sesionList1.add(sesion1);
        sesionList1.add(sesion2);
        sesionList2.add(sesion3);
        BillboardItem billboardItem1 = new BillboardItem(sesion2.getPelicula(),sesionList1);
        BillboardItem billboardItem2 = new BillboardItem(sesion3.getPelicula(),sesionList2);
        billboardItemList.add(billboardItem1);
        billboardItemList.add(billboardItem2);

        List<BillboardItem> findBillboardItemList = catalogService.getBillboard(LocalDate.now());

        assertEquals(billboardItemList.get(0).getPelicula(),findBillboardItemList.get(0).getPelicula());
        assertEquals(billboardItemList.get(1).getPelicula(),findBillboardItemList.get(1).getPelicula());
        assertEquals(billboardItemList.get(0).getListaSesiones().size(), findBillboardItemList.get(0).getListaSesiones().size());
        assertEquals(billboardItemList.get(1).getListaSesiones().size(), findBillboardItemList.get(1).getListaSesiones().size());
        assertEquals(billboardItemList.get(0).getListaSesiones().get(0).getFecha(),findBillboardItemList.get(0).getListaSesiones().get(0).getFecha());
        assertEquals(billboardItemList.get(0).getListaSesiones().get(1).getFecha(),findBillboardItemList.get(0).getListaSesiones().get(1).getFecha());
        assertEquals(billboardItemList.get(1).getListaSesiones().get(0).getFecha(),findBillboardItemList.get(1).getListaSesiones().get(0).getFecha());
    }

    @Test
    public void getBillboardDayBeforeTodayTest(){
        assertThrows(DayNotValidException.class, () -> catalogService.getBillboard(LocalDate.now().minusDays(1)));
    }

    @Test
    public void getBillboard20DaysLaterTest(){
        assertThrows(DayNotValidException.class, () -> catalogService.getBillboard(LocalDate.now().plusDays(20)));
    }

    @Test
    public void getPeliculaTest() throws InstanceNotFoundException {
        Pelicula pelicula = crearPelicula("Avatar");
        peliculaDao.save(pelicula);
        assertEquals(pelicula, catalogService.getPelicula(pelicula.getId()));
    }

    @Test
    public void getPeliculaByNonExistentIdTest() {
        assertThrows(InstanceNotFoundException.class, () -> catalogService.getPelicula(NON_EXISTENT_ID));
    }

    @Test
    public void getSesionTest() throws InstanceNotFoundException, SesionDateBeforeNowException {
        Sala sala = crearSala("sala1", 20);
        salaDao.save(sala);
        Pelicula pelicula = crearPelicula("Avatar");
        peliculaDao.save(pelicula);
        Sesion sesion = crearSesion(pelicula, LocalDateTime.now().plusHours(2),sala);
        sesionDao.save(sesion);


        assertEquals(sesion, catalogService.getSesion(sesion.getId()));

    }

    @Test
    public void getSesionByNonExistentIdTest() {
        assertThrows(InstanceNotFoundException.class, () -> catalogService.getSesion(NON_EXISTENT_ID));
    }

    @Test
    public void getSesionAlreadyStartedTest() {
        Sala sala = crearSala("sala1", 20);
        salaDao.save(sala);
        Pelicula pelicula = crearPelicula("Avatar");
        peliculaDao.save(pelicula);
        Sesion sesion = crearSesion(pelicula, LocalDateTime.now().minusHours(2),sala);
        sesionDao.save(sesion);

        assertThrows(SesionDateBeforeNowException.class, () -> catalogService.getSesion(sesion.getId()));
    }

}
