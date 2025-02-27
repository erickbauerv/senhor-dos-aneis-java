package Exceptions;

public class PersonagemJaEstaNoMapaException extends RuntimeException {
    public PersonagemJaEstaNoMapaException() {
        super("Personagem já está nessa posição.");
    }
}
