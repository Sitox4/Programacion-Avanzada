package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

public class BillboardItemDto {
    private Long peliculaId;
    private String tituloPelicula;
    private List<BillboardParamsDto> listaHoras;

    public BillboardItemDto() {
    }

    public BillboardItemDto(Long peliculaId,String tituloPelicula, List<BillboardParamsDto> listaHoras) {
        this.peliculaId = peliculaId;
        this.tituloPelicula = tituloPelicula;
        this.listaHoras = listaHoras;
    }

    @NotNull
    public Long getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(Long peliculaId) {
        this.peliculaId = peliculaId;
    }

    @NotNull
    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    @NotNull
    public List<BillboardParamsDto> getListaHoras() {
        return listaHoras;
    }

    public void setListaHoras(List<BillboardParamsDto> listaHoras) {
        this.listaHoras = listaHoras;
    }
}