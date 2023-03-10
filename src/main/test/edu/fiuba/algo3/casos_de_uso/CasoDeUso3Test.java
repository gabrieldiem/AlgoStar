package edu.fiuba.algo3.casos_de_uso;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso3Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoConstruirUnExtractorDondeHayVolcanDeGas(){
        // En esta prueba genero un criadero para poner moho sobre el volcan de gas y luego
        // construir el extractor
        Mapa elMapa = Mapa.obtener();
        Gas gasDelImperio = new Gas(0);
        Criadero criadero = new Criadero();
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(0,1);

        elMapa.colocarOcupable(criadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            criadero.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertDoesNotThrow(() -> elMapa.colocarOcupable(new Extractor(gasDelImperio), coordenada));
    }

    @Test
    public void test02PuedoConstruirUnAsimiladorDondeHayVolcanDeGas(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Gas gasDelImperio = new Gas(0);

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertDoesNotThrow(() -> elMapa.colocarOcupable(new Asimilador(gasDelImperio), coordenada));
    }

    @Test
    public void test03NoPuedoConstruirUnCriaderoDondeHayVolcanDeGas(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new Criadero(), coordenada));
    }

    @Test
    public void test04NoPuedoConstruirPilonDondeHayUnVolcanDeGas(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new Pilon(), coordenada));
    }

    @Test
    public void test05NoPuedoConstruirReservaDeProduccionDondeHayUnVolcanDeGas(){
        // En esta prueba genero un criadero para poner moho sobre el volcan de gas y luego
        // construir el reserva de produccion
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(0,1);
        Criadero criadero = new Criadero();

        elMapa.colocarOcupable(criadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            criadero.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new ReservaDeReproduccion(), coordenada));
    }

    @Test
    public void test06NoPuedoConstruirGuaridaDondeHayUnVolcanDeGas(){
        // En esta prueba genero un criadero para poner moho sobre el volcan de gas y luego
        // construir el guarida
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(0,1);
        Criadero criadero = new Criadero();

        elMapa.colocarOcupable(criadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            criadero.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new Guarida(), coordenada));
    }

    @Test
    public void test07NoPuedoConstruirEspiralDondeHayUnVolcanDeGas(){
        // En esta prueba genero un criadero para poner moho sobre el volcan de gas y luego
        // construir la espiral
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenadaCriadero = new Coordenada(0,1);
        Criadero criadero = new Criadero();

        elMapa.colocarOcupable(criadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            criadero.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new Espiral(), coordenada));
    }

    @Test
    public void test08NoPuedoConstruirUnNexoMineralDondeHayVolcanDeGas(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Recurso mineralDelImperio = new Gas(0);

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new NexoMineral(mineralDelImperio), coordenada));
    }

    @Test
    public void test09NoPuedoConstruirAccesoDondeHayUnVolcanDeGas(){
        // En esta prueba genero un criadero para poner moho sobre el volcan de gas y luego
        // construir el acceso
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenadaPilon = new Coordenada(0,1);
        Pilon pilon = new Pilon();
        pilon.asignarSuministro(new Suministro(0));

        elMapa.colocarOcupable(pilon, coordenadaPilon);

        for (int i = 0; i < 5; i++)
            pilon.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new Acceso(), coordenada));
    }

    @Test
    public void test10NoPuedoConstruirPuertoEstelarDondeHayUnVolcanDeGas(){
        // En esta prueba genero un criadero para poner moho sobre el volcan de gas y luego
        // construir la puerta estelar
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Coordenada coordenadaPilon = new Coordenada(0,1);
        Pilon pilon = new Pilon();
        pilon.asignarSuministro(new Suministro(0));

        elMapa.colocarOcupable(pilon, coordenadaPilon);

        for (int i = 0; i < 5; i++)
            pilon.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(),coordenada);

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> elMapa.colocarOcupable(new PuertoEstelar(), coordenada));
    }
}
