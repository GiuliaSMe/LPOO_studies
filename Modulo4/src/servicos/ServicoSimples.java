package servicos;

import excecoes.*;

public class ServicoSimples extends Servico{

    public ServicoSimples(String servico, double valorBase, String descricao ) throws ValorInvalidoException{
        super(servico, valorBase, descricao);
    }

    
    /**
     * Retorna uma descrição resumida do serviço.
     *
     * @return texto com nome, valor e descrição.
     */
    @Override public String getInfoServico(){
        String info = """
        - %s (R$ %.2f): 
            Tipo: Simples
            Descrição: %s    
        """.formatted(this.servico, this.valorBase, this.descricao);

        return info;
    }

    /**
     * Calcula o valor final do serviço para a ordem.
     *
     * @return valor final do serviço.
     */
    @Override public double getValorFinal(){
        return this.valorBase;
    }
}