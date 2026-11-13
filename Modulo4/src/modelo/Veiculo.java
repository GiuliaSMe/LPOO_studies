package modelo;
/* TODO
Veiculo: atributos privados placa (String), modelo (String) e ano (int). Crie construtor, getters e
setters necessários e exibirInformacoes(). Placa e modelo não podem ser vazios; ano deve ser
positivo e não superior ao ano atual. A placa identifica o veículo na pesquisa (comparação sem
diferenciar maiúsculas/minúsculas).
 */

import excecoes.*;

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
    public Veiculo(String placa, String modelo, int ano) throws ValorInvalidoException{
        if (placa == null || placa.trim().isEmpty()){
            throw new ValorInvalidoException("A placa não pode ser vazia.");}
        if (modelo == null || modelo.trim().isEmpty()){
            throw new ValorInvalidoException("O modelo não pode ser vazio.");}
        if (ano <= 0 || ano > java.time.Year.now().getValue()){
            throw new ValorInvalidoException("Ano inválido.");}
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