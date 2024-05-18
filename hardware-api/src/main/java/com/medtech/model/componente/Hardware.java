package com.medtech.model.componente;

public abstract class Hardware {

    private String nomeHardware;

    private String unidadeDeMedida;

    private Double medida;

    private String descricaoHardware;

    public Hardware(String nomeHardware, String unidadeDeMedida, Double medida, String descricaoHardware) {
        this.nomeHardware = nomeHardware;
        this.unidadeDeMedida = unidadeDeMedida;
        this.medida = medida;
        this.descricaoHardware = descricaoHardware;
    }

    public Hardware() {
    }

    public String getNomeHardware() {
        return nomeHardware;
    }

    public String getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public Double getMedida() {
        return medida;
    }

    public String getDescricaoHardware() {
        return descricaoHardware;
    }

    public void setNomeHardware(String nomeHardware) {
        this.nomeHardware = nomeHardware;
    }

    public void setUnidadeDeMedida(String unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public void setMedida(Double medida) {
        this.medida = medida;
    }

    public void setDescricaoHardware(String descricaoHardware) {
        this.descricaoHardware = descricaoHardware;
    }

    public int getIdComputador() {
        return 0;
    }
}
