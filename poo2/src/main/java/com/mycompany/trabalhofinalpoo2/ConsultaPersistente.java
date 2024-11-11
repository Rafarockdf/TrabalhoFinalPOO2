package com.mycompany.trabalhofinalpoo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConsultaPersistente {

    private static ConsultaPersistente instancia;  // Instância única da classe
    
    // Construtor privado para evitar criação de instâncias fora da classe
    private ConsultaPersistente() {}

    // Método para obter a instância única (Singleton)
    public static synchronized ConsultaPersistente getInstancia() {
        if (instancia == null) {
            instancia = new ConsultaPersistente();
        }
        return instancia;
    }

    // Método para abrir conexão com o banco de dados
    private Connection abrirConexao() {
        Connection conexao = null;
        try {
            // Estabelecendo a conexão com o banco de dados PostgreSQL
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TrabalhoFinalPOO2", "postgres", "otavio2004");
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conexao;
    }

    // Método para registrar uma consulta
    public void registrarConsulta(Consulta consulta) throws ParseException {
        Connection conexao = abrirConexao();
        if (conexao != null) {
            String queryConsulta = "INSERT INTO consulta (paciente_cpf, medico_crm, data_consulta, motivo, observacoes) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(queryConsulta)) {
                stmt.setString(1, consulta.getPacienteCpf());
                stmt.setString(2, consulta.getMedicoCrm());

                // Convertendo a data da consulta
                String data = consulta.getDataConsulta();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date parsedDate = formatter.parse(data);
                java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
                stmt.setDate(3, sqlDate);

                stmt.setString(4, consulta.getMotivo());
                stmt.setString(5, consulta.getObservacoes());

                stmt.executeUpdate();
                System.out.println("Consulta registrada com sucesso.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                fecharConexao(conexao);
            }
        }
    }
     public void pesquisarConsultas() {
        Connection conexao = abrirConexao();

        if (conexao != null) {
            String query = "SELECT * FROM consulta";  // Seleciona todas as consultas

            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                if (!rs.isBeforeFirst()) {
                    System.out.println("Nenhuma consulta encontrada.");
                } else {
                    // Itera sobre os resultados e imprime os dados
                    while (rs.next()) {
                        String pacienteCpf = rs.getString("paciente_cpf");
                        String medicoCrm = rs.getString("medico_crm");
                        java.sql.Date dataConsulta = rs.getDate("data_consulta");
                        String motivo = rs.getString("motivo");
                        String observacoes = rs.getString("observacoes");

                        // Formata a data da consulta
                        SimpleDateFormat formatterOut = new SimpleDateFormat("dd/MM/yyyy");
                        String dataConsultaStr = formatterOut.format(dataConsulta);

                        // Imprime os detalhes da consulta
                        System.out.println("Paciente CPF: " + pacienteCpf);
                        System.out.println("Médico CRM: " + medicoCrm);
                        System.out.println("Data da Consulta: " + dataConsultaStr);
                        System.out.println("Motivo: " + motivo);
                        System.out.println("Observações: " + observacoes);
                        System.out.println("-----------------------------------------");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                fecharConexao(conexao);
            }
        }
    }
   public void deletarConsulta(String pacienteCpf, String medicoCrm, String dataConsulta) {
    Connection conexao = abrirConexao();

    if (conexao != null) {
        // Preparando a query de exclusão
        String queryDelete = "DELETE FROM consulta WHERE paciente_cpf = ? AND medico_crm = ? AND data_consulta = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(queryDelete)) {
            stmt.setString(1, pacienteCpf);
            stmt.setString(2, medicoCrm);

            // Tentar converter a data para o formato correto (yyyy-mm-dd)
            SimpleDateFormat sdfEntrada = new SimpleDateFormat("yyyy/MM/dd");  // Formato de entrada
            SimpleDateFormat sdfSaida = new SimpleDateFormat("yyyy-MM-dd");  // Formato de saída esperado

            java.util.Date date = sdfEntrada.parse(dataConsulta);  // Converter de String para Date
            String dataFormatada = sdfSaida.format(date);  // Converter para o formato correto (yyyy-MM-dd)

            // Convertendo para java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(dataFormatada); 
            stmt.setDate(3, sqlDate);

            // Executando o comando de delete
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Consulta deletada com sucesso.");
            } else {
                System.out.println("Nenhuma consulta encontrada para deletar.");
            }
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        } finally {
            fecharConexao(conexao);
        }
    }
}



    // Método para fechar a conexão com o banco de dados
    private void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
