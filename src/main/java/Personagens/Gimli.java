package Personagens;

import Enums.EnumClasse;
import Interfaces.Racas.Anao;

public class Gimli extends Personagem implements Anao {
    private int vezesQueBebeu;
    private boolean bebado;

    public Gimli(){
        super(9, 2, 4, 60, true, "I", EnumClasse.GUERREIRO);
    }

    @Override
    public void beber() {
        this.vezesQueBebeu += 1;
        if(vezesQueBebeu >= 3){
            this.bebado = true;
        }
    }

    @Override
    public String falar() {
        return this.bebado ? "What did I say? He can't hold his liquor!" : "Let them come. There is one Dwarf yet in Moria who still draws breath.";
    }
}
