package ui;
import java.util.InputMismatchException;
import java.util.Scanner;

import excecoes.*;
import gerenciadores.GerenciadorOrdens;
import modelo.*;
import servicos.*;

/**
 * Responsável pela interação do usuário com o sistema da oficina.
 * Reúne os fluxos de cadastro, menu e consultas de ordens de serviço.
 */
public class GerenciadorUI{

    //* =========== entrada de valores simplificada ===========

    private String lerTexto(Scanner scanner, String mensagem){
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    /** Repete a pergunta até o usuário digitar um inteiro válido. */
    private int lerInt(Scanner scanner, String mensagem){
        while (true) {
            System.out.print(mensagem);
            try {
                int numero = scanner.nextInt();
                scanner.nextLine();   // limpa o Enter que sobrou no buffer
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.nextLine();   // descarta o texto inválido
            }
        }
    }

    /** Repete a pergunta até o usuário digitar um número válido (aceita vírgula). */
    private double lerDouble(Scanner scanner, String mensagem) {
        
        while (true) {
            System.out.print(mensagem);
            try {
                double numero = scanner.nextDouble();
                scanner.nextLine();   // limpa o Enter que sobrou no buffer
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.nextLine();   // descarta o texto inválido
            }
        }
        
    }

    /** Repete a pergunta até o usuário responder s ou n. */
    private boolean lerSimNao(Scanner scanner, String mensagem) {
        while (true) {
            String resposta = lerTexto(scanner, mensagem).trim();
            if (resposta.equalsIgnoreCase("s")){return true;}
            if (resposta.equalsIgnoreCase("n")){return false;}
            System.out.println("Digite 's' para sim ou 'n' para não.");
        }
    }

    //* ==================== menu ====================
    /**
    * Exibe o menu principal da oficina e lê a opção escolhida.
    * @param scanner entrada de dados do usuário.
    * @return número correspondente à ação selecionada.
    */
    public int menu(Scanner scanner){
        String textoMenu = """
            \n
            ================ OFICINA BOX 42 ================
            ===================== MENU =====================

            0. Criar ordem de serviço
            1. Adicionar serviço à ordem
            2. Listar ordens
            3. Buscar ordens por cliente
            4. Buscar ordens por placa
            5. Aplicar desconto
            6. Finalizar ordem
            7. Sair
            
        Escolha uma opção: """; 
        
        int escolhaOpcaoMenu = lerInt(scanner, textoMenu);
        
        return escolhaOpcaoMenu;
    }

        /**
         * Executa a ação correspondente à opção escolhida no menu principal.
         * @param escolha opção informada pelo usuário.
         * @param scanner entrada de dados do usuário.
         * @param gerenciadorOrdens estrutura responsavel por armazenar as ordens.
         */
        public void escolhaMenu(int escolha, Scanner scanner, GerenciadorOrdens gerenciadorOrdens){
            try {
                switch (escolha){
                    case 0:
                        cadastroOrdemServico(scanner, gerenciadorOrdens);
                        break;
                    case 1:
                        adicionarServicoNaOrdem(scanner, gerenciadorOrdens);
                        break;
                    case 2:
                        System.out.print(gerenciadorOrdens.getOrdensServico());
                        break;
                    case 3:
                        buscaPorCliente(scanner, gerenciadorOrdens);
                        break;
                    case 4:
                        buscaPorPlaca(scanner, gerenciadorOrdens);
                        break;
                    case 5:
                        aplicarDescontoNaOrdem(scanner, gerenciadorOrdens);
                        break;
                    case 6:
                        finalizarOrdem(scanner, gerenciadorOrdens);
                        break;
                    case 7:
                        System.out.println("Saindo... ");
                        break;
                    default:
                        System.out.println("\nOpção inválida");
                        break;
                }
            }
            catch(ValorInvalidoException | OrdemFinalizadaException | OrdemVaziaException | CapacidadeExcedidaException e){
                System.out.println("\nErro: " + e.getMessage());
            }
        }

    //* ================= cadastros =================

    /**
     * Solicita e retorna o nome do cliente informado no terminal.
     *
     * @param scanner entrada de dados do usuário.
     * @return nome cadastrado do cliente.
     */
    public String cadastroCliente(Scanner scanner){
        String cliente = lerTexto(scanner, "\nNome do Cliente: ");
        return cliente;
    }

    /**
     * Coleta os dados do veículo e instancia um objeto Veiculo.
     *
     * @param scanner entrada de dados do usuário.
     * @return veículo preenchido com placa, modelo e ano.
    */
    public Veiculo cadastroVeiculo(Scanner scanner){

        String placa = lerTexto(scanner, "\nPlaca: ");
        String modelo = lerTexto(scanner, "Modelo do Veiculo: ");
        int ano = lerInt(scanner, "Ano: ");

        try {
            return new Veiculo(placa, modelo, ano);
        }
        catch(ValorInvalidoException e){
            System.out.println("\nErro: " + e.getMessage());
            return cadastroVeiculo(scanner);
        }
    }


    /**
     * Cria uma ordem de serviço completa a partir dos dados do cliente, veículo e serviços.
     * @param scanner entrada de dados do usuário.
     * @param GerenciadorOrdens onde as ordens sao guardadas
     * @return ordem finalizada com os serviços informados.
     */
    public void cadastroOrdemServico(Scanner scanner, GerenciadorOrdens gerenciadorOrdens)
        throws ValorInvalidoException, CapacidadeExcedidaException {
        System.out.println("\n============= NOVA ORDEM DE SERVIÇO =============");
        String cliente = cadastroCliente(scanner);
        Veiculo veiculo = cadastroVeiculo(scanner);
        int capacidade = lerInt(scanner, "Capacidade máxima de serviços: ");

        OrdemServico ordem = new OrdemServico(cliente, veiculo, capacidade);
        gerenciadorOrdens.addOrdem(ordem);
        System.out.println("\nOrdem criada com sucesso. Número da ordem: " + ordem.getNumeroOrdem());
    }

    /** Pede o número da ordem e devolve a ordem, ou null (com aviso) se não existir. */
    private OrdemServico selecionarOrdem(Scanner scanner, GerenciadorOrdens gerenciadorOrdens) {
        int numero = lerInt(scanner, "\nNúmero da ordem: ");
        OrdemServico ordem = gerenciadorOrdens.buscarPorNumero(numero);
        if (ordem == null) {
            System.out.println("Ordem não encontrada.");
        }
        return ordem;
    }

    public void adicionarServicoNaOrdem(Scanner scanner, GerenciadorOrdens gerenciadorOrdens)throws ValorInvalidoException, OrdemFinalizadaException, CapacidadeExcedidaException{
        OrdemServico ordem = selecionarOrdem(scanner, gerenciadorOrdens);
        if (ordem == null) return;

        Servico servico = cadastroServico(scanner);
        if (servico == null) return; // tipo inválido, já avisado

        ordem.addServico(servico);
        System.out.println("\nServiço adicionado com sucesso.");
    }

    public void aplicarDescontoNaOrdem(Scanner scanner, GerenciadorOrdens gerenciadorOrdens) throws ValorInvalidoException, OrdemFinalizadaException{
        OrdemServico ordem = selecionarOrdem(scanner, gerenciadorOrdens);
        if (ordem == null) return;

        double percentual = lerDouble(scanner, "Percentual de desconto (0-100): ");
        double novoTotal = ordem.aplicarDesconto(percentual);
        System.out.printf("%nDesconto aplicado. Novo total: R$ %.2f%n", novoTotal);
    }

    public void finalizarOrdem(Scanner scanner, GerenciadorOrdens gerenciadorOrdens) throws OrdemVaziaException{

        OrdemServico ordem = selecionarOrdem(scanner, gerenciadorOrdens);
        if (ordem == null) return;

        ordem.finalizar();
        System.out.println("\nOrdem finalizada com sucesso.");
    }

    /**
     * Solicita o tipo de serviço que será registrado e chama o cadastro específico.
     * @param scanner entrada de dados do usuário.
     * @return serviço instanciado de acordo com a escolha.
     */
    public Servico cadastroServico(Scanner scanner){
        String textoOpcoesServicos = """
        \n
        ================ TIPOS DE SERVIÇO ================

            0. Serviço Simples;
            1. Serviço com Peça;
            2. Serviço com Horas;
            3. Serviço de Diagnóstico;

        Escolha uma opção: """;


        int escolhaOpcaoTipoServico = lerInt(scanner, textoOpcoesServicos);        
        Servico novoServico = escolhaTipoServico(escolhaOpcaoTipoServico, scanner);

        return novoServico;
    }

        /**
         * Direciona a criação do serviço conforme a opção escolhida.
         * @param escolha código do tipo de serviço.
         * @param scanner entrada de dados do usuário.
         * @return novo serviço cadastrado.
         */
        public Servico escolhaTipoServico(int escolha, Scanner scanner){
            try{
                switch (escolha){
                    case 0:
                        return cadastroServicoSimples(scanner);
                    case 1:
                        return cadastroServicoComPeca(scanner);
                    case 2:
                        return cadastroServicoComHora(scanner);
                    case 3:
                        return cadastroServicoDiagnostico(scanner);
                    default:
                        System.out.print("\nOpção inválida");
                        return cadastroServico(scanner);
                        
                }
            }
            catch(ValorInvalidoException e){
                System.out.println("\nErro: " + e.getMessage());
                return cadastroServico(scanner);
            }
        }

    /**
     * Coleta dados de um serviço simples e cria a instância correspondente.
     *
     * @param scanner entrada de dados do usuário.
     * @return serviço com descrição e valor base.
     */
    public Servico cadastroServicoSimples(Scanner scanner) throws ValorInvalidoException{

        String servico = lerTexto(scanner, "\nServiço Realizado: ");        
        double valorBase = lerDouble(scanner, "Valor Cobrado: R$ ");
        String descricao = lerTexto(scanner, "Descrição: ");

        return new ServicoSimples(servico, valorBase, descricao);
    }

    /**
     * Coleta dados de um serviço que envolve peças e cria a instância correspondente.
     *
     * @param scanner entrada de dados do usuário.
     * @return serviço com valor de peça e descrição.
     */
    public Servico cadastroServicoComPeca(Scanner scanner) throws ValorInvalidoException{

        String servico = lerTexto(scanner, "\nServiço Realizado: ");
        double valorBase = lerDouble(scanner, "Valor Base: R$ ");
        String peca = lerTexto(scanner, "Peça Utilizada: ");
        double valorPeca = lerDouble(scanner, "Valor da Peça: R$");
        String descricao = lerTexto(scanner, "Descrição: ");

        return new ServicoComPecas(servico, valorBase, descricao, peca, valorPeca);
    }

    /**
     * Coleta dados de um serviço cobrado por hora de trabalho.
     *
     * @param scanner entrada de dados do usuário.
     * @return serviço com cálculo por tempo e valor por hora.
     */
    public Servico cadastroServicoComHora(Scanner scanner) throws ValorInvalidoException{

        String servico = lerTexto(scanner, "\nServiço Realizado: ");
        double valorBase = lerDouble(scanner, "Valor Base: R$ ");
        double valorHora = lerDouble(scanner, "Valor da Hora: R$ ");
        int horasTrabalhadas = lerInt(scanner, "Horas Trabalhadas: ");
        String descricao = lerTexto(scanner, "Descrição: ");

        return new ServicoComHora(servico, valorBase, descricao, valorHora, horasTrabalhadas);
    }

    /**
     * Coleta dados de um serviço de diagnóstico e cria a instância correspondente.
     *
     * @param scanner entrada de dados do usuário.
     * @return serviço de diagnóstico com informação de reparo.
     */
    public Servico cadastroServicoDiagnostico(Scanner scanner) throws ValorInvalidoException{

        String servico = lerTexto(scanner, "\nServiço Realizado: ");
        double valorBase = lerDouble(scanner, "Valor Base: R$");
        double valorDiagnostico = lerDouble(scanner, "Valor do Diagnóstico: R$ ");
        boolean possuiReparo = lerSimNao(scanner, "Houve reparo? (s/n): ");
        String descricao = lerTexto(scanner, "Descrição: ");

        return new ServicoDiagnostico(servico, valorBase, descricao, possuiReparo, valorDiagnostico);
    }

    

    //* ==================== buscas ====================
    /**
     * Busca ordens pela placa informada pelo usuário.
     *
     * @param scanner entrada de dados do usuário.
     * @param gerenciadorOrdens referência ao armazenamento central.
     */
    public void buscaPorPlaca(Scanner scanner, GerenciadorOrdens gerenciadorOrdens){

        String placa = lerTexto(scanner, "\nPlaca: ");

        String resultado = gerenciadorOrdens.findPorPlaca(placa);
        System.out.println(resultado);
    }

    /**
     * Busca ordens pelo nome do cliente informado pelo usuário.
     *
     * @param scanner entrada de dados do usuário.
     * @param gerenciadorOrdens referência ao armazenamento central.
     */
    public void buscaPorCliente(Scanner scanner, GerenciadorOrdens gerenciadorOrdens) throws ValorInvalidoException{
        String cliente = lerTexto(scanner, "\nNome do Cliente: ");

        String resultado = gerenciadorOrdens.findPorInicioDoNome(cliente);
        System.out.println(resultado);
    }

}