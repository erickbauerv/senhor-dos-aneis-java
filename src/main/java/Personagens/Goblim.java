package Personagens;

import Enums.EnumClasse;
import Interfaces.Racas.Monstro;

public class Goblim extends Personagem implements Monstro {
    public Goblim(){
        super(3, 6, 1, 20, false, "M", EnumClasse.ARQUEIRO);
    }

    @Override
    public String grunir() {
        return "Iiisshhhh";
    }
}
