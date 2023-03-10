package edu.fiuba.algo3.casos_de_uso;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Scout;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Hidralisco;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CasoDeUso22Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01TengoCriaderoYPuedoConstruirUnZangano() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(),coordenadaCriadero);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(225), new Gas(0));

        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricasUnidadesZangano()));

        //pasa un turno y tengo un zangano
        imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneUnidad(new Zangano()));
    }

    @Test
    public void test02SiNoTengoReservaDeReproduccionNoPuedoConstruirUnZerlign() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(),coordenadaCriadero);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(200), new Gas(0));

        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> criadero.crearUnidad(new FabricasUnidadesZerling()));
    }

    @Test
    public void test03SiTengoReservaDeReproduccionPuedoConstruirUnZerlign() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(),coordenadaCriadero);
        Coordenada coordenadaReserva = new Coordenada(1,0);
        elMapa.colocarOcupable(new Zangano(),coordenadaReserva);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(1000), new Gas(0));

        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenadaReserva);

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(coordenadaCriadero);

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricasUnidadesZerling()));

        //No pasan los turnos, aun no tengo al Zerlign
        assertFalse(imperioZerg.tieneUnidad(new Zerling()));

        //Pasan dos turnos y lo tenemos
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneUnidad(new Zerling()));
    }

    @Test
    public void test04SiNoTengoGuaridaNoPuedoConstruirUnHidralisco() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(),coordenadaCriadero);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(5000), new Gas(1000));

        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(coordenadaCriadero);

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> criadero.crearUnidad(new FabricasUnidadesHidralisco()));
    }

    @Test
    public void test05SiTengoGuaridaPuedoConstruirUnHidralisco() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(),coordenadaCriadero);
        Coordenada coordenadaReserva = new Coordenada(1,0);
        elMapa.colocarOcupable(new Zangano(),coordenadaReserva);
        Coordenada coordenadaGuarida = new Coordenada(0,1);
        elMapa.colocarOcupable(new Zangano(),coordenadaGuarida);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(5000), new Gas(1000));

        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(),coordenadaReserva);

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEdificio(new FabricaGuarida(), coordenadaGuarida);

        //Esperamos a que se construya la guarida
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(coordenadaCriadero);

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricasUnidadesHidralisco()));
        //No pasan los turnos, aun no tengo al Hidralisco
        assertFalse(imperioZerg.tieneUnidad(new Hidralisco()));

        //Pasan los turnos y lo tenemos
        for (int i = 0; i < 4; i++)
            imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneUnidad(new Hidralisco()));
    }

    @Test
    public void test06SiNoTengoEspiralNoPuedoConstruirUnMutalisco() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(),coordenadaCriadero);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(5000), new Gas(1000));

        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(coordenadaCriadero);

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> criadero.crearUnidad(new FabricasUnidadesMutalisco()));
    }

    @Test
    public void test07SiTengoEspiralPuedoConstruirUnHidralisco() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(),coordenadaCriadero);
        Coordenada coordenadaReserva = new Coordenada(1,0);
        elMapa.colocarOcupable(new Zangano(),coordenadaReserva);
        Coordenada coordenadaGuarida = new Coordenada(0,1);
        elMapa.colocarOcupable(new Zangano(),coordenadaGuarida);
        Coordenada coordenadaEspiral = new Coordenada(1,1);
        elMapa.colocarOcupable(new Zangano(),coordenadaEspiral);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(5000), new Gas(1000));

        imperioZerg.construirEdificio(new FabricaCriadero(),coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenadaReserva);

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEdificio(new FabricaGuarida(), coordenadaGuarida);

        //Esperamos a que se construya la guarida
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEdificio(new FabricaEspiral(), coordenadaEspiral);

        //Espero a que se construya el espiral
        for (int i = 0; i < 10; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(coordenadaCriadero);

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricasUnidadesMutalisco()));
        //No pasan los turnos, aun no tengo al Hidralisco
        assertFalse(imperioZerg.tieneUnidad(new Mutalisco()));

        //Pasan los turnos y lo tenemos
        for (int i = 0; i < 7; i++)
            imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneUnidad(new Mutalisco()));
    }

    @Test
    public void test08TengoAccesoYPuedoConstruirUnZealor() {
        Protoss imperioProtoss = new Protoss();

        imperioProtoss.abastecerDeRecursos();
        imperioProtoss.construirEdificio(new FabricaPilon(), new Coordenada(0,0));

        //construimos el pilon
        for (int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada( 1, 0));

        //construimos el Acceso
        for (int i = 0; i < 8; i++)
            imperioProtoss.terminarTurno();

        Edificio acceso = imperioProtoss.conseguirEdificio(new Coordenada(1,0));

        assertDoesNotThrow(() -> acceso.crearUnidad(new FabricasUnidadesZealot()));
        //No pasan los turnos, aun no tengo al Zealot
        assertFalse(imperioProtoss.tieneUnidad(new Zealot()));

        //Pasan los turnos y lo tenemos
        for (int i = 0; i < 4; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tieneUnidad(new Zealot()));
    }

    @Test
    public void test09TengoAccesoYPuedoConstruirUnDragon() {
        Protoss imperioProtoss = new Protoss();

        imperioProtoss.abastecerDeRecursos();
        imperioProtoss.construirEdificio(new FabricaPilon(), new Coordenada(0,0));

        //construimos el pilon
        for (int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada( 1, 0));

        //construimos el Acceso
        for (int i = 0; i < 8; i++)
            imperioProtoss.terminarTurno();

        Edificio acceso = imperioProtoss.conseguirEdificio(new Coordenada(1,0));

        assertDoesNotThrow(() -> acceso.crearUnidad(new FabricasUnidadesDragon()));
        //No pasan los turnos, aun no tengo al Zealot
        assertFalse(imperioProtoss.tieneUnidad(new Dragon()));

        //Pasan los turnos y lo tenemos
        for (int i = 0; i < 6; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tieneUnidad(new Dragon()));
    }

    @Test
    public void test10NoTengoPuertoEstelarYNoPuedoConstruirUnScout() {
        Protoss imperioProtoss = new Protoss();

        imperioProtoss.abastecerDeRecursos();
        imperioProtoss.construirEdificio(new FabricaPilon(), new Coordenada(0,0));

        //construimos el pilon
        for (int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada( 1, 0));

        //construimos el Acceso
        for (int i = 0; i < 8; i++)
            imperioProtoss.terminarTurno();

        Edificio acceso = imperioProtoss.conseguirEdificio(new Coordenada(1,0));

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> acceso.crearUnidad((new FabricasUnidadesScout())));
    }

    @Test
    public void test11TengoPuertoEstelarYPuedoConstruirUnScout() {
        Protoss imperioProtoss = new Protoss();

        imperioProtoss.abastecerDeRecursos();
        imperioProtoss.construirEdificio(new FabricaPilon(), new Coordenada(0,0));

        //construimos el pilon
        for (int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada( 1, 0));

        //construimos el Acceso
        for (int i = 0; i < 8; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaPuertoEstelar(), new Coordenada(0,1));

        //construimos el puerto estelar
        for (int i = 0; i < 10; i++)
            imperioProtoss.terminarTurno();

        Edificio acceso = imperioProtoss.conseguirEdificio(new Coordenada(1,0));

        assertDoesNotThrow(() -> acceso.crearUnidad(new FabricasUnidadesScout()));
        //No pasan los turnos, aun no tengo al Zealot
        assertFalse(imperioProtoss.tieneUnidad(new Scout()));

        //Pasan los turnos y lo tenemos
        for (int i = 0; i < 9; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tieneUnidad(new Scout()));
    }
}
