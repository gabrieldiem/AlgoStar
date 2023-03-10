package edu.fiuba.algo3.casos_de_uso;

import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.*;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasoDeUso29Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01InicioZergCreoCriaderosParaChequearQueAumenteBienElSuministro(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(9000), new Gas(9000));
        for ( int i = 1; i <41; i++){
            Coordenada unaCoordenada = new Coordenada(i,0);
            elMapa.colocarOcupable(new Zangano(), unaCoordenada);
            imperioZerg.construirEdificio(new FabricaCriadero(), unaCoordenada);
        }

        for ( int j = 1; j < 5 ; j++ ){
            imperioZerg.terminarTurno();
        }

        assertTrue(imperioZerg.tenesEsteSuministro(200));

    }

    @Test
    public void test02InicioZergCreoCriaderosParaChequearQueNoPaseElLimiteDeSuministros(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(15000), new Gas(15000));
        for ( int i = 1; i <50; i++){
            Coordenada unaCoordenada = new Coordenada(i,0);
            elMapa.colocarOcupable(new Zangano(), unaCoordenada);
            imperioZerg.construirEdificio(new FabricaCriadero(), unaCoordenada);
        }

        for ( int j = 1; j < 5 ; j++ ){
            imperioZerg.terminarTurno();
        }

        assertTrue(imperioZerg.tenesEsteSuministro(200));

    }

    @Test
    public void test03Creo39CriaderosYMeFalta5ParaElMaximoYNoLlegoCreandoOtrasConstrucciones(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(9000), new Gas(9000));
        for ( int i = 1; i <40; i++){
            Coordenada unaCoordenada = new Coordenada(i,0);
            elMapa.colocarOcupable(new Zangano(), unaCoordenada);
            imperioZerg.construirEdificio(new FabricaCriadero(), unaCoordenada);

        }

        for ( int j = 1; j < 5 ; j++ ){
            imperioZerg.terminarTurno();
        }

        Coordenada unaCoordenada = new Coordenada(1,1);

        elMapa.colocarOcupable(new Zangano(), unaCoordenada);
        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), unaCoordenada);

        unaCoordenada = new Coordenada(2,1);
        elMapa.colocarOcupable(new Zangano(), unaCoordenada);
        imperioZerg.construirEdificio(new FabricaGuarida(), unaCoordenada);

        unaCoordenada = new Coordenada(3,1);
        elMapa.colocarOcupable(new Zangano(), unaCoordenada);
        imperioZerg.construirEdificio(new FabricaEspiral(), unaCoordenada);

        for ( int j = 1; j < 16 ; j++ ){
            imperioZerg.terminarTurno();
        }

        assertTrue(imperioZerg.tenesEsteSuministro(195));
    }

    @Test
    public void test04InicioProtossCreoPilonesParaChequearQueAumenteBienElSuministro(){
        Protoss imperioProtoss = new Protoss();

        imperioProtoss.abastecerDeRecursos(new Mineral(9000), new Gas(9000));
        for ( int i = 1; i <41; i++){
            Coordenada unaCoordenada = new Coordenada(i,0);
            imperioProtoss.construirEdificio(new FabricaPilon(), unaCoordenada);
        }

        for ( int j = 1; j < 6 ; j++ ){
            imperioProtoss.terminarTurno();
        }

        assertTrue(imperioProtoss.tenesEsteSuministro(200));

    }

    @Test
    public void test05InicioProtossCreoPilonesParaChequearQueNoAumenteMasDelLimite(){
        Protoss imperioProtoss = new Protoss();

        imperioProtoss.abastecerDeRecursos(new Mineral(9000), new Gas(9000));
        for ( int i = 1; i <50; i++){
            Coordenada unaCoordenada = new Coordenada(i,0);
            imperioProtoss.construirEdificio(new FabricaPilon(), unaCoordenada);
        }

        for ( int j = 1; j < 6 ; j++ ){
            imperioProtoss.terminarTurno();
        }

        assertTrue(imperioProtoss.tenesEsteSuministro(200));

    }

    @Test
    public void test06Creo39PilonesYMeFalta5ParaElMaximoYNoLlegoCreandoOtrasConstrucciones(){
        Protoss imperioProto = new Protoss();

        imperioProto.abastecerDeRecursos(new Mineral(15000), new Gas(15000));
        for ( int i = 1; i <40; i++){
            Coordenada unaCoordenada = new Coordenada(i,0);
            imperioProto.construirEdificio(new FabricaPilon(), unaCoordenada);

        }

        for ( int j = 1; j < 6 ; j++ ){
            imperioProto.terminarTurno();
        }

        Coordenada unaCoordenada = new Coordenada(1,1);
        imperioProto.construirEdificio(new FabricaAcceso(), unaCoordenada);

        unaCoordenada = new Coordenada(2,1);
        imperioProto.construirEdificio(new FabricaPuertoEstelar(), unaCoordenada);

        for ( int j = 1; j < 16 ; j++ ){
            imperioProto.terminarTurno();
        }

        assertTrue(imperioProto.tenesEsteSuministro(195));
    }
}
