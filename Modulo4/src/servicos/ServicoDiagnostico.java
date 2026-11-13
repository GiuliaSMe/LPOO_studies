package servicos;

import excecoes.*;
/*VRFY
ServicoDiagnostico: atributo privado possuiReparo (boolean). Se verdadeiro, aplica desconto
de 50% ao valor base; caso contrário, cobra o valor base integral. Todas as subclasses devem
possuir construtores, encapsular seus atributos, utilizar super(...) e implementar
calcularPrecoFinal() e exibirInformacoes() com @Override. O resumo de cada serviço deve
mostrar descrição, tipo e preço calculado.
 */

/**
 * Serviço de diagnóstico com a possibilidade de desconto quando há reparo.
 * O valor final pode ser reduzido quando o diagnóstico resulta em manutenção.
 */
public class ServicoDiagnostico extends Servico{
    private boolean possuiReparo;
    private double valorDiagnostico;

    /**
     * Cria um serviço de diagnóstico com indicação de reparo.
     *
     * @param servico nome do serviço.
     * @param valorBase valor base do diagnóstico.
     * @param descricao observações do atendimento.
     * @param possuiReparo resposta do usuário indicando se houve reparo.
     * @param valorDiagnostico custo do diagnóstico realizado.
     */
    public ServicoDiagnostico(String servico, double valorBase, String descricao, boolean possuiReparo, double valorDiagnostico) throws ValorInvalidoException{
        super(servico, valorBase, descricao);
        if (valorDiagnostico < 0)
            throw new ValorInvalidoException("O valor do diagnóstico não pode ser negativo.");
        this.possuiReparo = possuiReparo;
        this.valorDiagnostico = valorDiagnostico;
    }

    /**
     * Calcula o valor final aplicando desconto quando houver reparo.
     *
     * @return custo total do diagnóstico com ajuste de reparo.
     */
    @Override public double getValorFinal(){
        //evita modificar a variável original
        double valorDiagnosticoCalculo = this.valorDiagnostico; 

        if (this.possuiReparo){
            valorDiagnosticoCalculo *= 0.5;
        }

        return this.valorBase + valorDiagnosticoCalculo;
    }

    /**
     * Retorna a descrição detalhada do diagnóstico e do status de reparo.
     *
     * @return texto do serviço com indicação de reparo.
     */
    @Override public String getInfoServico(){

        String reparo = "";

        if (possuiReparo){
            String textoReparo = "Sim (Diagnóstico 50%off)";
            reparo = textoReparo;
        }
        else{
            String textoReparo = "Não";
            reparo = textoReparo;
        }

        String info = """
        - %s (R$ %.2f):
            Tipo: Diagnóstico
            Valor Base: R$ %.2f
            Reparo: %s
            Descrição: %s 
        """.formatted(this.servico, this.getValorFinal(), this.valorBase, reparo, this.descricao);

        return info;
    }
} 