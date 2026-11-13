package servicos;

import excecoes.*;

//VRFY: ServicoComPecas: atributo privado valorPecas (double), não negativo. Preço final = valorBase + valorPecas.

/**
 * Serviço que inclui custo de uma peça utilizada no atendimento.
 * Soma o valor da peça ao valor base do serviço.
 */
public class ServicoComPecas extends Servico{
    private String peca;
    private double valorPeca;
    
    /**
     * Cria um serviço com peça adicional.
     *
     * @param servico nome do serviço.
     * @param valorBase valor inicial do serviço.
     * @param descricao observações do atendimento.
     * @param peca nome da peça empregada.
     * @param valorPeca custo da peça utilizada.
     */
    public ServicoComPecas(String servico, double valorBase, String descricao, String peca, double valorPeca) throws ValorInvalidoException{
        super(servico, valorBase, descricao);
        if (valorPeca < 0)
            throw new ValorInvalidoException("O valor da peça não pode ser negativo.");
        this.peca = peca;
        this.valorPeca = valorPeca;
    }

    /**
     * Calcula o valor final somando o custo base e o valor da peça.
     *
     * @return valor total do serviço com peça.
     */
    @Override public double getValorFinal(){
        return this.valorBase + this.valorPeca;
    }

    /**
     * Retorna a descrição detalhada do serviço com peça.
     *
     * @return texto com peça, valor e descrição.
     */
    @Override public String getInfoServico(){
        String info = """
        - %s (R$ %.2f): 
            Tipo: Com Peças
            Valor Base: %.2f
            Peça: %s (R$ %.2f)
            Descrição: %s
        """.formatted(this.servico, this.getValorFinal(), this.valorBase, this.peca, this.valorPeca, this.descricao);

        return info;
    }

}