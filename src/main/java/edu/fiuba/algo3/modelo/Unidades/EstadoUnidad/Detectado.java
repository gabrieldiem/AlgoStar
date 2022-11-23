package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SinRevelar;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;

public class Detectado implements Visibilidad {

    private Zealot unZealot;

    public Detectado(Zealot unZealot) {
        this.unZealot = unZealot;
    }

    @Override
    public void recibirAtaque(Ataque unAtaque) {
        unZealot.recibirAtaqueDefault(unAtaque);
    }

    @Override
    public Visibilidad verificarVisibilidadDe(Casilla unaCasilla) {
        if (unaCasilla.tieneEsteRevelable(new SinRevelar())) {
            return new Invisible(unZealot);
        }

        return this;
    }

    @Override
    public Visibilidad aumentarContador() {
        return this;
    }
}