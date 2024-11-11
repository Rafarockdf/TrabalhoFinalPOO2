/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo2;


public class PacienteRegular extends Paciente {
    private String historicoConsultas;
    private String medicoPreferencia;
    private String planoDeSaude;
    private String periodicidadeConsultas;
    private String observacoes;

    public PacienteRegular() {
        super("", "", "", "","");
    }

    public void setInformacoes(String nome,String cpf, String dataNascimento, String endereco, String contato,
                           String historicoConsultas, String medicoPreferencia, String planoDeSaude,
                           String periodicidadeConsultas, String observacoes) {
         // Configurando os atributos da classe pai
        setNome(nome);
        setDataNascimento(dataNascimento);
        setEndereco(endereco);
        setContato(contato);
        setCpf(cpf);
        //Atrbitos classe filha
        this.historicoConsultas = historicoConsultas;
        this.medicoPreferencia = medicoPreferencia;
        this.planoDeSaude = planoDeSaude;
        this.periodicidadeConsultas = periodicidadeConsultas;
        this.observacoes = observacoes;
    }
    

    // Getters e Setters
    public String getHistoricoConsultas() {
        return historicoConsultas;
    }

    public void setHistoricoConsultas(String historicoConsultas) {
        this.historicoConsultas = historicoConsultas;
    }

    public String getMedicoPreferencia() {
        return medicoPreferencia;
    }

    public void setMedicoPreferencia(String medicoPreferencia) {
        this.medicoPreferencia = medicoPreferencia;
    }

    public String getPlanoDeSaude() {
        return planoDeSaude;
    }

    public void setPlanoDeSaude(String planoDeSaude) {
        this.planoDeSaude = planoDeSaude;
    }

    public String getPeriodicidadeConsultas() {
        return periodicidadeConsultas;
    }

    public void setPeriodicidadeConsultas(String periodicidadeConsultas) {
        this.periodicidadeConsultas = periodicidadeConsultas;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    @Override
    public String toString() {
        return "PacienteRegular{" +
            "nome='" + getNome() + '\'' +
            ", cpf ='"+ getCpf() + '\'' + 
            ", dataNascimento='" + getDataNascimento() + '\'' +
            ", endereco='" + getEndereco() + '\'' +
            ", contato='" + getContato() + '\'' +
            ", historicoConsultas='" + historicoConsultas + '\'' +
            ", medicoPreferencia='" + medicoPreferencia + '\'' +
            ", planoDeSaude='" + planoDeSaude + '\'' +
            ", periodicidadeConsultas='" + periodicidadeConsultas + '\'' +
            ", observacoes='" + observacoes + '\'' +
            '}';
    }


    
    
}
