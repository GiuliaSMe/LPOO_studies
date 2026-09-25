/**
 * Centraliza o cadastro e a consulta das ordens de serviço da oficina.
 * Permite registrar novas demandas e localizar registros por placa ou cliente.
 */
public class GerenciadorOrdens{
    private OrdemServico[] ordens = new OrdemServico[100];
    private int contadorOrdens = 0; //somente para garantir que o novo item no array de ordens seja sempre o seguinte, para evitar que alguma ordem seja sobrescrita

    /**
     * Registra uma nova ordem no conjunto de ordens da oficina.
     *
     * @param newOrdem ordem a ser adicionada ao sistema.
     */
    public void addOrdem(OrdemServico newOrdem){
        this.ordens[contadorOrdens++] = newOrdem;
    }

    /**
     * Retorna o resumo de todas as ordens cadastradas no momento.
     *
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
     * Busca ordens cujo número de placa contenha o valor informado.
     *
     * @param placaProcurada sequência de caracteres usada para localizar a placa.
     * @return relatório com os resultados encontrados ou mensagem de ausência.
     */
    public String findPorPlaca(String placaProcurada){
        OrdemServico[] encontrados = new OrdemServico[100];
        int contadorEncontrados = 0; 
        String textoNaoEncontrado = "Nenhuma placa encontrada com: " + placaProcurada;
        String textoEncontrado = "Ordens com: " + placaProcurada + "\n";

        for (OrdemServico ordem : this.ordens){
            if (ordem == null){
                continue;
            }

            String placaDessaOrdem = ordem.getPlacaOrdem();

            if (placaDessaOrdem.indexOf(placaProcurada) != (-1)){
                encontrados[contadorEncontrados++] = ordem;
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
     * Busca ordens associadas a um cliente cujo nome contenha o valor informado.
     *
     * @param clienteProcurado nome ou trecho do nome do cliente.
     * @return relatório com os resultados encontrados ou mensagem de ausência.
     */
    public String findPorCliente(String clienteProcurado){
        OrdemServico[] encontrados = new OrdemServico[100];
        int contadorEncontrados = 0;
        String textoNaoEncontrado = "Nenhum cliente encontrado com: " + clienteProcurado;
        String textoEncontrado = "Ordens com: " + clienteProcurado + "\n";

        for (OrdemServico ordem : this.ordens){
            if (ordem == null){
                continue;
            }
            String clienteDessaOrdem = ordem.getClienteOrdem();

            if (clienteDessaOrdem.indexOf(clienteProcurado) != (-1)){
                encontrados[contadorEncontrados++] = ordem;
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