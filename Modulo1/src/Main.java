public class Main{
    public static void main(String[] args){
        Veiculo Carro1 = new Veiculo("qwe-1234", "renegade", 2021);
        Veiculo Carro2 = new Veiculo("qwu-1234", "minicooper", 2011);

        OrdemServico Servico1 = new OrdemServico("Joao", "troca do óleo", 100.00, Carro1);

        Servico1.exibirResumo();

        Carro1.calcularIdade(2026);

        Servico1.aplicarDesconto(10);

        Servico1.exibirResumo();
        
        Servico1.veiculo = Carro2;
        
        Servico1.exibirResumo();
        
        Servico1.aplicarDesconto(150);
        Servico1.exibirResumo();
    }
}
