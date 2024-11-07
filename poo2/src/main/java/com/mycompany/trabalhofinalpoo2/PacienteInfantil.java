/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo2;

public class PacienteInfantil extends Paciente {
    private String nomeResponsavel;
    private String contatoResponsavel;
    private String informacoesPediatricas;
    private String escola;
    private boolean autorizacaoTratamentos;
    private String medicoPediatraPreferencial;
    private String dataCadastroInfantil;

    public PacienteInfantil(String nome, String dataNascimento, String endereco, String contato,
                            String nomeResponsavel, String contatoResponsavel, String informacoesPediatricas,
                            String escola, boolean autorizacaoTratamentos, String medicoPediatraPreferencial) {
        super(nome, dataNascimento, endereco, contato);
        this.nomeResponsavel = nomeResponsavel;
        this.contatoResponsavel = contatoResponsavel;
        this.informacoesPediatricas = informacoesPediatricas;
        this.escola = escola;
        this.autorizacaoTratamentos = autorizacaoTratamentos;
        this.medicoPediatraPreferencial = medicoPediatraPreferencial;
    }

    // Getters e Setters
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getContatoResponsavel() {
        return contatoResponsavel;
    }

    public void setContatoResponsavel(String contatoResponsavel) {
        this.contatoResponsavel = contatoResponsavel;
    }

    public String getInformacoesPediatricas() {
        return informacoesPediatricas;
    }

    public void setInformacoesPediatricas(String informacoesPediatricas) {
        this.informacoesPediatricas = informacoesPediatricas;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public boolean isAutorizacaoTratamentos() {
        return autorizacaoTratamentos;
    }

    public void setAutorizacaoTratamentos(boolean autorizacaoTratamentos) {
        this.autorizacaoTratamentos = autorizacaoTratamentos;
    }

    public String getMedicoPediatraPreferencial() {
        return medicoPediatraPreferencial;
    }

    public void setMedicoPediatraPreferencial(String medicoPediatraPreferencial) {
        this.medicoPediatraPreferencial = medicoPediatraPreferencial;
    }
}
