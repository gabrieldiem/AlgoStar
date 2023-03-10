package edu.fiuba.algo3.casos_de_uso;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.PuertoEstelar;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Espiral;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Extractor;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Guarida;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso5Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01IntentarConstruirExtractorEnLugarSinMohoLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso gasDelImperio = new Recurso(0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new Extractor(gasDelImperio), coordenada));
    }

    @Test
    public void test02IntentarConstruirReservaDeReproduccionEnLugarSinMohoLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new ReservaDeReproduccion(), coordenada));
    }

    @Test
    public void test03IntentarConstruirGuaridaEnLugarSinMohoLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new Guarida(), coordenada));
    }

    @Test
    public void test04IntentarConstruirEspiralEnLugarSinMohoLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new Espiral(), coordenada));
    }

    @Test
    public void test05IntentarConstruirAccesoFueraDelRangoDeUnPilonLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new Acceso(), coordenada));
    }

    @Test
    public void test06IntentarConstruirPuertoEstelarFueraDelRangoDeUnPilonLanzaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new PuertoEstelar(), coordenada));
    }
}
