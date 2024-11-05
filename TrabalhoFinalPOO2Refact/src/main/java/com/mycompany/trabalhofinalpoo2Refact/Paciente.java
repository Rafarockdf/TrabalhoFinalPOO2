/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo2Refact;

public class Paciente {
    private String nome;
    private String dataNascimento;
    private String endereco;
    private String contato;
    private Boolean convenio;
    // private String nivelPrioridade;
    

    public Paciente(String nome, String dataNascimento, String endereco, String contato,Boolean convenio) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.contato = contato;
        this.convenio = convenio;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public Boolean setConvenio(){
        return this.convenio;
    } 
    public void setConvenio(Boolean convenio){
        this.convenio = convenio;
    }
    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
