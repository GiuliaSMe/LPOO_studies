public class Veiculo{
    private String placa;
    private String modelo;
    private int ano;

    public Veiculo(String placa, String modelo, int ano){
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
    }
 
    public String getVeiculo(){
        return("Placa: "+this.placa+" | Modelo: "+this.modelo+" | Ano: "+this.ano);
    }

    public String getPlaca(){
        return this.placa;
    }

    public void calcularIdade(int anoVigente){
        int idadeVeiculo = anoVigente - this.ano;
        System.out.println("\nIdade do veículo: " + idadeVeiculo);
    }
}