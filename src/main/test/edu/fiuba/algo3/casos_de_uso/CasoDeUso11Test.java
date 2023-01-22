package edu.fiuba.algo3.casos_de_uso;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.DanioTerrestre;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso11Test {

    @Test
    public void test01PuedoDaniarElEscudoDeUnAccesoSeRegeneraYPuedoDaniarloDeVuelta(){
        Acceso unAcceso = new Acceso();
        Ataque unAtaque = new Ataque(new DanioTerrestre(500));
        Ataque ataqueCasiLetal = new Ataque(new DanioTerrestre(999));

        // Acceso tiene 500E / 500V
        unAcceso.recibirAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow( () -> unAcceso.recibirAtaque(ataqueCasiLetal) );
    }

    @Test
    public void test02PuedoDaniarElEscudoDeUnNexoMineralSeRegeneraYPuedoDaniarloDeVuelta(){
        Recurso mineralesDelImperio = new Recurso(0);
        NexoMineral unNexoMineral = new NexoMineral(mineralesDelImperio);
        Ataque unAtaque = new Ataque(new DanioTerrestre(250));
        Ataque ataqueCasiLetal = new Ataque(new DanioTerrestre(499));

        // Nexo Mineral tiene 250E / 250V
        unNexoMineral.recibirAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unNexoMineral.pasarTurno();

        assertDoesNotThrow( () -> unNexoMineral.recibirAtaque(ataqueCasiLetal) );
    }

    @Test
    public void test03PuedoDaniarElEscudoDeUnPilonYPuedoDaniarloDeVuelta(){
        Pilon unPilon = new Pilon();
        Ataque unAtaque = new Ataque(new DanioTerrestre(300));
        Ataque ataqueCasiLetal = new Ataque(new DanioTerrestre(599));
        unPilon.asignarSuministro(new Suministro(0));

        // Pilon tiene 300E / 300V
        unPilon.recibirAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unPilon.pasarTurno();

        assertDoesNotThrow( () -> unPilon.recibirAtaque(ataqueCasiLetal) );
    }

    @Test
    public void test04PuedoDaniarElEscudoDeUnAsimiladorYPuedoDaniarloDeVuelta(){
        Recurso gasDelImperio = new Recurso(0);
        Asimilador unAsimilador = new Asimilador(gasDelImperio);
        Ataque unAtaque = new Ataque(new DanioTerrestre(300));
        Ataque ataqueCasiLetal = new Ataque(new DanioTerrestre(599));

        // Asimilador tiene 450E / 450V
        unAsimilador.recibirAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unAsimilador.pasarTurno();

        assertDoesNotThrow( () -> unAsimilador.recibirAtaque(ataqueCasiLetal) );
    }

    @Test
    public void test05PuedoDaniarElEscudoDeUnPuertoEstelarYPuedoDaniarloDeVuelta() {
        PuertoEstelar unPuertoEstelar = new PuertoEstelar();
        Ataque unAtaque = new Ataque(new DanioTerrestre(600));
        Ataque ataqueCasiLetal = new Ataque(new DanioTerrestre(1199));

        // Puerto estelar tiene 600E / 600V
        unPuertoEstelar.recibirAtaque(unAtaque);

        for (int i = 0; i < 7; i++)
            unPuertoEstelar.pasarTurno();

        assertDoesNotThrow(() -> unPuertoEstelar.recibirAtaque(ataqueCasiLetal));
    }
}
