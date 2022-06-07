package org.in5bm.asanabria.jbeltran.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.in5bm.asanabria.jbeltran.system.Principal;
/**
 *
 * @author Angel Sanabria y Gabriel Beltran
 * @date 29/04/2022
 * @time 16:05:06
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067, 2021022
 */
public class ControladorMenuPrincipal implements Initializable {
    ControladorAlumnos alumno = new ControladorAlumnos();
    private Principal escenarioPrincipal;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private MenuItem moduloAlumnos;

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    @FXML
    private MenuItem moduloAsignacionAlumnos;

    @FXML
    private MenuItem moduloCarrerasTecnicas;

    @FXML
    private MenuItem moduloCursos;

    @FXML
    private MenuItem moduloHorarios;

    @FXML
    private MenuItem moduloInstructores;

    @FXML
    private MenuItem moduloSalones;

    @FXML
    private void ventanaAlumno(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaAlumno();
    } 

    @FXML
    void ventanaAsignacion(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaAsignacion();
    }
    
    @FXML
    void ventanaCarreras(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaCarreras();
    }

    @FXML
    void ventanaCurso(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaCursos();
    }

    @FXML
    void ventanaHorario(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaHorarios();
    }

    @FXML
    void ventanaInstructor(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaInstructor();
    }

    @FXML
    void ventanaSalon(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaSalones();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
}
