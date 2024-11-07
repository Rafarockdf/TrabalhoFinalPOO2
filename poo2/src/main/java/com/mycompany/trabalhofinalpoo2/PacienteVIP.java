/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo2;

public class PacienteVIP extends Paciente {
    private CriterioPrioridade criterioPrioridade;
    private int nivelPrioridade;
    private String servicosAdicionais;
    private String acessoEspecialistas;
    private String dataCadastroVIP;

    public PacienteVIP(String nome, String dataNascimento, String endereco, String contato,
                       CriterioPrioridade criterioPrioridade, String servicosAdicionais,
                       String acessoEspecialistas, String dataCadastroVIP, int nivelPrioridade) {
        super(nome, dataNascimento, endereco, contato);
        this.criterioPrioridade = criterioPrioridade;
        this.servicosAdicionais = servicosAdicionais;
        this.acessoEspecialistas = acessoEspecialistas;
        this.dataCadastroVIP = dataCadastroVIP;
        this.nivelPrioridade = nivelPrioridade;
    }
    // Getters e Setters
    public int getNivelPrioridade() {
        return nivelPrioridade;
    }

    public void setNivelPrioridade(int nivelPrioridade) {
        this.nivelPrioridade = nivelPrioridade;
    }
    
    public CriterioPrioridade getCriterioPrioridade() {
        return criterioPrioridade;
    }

    public void setCriterioPrioridade(CriterioPrioridade criterioPrioridade) {
        this.criterioPrioridade = criterioPrioridade;
    }

    public String getServicosAdicionais() {
        return servicosAdicionais;
    }

    public void setServicosAdicionais(String servicosAdicionais) {
        this.servicosAdicionais = servicosAdicionais;
    }

    public String getAcessoEspecialistas() {
        return acessoEspecialistas;
    }

    public void setAcessoEspecialistas(String acessoEspecialistas) {
        this.acessoEspecialistas = acessoEspecialistas;
    }



    public String getDataAdesaoProgramaVIP() {
        return dataCadastroVIP;
    }

    public void setDataAdesaoProgramaVIP(String dataAdesaoProgramaVIP) {
        this.dataCadastroVIP = dataAdesaoProgramaVIP;
    }

    // Método para verificar se o paciente tem alta prioridade
    public boolean isAltaPrioridade() {
        return criterioPrioridade != null;
    }

    @Override
    public String toString() {
        return "PacienteVIP{" +
                "nome='" + getNome() + '\'' +
                ", criterioPrioridade=" + criterioPrioridade +
                ", nivelPrioridade=" + nivelPrioridade +
                ", servicosAdicionais='" + servicosAdicionais + '\'' +
                ", acessoEspecialistas='" + acessoEspecialistas + '\'' +
                ", dataAdesaoProgramaVIP='" + dataCadastroVIP + '\'' +
                '}';
    }
}

