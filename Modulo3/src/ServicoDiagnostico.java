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
    public ServicoDiagnostico(String servico, double valorBase, String descricao, String possuiReparo, double valorDiagnostico){
        super(servico, valorBase, descricao);
        setPossuiReparo(possuiReparo);
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
     * Define se o diagnóstico resultou em reparo para aplicar o desconto.
     *
     * @param opcao resposta do usuário em formato s/n.
     */
    public void setPossuiReparo(String opcao){
        if (opcao.equalsIgnoreCase("s")){
            possuiReparo = true;
        }
        else if (opcao.equalsIgnoreCase("n")){
            possuiReparo = false;
        }
        else{
            System.out.print("\nOpção inválida");
            possuiReparo = false;
        }
            
        
    }

    /**
     * Retorna a descrição detalhada do diagnóstico e do status de reparo.
     *
     * @return texto do serviço com indicação de reparo.
     */
    public String getInfoServico(){

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
            Valor Base: R$ %.2f
            Reparo: %s
            Descrição: %s 
        """.formatted(this.servico, this.getValorFinal(), this.valorBase, reparo, this.descricao);

        return info;
    }
} 