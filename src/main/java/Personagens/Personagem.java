package Personagens;

import Enums.EnumClasse;
import SimuladorManagement.Mapa;

public abstract class Personagem {
    protected int forca;
    protected int agilidade;
    protected int inteligencia;
    protected int constituicao;
    protected boolean fazParteDaSociedadeDoAnel;
    protected String toString;
    protected EnumClasse classe;

    protected Personagem(int forca, int agilidade, int inteligencia, int constituicao, boolean fazParteDaSociedadeDoAnel, String toString, EnumClasse classe){
        this.forca = forca;
        this.agilidade = agilidade;
        this.inteligencia = inteligencia;
        this.constituicao = constituicao;
        this.fazParteDaSociedadeDoAnel = fazParteDaSociedadeDoAnel;
        this.toString = toString;
        this.classe = classe;
    }

    public void receberDano(int dano){
        this.constituicao -= dano;
    }

    public int getForca(){
        return this.forca;
    }

    public EnumClasse getClasse(){
        return this.classe;
    }

    public int getConstituicao(){
        return this.constituicao;
    }

    public int getAgilidade(){
        return this.agilidade;
    }

    public int getInteligencia(){
        return this.inteligencia;
    }

    public boolean getFazParteDaSociedadeDoAnel(){
        return this.fazParteDaSociedadeDoAnel;
    }

    @Override
    public String toString(){
        return this.toString;
    }
}
