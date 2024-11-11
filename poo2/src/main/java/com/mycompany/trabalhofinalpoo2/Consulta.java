package com.mycompany.trabalhofinalpoo2;

public class Consulta {
    private String pacienteCpf;
    private String medicoCrm;
    private String dataConsulta;
    private String motivo;
    private String observacoes;

    // Construtor
    public Consulta(String pacienteCpf, String medicoCrm, String dataConsulta, String motivo, String observacoes) {
        this.pacienteCpf = pacienteCpf;
        this.medicoCrm = medicoCrm;
        this.dataConsulta = dataConsulta;
        this.motivo = motivo;
        this.observacoes = observacoes;
    }

    // Getters e Setters
    public String getPacienteCpf() {
        return pacienteCpf;
    }

    public void setPacienteCpf(String pacienteCpf) {
        this.pacienteCpf = pacienteCpf;
    }

    public String getMedicoCrm() {
        return medicoCrm;
    }

    public void setMedicoCrm(String medicoCrm) {
        this.medicoCrm = medicoCrm;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
