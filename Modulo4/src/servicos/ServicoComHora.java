package servicos;

import excecoes.*;
//VRFY: ServicoPorHora: atributos privados horasTrabalhadas (double) e valorHora (double), ambos maiores que zero. Preço final = valorBase + horasTrabalhadas × valorHora.

/**
 * Serviço cujo valor depende do tempo de atendimento em horas.
 * Calcula o total a partir do valor base e da quantidade de horas trabalhadas.
 */
public class ServicoComHora extends Servico{
    private double valorHora;
    private int horasTrabalhadas;

    /**
     * Cria um serviço cobrado por hora trabalhada.
     *
     * @param servico nome do serviço.
     * @param valorBase valor inicial do atendimento.
     * @param descricao observações do serviço.
     * @param valorHora valor cobrado por hora.
     * @param horasTrabalhadas quantidade de horas executadas.
     */
    public ServicoComHora(String servico, double valorBase, String descricao, double valorHora, int horasTrabalhadas) throws ValorInvalidoException{
        super(servico, valorBase, descricao);
        if (valorHora <= 0 || horasTrabalhadas <= 0)
        throw new ValorInvalidoException("Horas e valor da hora devem ser maiores que zero.");
        this.valorHora = valorHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    /**
     * Calcula o valor total considerando o valor base e as horas trabalhadas.
     *
     * @return custo final do serviço.
     */
    @Override public double getValorFinal(){
        return this.valorBase + (this.valorHora * horasTrabalhadas);
    }

    /**
     * Retorna a descrição detalhada do serviço por hora.
     *
     * @return texto com valor, quantidade de horas e descrição.
     */
    @Override public String getInfoServico(){
        String info = """
        - %s (R$ %.2f): 
            Tipo: Com Hora
            Valor Base: %.2f
            Horas trabalhadas(%.2f/hora): %d
            Descrição: %s    
        """.formatted(this.servico, this.getValorFinal(), this.valorBase, this.valorHora, this.horasTrabalhadas, this.descricao);

        return info;
    }
}