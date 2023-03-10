package edu.fiuba.algo3.casos_de_uso;

import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaAsimilador;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaNexoMineral;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasoDeUso15Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01NoSePuedeRecolectarGasDespuesDeAgotarTodoElRecursoDelVolcan() {
        Mapa mapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenadasGas = new Coordenada(0,0);

        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);
        protoss.abastecerDeRecursos(new Mineral(100), new Gas(0));
        protoss.construirEdificio(new FabricaAsimilador(), coordenadasGas);

        //Construyo el asimilador
        for (int i = 0; i < 5; i++)
            protoss.terminarTurno();

        //  Saco completamente el material
        for (int i = 0; i < 250; i++)
            protoss.terminarTurno();

        //Verifico haber sacado la totalidad
        assertTrue(protoss.tienesEstaCantidadDeGas(5000));

        //Intento minar otra vez y no obtengo nada
        protoss.terminarTurno();

        assertTrue(protoss.tienesEstaCantidadDeGas(5000));
    }

    @Test
    public void test02NoSePuedeRecolectarMineralDespuesDeAgotarTodoElRecursoDeUnNodoMineral(){
        Mapa mapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenadaMineral = new Coordenada(0,0);

        mapa.colocarMaterial(new MineralRecolectable(), coordenadaMineral);
        protoss.abastecerDeRecursos(new Mineral(50), new Gas(0));
        protoss.construirEdificio(new FabricaNexoMineral(), coordenadaMineral);

        //Construyo el nexo
        for (int i = 0; i < 3; i++)
            protoss.terminarTurno();

        //Gasto el mineral
        for (int i = 0; i < 200; i++)
            protoss.terminarTurno();

        assertTrue(protoss.tienesEstaCantidadDeMineral(2000));

        //Pasa otro turno y no obteno mas
        protoss.terminarTurno();

        assertTrue(protoss.tienesEstaCantidadDeMineral(2000));
    }
}
