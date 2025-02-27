package Personagens;

import Enums.EnumClasse;
import Interfaces.Racas.Elfo;

public class Legolas extends Personagem implements Elfo {
    public Legolas(){
        super(5, 10, 6, 80, true, "L", EnumClasse.ARQUEIRO);
    }

    @Override
    public String falarElfico() {
        return "I amar prestar aen, han mathon ne nem, han mathon ne chae, a han noston ned.";
    }

    @Override
    public String falar() {
        return "They're taking the Hobbits to Isengard!";
    }
}
