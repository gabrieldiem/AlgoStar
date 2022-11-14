package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public class CasillaVacia extends Casilla{

    public CasillaVacia(Coordenada coordenada){
        estadoRecolectable = new NoRecolectable();
        estadoMoho = new SinMoho();
        estadoCarga = new SinCarga();
        this.coordenada = coordenada;
    }

    public Casilla construirEdificio(Edificio unEdificio){

        unEdificio.verificarConstruccion(this);

        return new CasillaOcupada(coordenada);
    }

    public Coordenada obtenerCoordenada(){
        return this.coordenada;
    }
}