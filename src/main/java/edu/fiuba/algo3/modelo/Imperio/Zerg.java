package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.GestorDeCrianza;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoHayMutaliscoParaEvolucionar;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Devorador;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;

import java.util.ArrayList;
import java.util.LinkedList;


public class Zerg extends Imperio {

    LinkedList<GestorDeCrianza> gestoresDeEvoluciones = new LinkedList<>();
    //GestorDeCrianza gestorDeEvoluciones = new GestorDeCrianza();

    public Zerg() {
        mineralesDelImperio = new Mineral(cantidadInicialDeMineral);
        gasDelImperio = new Gas(0);
        this.poblacion = new Suministro(0);
        edificios = new LinkedList<>();
        this.fabricasDisponibles = new FabricasDisponibles();
        unidades = new ArrayList<>();
        this.identificador = "zerg";
    }

    public void inicializarAsentamientoPrimerTurno() {
        Mapa elMapa = Mapa.obtener();

        Casilla casillaBase = elMapa.obtenerVolcanBaseLejanaPrimeraMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaCriadero = new Coordenada(coordenadaBase.getCoordenadaX() - 2, coordenadaBase.getCoordenadaY());

        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarListaDeUnidadesImperio(unidades);
        this.construirEdificioSinVerificacionesMateriales(unCriadero, coordenadaCriadero);
        unCriadero.asignarRecursos(mineralesDelImperio, gasDelImperio);
        unCriadero.construirInmediatamente();
    }

    private void verificarZangano(Coordenada coordenada) {
        Mapa elMapa = Mapa.obtener();
        if (!elMapa.tieneEsteOcupable(new Zangano(), coordenada))
            throw new ErrorEdificioNoSePuedeConstruirEnEstaCasilla();

        ((Unidad) elMapa.obtenerOcupable(coordenada)).destruirUnidad();
    }

    public void construirCriadero(Coordenada coordenada) {
        Criadero criadero = new Criadero();
        criadero.asignarListaDeUnidades(fabricasDisponibles);
        criadero.asignarListaDeUnidadesImperio(unidades);
        criadero.asignarRecursos(mineralesDelImperio, gasDelImperio);
        this.comprobarRequisitosMateriales(criadero);
        verificarZangano(coordenada);
        this.construirEdificio(criadero, coordenada);
    }

    public void construirReservaDeReproduccion(Coordenada coordenada) {
        ReservaDeReproduccion reserva = new ReservaDeReproduccion();
        reserva.asignarListaDeUnidades(fabricasDisponibles);
        this.comprobarRequisitosMateriales(reserva);
        verificarZangano(coordenada);
        this.construirEdificio(reserva, coordenada);
    }

    public void construirExtractor(Coordenada coordenada) {
        Extractor extractor = new Extractor(gasDelImperio);
        this.comprobarRequisitosMateriales(extractor);
        verificarZangano(coordenada);
        this.construirEdificio(extractor, coordenada);
    }

    public void construirGuarida(Coordenada coordenada) {
        this.comprobarRequisitos(Guarida.requisitos());
        Guarida guarida = new Guarida();
        guarida.asignarListaDeUnidades(fabricasDisponibles);
        this.comprobarRequisitosMateriales(guarida);
        verificarZangano(coordenada);
        this.construirEdificio(guarida, coordenada);
    }

    public void construirEspiral(Coordenada coordenada) {
        this.comprobarRequisitos(Espiral.requisitosEdilicios());
        Espiral espiral = new Espiral();
        espiral.asignarListaDeUnidades(fabricasDisponibles);
        this.comprobarRequisitosMateriales(espiral);
        verificarZangano(coordenada);
        this.construirEdificio(espiral, coordenada);
    }

    private void validarPreRequisitosDeEvolucionDeMutalisco(Unidad unaUnidad){
        //revisamos que tengamos al mutalisco
        if (!this.tieneUnidad(new Mutalisco())) {
            throw new ErrorNoHayMutaliscoParaEvolucionar();
        }
        //comprobamos los materiales
        this.comprobarRequisitosMateriales(unaUnidad);

        //sacamos el mutalisco
        Mutalisco mutalisco = new Mutalisco();
        for (Unidad unidad : unidades) {
            if (unidad.esIgualA(mutalisco)){
                Mapa.obtener().quitarUnidad(unidad.obtenerCoordenada());
                unidades.remove(unidad);

                GestorDeCrianza nuevoGestorEvoluciones = new GestorDeCrianza(unidad.obtenerCoordenada());
                nuevoGestorEvoluciones.agregarUnidad(unaUnidad, unidades, mineralesDelImperio);
                gestoresDeEvoluciones.add(nuevoGestorEvoluciones);

                break;
            }
        }
    }

    public void evolucionarMutaliscoAGuardian(){
        Unidad guardian = new Guardian();
        validarPreRequisitosDeEvolucionDeMutalisco(guardian);
    }

    public void evolucionarMutaliscoADevorador() {
        Unidad devorador = new Devorador();
        validarPreRequisitosDeEvolucionDeMutalisco(devorador);
    }

    @Override
    public void terminarTurno(){
        super.terminarTurno();
        for(GestorDeCrianza gestorDeEvoluciones : gestoresDeEvoluciones)
            gestorDeEvoluciones.actualizar();
    }

    @Override
    public void prepararParaRevancha(){
        mineralesDelImperio = new Mineral(cantidadInicialDeMineral);
        gasDelImperio = new Gas(0);
        this.poblacion = new Suministro(0);
        edificios = new LinkedList<>();
        this.fabricasDisponibles = new FabricasDisponibles();
        unidades = new ArrayList<>();
    }

    private void verificarZanganoSoloComprobacion(Coordenada coordenada){
        Mapa elMapa = Mapa.obtener();
        if (!elMapa.tieneEsteOcupable(new Zangano(), coordenada))
            throw new ErrorEdificioNoSePuedeConstruirEnEstaCasilla();
    }

    public void verificarConstruccionDeEdificio(Edificio unEdificio, Coordenada coordenada){
        comprobarRequisitosMaterialesVerificacion(unEdificio);
        verificarZanganoSoloComprobacion(coordenada);
        Mapa.obtener().construirEdificioVerificacion(unEdificio, coordenada);
        this.comprobarRequisitos(unEdificio.obtenerRequisitosEdilicios());
    }
}
