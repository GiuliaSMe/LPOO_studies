public class Servico{
    protected String descricao;
    protected double valorBase;
    protected  String servico;

    public Servico(String servico, double valorBase, String descricao){
        this.servico = servico;
        this.valorBase = valorBase;
        this.descricao = descricao;
    }

    public String getInfoServico(){
        String info = """
        - %s (R$ %.2f): 
            Descrição: %s    
        """.formatted(this.servico, this.valorBase, this.descricao);

        return info;
    }

    public double getValorFinal(){
        return this.valorBase;
    }
}