package com.mycompany.trabalhofinalpoo2;
import java.sql.*;
import java.util.Scanner;

public class PacienteManager {

    private Scanner scanner = new Scanner(System.in);

    private Connection abrirConexao() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/TrabalhoFinalPOO2", "postgres", "otavio2004");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void consultarEAlterarPaciente(String cpf) {
        Connection conexao = abrirConexao();
        if (conexao != null) {
            String queryPaciente = "SELECT * FROM paciente WHERE cpf = ?";
            String queryPacienteVIP = "SELECT * FROM PacienteVIP WHERE paciente_cpf = ?";
            String queryPacienteRegular = "SELECT * FROM PacienteRegular WHERE paciente_cpf = ?";
            String queryPacienteInfantil = "SELECT * FROM PacienteInfantil WHERE paciente_cpf = ?";

            try {
                // Consultar se o CPF existe na tabela 'paciente'
                PreparedStatement stmt = conexao.prepareStatement(queryPaciente);
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();

                if(rs.next()) {
                    // Caso contrário, consulta as outras tabelas
                    stmt = conexao.prepareStatement(queryPacienteVIP);
                    stmt.setString(1, cpf);
                    rs = stmt.executeQuery();

                    if (rs.next()) {
                        System.out.println("Paciente VIP encontrado.");
                        atualizarPaciente(cpf, conexao);
                        atualizarPacienteVIP(cpf, conexao);
                    } else {
                        stmt = conexao.prepareStatement(queryPacienteRegular);
                        stmt.setString(1, cpf);
                        rs = stmt.executeQuery();

                        if (rs.next()) {
                            System.out.println("Paciente Regular encontrado.");
                            // Atualizar tanto na tabela 'paciente' quanto em 'PacienteRegular'
                            atualizarPaciente(cpf, conexao);
                            atualizarPacienteRegular(cpf, conexao);
                        } else {
                            stmt = conexao.prepareStatement(queryPacienteInfantil);
                            stmt.setString(1, cpf);
                            rs = stmt.executeQuery();

                            if (rs.next()) {
                                System.out.println("Paciente Infantil encontrado.");
                                atualizarPaciente(cpf, conexao);
                                atualizarPacienteInfantil(cpf, conexao);
                            } else {
                                System.out.println("Paciente não encontrado em nenhuma das tabelas.");
                            }
                        }
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                fecharConexao(conexao);
            }
        }
    }

    private void atualizarPaciente(String cpf, Connection conexao) {
        System.out.print("Digite o novo nome do paciente: ");
        String novoNome = scanner.nextLine();

        System.out.print("Digite o novo endereço do paciente: ");
        String novoEndereco = scanner.nextLine();

        System.out.print("Digite o novo contato do paciente: ");
        String novoContato = scanner.nextLine();

        String query = "UPDATE paciente SET nome = ?, endereco = ?, contato = ? WHERE cpf = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setString(1, novoNome);
            stmt.setString(2, novoEndereco);
            stmt.setString(3, novoContato);
            stmt.setString(4, cpf);
            stmt.executeUpdate();
            System.out.println("Dados do paciente atualizados com sucesso na tabela 'paciente'.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void atualizarPacienteVIP(String cpf, Connection conexao) {
        System.out.print("Digite o novo nivel de prioridade do paciente VIP: ");
        int novoNivel = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite os novos serviços adicionais: ");
        String novoServ = scanner.nextLine();

        System.out.print("Digite o novo acesso a especialistas: ");
        String novoAcesso = scanner.nextLine();

        String query = "UPDATE PacienteVIP SET nivel_prioridade = ?, servicos_adicionais = ?, acesso_especialistas = ? WHERE paciente_cpf = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setInt(1, novoNivel);
            stmt.setString(2, novoServ);
            stmt.setString(3, novoAcesso);
            stmt.setString(4, cpf);
            stmt.executeUpdate();
            System.out.println("Dados do paciente VIP atualizados com sucesso na tabela 'PacienteVIP'.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void atualizarPacienteRegular(String cpf, Connection conexao) {
        System.out.print("Digite o histórico de consultas do paciente Regular: ");
        String novoHistoricoConsultas = scanner.nextLine();

        System.out.print("Digite o médico preferido do paciente Regular: ");
        String novoMedicoPreferencia = scanner.nextLine();

        System.out.print("Digite o plano de saúde do paciente Regular: ");
        String novoPlanoDeSaude = scanner.nextLine();

        System.out.print("Digite a periodicidade das consultas do paciente Regular: ");
        String novaPeriodicidadeConsultas = scanner.nextLine();

        System.out.print("Digite as observações do paciente Regular: ");
        String novasObservacoes = scanner.nextLine();

        String query = "UPDATE PacienteRegular SET "+
                       "historico_consultas = ?, medico_preferencia = ?, plano_de_saude = ?, " +
                       "periodicidade_consultas = ?, observacoes = ? WHERE paciente_cpf = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setString(1, novoHistoricoConsultas);
            stmt.setString(2, novoMedicoPreferencia);
            stmt.setString(3, novoPlanoDeSaude);
            stmt.setString(4, novaPeriodicidadeConsultas);
            stmt.setString(5, novasObservacoes);
            stmt.setString(6, cpf);
            stmt.executeUpdate();
            System.out.println("Dados do paciente Regular atualizados com sucesso na tabela 'PacienteRegular'.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void atualizarPacienteInfantil(String cpf, Connection conexao) {
        System.out.print("Digite o novo nome do responsavel do paciente Infantil: ");
        String novoNomeR = scanner.nextLine();

        System.out.print("Digite o novo contato do responsavel do paciente Infantil: ");
        String novoContato = scanner.nextLine();

        System.out.print("Digite as novas informações pediátricas: ");
        String novoInfo = scanner.nextLine();
        
        System.out.print("Digite a nova escola ");
        String novoEscola = scanner.nextLine();
        
        System.out.print("Digite as novas autorizações de tratamentos: ");
        Boolean novoAtt = scanner.nextBoolean();
        
        scanner.nextLine();
        System.out.print("Digite o novo medico preferencial: ");
        String novoMed = scanner.nextLine();   
           
        
        String query = "UPDATE PacienteInfantil SET nome_responsavel = ?, contato_responsavel = ?, informacoes_pediatricas = ?, " +
               "escola = ?, autorizacao_tratamentos = ?, medico_pediatra_preferencial = ? WHERE paciente_cpf = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setString(1, novoNomeR);
            stmt.setString(2, novoContato);
            stmt.setString(3, novoInfo);
            stmt.setString(4, novoEscola);
            stmt.setBoolean(5, novoAtt);
            stmt.setString(6, novoMed);
            stmt.setString(7, cpf);
            stmt.executeUpdate();
            System.out.println("Dados do paciente Infantil atualizados com sucesso na tabela 'PacienteInfantil'.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
