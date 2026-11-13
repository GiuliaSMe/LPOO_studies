package gerenciadores;

import modelo.OrdemServico;
import interfaces.Buscavel;
import excecoes.*;

/*TODO:
[ ] O construtor recebe a capacidade do array, que deve ser positiva. 
[ ]Implemente adicionarOrdem(...), listarOrdens(), buscarPorNumero(int), buscarPorPlaca(String) e o método da interface Buscavel. 
 */

/**
 * Centraliza o cadastro e a consulta das ordens de serviço da oficina.
 * Permite registrar novas demandas e localizar registros por placa ou cliente.
 */
public class GerenciadorOrdens implements Buscavel{
    private int quantidadeMaxOrdens;
    private OrdemServico[] ordens;
    private int contadorOrdens = 0; //somente para garantir que o novo item no array de ordens seja sempre o seguinte, para evitar que alguma ordem seja sobrescrita

    /**
     * Contrutor determina a quantidade maxima de ordens que pode ser armazenada.
     * @param quantidade quantidade maxima de ordens.
     */
    public GerenciadorOrdens(int quantidade)throws ValorInvalidoException {
        if (quantidade <= 0) {
            throw new ValorInvalidoException("A capacidade deve ser positiva.");
        }
    
        this.quantidadeMaxOrdens = quantidade;
        this.ordens =  new OrdemServico[quantidadeMaxOrdens];
    }

    /**
     * Retorna o resumo de todas as ordens cadastradas no momento.
     * @return texto com os dados de cada ordem ou uma mensagem caso não exista registro.
    */
    public String getOrdensServico(){
        String textoTodasOrdens = "";
        for (int i = 0; i<contadorOrdens; i++){
            textoTodasOrdens += this.ordens[i].getResumoOrdemServico();
        }

        if (this.contadorOrdens == 0){
            textoTodasOrdens = "\nNenhuma Ordem Encontrada :(";
        }
        
        return textoTodasOrdens;
        
    }
    
    /**
     * Registra uma nova ordem no conjunto de ordens da oficina.
     *
     * @param novaOrdem ordem a ser adicionada ao sistema.
     */
    public void addOrdem(OrdemServico novaOrdem)
        throws ValorInvalidoException, CapacidadeExcedidaException {
        if (novaOrdem == null) {
            throw new ValorInvalidoException("A ordem não pode ser nula.");
        }
        if (contadorOrdens == ordens.length) {
            throw new CapacidadeExcedidaException("Capacidade máxima de ordens atingida.");
        }
        novaOrdem.setNumero(contadorOrdens + 1);   // número = posição + 1
        ordens[contadorOrdens++] = novaOrdem;
    }

    public OrdemServico buscarPorNumero(int numero) {
        int indice = numero - 1;
        if (indice < 0 || indice >= contadorOrdens) {
            return null;
        }
        return ordens[indice];
    }

    /**
     * Busca ordens cujo número de placa contenha o valor informado.
     *
     * @param placaProcurada sequência de caracteres usada para localizar a placa.
     * @return relatório com os resultados encontrados ou mensagem de ausência.
     */
    public String findPorPlaca(String placaProcurada){
        OrdemServico[] encontrados = new OrdemServico[this.contadorOrdens];        
        int contadorEncontrados = 0; 
        String textoNaoEncontrado = "Nenhuma placa encontrada com: " + placaProcurada;
        String textoEncontrado = "Ordens com: " + placaProcurada + "\n";

        for (int i = 0; i < contadorOrdens; i++){
            if (ordens[i].getPlacaOrdem().equalsIgnoreCase(placaProcurada.trim())){
            encontrados[contadorEncontrados++] = ordens[i];
            }
        }

        if (contadorEncontrados == 0){
            return textoNaoEncontrado;
        }
        else{

            for (int i = 0; i<contadorEncontrados; i++){
                textoEncontrado += encontrados[i].getResumoOrdemServico();
            }

            return textoEncontrado;
        }
    }

    /**
     * Busca ordens associadas a um cliente cujo nome comece com o valor informado.
     *
     * @param clienteProcurado nome ou trecho do nome do cliente.
     * @return relatório com os resultados encontrados ou mensagem de ausência.
     */
    @Override public String findPorInicioDoNome(String inicio) throws ValorInvalidoException{
        OrdemServico[] encontrados = new OrdemServico[this.contadorOrdens];
        int contadorEncontrados = 0;
        String textoNaoEncontrado = "Nenhum cliente encontrado com: " + inicio;
        String textoEncontrado = "Ordens com: " + inicio + "\n";

        if (inicio == null || inicio.trim().isEmpty()){
            throw new ValorInvalidoException("O texto de busca não pode ser vazio.");
        }

        String alvo = inicio.trim().toLowerCase();

        for (int i = 0; i < this.contadorOrdens; i++){
            if (this.ordens[i].getClienteOrdem().toLowerCase().startsWith(alvo)){
                encontrados[contadorEncontrados++] = this.ordens[i];
            }
        }

        if (contadorEncontrados == 0){
            return textoNaoEncontrado;
        }
        else{
            for (int i = 0; i<contadorEncontrados; i++){
                textoEncontrado += encontrados[i].getResumoOrdemServico();
            }
            return textoEncontrado;
        }
    }
}