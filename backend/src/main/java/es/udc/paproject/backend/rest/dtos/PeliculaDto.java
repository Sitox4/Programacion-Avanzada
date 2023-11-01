package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;

public class PeliculaDto {

    private Long id;
    private String titulo;
    private String resumen;
    private int duracion;

    public PeliculaDto() {
    }

    public PeliculaDto(Long id, String titulo, String resumen, int duracion) {
        this.id = id;
        this.titulo = titulo;
        this.resumen = resumen;
        this.duracion = duracion;
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @NotNull
    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    @NotNull
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
