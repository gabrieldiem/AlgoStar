package edu.fiuba.algo3.casos_de_uso;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.DanioTerrestre;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso13Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01SeDestruyeUnCriaderoYsePuedeSerguirConstruyendoSobreElMohoQueDejo(){
        // En esta prueba genero un criadero para poner moho sobre una casilla y luego
        // construir una reserva de produccion
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,1);
        Criadero criadero = new Criadero();
        Ataque unAtaque = new Ataque(new DanioTerrestre(500));

        elMapa.colocarOcupable(criadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            criadero.pasarTurno();

        criadero.recibirAtaque(unAtaque);

        assertDoesNotThrow(() -> elMapa.colocarOcupable(new ReservaDeReproduccion(), coordenadaCriadero));
    }
}
