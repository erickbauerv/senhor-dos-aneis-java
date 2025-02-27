package SimuladorManagement;

import Exceptions.PersonagemJaEstaNoMapaException;
import Exceptions.PersonagemNaoEncontradoNoMapaException;
import Exceptions.PosicaoOcupadaException;
import Personagens.Personagem;

import java.util.*;

public class Mapa {
    private List<String> mapaString;
    private List<Personagem> personagemsEmCampo;

    public Mapa(){
        this.mapaString = new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
        this.personagemsEmCampo = new ArrayList<>();
    }

    public String exibir(){
        StringBuilder mapaBuilder = new StringBuilder();

        for(String posicao : mapaString){
            mapaBuilder.append("|").append(posicao);
        }

        return mapaBuilder.append("|").toString();
    }

    public void inserir(int posicao, Personagem personagem){
        if(Objects.equals(mapaString.get(posicao), " ")){
            this.mapaString.set(posicao, personagem.toString());
            if(!personagemsEmCampo.contains(personagem)){
                personagemsEmCampo.add(personagem);
            }
        } else if (Objects.equals(mapaString.get(posicao), personagem.toString())) {
            throw new PersonagemJaEstaNoMapaException();
        } else {
            throw new PosicaoOcupadaException();
        }
    }

    public void movimentar(int posicaoAtual, int novaPosicao, Personagem personagem){
        if(Objects.equals(mapaString.get(novaPosicao), " ")){
            this.mapaString.set(posicaoAtual, " ");
            this.mapaString.set(novaPosicao, personagem.toString());
            if(!personagemsEmCampo.contains(personagem)){
                personagemsEmCampo.add(personagem);
            }
        } else if (Objects.equals(mapaString.get(novaPosicao), personagem.toString())) {
            throw new PersonagemJaEstaNoMapaException();
        } else {
            throw new PosicaoOcupadaException();
        }
    }

    public void remover(int posicao, Personagem personagem){
        this.mapaString.set(posicao, " ");
        this.personagemsEmCampo.remove(personagem);
    }

    public int buscarPosicao(Personagem personagem){
        try {
            return mapaString.indexOf(personagem.toString());
        } catch (Exception ex){
            throw new PersonagemNaoEncontradoNoMapaException();
        }
    }

    public Personagem buscarCasa(int posicao){
        String toStringPersonagem = mapaString.get(posicao);
        Personagem personagemNaPosicao = null;

        if(Objects.equals(toStringPersonagem, " ")){
            return null;
        }

        for (Personagem personagem: personagemsEmCampo){
            if(Objects.equals(personagem.toString(), toStringPersonagem)){
                personagemNaPosicao = personagem;
            }
        }

        return personagemNaPosicao;
    }

    public List<String> getMapaString(){
        return this.mapaString;
    }

    public List<Personagem> getPersonagemsEmCampo(){
        return this.personagemsEmCampo;
    }

    public void setPersonagemsEmCampo(List<Personagem> personagemsEmCampo){
        this.personagemsEmCampo = personagemsEmCampo;
    }
}
