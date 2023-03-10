package edu.fiuba.algo3.clases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesDragon;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesZealot;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccesoTest {

    @Test
    public void test01PuedoCrearUnAcceso(){
        Acceso unAcceso = new Acceso();

        assertNotNull(unAcceso);
    }

    @Test
    public void test02UnAccesoNoSeConstruyeEn7TurnosIntentandoCrearFabricaDragon(){
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);

        for (int i = 0; i < 7; i++)
            unAcceso.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unAcceso.crearUnidad(new FabricasUnidadesDragon()));
    }

    @Test
    public void test03UnAccesoNoSeConstruyeEn7TurnosIntentandoCrearFabricaZealot(){
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);

        for (int i = 0; i < 7; i++)
            unAcceso.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unAcceso.crearUnidad(new FabricasUnidadesZealot()));
    }

    @Test
    public void test04UnAccesoSeConstruyeEn8TurnosIntentandoCrearFabricaDragon(){
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);
        unAcceso.asignarRecursos(new Mineral(1000), new Gas(1000));
        unAcceso.asignarSuministro(new Suministro(3));

        for (int i = 0; i < 8; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow(() -> unAcceso.crearUnidad(new FabricasUnidadesDragon()));
    }

    @Test
    public void test05UnAccesoSeConstruyeEn8TurnosIntentandoCrearFabricaZealot(){
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);
        unAcceso.asignarRecursos(new Mineral(1000), new Gas(1000));
        unAcceso.asignarSuministro(new Suministro(2));

        for (int i = 0; i < 8; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow(() -> unAcceso.crearUnidad(new FabricasUnidadesZealot()));
    }
}
