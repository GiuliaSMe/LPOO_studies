public class OrdemServico{
    private String cliente;
    private String descricao;
    private double valorServico;
    public Veiculo veiculo;

    public OrdemServico(String cliente, String descricao, double valorServico, Veiculo veiculo){
        this.cliente = cliente;
        this.descricao = descricao;
        this.valorServico = valorServico;
        this.veiculo = veiculo;
    }

    public void exibirResumo(){
        System.out.print("\nNome do cliente: "+ this.cliente + "\nDescrição: "+ this.descricao + "\nValor do serviço: "+ this.valorServico + "\nVeículo do serviço: ");
        this.veiculo.exibirInfo();
    }

    public void aplicarDesconto(int percentual){
        if (percentual>100){
            System.out.println("\nDesconto inválido!");
            return;
        }
        double desconto = this.valorServico * (percentual/100);
        this.valorServico -= desconto;
    }
}
