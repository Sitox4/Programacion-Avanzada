package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;

public class BillboardParamsDto {
    private Long sesionId;
    private long date;

    public BillboardParamsDto() {
    }

    public BillboardParamsDto(Long sesionId, long date) {
        this.sesionId = sesionId;
        this.date = date;
    }

    @NotNull
    public Long getSesionId() {
        return sesionId;
    }

    public void setSesionId(Long sesionId) {
        this.sesionId = sesionId;
    }

    @NotNull
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}