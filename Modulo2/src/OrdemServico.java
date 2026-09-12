public class OrdemServico{
    private String cliente;
    public Veiculo veiculo;
    private Servico[] servicosPrestados = new Servico[6];
    private int contadorServicos = 0; //somente para garantir que o novo item no array de ordens seja sempre o seguinte, para evitar que alguma ordem seja sobrescrita

    public OrdemServico(String cliente, Veiculo veiculo){
        this.cliente = cliente;
        this.veiculo = veiculo;
    }
 
    public void addServico(Servico servico){
        this.servicosPrestados[contadorServicos++] = servico;
    }

    public void showResumoOrdemServico(){
        String resumo = """
            === RESUMO DE ORDEM ===
            Cliente: %s
            Veículo: %s

            Total: R$ %.2f

            Serviços:
            %s
            """.formatted(this.cliente, this.veiculo.getVeiculo(), this.calcValorTotal(),this.showServicosAssociados());

        System.out.print(resumo);
    }

    //TODO: decidir oq fazer com isso
    /*
    public void aplicarDesconto(int percentual){
        if (percentual>100){
            System.out.println("\nDesconto inválido!");
            return;
        }
        double desconto = this.valorServico * (percentual/100);
        this.valorServico -= desconto;
    }
     */
    

    public String showServicosAssociados(){
        String textoServicosPrestados = "";

        for (Servico services : this.servicosPrestados){
            if (services != null){
                textoServicosPrestados += services.showInfoServico();
            }
        }

        return textoServicosPrestados;
    }

    public double calcValorTotal(){
        double valorTotal = 0;

        for (Servico services : this.servicosPrestados){
            if (services != null ){
                valorTotal += services.getValor();
            }
        }

        return valorTotal;
    }
}
