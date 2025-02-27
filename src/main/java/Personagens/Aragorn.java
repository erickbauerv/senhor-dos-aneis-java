package Personagens;

import Enums.EnumClasse;
import Interfaces.Racas.Humano;

public class Aragorn extends Personagem implements Humano {
    public Aragorn(){
        super(10, 7, 6, 60, true, "A", EnumClasse.GUERREIRO);
    }

    @Override
    public void envelhecer() {
        final int DANO_POR_ENVELHECIMENTO = 1;
        this.constituicao -= DANO_POR_ENVELHECIMENTO;
    }

    @Override
    public String falar() {
        return "A day may come when the courage of men fails… but it is not THIS day.";
    }
}
