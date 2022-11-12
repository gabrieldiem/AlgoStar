package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Edificio_Protoss.Acceso;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccesoTest {

    @Test
    public void test01PuedoCrearUnAcceso(){
        Acceso unAcceso = new Acceso();

        assertNotNull(unAcceso);
    }

    @Test
    public void test02UnAccesoNoSeConstruyeEn7Turnos(){
        Acceso unAcceso = new Acceso();

        for (int i = 0; i < 7; i++){
            unAcceso.pasarTurno();
        }

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unAcceso.crearFabricaDragon());
    }

    @Test
    public void test03UnAccesoNoSeConstruyeEn7Turnos(){
        Acceso unAcceso = new Acceso();

        for (int i = 0; i < 7; i++){
            unAcceso.pasarTurno();
        }

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unAcceso.crearFabricaZealot());
    }

    @Test
    public void test04UnAccesoSeConstruyeEn8Turnos(){
        Acceso unAcceso = new Acceso();

        for (int i = 0; i < 8; i++){
            unAcceso.pasarTurno();
        }

        assertDoesNotThrow(() -> unAcceso.crearFabricaDragon());
    }

    @Test
    public void test05UnAccesoSeConstruyeEn8Turnos(){
        Acceso unAcceso = new Acceso();

        for (int i = 0; i < 8; i++){
            unAcceso.pasarTurno();
        }

        assertDoesNotThrow(() -> unAcceso.crearFabricaZealot());
    }
}
