package com.mycompany.trabalhofinalpoo2;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class TrabalhoFinalPOO2 {

    public static void main(String[] args) throws ParseException {
        int opcaoMenuInicio = 0,opcaoMenuCadastrar=0;
        Scanner scanner = new Scanner(System.in);
        String menuInicio = "********Menu********\n"
                          + "1 - Cadastrar Informações\n"
                          + "2 - Alterar Informações\n"
                          + "3 - Excluir Informações\n"
                          + "4 - Visualizar Todas Informações\n"
                          + "5 - Visualizar Informações específicas\n"
                          + "6 - Sair\n"
                          + "Escolha: ";
        String menuEntidades = "1 - Paciente\n"
                             + "2 - Médico\n"
                             + "3 - Consulta\n"
                             + "Esolha: ";

        
        while(opcaoMenuInicio !=6){
            System.out.println(menuInicio);
            opcaoMenuInicio = scanner.nextInt();
            if (opcaoMenuInicio == 1) {
                System.out.println(menuEntidades);
                opcaoMenuCadastrar = scanner.nextInt();
                if(opcaoMenuCadastrar == 1){
                    System.out.println("Escolha o tipo de paciente:");
                    System.out.println("1 - Paciente Regular");
                    System.out.println("2 - Paciente VIP");
                    System.out.println("3 - Paciente Infantil");
                    System.out.println("Digite qual paciente você deseja inserir");
                    int tipoPaciente = scanner.nextInt();
                    scanner.nextLine(); // Consome a quebra de linha
                    switch (tipoPaciente) {
                        case 1:
                            System.out.println("Você escolheu Paciente Regular.");
                            Paciente pacienteRegular = PacienteFactory.criarPaciente("Regular");
                            System.out.println(pacienteRegular instanceof PacienteRegular);
                            System.out.println();
                            System.out.println("Digite o nome do paciente: ");
                            String nomePacienteRegular = scanner.nextLine();
                            System.out.println("Digite o cpf do paciente: ");
                            String cpfPacienteRegular = scanner.nextLine();
                            System.out.println("Digite a data de nascimento do paciente: ");
                            String dataPacienteRegular = scanner.nextLine();
                            System.out.println("Digite o endereço do paciente: ");
                            String endereçoPacienteRegular = scanner.nextLine();
                            System.out.println("Digite o contato do paciente: ");
                            String contatoPacienteRegular = scanner.nextLine();
                            System.out.println("Digite o historico do paciente: ");
                            String historicoPacienteRegular = scanner.nextLine();
                            System.out.println("Digite o medico preferido do paciente: ");
                            String medicoPacienteRegular = scanner.nextLine();
                            System.out.println("Digite o plano de saúde do paciente: ");
                            String planoPacienteRegular = scanner.nextLine();
                            System.out.println("Digite o periodo do paciente: ");
                            String periodicidadePacienteRegular = scanner.nextLine();
                            System.out.println("Digite observações paciente: ");
                            String observacoesPacienteRegular = scanner.nextLine();
                            ((PacienteRegular) pacienteRegular).setInformacoes(
                                    nomePacienteRegular,
                                    cpfPacienteRegular,
                                    dataPacienteRegular,
                                    endereçoPacienteRegular,
                                    contatoPacienteRegular,
                                    historicoPacienteRegular,
                                    medicoPacienteRegular,
                                    planoPacienteRegular,
                                    periodicidadePacienteRegular,
                                    observacoesPacienteRegular);
                            
                            System.out.println(pacienteRegular.toString());
                            PacientePersistenteRegular p = new PacientePersistenteRegular();
                            p.executarOperacao(pacienteRegular);
                            break;
                        case 2:
                            System.out.println("Você escolheu Paciente VIP.");
                            Paciente pacienteVIP = PacienteFactory.criarPaciente("VIP");
                            System.out.println(pacienteVIP instanceof PacienteVIP);
                            System.out.println();
                            System.out.println("Digite o nome do paciente VIP: ");
                            String nomePacienteVIP = scanner.nextLine();
                            System.out.println("Digite o cpf do paciente VIP: ");
                            String cpfPacienteVIP = scanner.nextLine();
                            System.out.println("Digite a data de nascimento do paciente VIP: ");
                            String dataPacienteVIP = scanner.nextLine();
                            System.out.println("Digite o endereço do paciente VIP: ");
                            String endereçoPacienteVIP = scanner.nextLine();
                            System.out.println("Digite o contato do paciente VIP: ");
                            String contatoPacienteVIP = scanner.nextLine();
                            System.out.println("Digite os serviços adicionais do paciente VIP: ");
                            String servicosAdicionaisPacienteVIP = scanner.nextLine();
                            System.out.println("Digite o acesso de Especialistas do paciente VIP: ");
                            String acessoEspecialistasPacienteVIP = scanner.nextLine();

                            System.out.println("Digite o nível de prioridade do paciente VIP: ");
                            int nivelPrioridadePacienteVIP = scanner.nextInt();
                            ((PacienteVIP) pacienteVIP).setInformacoes(
                            		nomePacienteVIP,
                            		cpfPacienteVIP,
                            		dataPacienteVIP,
                            		endereçoPacienteVIP,
                            		contatoPacienteVIP,
                            		servicosAdicionaisPacienteVIP,
                            		acessoEspecialistasPacienteVIP,
                            		nivelPrioridadePacienteVIP);
                            
                            System.out.println(pacienteVIP.toString());
                            PacientePersistenteVIP pvip = new PacientePersistenteVIP();
                            pvip.executarOperacao(pacienteVIP);
                            break;
                        case 3:
                            System.out.println("Você escolheu Paciente Infantil.");
                            Paciente pacienteInfantil = PacienteFactory.criarPaciente("Infantil");
                            System.out.println(pacienteInfantil instanceof PacienteInfantil);
                            System.out.println();
                            System.out.println("Digite o nome do paciente Infantil: ");
                            String nomePacienteInfantil = scanner.nextLine();
                            System.out.println("Digite o cpf do paciente Infantil: ");
                            String cpfPacienteInfantil = scanner.nextLine();
                            System.out.println("Digite a data de nascimento do paciente Infantil: ");
                            String dataPacienteInfantil = scanner.nextLine();
                            System.out.println("Digite o endereço do paciente Infantil: ");
                            String endereçoPacienteInfantil = scanner.nextLine();
                            System.out.println("Digite o contato do paciente Infantil: ");
                            String contatoPacienteInfantil = scanner.nextLine();
                            System.out.println("Digite o nome do responsável do paciente Infantil: ");
                            String nomeResponsavelPacienteInfantil = scanner.nextLine();
                            System.out.println("Digite o contato do responsável do paciente Infantil: ");
                            String contatoResponsavelPacienteInfantil = scanner.nextLine();
                            System.out.println("Digite as informações pediátricas do paciente Infantil: ");
                            String informacoesPediatricasPacienteInfantil = scanner.nextLine();
                            System.out.println("Digite a escola do paciente Infantil: ");
                            String escolaPacienteInfantil = scanner.nextLine();
                            System.out.println("Digite a autorização de tratamentos do paciente Infantil: ");
                            boolean autorizacaoTratamentosPacienteInfantil = scanner.nextBoolean();
                            System.out.println("Digite o médico Pediatra preferencial do paciente Infantil: ");
                            String medicoPediatraPreferencialPacienteInfantil = scanner.nextLine();
                            ((PacienteInfantil) pacienteInfantil).setInformacoes(
                            		nomePacienteInfantil,
                            		cpfPacienteInfantil,
                            		dataPacienteInfantil,
                            		endereçoPacienteInfantil,
                            		contatoPacienteInfantil,
                            		nomeResponsavelPacienteInfantil,
                            		contatoResponsavelPacienteInfantil,
                            		informacoesPediatricasPacienteInfantil,
                            		escolaPacienteInfantil,
                            		autorizacaoTratamentosPacienteInfantil,
                            		medicoPediatraPreferencialPacienteInfantil);
                            
                            System.out.println(pacienteInfantil.toString());
                            PacientePersistenteInfantil pInfantil = new PacientePersistenteInfantil();
                            pInfantil.executarOperacao(pacienteInfantil);
                            break;
                            
                        default:
                            System.out.println("Opção inválida!");
                        }
                } else if(opcaoMenuCadastrar == 2){
                    // Solicitar informações do médico
                    scanner.nextLine();
                    System.out.print("Digite o CRM do médico: ");
                    String crm = scanner.nextLine();

                    System.out.print("Digite o nome do médico: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite a especialidade do médico: ");
                    String especialidade = scanner.nextLine();

                    System.out.print("Digite o telefone do médico: ");
                    String telefone = scanner.nextLine();

                    System.out.print("Digite o endereço do médico: ");
                    String endereco = scanner.nextLine();

                    System.out.print("Digite o email do médico: ");
                    String email = scanner.nextLine();

                    System.out.print("Digite a data de nascimento do médico (dd/MM/yyyy): ");
                    String dataNascimento = scanner.nextLine();

                    // Criar o objeto Medico com os dados fornecidos
                    Medico medico = new Medico(crm, nome, especialidade, telefone, endereco, email, dataNascimento);
                    MedicoPersistente medicoPersistente = new MedicoPersistente();
                    medicoPersistente.inserirMedico(medico);
                } else if( opcaoMenuCadastrar == 3 ){
                    // Solicitar informações do paciente
                    scanner.nextLine();
                    System.out.println("Informe o CPF do paciente:");
                    String cpfPaciente = scanner.nextLine();

                    // Solicitar informações do médico
                    System.out.println("Informe o CRM do médico:");
                    String crmMedico = scanner.nextLine();

                    // Solicitar informações da consulta
                    System.out.println("Informe a data da consulta (dd/MM/yyyy):");
                    String dataConsulta = scanner.nextLine();

                    System.out.println("Informe o motivo da consulta:");
                    String motivoConsulta = scanner.nextLine();

                    System.out.println("Informe observações sobre a consulta:");
                    String observacoesConsulta = scanner.nextLine();

                    // Criar um objeto consulta
                    Consulta consulta = new Consulta(cpfPaciente,crmMedico,dataConsulta,motivoConsulta,observacoesConsulta);
                    ConsultaPersistente consultaPersistente = ConsultaPersistente.getInstancia();
                    consultaPersistente.registrarConsulta(consulta);

                }
            } else if (opcaoMenuInicio == 2) {
                System.out.println(menuEntidades);
               int opcaoMenuAlterar = scanner.nextInt();
                scanner.nextLine();  // Consome o \n restante após nextInt()

                if (opcaoMenuAlterar == 1) {
                    PacienteManager manager = new PacienteManager();
                    System.out.println("Digite o CPF do paciente: ");
                    String cpf = scanner.nextLine();  // Agora lê corretamente a linha inteira para o CPF

                    // Chama o método para consultar e alterar os dados
                    manager.consultarEAlterarPaciente(cpf);
    }
    if (opcaoMenuAlterar == 2) {
                scanner.nextLine(); // Limpar o buffer
                System.out.print("Digite o CRM do médico que deseja atualizar: ");
                String crm = scanner.nextLine();

                System.out.print("Digite o novo nome do médico: ");
                String nome = scanner.nextLine();

                System.out.print("Digite a nova especialidade do médico: ");
                String especialidade = scanner.nextLine();

                System.out.print("Digite o novo telefone do médico: ");
                String telefone = scanner.nextLine();

                System.out.print("Digite o novo endereço do médico: ");
                String endereco = scanner.nextLine();

                System.out.print("Digite o novo email do médico: ");
                String email = scanner.nextLine();

                System.out.print("Digite a nova data de nascimento do médico (dd/MM/yyyy): ");
                String dataNascimento = scanner.nextLine();

                Medico medicoAtualizado = new Medico(crm, nome, especialidade, telefone, endereco, email, dataNascimento);
                MedicoPersistente medicoPersistente = new MedicoPersistente();
                medicoPersistente.atualizarMedico(crm, medicoAtualizado);
                }
            } else if(opcaoMenuInicio == 3){
                System.out.println(menuEntidades);
                int opcaoMenuEntidades = scanner.nextInt();
                if(opcaoMenuEntidades==1){
                scanner.nextLine();
                System.out.println("Digite o CPF do paciente a ser deletado: ");
                String cpf = scanner.nextLine();
                PacienteManager manager = new PacienteManager();
                manager.deletarPaciente(cpf);
                }
                else if(opcaoMenuEntidades==3){
                    scanner.nextLine();
                    System.out.print("Digite o CPF do paciente (somente números): ");
                    String pacienteCpf = scanner.nextLine();
                    System.out.print("Digite o CRM do médico: ");
                    String medicoCrm = scanner.nextLine();
                    System.out.print("Digite a data da consulta (formato: yyyy-mm-dd): ");
                    String dataConsulta = scanner.nextLine();
                    ConsultaPersistente consultaPersistente = ConsultaPersistente.getInstancia();
                    consultaPersistente.deletarConsulta(pacienteCpf, medicoCrm, dataConsulta);
                }
            } else if(opcaoMenuInicio == 4){
              System.out.println(menuEntidades);
              int opcaoEntidade = scanner.nextInt();
              scanner.nextLine();
              switch (opcaoEntidade) {
                    case 1:
                        System.out.println("1 - Regular");
                        System.out.println("2 - VIP");
                        System.out.println("3 - Infantil");
                        int opcaoTipoEntidade =  scanner.nextInt();
                        scanner.nextLine();
                        switch (opcaoTipoEntidade) {
                            case 1:
                                PacientePersistenteRegular pacientePersistente = new PacientePersistenteRegular();
                                pacientePersistente.executarConsulta();
                                break;
                            case 2:
                                PacientePersistenteVIP pacientePersistenteVIP = new PacientePersistenteVIP();
                                pacientePersistenteVIP.executarConsulta();
                                break;
                            case 3:
                                PacientePersistenteInfantil pacientePersistenteInfantil  = new PacientePersistenteInfantil();
                                pacientePersistenteInfantil .executarConsulta();
                                break;
                            default:
                                break;
                        }
                        
                        break;

                    case 2:
                        MedicoPersistente medicoPersistente = new MedicoPersistente();
                        medicoPersistente.consultarMedicos();
                        break;
                    case 3:
                        ConsultaPersistente consultaPersistente = ConsultaPersistente.getInstancia();
                        consultaPersistente.pesquisarConsultas(); 
                        break;
                  }
              

                } else if(opcaoMenuInicio == 5){
                  //Ver  
                } else if(opcaoMenuInicio == 6){
                  System.out.println("\nSaindo...");  
                }
            }
    }
}
