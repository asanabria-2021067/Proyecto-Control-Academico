package org.in5bm.asanabria.jbeltran.system;

import org.in5bm.asanabria.jbeltran.controllers.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Angel Sanabria y Gabriel Beltran
 * @date 29/04/2022
 * @time 16:05:06
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067, 2021022
 */
public class Principal extends Application{

    private final String PAQUETE_VIEW = "../views/";
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";
    private Stage escenarioPrincipal;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.escenarioPrincipal = primaryStage;
        try {
            this.escenarioPrincipal.getIcons().add(new Image(PAQUETE_IMAGE + "logoBarra.png"));
            this.escenarioPrincipal.setResizable(false);
            this.escenarioPrincipal.centerOnScreen();
            this.escenarioPrincipal.setTitle(("Control Academico - Angel Sanabria - Gabriel Beltran"));
            //this.escenarioPrincipal.show();
            mostrarEscenaPrincipal();
            //this.mostrarEscenaAlumno();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Initializable cambiarEscena(String vistaFxml, int alto, int ancho) throws IOException{
        FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(this.PAQUETE_VIEW + vistaFxml));
        Scene scene = new Scene((AnchorPane) cargadorFXML.load(), alto, ancho);
        escenarioPrincipal.setScene(scene);
        this.escenarioPrincipal.sizeToScene();
        this.escenarioPrincipal.show();
        return (Initializable)cargadorFXML.getController();
    }
    public void mostrarEscenaPrincipal(){
        try {
            ControladorMenuPrincipal menuController = (ControladorMenuPrincipal) cambiarEscena("MenuPrincipalView.fxml",1200,700);
            menuController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("Se produjo un error");
            ex.printStackTrace();
        }
    }
    public void mostrarEscenaAlumno(){
        //ControladorAlumnos controllerA = new ControladorAlumnos();
        try {
            ControladorAlumnos alumnosController = (ControladorAlumnos) cambiarEscena("AlumnosView.fxml",1200,700);
            alumnosController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("Se produjo un error");
            ex.printStackTrace();
        }
    }
    public void mostrarEscenaCursos(){
        try {
            ControladorCursos cursosController = (ControladorCursos) cambiarEscena("CursosView.fxml",1200,700);
            cursosController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("Se produjo un error");
            ex.printStackTrace();
        }
    }
    public void mostrarEscenaInstructor(){
        try {
            ControladorInstructores instructoresController = (ControladorInstructores)cambiarEscena("InstructoresView.fxml",1200,700);
            instructoresController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("Se produjo un error");
            ex.printStackTrace();
        }
    }
    public void mostrarEscenaAsignacion(){
        try {
            ControladorAsignacionAlumnos asignacionController = (ControladorAsignacionAlumnos)cambiarEscena("AsignacionAlumnosView.fxml",1200,700);
            asignacionController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("Se produjo un error");
            ex.printStackTrace();
        }
    }
    public void mostrarEscenaSalones(){
        try {
            ControladorSalones salonesController = (ControladorSalones)cambiarEscena("SalonesView.fxml",1200,700);
            salonesController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("Se produjo un error");
            ex.printStackTrace();
        }
    }
    public void mostrarEscenaCarreras(){
        try {
            ControladorCarrerasTecnicas carrerasController = (ControladorCarrerasTecnicas)cambiarEscena("CarrerasTecnicasView.fxml",1200,700);
            carrerasController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("Se produjo un error");
            ex.printStackTrace();
        }
    }
    public void mostrarEscenaHorarios(){
        try {
            ControladorHorarios horariosController = (ControladorHorarios)cambiarEscena("HorariosView.fxml",1200,700);
            horariosController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("Se produjo un error");
            ex.printStackTrace();
        }
    }
    public void mostrarAcercaDe(){
        //ControladorAlumnos controllerA = new ControladorAlumnos();
        try {
            ControladorAcercaDe acercaDeController = (ControladorAcercaDe) cambiarEscena("AcercaDeView.fxml",600,400);
            acercaDeController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("Se produjo un error");
            ex.printStackTrace();
        }
    }
}