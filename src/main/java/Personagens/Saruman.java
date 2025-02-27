package Personagens;

import Enums.EnumClasse;
import Interfaces.Racas.Maia;

public class Saruman extends Personagem implements Maia {
    public Saruman(){
        super(2, 2, 9, 70, false, "S", EnumClasse.MAGO);
    }

    @Override
    public Personagem ressucitar() {
        return null;
    }

    @Override
    public String falar() {
        return "Against the power of Mordor there can be no victory.";
    }
}
