package com.mycompany.trabalhofinalpoo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

public abstract class PersistenciaDeDados {
    protected abstract Connection abrirConexao() throws SQLException;
    protected abstract void efetuarInsert(Paciente paciente) throws SQLException;
    protected abstract void consultarPacientes() throws SQLException;
    protected abstract void fecharConexao(Connection conexao) throws SQLException;
    
    
    public void executarOperacao(Paciente paciente) {
        try {
            Connection conexao = abrirConexao();
            if (conexao != null) {
                efetuarInsert(paciente);
                fecharConexao(conexao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void executarConsulta(){
        try {
            Connection conexao = abrirConexao();
            if (conexao != null) {
                consultarPacientes();
                fecharConexao(conexao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
