package Personagens;

import Enums.EnumClasse;
import Interfaces.Racas.Monstro;

public class Orc extends Personagem implements Monstro {
    public Orc(){
        super(7, 4, 1, 30, false, "O", EnumClasse.GUERREIRO);
    }

    @Override
    public String grunir() {
        return "Arrrggghhh";
    }
}
