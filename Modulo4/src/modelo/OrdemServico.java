package modelo;
/*TODO:
Crie a classe OrdemServico com atributos privados: numero (int), nomeCliente (String), veiculo
(Veiculo), servicos (Servico[]), quantidadeServicos (int), percentualDesconto (double) e estado
(ABERTA ou FINALIZADA; pode usar enum ou String). 
[ ]No construtor, receba número, cliente, veículo e capacidade máxima de serviços; 
[ ]crie internamente o array. 
[ ]O número deve ser positivo e único no gerenciador; 
[ ]nome não pode ser vazio; 
[ ]veículo não pode ser nulo;
[ ]capacidade deve ser maior que zero. 
 */

import servicos.Servico;
import excecoes.*;
import interfaces.*;

/**
 * Representa uma ordem de serviço cadastrada na oficina.
 * Agrupa o cliente, o veículo e os serviços executados.
 */
public class OrdemServico implements Descontavel{
    private enum Estado{
        Aberta,
        Finalizada
    };

    private String cliente;
    private Veiculo veiculo;
    private int numero;
    private double percentualDesconto = 0; //TODO: entrar com essa merda em algum lugar
    private Estado statusOrdem;
    private int quantidadeMaxServicos;
    private Servico[] servicosPrestados;
    private int contadorServicos = 0; //somente para garantir que o novo item no array de ordens seja sempre o seguinte, para evitar que alguma ordem seja sobrescrita

    /**
     * Cria uma ordem de serviço vinculada a um cliente e veículo específicos.
     *
     * @param cliente nome do cliente atendido.
     * @param veiculo veículo relacionado ao atendimento.
     */
    public OrdemServico(String cliente, Veiculo veiculo, int quantidadeMaxServicos)throws ValorInvalidoException{
        if (cliente == null || cliente.trim().isEmpty()){
            throw new ValorInvalidoException("O nome do cliente não pode ser vazio.");}
        if (veiculo == null){
            throw new ValorInvalidoException("O veículo não pode ser nulo.");}
        if (quantidadeMaxServicos <= 0){
            throw new ValorInvalidoException("A capacidade de serviços deve ser maior que zero.");}
        this.cliente = cliente.trim();
        this.veiculo = veiculo;
        this.quantidadeMaxServicos = quantidadeMaxServicos;
        this.servicosPrestados = new Servico[this.quantidadeMaxServicos];
        this.statusOrdem = Estado.Aberta;
    }

    /**
     * Adiciona um serviço à lista de atendimentos da ordem se a ordem ainda estiver aberta.
     *
     * @param servico serviço prestado a ser registrado nao nulo.
     */
    public void addServico(Servico servico)throws ValorInvalidoException, OrdemFinalizadaException, CapacidadeExcedidaException{
        if (statusOrdem == Estado.Finalizada)
            throw new OrdemFinalizadaException("Ordem finalizada não aceita novos serviços.");
        if (servico == null)
            throw new ValorInvalidoException("O serviço não pode ser nulo.");
        if (contadorServicos == servicosPrestados.length)
            throw new CapacidadeExcedidaException("Capacidade máxima de serviços atingida.");
        
        this.servicosPrestados[contadorServicos++] = servico;
    }

    /**
     * Retorna a placa do veículo associado à ordem.
     * @return placa do veículo.
     */
    public String getPlacaOrdem(){return this.veiculo.getPlaca();}

    /**
     * Retorna o nome do cliente cadastrado na ordem.
     * @return nome do cliente.
     */
    public String getClienteOrdem(){return this.cliente;}

    /**
     * Retorna o numero cadastrado na ordem.
     * @return numero da ordem salva.
     */
    public int getNumeroOrdem(){return this.numero;}

    /**
     * Produz um resumo textual completo da ordem com cliente, veículo e total.
     * @return informações formatadas da ordem de serviço.
     */
    public String getResumoOrdemServico(){
        String resumo = """

            === RESUMO DA ORDEM Nº %d ===
            Cliente: %s
            Veículo: %s
            Estado: %s

            Subtotal: R$ %.2f
            Total (Desconto de %.2f%%): R$ %.2f

                = SERVIÇOS =
            %s
            """.formatted(
                this.numero, 
                this.cliente, 
                this.veiculo.getVeiculo(), 
                this.statusOrdem,
                this.calcSubTotal(),
                this.percentualDesconto,
                this.calcValorTotal(),
                this.getServicosAssociados());

        return resumo;
    }
    
    /**
     * Consolida a descrição de todos os serviços vinculados à ordem.
     * @return texto com os serviços associados.
     */
    public String getServicosAssociados(){
        String textoServicosPrestados = "";

        for (Servico services : this.servicosPrestados){
            if (services != null){
                textoServicosPrestados += services.getInfoServico();
            }
        }

        return textoServicosPrestados;
    }

    /**
     * Calcula o valor total de todos os serviços registrados na ordem.
     *
     * @return soma total dos serviços prestados.
     */
    public double calcSubTotal(){
        double valorTotal = 0;

        for (Servico services : this.servicosPrestados){
            if (services != null ){
                valorTotal += services.getValorFinal();
            }
        }
        return valorTotal;
    }

    /**
     * Aplica desconto ao valor total de todos os serviços registrados na ordem.
     *
     * @return valor final dos serviços prestados com desconto aplicado.
     */
    public double calcValorTotal(){
        double subtotal = calcSubTotal();
        double valorFinal;

        if (percentualDesconto != 0){valorFinal = subtotal - (subtotal*(percentualDesconto/100));}
        else{valorFinal = subtotal;}
        
        return valorFinal;
    }

    @Override public double aplicarDesconto(double percentual)
            throws ValorInvalidoException, OrdemFinalizadaException{

        if (statusOrdem == Estado.Finalizada) {
            throw new OrdemFinalizadaException("Ordem finalizada não aceita alteração de desconto.");
        }
        if (percentual < 0 || percentual > 100) {
            throw new ValorInvalidoException("O desconto deve estar entre 0 e 100.");
        }
        this.percentualDesconto = percentual;
        return calcValorTotal();
    }

    public void setNumero(int numero){this.numero = numero;}
    public void finalizar()throws OrdemVaziaException{
        if (contadorServicos == 0)
            throw new OrdemVaziaException("Não é possível finalizar uma ordem sem serviços.");
        this.statusOrdem = Estado.Finalizada;}
}
