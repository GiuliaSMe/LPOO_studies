package interfaces;
import excecoes.*;

public interface Buscavel {
    public String findPorInicioDoNome(String inicio) throws ValorInvalidoException;
}
