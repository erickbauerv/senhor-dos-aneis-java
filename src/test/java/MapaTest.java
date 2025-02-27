import SimuladorManagement.Mapa;
import org.junit.Assert;
import org.junit.Test;

public class MapaTest {
    @Test
    public void deveExibirAsPosicoesDoMapaQuandoSemPersonagens(){
        Mapa mapa = new Mapa();
        String valorEsperado = "| | | | | | | | | | |";

        String posicoes = mapa.exibir();

        Assert.assertEquals(valorEsperado, posicoes);
    }
}
