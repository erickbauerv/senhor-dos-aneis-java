package Personagens;

import Enums.EnumClasse;
import Interfaces.Racas.Humano;

public class Boromir extends Personagem implements Humano {
    public Boromir(){
        super(7, 6, 3, 40, true, "B", EnumClasse.GUERREIRO);
    }

    @Override
    public void envelhecer() {
        this.constituicao += 2;
    }

    @Override
    public String falar() {
        return "One does not simply walk into Mordor.";
    }
}
