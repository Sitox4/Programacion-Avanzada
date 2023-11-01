package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.BillboardItem;
import es.udc.paproject.backend.model.entities.Sesion;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BillboardItemConversor {
    private BillboardItemConversor() {}

    public final static BillboardItemDto toBillboardItemDto(BillboardItem billboardItem) {
        List<BillboardParamsDto> listaHoras = new ArrayList<>();
        for (int i=0;i<billboardItem.getListaSesiones().size();i++){
            Sesion sesionActual = billboardItem.getListaSesiones().get(i);
            listaHoras.add(new BillboardParamsDto(sesionActual.getId(),toMillis(sesionActual.getFecha())));
        }
        return new BillboardItemDto(billboardItem.getPelicula().getId(),billboardItem.getPelicula().getTitulo(),listaHoras);
    }

    public final static List<BillboardItemDto> toBillboardItemDtos(List<BillboardItem> billboardItems) {
        return billboardItems.stream().map(BillboardItemConversor::toBillboardItemDto).collect(Collectors.toList());
    }

    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }
}