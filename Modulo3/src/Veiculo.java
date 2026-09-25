/**
 * Representa um veículo cadastrado na oficina.
 * Armazena dados essenciais para identificação e análise do atendimento.
 */
public class Veiculo{
    private String placa;
    private String modelo;
    private int ano;

    /**
     * Cria um veículo com placa, modelo e ano de fabricação.
     *
     * @param placa identificação do veículo.
     * @param modelo modelo do automóvel.
     * @param ano ano de fabricação do veículo.
     */
    public Veiculo(String placa, String modelo, int ano){
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
    }
 
    /**
     * Retorna uma descrição completa do veículo em formato legível.
     *
     * @return texto contendo placa, modelo e ano.
     */
    public String getVeiculo(){
        return("Placa: "+this.placa+" | Modelo: "+this.modelo+" | Ano: "+this.ano);
    }

    /**
     * Retorna apenas a placa do veículo.
     *
     * @return placa cadastrada.
     */
    public String getPlaca(){
        return this.placa;
    }

    /**
     * Calcula a idade do veículo a partir do ano atual informado.
     *
     * @param anoVigente ano base para cálculo da idade.
     */
    public void calcularIdade(int anoVigente){
        int idadeVeiculo = anoVigente - this.ano;
        System.out.println("\nIdade do veículo: " + idadeVeiculo);
    }
}