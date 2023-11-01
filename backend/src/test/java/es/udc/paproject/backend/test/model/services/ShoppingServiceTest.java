package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.ShoppingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static  org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ShoppingServiceTest {
    private final Long NON_EXISTENT_ID = (long) -1;
    @Autowired
    private PeliculaDao peliculaDao;
    @Autowired
    private SalaDao salaDao;
    @Autowired
    private SesionDao sesionDao;
    @Autowired
    private CompraDao compraDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private ShoppingService shoppingService;

    private Pelicula crearPelicula(String titulo) {
        return new Pelicula(titulo, "resumen de la pelicula", 140);
    }
    private Sesion crearSesion(Pelicula pelicula, LocalDateTime fecha, Sala sala){
        return new Sesion(fecha, new BigDecimal(5), pelicula, sala, sala.getCapacidad());
    }

    private Sala crearSala(String nombre, int capacity){
        return new Sala(nombre, capacity);
    }

    private User crearUser(String username, String password){
        User user = new User(username, password, "Dani", "Lago", "daniel.lago.iglesias@udc.es");
        user.setRole(User.RoleType.ESPECTADOR);
        return user;
    }

    @Test
    public void buyTest() throws InstanceNotFoundException, SesionDateBeforeNowException, NotEnoughtTicketsException{
        Sala sala = crearSala("sala1", 20);
        salaDao.save(sala);
        Pelicula pelicula = crearPelicula("Avatar");
        peliculaDao.save(pelicula);
        Sesion sesion = crearSesion(pelicula, LocalDateTime.now().plusHours(2).withNano(0),sala);
        sesionDao.save(sesion);

        User user = crearUser("dani25", "Dani25");
        userDao.save(user);
        Compra compra = shoppingService.buyTicket(user.getId(), 2, "1234567891234567", sesion.getId());

        Optional<Compra> foundCompra = compraDao.findById(compra.getId());

        assertTrue(foundCompra.isPresent());
        assertEquals(user,foundCompra.get().getUser());
        assertEquals(sesion,foundCompra.get().getSesion());
        assertEquals(compra.getFecha(),foundCompra.get().getFecha());
        assertEquals(compra.getTarjeta(),foundCompra.get().getTarjeta());
        assertEquals(compra.isEntregada(),foundCompra.get().isEntregada());
        assertEquals(compra.getCantidad(),foundCompra.get().getCantidad());
        assertEquals(2,foundCompra.get().getCantidad());
        assertEquals("Avatar",foundCompra.get().getSesion().getPelicula().getTitulo());
        assertEquals(LocalDateTime.now().plusHours(2).withNano(0),foundCompra.get().getSesion().getFecha());
        assertEquals(20-compra.getCantidad(),foundCompra.get().getSesion().getTicketsLibres());
    }

    @Test
    public void buyNonExistentSesionIdTest(){
        User user = crearUser("dani25", "Dani25");
        userDao.save(user);
        assertThrows(InstanceNotFoundException.class, ()->shoppingService.buyTicket(user.getId(),2,"123567891234567",NON_EXISTENT_ID));
    }

    @Test
    public void buyNonExistentUserIdTest(){
        Sala sala = crearSala("sala1", 20);
        salaDao.save(sala);
        Pelicula pelicula = crearPelicula("Avatar");
        peliculaDao.save(pelicula);
        Sesion sesion = crearSesion(pelicula, LocalDateTime.now().plusHours(2),sala);
        sesionDao.save(sesion);
        assertThrows(InstanceNotFoundException.class, ()->shoppingService.buyTicket(NON_EXISTENT_ID,2,"123567891234567",sesion.getId()));
    }

    @Test
    public void buySesionAlreadyStartedTest(){
        Sala sala = crearSala("sala1", 20);
        salaDao.save(sala);
        Pelicula pelicula = crearPelicula("Avatar");
        peliculaDao.save(pelicula);
        Sesion sesion = crearSesion(pelicula, LocalDateTime.now().minusHours(2),sala);
        sesionDao.save(sesion);

        User user = crearUser("dani25", "Dani25");
        userDao.save(user);

        assertThrows(SesionDateBeforeNowException.class, ()->shoppingService.buyTicket(user.getId(),2,"123567891234567",sesion.getId()));
    }

    @Test
    public void buyTooMuchTicketsTest(){
        Sala sala = crearSala("sala1", 9);
        salaDao.save(sala);
        Pelicula pelicula = crearPelicula("Avatar");
        peliculaDao.save(pelicula);
        Sesion sesion = crearSesion(pelicula, LocalDateTime.now().plusHours(2),sala);
        sesionDao.save(sesion);

        User user = crearUser("dani25", "Dani25");
        userDao.save(user);

        assertThrows(NotEnoughtTicketsException.class, ()->shoppingService.buyTicket(user.getId(),10,"123567891234567",sesion.getId()));
    }

    @Test
    public void getComprasTest() {  //MODIFICANDO EL NEW COMPRA CREO QUE FUNCIONA COMPROBNDO EL EXPECTEDBLOCK CON LA BUSQUEDA
        Sala sala = crearSala("sala1", 15);
        salaDao.save(sala);
        Pelicula pelicula = crearPelicula("Avatar");
        peliculaDao.save(pelicula);
        Sesion sesion = crearSesion(pelicula, LocalDateTime.now().plusHours(2),sala);
        sesionDao.save(sesion);

        User user = crearUser("dani25", "Dani25");
        userDao.save(user);

        Compra compra1 = new Compra(1,"1234567891234567",user, sesion, LocalDateTime.of(2017, 10, 1, 10, 2, 3),false);
        compraDao.save(compra1);
        Compra compra2 = new Compra(2,"1234567891234567",user, sesion, LocalDateTime.of(2017, 10, 1, 10, 2, 3),false);
        compraDao.save(compra2);
        Compra compra3 = new Compra(2,"1234567891234567",user, sesion, LocalDateTime.of(2017, 10, 1, 10, 2, 3),false);
        compraDao.save(compra3);

        Block<Compra> expectedBlock = new Block<>(Arrays.asList(compra3, compra2), true);
        assertEquals(expectedBlock.getItems().size(),shoppingService.getCompras(user.getId(),0,2).getItems().size());

        expectedBlock = new Block<>(Arrays.asList(compra1), false);
        assertEquals(expectedBlock.getItems().size(), shoppingService.getCompras(user.getId(), 1, 2).getItems().size());
    }

    @Test
    public void giveTicketsTest() throws SesionDateBeforeNowException, InstanceNotFoundException, TicketsAlreadyTakenException, NotCorrectCardException {
        Sala sala = crearSala("sala1", 20);
        salaDao.save(sala);
        Pelicula pelicula = crearPelicula("Avatar");
        peliculaDao.save(pelicula);
        Sesion sesion = crearSesion(pelicula, LocalDateTime.now().plusHours(2),sala);
        sesionDao.save(sesion);

        User user = crearUser("dani25", "Dani25");
        userDao.save(user);

        Compra compra = new Compra(1,"1234567891234567",user, sesion, LocalDateTime.of(2017, 10, 1, 10, 2, 3),false);
        compraDao.save(compra);
        assertFalse(compra.isEntregada());

        shoppingService.giveTicket(compra.getId(),"1234567891234567");
        assertTrue(compra.isEntregada());
    }

    @Test
    public void giveTicketsNonExistentCompraIdTest(){
        User user = crearUser("dani25", "Dani25");
        userDao.save(user);

        assertThrows(InstanceNotFoundException.class, ()->shoppingService.giveTicket(1L, "123567891234567"));
    }

    @Test
    public void giveTicketsSesionAlreadyStartedTest(){
        Sala sala = crearSala("sala1", 15);
        salaDao.save(sala);
        Pelicula pelicula = crearPelicula("Avatar");
        peliculaDao.save(pelicula);
        Sesion sesion = crearSesion(pelicula, LocalDateTime.now().minusHours(2),sala);
        sesionDao.save(sesion);

        User user = crearUser("dani25", "Dani25");
        userDao.save(user);

        Compra compra1 = new Compra(1,"1234567891234567",user, sesion, LocalDateTime.of(2017, 10, 1, 10, 2, 3),false);
        compraDao.save(compra1);

        assertThrows(SesionDateBeforeNowException.class, ()->shoppingService.giveTicket(compra1.getId(), "123567891234567"));
    }

    @Test
    public void giveTicketsAlreadyTakenTest(){
        Sala sala = crearSala("sala1", 15);
        salaDao.save(sala);
        Pelicula pelicula = crearPelicula("Avatar");
        peliculaDao.save(pelicula);
        Sesion sesion = crearSesion(pelicula, LocalDateTime.now().plusHours(2),sala);
        sesionDao.save(sesion);

        User user = crearUser("dani25", "Dani25");
        userDao.save(user);

        Compra compra1 = new Compra(1,"1234567891234567",user, sesion, LocalDateTime.of(2017, 10, 1, 10, 2, 3),true);
        compraDao.save(compra1);

        assertThrows(TicketsAlreadyTakenException.class, ()->shoppingService.giveTicket(compra1.getId(), "123567891234567"));
    }

    @Test
    public void giveTicketsIncorrectCardTest(){
        Sala sala = crearSala("sala1", 15);
        salaDao.save(sala);
        Pelicula pelicula = crearPelicula("Avatar");
        peliculaDao.save(pelicula);
        Sesion sesion = crearSesion(pelicula, LocalDateTime.now().plusHours(2),sala);
        sesionDao.save(sesion);

        User user = crearUser("dani25", "Dani25");
        userDao.save(user);

        Compra compra1 = new Compra(1,"1234567891234567",user, sesion, LocalDateTime.of(2017, 10, 1, 10, 2, 3),false);
        compraDao.save(compra1);

        assertThrows(NotCorrectCardException.class, ()->shoppingService.giveTicket(compra1.getId(), "123567891234560"));
    }
}