package Personagens;

import Enums.EnumClasse;
import Interfaces.Racas.Maia;

public class Gandalf extends Personagem implements Maia {
    public Gandalf(){
        super(2, 3, 10, 80, true, "G", EnumClasse.MAGO);
    }

    @Override
    public Personagem ressucitar() {
        if(this.constituicao <= 0){
            return new Gandalf();
        }

        return null;
    }

    @Override
    public String falar() {
        return "A Wizard is never late, nor is he early. He arrives precisely when he means to.";
    }
}
