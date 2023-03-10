package edu.fiuba.algo3.clases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Extractor;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaCriadero;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaExtractor;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEstaUnidadNoSePuedeContratar;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExtractorTest {

    //El Zangano se mete bajo tierra y aparece en el gas

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoCrearUnExtractor(){
        Recurso gasDelImperio = new Gas(0);
        Extractor unExtractor = new Extractor(gasDelImperio);

        assertNotNull(unExtractor);
    }

    @Test
    public void test02NoPuedoExtraerElGasDeUnExtractorQueNoEstaConstruidoEn5Turnos(){
        Recurso gasDelImperio = new Gas(0);
        Extractor unExtractor = new Extractor(gasDelImperio);

        for(int i = 0; i < 5; i++)
            unExtractor.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unExtractor.contratarZangano(new Zangano()));
    }

    @Test
    public void test03NoPuedoContratarUnZanganoEnUnExtractorQueNoEstaConstruidoEn5Turnos(){
        Recurso gasDelImperio = new Gas(0);
        Extractor unExtractor = new Extractor(gasDelImperio);

        for(int i = 0; i < 5; i++)
            unExtractor.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unExtractor.contratarZangano(new Zangano()));
    }

    @Test
    public void test04PuedoContratarUnZanganoEnUnExtractorQueEstaConstruidoEn6Turnos(){
        Mapa mapa = Mapa.obtener();
        Zerg zerg = new Zerg();
        Coordenada coordenadasGas = new Coordenada(0,0);

        zerg.abastecerDeRecursos(new Mineral(300), new Gas(0));
        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);

        Coordenada coordenadaCriadero = new Coordenada(1,1);
        mapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        zerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            zerg.terminarTurno();

        mapa.colocarOcupable(new Zangano(), coordenadasGas);
        zerg.construirEdificio(new FabricaExtractor(), coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++)
            zerg.terminarTurno();

        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        assertDoesNotThrow( () -> extractor.contratarUnidad(new Zangano()));
    }

    @Test
    public void test05PuedoContratarUnZanganoEnUnExtractorYExtraeCadaTurno(){
        Mapa mapa = Mapa.obtener();
        Zerg zerg = new Zerg();
        Coordenada coordenadasGas = new Coordenada(0,0);

        zerg.abastecerDeRecursos(new Mineral(300), new Gas(0));
        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);

        Coordenada coordenadaCriadero = new Coordenada(1,1);
        mapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        zerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            zerg.terminarTurno();

        mapa.colocarOcupable(new Zangano(), coordenadasGas);
        zerg.construirEdificio(new FabricaExtractor(), coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++)
            zerg.terminarTurno();

        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        extractor.contratarUnidad(new Zangano());
        zerg.terminarTurno();

        assertTrue(zerg.tienesEstaCantidadDeGas(10));
    }

    @Test
    public void test06PuedoContratarTresZanganosEnUnExtractorYExtraeCadaTurno() {
        Mapa mapa = Mapa.obtener();
        Zerg zerg = new Zerg();
        Coordenada coordenadasGas = new Coordenada(0, 0);

        zerg.abastecerDeRecursos(new Mineral(300), new Gas(0));
        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);

        Coordenada coordenadaCriadero = new Coordenada(1,1);
        mapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        zerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            zerg.terminarTurno();

        mapa.colocarOcupable(new Zangano(), coordenadasGas);
        zerg.construirEdificio(new FabricaExtractor(), coordenadasGas);

        //Construimos el extractor
        for (int i = 0; i < 6; i++)
            zerg.terminarTurno();

        Edificio extractor = zerg.conseguirEdificio(coordenadasGas);
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());

        zerg.terminarTurno();

        assertTrue(zerg.tienesEstaCantidadDeGas(30));
    }

    @Test
    public void test07IntentarContratarUnidadInvalidaLanzaExcepcion() {
        Recurso gasDelImperio = new Gas(0);
        Extractor unExtractor = new Extractor(gasDelImperio);

        for(int i = 0; i < 6; i++)
            unExtractor.pasarTurno();

        assertThrows(ErrorEstaUnidadNoSePuedeContratar.class,
                () -> unExtractor.contratarZangano(new Zerling()));
    }
}
