package edu.fiuba.algo3.modelo.States;

import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MineralBruto;

public class EstadoNexoMineralEnConstruccion implements EstadoNexoMineral{

    private int turnoParaEstarConstruido;

    public EstadoNexoMineralEnConstruccion(int turnoParaEstarConstruido){
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    public EstadoNexoMineral actualizar(){
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoNexoMineralConstruido();

        return this;
    }

    public void extraer(Recurso mineralesDelImperio, MineralBruto nodoMineral){
        throw new ErrorEdificioNoEstaConstruido();
    }
}
