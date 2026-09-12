/**GerenciadorOrdens
 * Responsável por guardar as ordens de serviço
 */

public class GerenciadorOrdens{
    private OrdemServico[] ordens = new OrdemServico[100];
    private int contadorOrdens = 0; //somente para garantir que o novo item no array de ordens seja sempre o seguinte, para evitar que alguma ordem seja sobrescrita

    public void addOrdem(OrdemServico newOrdem){
        this.ordens[contadorOrdens++] = newOrdem;
    }

    public void getOrdensServico(){
        for (int i = 0; i<contadorOrdens; i++){
            ordens[i].showResumoOrdemServico();
        }
        
    }

    //TODO: buscas 
}