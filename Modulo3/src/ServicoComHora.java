public class ServicoComHora extends Servico{
    private double valorHora;
    private int horasTrabalhadas;

    public ServicoComHora(String servico, double valorBase, String descricao, double valorHora, int horasTrabalhadas){
        super(servico, valorBase, descricao);
        this.valorHora = valorHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    @Override public double getValorFinal(){
        return this.valorBase + (this.valorHora * horasTrabalhadas);
    }

    public String getInfoServico(){
        String info = """
        - %s (R$ %.2f): 
            Valor Base: %.2f
            Horas trabalhadas(%.2f/hora): %d
            Descrição: %s    
        """.formatted(this.servico, this.getValorFinal(), this.valorBase, this.valorHora, this.horasTrabalhadas, this.descricao);

        return info;
    }
}