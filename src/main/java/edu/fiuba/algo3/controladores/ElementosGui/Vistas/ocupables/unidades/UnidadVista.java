package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios.CriaderoVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios.ReservaVista;
import edu.fiuba.algo3.controladores.MapaControlador;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;

import java.util.Objects;

public abstract class UnidadVista extends OcupableVista {

    protected void crearBoton(Button boton, Image imagenAColocar, double ancho, double alto){
        ImageView imagen = new ImageView();
        imagen.setPreserveRatio(false);
        imagen.setSmooth(true);

        imagen.setImage(imagenAColocar);

        imagen.setFitWidth(ancho);
        imagen.setFitHeight(alto);

        DropShadow efectorBordePNGBlanco = new DropShadow( 1, Color.WHITE );
        imagen.setEffect(efectorBordePNGBlanco);

        boton.setVisible(true);
        boton.setDisable(false);
        boton.setGraphic(imagen);
    }

    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual, MapaControlador mapaControlador){
        Button botonMover = arrayBotones[0];
        Button botonAtacar = arrayBotones[1];

        Unidad unidad = (Unidad) Mapa.obtener().obtenerOcupable(coordenada);

        double anchoBoton = botonMover.getPrefWidth();
        double altoBoton = botonMover.getPrefWidth();

        crearBoton(botonMover, new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/especial/mover.png"))), anchoBoton, altoBoton);
        crearBoton(botonAtacar, new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/especial/atacar.png"))), anchoBoton, altoBoton);

        botonMover.setOnAction(event -> mapaControlador.guardarCasillaMovimiento(coordenada));
        botonAtacar.setOnAction(actionEvent -> {});

        botonMover.setTooltip( new Tooltip("MOVER UNIDAD" + "\nMover a la unidad a otra casilla"));
        botonAtacar.setTooltip( new Tooltip("ATACAR" + "\nAtacar a un edificio o unidad"));

        JSONObject unidadJSON = ConvertidorJSON.convertirAJSON(unidad);
        if (unidadJSON.get(ConvertidorJSON.CAMINAR).equals("no_caminar")){
            botonMover.setDisable(true);
            botonMover.setTooltip(new Tooltip("MOVER UNIDAD" + "\nEsta unidad no se puede mover"));
        }
    }

}
