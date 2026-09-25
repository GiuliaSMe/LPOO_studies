/**
 * Representa uma ordem de serviço cadastrada na oficina.
 * Agrupa o cliente, o veículo e os serviços executados.
 */
public class OrdemServico{
    private String cliente;
    private Veiculo veiculo;
    private Servico[] servicosPrestados = new Servico[15];
    private int contadorServicos = 0; //somente para garantir que o novo item no array de ordens seja sempre o seguinte, para evitar que alguma ordem seja sobrescrita

    /**
     * Cria uma ordem de serviço vinculada a um cliente e veículo específicos.
     *
     * @param cliente nome do cliente atendido.
     * @param veiculo veículo relacionado ao atendimento.
     */
    public OrdemServico(String cliente, Veiculo veiculo){
        this.cliente = cliente;
        this.veiculo = veiculo;
    }
 
    /**
     * Adiciona um serviço à lista de atendimentos da ordem.
     *
     * @param servico serviço prestado a ser registrado.
     */
    public void addServico(Servico servico){
        this.servicosPrestados[contadorServicos++] = servico;
    }

    /**
     * Retorna a placa do veículo associado à ordem.
     *
     * @return placa do veículo.
     */
    public String getPlacaOrdem(){
        return this.veiculo.getPlaca();
    }

    /**
     * Retorna o nome do cliente cadastrado na ordem.
     *
     * @return nome do cliente.
     */
    public String getClienteOrdem(){
        return this.cliente;
    }

    /**
     * Produz um resumo textual completo da ordem com cliente, veículo e total.
     *
     * @return informações formatadas da ordem de serviço.
     */
    public String getResumoOrdemServico(){
        String resumo = """

            === RESUMO DE ORDEM ===
            Cliente: %s
            Veículo: %s

            Total: R$ %.2f

                = SERVIÇOS =
            %s
            """.formatted(this.cliente, this.veiculo.getVeiculo(), this.calcValorTotal(),this.getServicosAssociados());

        return resumo;
    }
    
    /**
     * Consolida a descrição de todos os serviços vinculados à ordem.
     *
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
    public double calcValorTotal(){
        double valorTotal = 0;

        for (Servico services : this.servicosPrestados){
            if (services != null ){
                valorTotal += services.getValorFinal();
            }
        }

        return valorTotal;
    }
}
