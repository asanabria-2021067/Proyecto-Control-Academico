/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.in5bm.asanabria.jbeltran.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.in5bm.asanabria.jbeltran.db.ConexionDb;
import org.in5bm.asanabria.jbeltran.models.Alumno;
import org.in5bm.asanabria.jbeltran.system.Principal;
import org.in5bm.asanabria.jbeltran.models.AsignacionAlumno;
import org.in5bm.asanabria.jbeltran.models.Curso;



/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:26:28
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class ControladorAsignacionAlumnos implements Initializable {
    private Principal escenarioPrincipal;
    private enum Operacion {
        NINGUNO, GUARDAR, ACTUALIZAR
    }
    int numero;
    ConexionDb con;
    private Operacion operacion = Operacion.NINGUNO;
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";
    
    @FXML
    private HBox hboxAñadir;
    @FXML
    private HBox hboxModificar;
    @FXML
    private HBox hboxEliminar;
    @FXML
    private HBox hboxReporte;
    @FXML
    private TextField textCarne;
    @FXML
    private ComboBox<Alumno> cmbAlumno;
    @FXML
    private ComboBox<Curso> cmbCurso;
    @FXML
    private TableView<AsignacionAlumno> tblAsignacionAlumno;
    @FXML
    private TableColumn<AsignacionAlumno,Integer> colId;
    @FXML
    private TableColumn<AsignacionAlumno, String> colAlumnoId;
    @FXML
    private TableColumn<AsignacionAlumno, Integer> colCursoId;
    @FXML
    private TableColumn<AsignacionAlumno, LocalDate> colFechaAsignacion;

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    @FXML
    private void clickRegresar(ActionEvent event){
        escenarioPrincipal.mostrarEscenaPrincipal();
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
    
    
}
