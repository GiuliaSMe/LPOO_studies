/**
 * Representa um serviço genérico prestado pela oficina.
 * Serve como base para tipos específicos de atendimento.
 */
public class Servico{
    protected String descricao;
    protected double valorBase;
    protected  String servico;

    /**
     * Cria um serviço básico com descrição e valor inicial.
     *
     * @param servico nome do serviço realizado.
     * @param valorBase valor base do serviço.
     * @param descricao observações sobre o atendimento.
     */
    public Servico(String servico, double valorBase, String descricao){
        this.servico = servico;
        this.valorBase = valorBase;
        this.descricao = descricao;
    }

    /**
     * Retorna uma descrição resumida do serviço.
     *
     * @return texto com nome, valor e descrição.
     */
    public String getInfoServico(){
        String info = """
        - %s (R$ %.2f): 
            Descrição: %s    
        """.formatted(this.servico, this.valorBase, this.descricao);

        return info;
    }

    /**
     * Calcula o valor final do serviço para a ordem.
     *
     * @return valor final do serviço.
     */
    public double getValorFinal(){
        return this.valorBase;
    }
}