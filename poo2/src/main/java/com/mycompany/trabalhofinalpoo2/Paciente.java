/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.trabalhofinalpoo2;
public abstract class Paciente{
    protected String nome;
    protected String cpf; 
    protected String dataNascimento;
    protected String endereco;
    protected String contato;

    public Paciente(String nome, String dataNascimento, String endereco, String contato,String cpf) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.contato = contato;
        this.cpf = cpf;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf=cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome(){
        return nome;
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
