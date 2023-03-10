package edu.fiuba.algo3.casos_de_uso;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Asimilador;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.NexoMineral;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Extractor;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasoDeUso7Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01MandoUnZanganoAExtraerUnMineralYRevisoQueDepositeEnElImperio(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0,0);
        Mineral mineralDelImperio = new Mineral(0);
        UnidadZerg unZangano = new Zangano();

        elMapa.colocarMaterial(new MineralRecolectable(),coordenada);
        unZangano.setDepositoRecurso(mineralDelImperio);
        elMapa.colocarOcupable(unZangano, coordenada);
        unZangano.extraer();

        assertTrue(mineralDelImperio.tenesCantidadDeRecurso(10));
    }

    @Test
    public void test02CreoUnAsimiladorYRevisoQueDepositeEnElImperio() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);
        Recurso mineralDelImperio = new Mineral(0);
        Edificio nexoMineral = new NexoMineral(mineralDelImperio);

        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);
        elMapa.colocarOcupable(nexoMineral, coordenada);

        for (int i = 0; i < 3; i++)
            nexoMineral.pasarTurno();

        nexoMineral.pasarTurno();

        assertTrue(mineralDelImperio.tenesCantidadDeRecurso(10));
    }

    @Test
    public void test03CreoUnNexoMineralYRevisoQueDepositeEnElImperio() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenada = new Coordenada(0, 0);
        Recurso gasDelImperio = new Mineral(0);
        Edificio Asimilador = new Asimilador(gasDelImperio);

        elMapa.colocarMaterial(new GasRecolectable(), coordenada);
        elMapa.colocarOcupable(Asimilador, coordenada);

        for (int i = 0; i < 5; i++)
            Asimilador.pasarTurno();

        Asimilador.pasarTurno();

        assertTrue(gasDelImperio.tenesCantidadDeRecurso(20));
    }

    @Test
    public void test04CreoUnExtractorYRevisoQueDepositeEnElImperio() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        Coordenada coordenadaExtractor = new Coordenada(0,5);
        Recurso gasDelImperio = new Gas(0);
        Edificio criadero = new Criadero();
        Edificio extractor = new Extractor(gasDelImperio);

        elMapa.colocarOcupable(criadero,coordenadaCriadero);

        for (int i = 0; i < 4; i++)
            criadero.pasarTurno();

        elMapa.colocarMaterial(new GasRecolectable(), coordenadaExtractor);
        elMapa.colocarOcupable(extractor, coordenadaExtractor);

        for (int i = 0; i < 6; i++)
            extractor.pasarTurno();

        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());
        extractor.contratarUnidad(new Zangano());

        extractor.pasarTurno();

        assertTrue(gasDelImperio.tenesCantidadDeRecurso(30));
    }
}