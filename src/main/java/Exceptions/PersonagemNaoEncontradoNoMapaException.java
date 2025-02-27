package Exceptions;

public class PersonagemNaoEncontradoNoMapaException extends RuntimeException {
    public PersonagemNaoEncontradoNoMapaException() {
        super("O personagem não foi encontrado no mapa.");
    }
}
