package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoRecolector;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoRecolectorEnConstruccion;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SinMoho;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

import java.util.ArrayList;

public class NexoMineral extends EdificioProtoss {

    private EstadoRecolector estado;
    private int turnoParaEstarConstruido = 4;
    private Recurso mineralesDelImperio;
    private MaterialBruto nodoMineral = null;
    private int cantidadDeExtraccionUnitaria = 10;
    private int valorVital = 250;

    private static ArrayList<Edificio> requisitosEdilicios = new ArrayList<Edificio>();



    public NexoMineral(Recurso mineralesDelImperio) {
        this.costoMineral = 50;
        this.costoGas = 0;
        this.estadoRecolectable = new MineralRecolectable();
        this.estadoMoho = new SinMoho();
        this.vida = new VidaConEscudo(valorVital, valorVital);
        this.superficieRequerida = new SuperficieTerrestre();
        this.mineralesDelImperio = mineralesDelImperio;
        estado = new EstadoRecolectorEnConstruccion(turnoParaEstarConstruido);
        this.identificador = "nexo_mineral";
    }

    public ArrayList<Edificio> obtenerRequisitosEdilicios(){
        return requisitosEdilicios;
    }

    public void pasarTurno() {
        estado = estado.actualizar();
        this.extraer();
        vida.pasarTurno();
    }

    private void extraer() {
        estado.extraer(mineralesDelImperio, nodoMineral, cantidadDeExtraccionUnitaria);
    }

    public void verificarConstruccion(Casilla unaCasilla){
        super.verificarConstruccion(unaCasilla);
        establecerSobreMineral(unaCasilla.obtenerMaterial());
    }

    public void establecerSobreMineral(MaterialBruto nodoMineral){
        this.nodoMineral = nodoMineral;
    }

    @Override
    protected String obtenerEstado() {
        return estado.getEstado();
    }
}
