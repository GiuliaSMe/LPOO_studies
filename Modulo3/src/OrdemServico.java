public class OrdemServico{
    private String cliente;
    private Veiculo veiculo;
    private Servico[] servicosPrestados = new Servico[15];
    private int contadorServicos = 0; //somente para garantir que o novo item no array de ordens seja sempre o seguinte, para evitar que alguma ordem seja sobrescrita

    public OrdemServico(String cliente, Veiculo veiculo){
        this.cliente = cliente;
        this.veiculo = veiculo;
    }
 
    public void addServico(Servico servico){
        this.servicosPrestados[contadorServicos++] = servico;
    }

    public String getPlacaOrdem(){
        return this.veiculo.getPlaca();
    }

    public String getClienteOrdem(){
        return this.cliente;
    }

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
    
    public String getServicosAssociados(){
        String textoServicosPrestados = "";

        for (Servico services : this.servicosPrestados){
            if (services != null){
                textoServicosPrestados += services.getInfoServico();
            }
        }

        return textoServicosPrestados;
    }

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
