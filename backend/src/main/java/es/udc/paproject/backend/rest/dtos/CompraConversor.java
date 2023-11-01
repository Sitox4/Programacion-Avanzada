package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Compra;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class CompraConversor {
    public CompraConversor(){}

    public final static CompraDto toCompraDto(Compra compra){
        return new CompraDto(compra.getId(),toMillis(compra.getFecha()),compra.getSesion().getPelicula().getTitulo(), compra.getCantidad(),compra.getSesion().getPrecio().multiply(new BigDecimal(compra.getCantidad())),toMillis(compra.getSesion().getFecha()),compra.isEntregada());
    }

    public final static List<CompraSummaryDto> toCompraSummaryDtos(List<Compra> compras){
        return compras.stream().map(CompraConversor::toCompraSummaryDto).collect(Collectors.toList());
    }

    private static CompraSummaryDto toCompraSummaryDto(Compra compra){
        return new CompraSummaryDto(compra.getId(),toMillis(compra.getFecha()),compra.getSesion().getPelicula().getTitulo(),
                compra.getCantidad(),new BigDecimal(compra.getCantidad()).multiply(compra.getSesion().getPrecio()),toMillis(compra.getSesion().getFecha()),compra.isEntregada());
    }

    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }
}