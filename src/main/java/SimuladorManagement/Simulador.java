package SimuladorManagement;

import Exceptions.SauronDominaOMundoException;
import Personagens.Personagem;

public class Simulador {
    private Mapa mapa;

    public Simulador(Mapa mapa){
        this.mapa = mapa;
    }

    public void simular(){
        for(int i = 0; i <= mapa.getPersonagemsEmCampo().size() - 1; i++){
            int posicaoPersonagem = mapa.buscarPosicao(this.mapa.getPersonagemsEmCampo().get(i));
            turnoPosicao(posicaoPersonagem);

            if(i == mapa.getPersonagemsEmCampo().size() - 1){
                i = -1;
            }

            if(simulacaoFinalizou()){
                break;
            }
        }
    }

    private void turnoPosicao(int posicao){
        Personagem personagem = mapa.buscarCasa(posicao);
        if(personagem == null){
            return;
        }

        switch (personagem.getClasse()){
            case GUERREIRO:
                guerreiroAtacar(personagem, posicao);
                guerreiroMovimentar(personagem, posicao);
                break;
            case MAGO:
                magoAtacar(personagem, posicao);
                magoMovimentar(personagem, posicao);
                break;
            case ARQUEIRO:
                arqueiroAtacar(personagem, posicao);
                arqueiroMovimentar(personagem, posicao);
                break;
        }
    }

    private void guerreiroAtacar(Personagem personagem, int posicao){
        int posicaoLimite = personagem.getFazParteDaSociedadeDoAnel() ? 9 : 0;
        int calculoInimigoMaisProximo = personagem.getFazParteDaSociedadeDoAnel() ? 1 : -1;

        if(posicao != posicaoLimite){
            int posicaoInimigo = posicao + calculoInimigoMaisProximo;
            Personagem inimigoMaisProximo = mapa.buscarCasa(posicaoInimigo);
            if(inimigoMaisProximo == null){
                return;
            }

            int dano = 2 * personagem.getForca();
            atacar(dano, posicaoInimigo, inimigoMaisProximo);
        }
    }

    private void guerreiroMovimentar(Personagem personagem, int posicaoAtual){
        int posicaoLimite = personagem.getFazParteDaSociedadeDoAnel() ? 9 : 0;
        int calculoNovaPosicao = personagem.getFazParteDaSociedadeDoAnel() ? 1 : -1;

        if(posicaoAtual != posicaoLimite){
            int novaPosicao = posicaoAtual + calculoNovaPosicao;
            boolean proximaPosicaoLivre = this.mapa.buscarCasa(novaPosicao) == null;
            if(proximaPosicaoLivre){
                this.mapa.movimentar(posicaoAtual, novaPosicao, personagem);
            }
        }
    }

    private void arqueiroAtacar(Personagem personagem, int posicao){
        Personagem inimigoMaisDistanteNoAlcance = null;
        int distanciaDoInimigo = 3;
        int posicaoInimigo = 0;

        if(personagem.getFazParteDaSociedadeDoAnel()){
            for(int i = 3; i >= 1; i--){
                posicaoInimigo = posicao + i;

                if(posicaoInimigo > 9){
                    distanciaDoInimigo -= 1;
                    continue;
                }

                inimigoMaisDistanteNoAlcance = mapa.buscarCasa(posicaoInimigo);
                if(inimigoMaisDistanteNoAlcance == null){
                    distanciaDoInimigo -= 1;
                    continue;
                }

                inimigoMaisDistanteNoAlcance =  inimigoMaisDistanteNoAlcance.getFazParteDaSociedadeDoAnel() ? null : inimigoMaisDistanteNoAlcance;
                if(inimigoMaisDistanteNoAlcance != null){
                    break;
                }

                distanciaDoInimigo -= 1;
            }
        } else {
            for(int i = -3; i <= -1; i++){
                posicaoInimigo = posicao + i;

                if(posicaoInimigo < 0){
                    distanciaDoInimigo -= 1;
                    continue;
                }

                inimigoMaisDistanteNoAlcance = mapa.buscarCasa(posicaoInimigo);
                if(inimigoMaisDistanteNoAlcance == null){
                    distanciaDoInimigo -= 1;
                    continue;
                }

                inimigoMaisDistanteNoAlcance = !inimigoMaisDistanteNoAlcance.getFazParteDaSociedadeDoAnel() ? null : inimigoMaisDistanteNoAlcance;
                if(inimigoMaisDistanteNoAlcance != null){
                    break;
                }

                distanciaDoInimigo -= 1;
            }
        }

        if(inimigoMaisDistanteNoAlcance == null){
            return;
        }

        int dano = distanciaDoInimigo * personagem.getAgilidade();
        atacar(dano, posicaoInimigo, inimigoMaisDistanteNoAlcance);
    }

    private void arqueiroMovimentar(Personagem personagem, int posicaoAtual){
        int posicaoLimite = personagem.getFazParteDaSociedadeDoAnel() ? 9 : 0;

        if(personagem.getFazParteDaSociedadeDoAnel()){
            for(int i = 2; i >= 1; i--){
                int novaPosicao = posicaoAtual + i;
                if(novaPosicao > posicaoLimite){
                    continue;
                }

                if(i == 2 && this.mapa.buscarCasa(novaPosicao) == null && this.mapa.buscarCasa(novaPosicao - 1) == null){
                    this.mapa.movimentar(posicaoAtual, novaPosicao, personagem);
                    break;
                } else if(i == 1 && this.mapa.buscarCasa(novaPosicao) == null){
                    this.mapa.movimentar(posicaoAtual, novaPosicao, personagem);
                    break;
                }
            }
        } else {
            for(int i = -2; i <= -1; i++){
                int novaPosicao = posicaoAtual + i;
                if(novaPosicao < posicaoLimite){
                    continue;
                }

                if(i == -2 && this.mapa.buscarCasa(novaPosicao) == null && this.mapa.buscarCasa(novaPosicao + 1) == null){
                    this.mapa.movimentar(posicaoAtual, novaPosicao, personagem);
                    break;
                } else if(i == -1 && this.mapa.buscarCasa(novaPosicao) == null){
                    this.mapa.movimentar(posicaoAtual, novaPosicao, personagem);
                    break;
                }
            }
        }
    }

    private void magoAtacar(Personagem personagem, int posicao){
        int dano = personagem.getInteligencia();

        for(int i = 0; i <= this.mapa.getMapaString().size() - 1; i++){
            Personagem alvo = this.mapa.buscarCasa(i);
            if(alvo != null && alvo.getFazParteDaSociedadeDoAnel() != personagem.getFazParteDaSociedadeDoAnel()){
                atacar(dano, this.mapa.buscarPosicao(alvo), this.mapa.buscarCasa(this.mapa.buscarPosicao(alvo)));
            }
        }
    }

    private void magoMovimentar(Personagem personagem, int posicaoAtual){
        if(this.mapa.getPersonagemsEmCampo().size() > 1){
            return;
        }

        int posicaoLimite = personagem.getFazParteDaSociedadeDoAnel() ? 9 : 0;
        int calculoNovaPosicao = personagem.getFazParteDaSociedadeDoAnel() ? 1 : -1;

        if(posicaoAtual < posicaoLimite){
            int novaPosicao = posicaoAtual + calculoNovaPosicao;
            boolean proximaPosicaoLivre = this.mapa.buscarCasa(novaPosicao) == null;
            if(proximaPosicaoLivre){
                this.mapa.movimentar(posicaoAtual, novaPosicao, personagem);
            }
        }
    }

    private boolean simulacaoFinalizou(){
        Personagem personagemPosicaoFinal = this.mapa.buscarCasa(9);
        if(personagemPosicaoFinal != null && personagemPosicaoFinal.getFazParteDaSociedadeDoAnel()){
            return true;
        }

        boolean personagemQueFazParteDaSociedadeDoAnelFoiEncontrado = false;
        for(Personagem personagem : this.mapa.getPersonagemsEmCampo()){
            if(personagem.getFazParteDaSociedadeDoAnel()){
                personagemQueFazParteDaSociedadeDoAnelFoiEncontrado = true;
                break;
            }
        }

        if(!personagemQueFazParteDaSociedadeDoAnelFoiEncontrado){
            throw new SauronDominaOMundoException();
        }

        return false;
    }

    private void atacar(int dano, int posicaoInimigo, Personagem inimigo){
        inimigo.receberDano(dano);

        if(inimigo.getConstituicao() <= 0){
            this.mapa.remover(posicaoInimigo, inimigo);
        }
    }
}
