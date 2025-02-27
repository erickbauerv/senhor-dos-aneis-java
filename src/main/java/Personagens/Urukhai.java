package Personagens;

import Enums.EnumClasse;
import Interfaces.Racas.Humano;
import Interfaces.Racas.Monstro;

public class Urukhai extends Personagem implements Monstro, Humano {
    public Urukhai(){
        super(8, 6, 3, 45, false, "U", EnumClasse.GUERREIRO);
    }

    @Override
    public void envelhecer() {
        this.constituicao -= 2;
    }

    @Override
    public String falar() {
        return "Looks like meat's back on the menu boys!";
    }

    @Override
    public String grunir() {
        return "Uuurrrrrr";
    }
}
