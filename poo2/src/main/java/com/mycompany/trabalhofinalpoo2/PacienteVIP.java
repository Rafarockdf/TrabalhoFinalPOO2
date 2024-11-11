/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo2;


public class PacienteVIP extends Paciente {
    
    private int nivelPrioridade;
    private String servicosAdicionais;
    private String acessoEspecialistas;
    private String dataCadastroVIP;

    public PacienteVIP() {
        super("", "", "", "","");
    }
    
    public void setInformacoes(String nome,String cpf, String dataNascimento, String endereco, String contato,
                       String servicosAdicionais,
                       String acessoEspecialistas, int nivelPrioridade) {
        // Configurando os atributos da classe pai
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.contato = contato;
        setCpf(cpf);
        //Atrbitos classe filha
      
        this.servicosAdicionais = servicosAdicionais;
        this.acessoEspecialistas = acessoEspecialistas;
        this.nivelPrioridade = nivelPrioridade;
    }
    // Getters e Setters
    public int getNivelPrioridade() {
        return nivelPrioridade;
    }

    public void setNivelPrioridade(int nivelPrioridade) {
        this.nivelPrioridade = nivelPrioridade;
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



    @Override
    public String toString() {
        return "PacienteVIP{" +
                "nome='" + getNome() + '\'' +
                ", nivelPrioridade=" + nivelPrioridade +
                ", servicosAdicionais='" + servicosAdicionais + '\'' +
                ", acessoEspecialistas='" + acessoEspecialistas + '\'' +
                ", dataAdesaoProgramaVIP='" + dataCadastroVIP + '\'' +
                '}';
    }
}

