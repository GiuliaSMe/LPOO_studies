public class Servico{
    private String descricao;
    private double valor;
    private String[] opcaoServicos = {
        "troca de óleo",
        "alinhamento",
        "balanceamento",
        "troca de pastilhas de freio",
        "revisão elétrica"
    };
    private String servico;

    public Servico(int opcao, double valor, String descricao){
        this.servico = opcaoServicos[opcao];
        this.valor = valor;
        this.descricao = descricao;
    }

    public String showInfoServico(){
        String info = """
        - %s (R$ %.2f): %s    
        """.formatted(this.servico, this.valor, this.descricao);

        return info;
    }

    public double getValor(){
        return this.valor;
    }
}