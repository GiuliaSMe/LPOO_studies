package servicos;
/* TODO
Servico: classe abstrata com atributos privados descricao (String) e valorBase (double); construtor
e métodos de acesso necessários. Descrição não pode ser vazia e valor base deve ser maior que
zero. Defina os métodos abstratos:
[ ]public abstract double calcularPrecoFinal();
[ ]public abstract void exibirInformacoes();
Não é permitido instanciar diretamente Servico. Os preços são expressos em reais e os cálculos
devem utilizar as regras das subclasses, sem testes manuais de tipo.
 */

import excecoes.ValorInvalidoException;

/**
 * Representa um serviço genérico prestado pela oficina.
 * Serve como base para tipos específicos de atendimento.
 */
public abstract class Servico{
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
    public Servico(String servico, double valorBase, String descricao)throws ValorInvalidoException{
        if (servico == null || servico.trim().isEmpty())
            throw new ValorInvalidoException("O nome do serviço não pode ser vazio.");
        if (descricao == null || descricao.trim().isEmpty())
            throw new ValorInvalidoException("A descrição não pode ser vazia.");
        if (valorBase <= 0)
            throw new ValorInvalidoException("O valor base deve ser maior que zero.");

        this.servico = servico;
        this.valorBase = valorBase;
        this.descricao = descricao;
    }

    public String getServico(){return servico;}
    public String getDescricao(){return descricao;}
    public double getValorBase(){return valorBase;}
    public abstract String getInfoServico();
    public abstract double getValorFinal();
}