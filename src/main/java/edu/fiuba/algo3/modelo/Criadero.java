package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;

public class Criadero extends Edificio {

    private Recurso minerales;
    private Imperio zergs;
    private final int maxLarvas = 3;
    private int cantidadLarvas;

    public Criadero(NodoCompatible requisitos, Recurso _minerales, Imperio _zergs) {
        this.nodoCompatible = requisitos;
        this.minerales = _minerales;
        this.zergs = _zergs;
        cantidadLarvas = maxLarvas;
    }

    public Zangano criarZangano() {
        if (cantidadLarvas == 0)
            throw new CriaderoSinLarvas();

        cantidadLarvas--;
        return new Zangano(new Coordenadas(0,0), minerales);
    }

    public void accionDeTurno() {
        aumentarLarvas();
    }

    private void aumentarLarvas() {
        if (cantidadLarvas < maxLarvas)
            cantidadLarvas++;
    }
}
