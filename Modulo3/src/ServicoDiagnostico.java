public class ServicoDiagnostico extends Servico{
    private boolean possuiReparo;
    private double valorDiagnostico;

    public ServicoDiagnostico(String servico, double valorBase, String descricao, String possuiReparo, double valorDiagnostico){
        super(servico, valorBase, descricao);
        setPossuiReparo(possuiReparo);
        this.valorDiagnostico = valorDiagnostico;
    }

    @Override public double getValorFinal(){
        //evita modificar a variável original
        double valorDiagnosticoCalculo = this.valorDiagnostico; 

        if (this.possuiReparo){
            valorDiagnosticoCalculo *= 0.5;
        }

        return this.valorBase + valorDiagnosticoCalculo;
    }

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