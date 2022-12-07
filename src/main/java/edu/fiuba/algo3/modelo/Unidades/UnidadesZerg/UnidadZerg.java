package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Excepciones.ErrorFuegoCompañero;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

public abstract class UnidadZerg extends Unidad {

    protected MaterialBruto recursoARecolectar;
    protected Recurso mineralDelImperio;

    public boolean perteneceAImperio(Imperio imperio) {
        return imperio.getClass().equals(Zerg.class);
    }


    public void setDepositoRecurso( Recurso recursoImperio ){
    }

    public void extraer(){
    }

    @Override
    protected void verificarFuegoAliado(Casilla casillaAAtacar) {
        if (casillaAAtacar.esFuegoAliado(new Zerg()))
            throw new ErrorFuegoCompañero();
    }
}
