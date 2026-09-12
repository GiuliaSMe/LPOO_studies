public class Veiculo{
    private String placa;
    private String modelo;
    private int ano;

    public Veiculo(String placa, String modelo, int ano){
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
    }

    public void exibirInfo(){
        System.out.println("Placa: "+this.placa+"; Modelo: "+this.modelo+"; Ano: "+this.ano);
    }

    public void calcularIdade(int anoVigente){
        int idadeVeiculo = anoVigente - this.ano;
        System.out.println("\nIdade do veículo: " + idadeVeiculo);
    }
}