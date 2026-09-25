public class ServicoComPecas extends Servico{
    private String peca;
    private double valorPeca;
    
    public ServicoComPecas(String servico, double valorBase, String descricao, String peca, double valorPeca){
        super(servico, valorBase, descricao);
        this.peca = peca;
        this.valorPeca = valorPeca;
    }

    @Override public double getValorFinal(){
        return this.valorBase + this.valorPeca;
    }

    @Override public String getInfoServico(){
        String info = """
        - %s (R$ %.2f): 
            Valor Base: %.2f
            Peça: %s (R$ %.2f)
            Descrição: %s
        """.formatted(this.servico, this.getValorFinal(), this.valorBase, this.peca, this.valorPeca, this.descricao);

        return info;
    }

}