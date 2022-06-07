
package org.in5bm.asanabria.jbeltran.controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.in5bm.asanabria.jbeltran.db.ConexionDb;
import org.in5bm.asanabria.jbeltran.models.CarreraTecnica;
import org.in5bm.asanabria.jbeltran.models.Curso;
import org.in5bm.asanabria.jbeltran.models.Horario;
import org.in5bm.asanabria.jbeltran.models.Instructor;
import org.in5bm.asanabria.jbeltran.models.Salon;
import org.in5bm.asanabria.jbeltran.system.Principal;

/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:10:17
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class ControladorCursos implements Initializable{
   private Principal escenarioPrincipal;

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
        cargarCombos();
    }
     @FXML
    private HBox hboxAñadir;

    @FXML
    private Button btnNuevo;

    @FXML
    private ImageView imageNuevo;

    @FXML
    private HBox hboxModificar;

    @FXML
    private Button btnModificar;

    @FXML
    private ImageView imageModificar;

    @FXML
    private HBox hboxEliminar;

    @FXML
    private Button btnEliminar;

    @FXML
    private ImageView imageEliminar;

    @FXML
    private HBox hboxReporte;

    @FXML
    private Button btnReporte;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombreCurso;

    @FXML
    private Spinner<Integer> spCupoMaximo;

    @FXML
    private Spinner<Integer> spCupoMinimo;

    @FXML
    private Spinner<Integer> spCiclo;

    @FXML
    private ComboBox<CarreraTecnica> cmbCarreras;

    @FXML
    private ComboBox<Horario> cmbHorario;

    @FXML
    private ComboBox<Instructor> cmbInstructor;

    @FXML
    private ComboBox<Salon> cmbSalon;

    @FXML
    private TableView<Curso> tblCursos;

    @FXML
    private TableColumn<Curso, Integer> colId;

    @FXML
    private TableColumn<Curso, String> colNombre;

    @FXML
    private TableColumn<Curso, Integer> colCiclo;

    @FXML
    private TableColumn<Curso, Integer>colCupoMax;

    @FXML
    private TableColumn<Curso, Integer> colCupoMin;

    @FXML
    private TableColumn<Curso, String> colCarreras;

    @FXML
    private TableColumn<Curso, Integer> colHorarios;

    @FXML
    private TableColumn<Curso, Integer> colInstructor;

    @FXML
    private TableColumn<Curso, Integer> colSalon;
    
    
    public void cargarCombos(){
        ArrayList<Curso> combo1= new ArrayList<>(); 
        try{
            String SQL= "SELECT DISTINCT cursos.carreras_tecnicas_id AS ID , carreras_tecnicas.carrera AS CARRERAS FROM cursos, carreras_tecnicas WHERE cursos.carreras_tecnicas_id = carreras_tecnicas.codigo_tecnico ORDER BY carrera ASC";
            
            
            PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            //cmbCarreras.setId("Seleccione una carrera");
            while (rs.next()){
                Curso curso = new Curso();
                CarreraTecnica carrera = new CarreraTecnica();
                curso.setCarreraTecnicaId(rs.getString("ID"));
                carrera.setCarrera(rs.getString("CARRERAS"));
                
                combo1.add(curso);
                //combo1.add(carrera);
                //cmbCarreras.getItems().add(curso);
                //cmbCarreras.getItems().addAll(rs.getInt("ID"), rs.getString("CARRERAS"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    /*
        try{
            String SQL= "SELECT DISTINCT seccion FROM carreras_tecnicas ORDER BY seccion ASC";
            PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            cboxSeccion.setId("Seleccione una seccion");
            while (rs.next()){
                cboxSeccion.getItems().addAll(rs.getString("seccion"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            String SQL= "SELECT DISTINCT grado FROM carreras_tecnicas ORDER BY grado ASC";
            PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            cboxGrado.setId("Seleccione un grado");
            while (rs.next()){
                cboxGrado.getItems().addAll(rs.getString("grado"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        */
     
}
