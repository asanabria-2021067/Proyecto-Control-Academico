
package org.in5bm.asanabria.jbeltran.controllers;

import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.in5bm.asanabria.jbeltran.db.ConexionDb;
import org.in5bm.asanabria.jbeltran.models.Horario;
import org.in5bm.asanabria.jbeltran.system.Principal;

/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:25:04
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class ControladorHorarios implements Initializable {
    private Principal escenarioPrincipal;
    private enum Operacion {
        NINGUNO, GUARDAR, ACTUALIZAR
    }
    int numero;
    ConexionDb con;
    Horario horario = new Horario();
    private Operacion operacion = Operacion.NINGUNO;
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";
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
        cargarDatos();
    }
    
    @FXML
    private HBox hboxAñadir;
     
    @FXML
    private TableView<?> tblHorarios;
    
    @FXML
    private HBox hboxModificar;

    @FXML
    private HBox hboxEliminar;

    @FXML
    private HBox hboxReporte;

    @FXML
    private TextField txtId;

    @FXML
    private CheckBox cbLunes;

    @FXML
    private CheckBox cbMartes;

    @FXML
    private CheckBox cbMiercoles;

    @FXML
    private CheckBox cbJueves;

    @FXML
    private CheckBox cbViernes;

    @FXML
    private JFXTimePicker dpInicio;

    @FXML
    private JFXTimePicker dpFinal;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colHorarioInicio;

    @FXML
    private TableColumn<?, ?> colHorarioFinal;

    @FXML
    private TableColumn<?, ?> colLunes;

    @FXML
    private TableColumn<?, ?> colMartes;

    @FXML
    private TableColumn<?, ?> colMiercoles;

    @FXML
    private TableColumn<?, ?> colJueves;

    @FXML
    private TableColumn<?, ?> colViernes;

    private ObservableList<Horario> listaHorarios = FXCollections.observableArrayList();
    
    public ControladorHorarios() {
    }
    
    public ObservableList getHorarios() {
        ArrayList<Horario> lista = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareCall("CALL sp_read_horarios");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Horario horario = new Horario();
                horario.setId(rs.getInt(1));
                horario.setHorarioInicio(rs.getTime(2).toLocalTime());
                horario.setHorarioFinal(rs.getTime(3).toLocalTime());
                horario.setLunes(rs.getBoolean(4));
                horario.setMartes(rs.getBoolean(5));
                horario.setMiercoles(rs.getBoolean(6));
                horario.setJueves(rs.getBoolean(7));
                horario.setViernes(rs.getBoolean(8));
                lista.add(horario);
                //System.out.println(alumno.toString());
            }
            listaHorarios = FXCollections.observableArrayList(lista);
            numero = listaHorarios.size();
        } catch (SQLException e) {
            System.err.println("\n Se produjo un error al intentar consultar la lista alumnos");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaHorarios;
    }
    public void cargarDatos() {
        tblHorarios.setItems(getHorarios());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colHorarioInicio.setCellValueFactory(new PropertyValueFactory<>("horarioInicio"));
        colHorarioFinal.setCellValueFactory(new PropertyValueFactory<>("horarioFinal"));
        colLunes.setCellValueFactory(new PropertyValueFactory<>("lunes"));
        colMartes.setCellValueFactory(new PropertyValueFactory<>("martes"));
        colMiercoles.setCellValueFactory(new PropertyValueFactory<>("miercoles"));
        colJueves.setCellValueFactory(new PropertyValueFactory<>("jueves"));
        colViernes.setCellValueFactory(new PropertyValueFactory<>("viernes"));

        //clickTabla();
    }
    
    public boolean existeElemento() {
        
        if((tblHorarios.getSelectionModel().getSelectedItem() != null)){
            return true;
        } else if ((tblHorarios.getSelectionModel().getSelectedItem() == null)) {
            return false;
        } 
         
        // hacer if que si es igual a null poner todos los txt vacios
        //return (tblAlumnos.getSelectionModel().getSelectedItem() != null);
        return false;
    }
    
    @FXML
    public void seleccionarElemento() {
        if (existeElemento()) {
            txtId.setText(String.valueOf(((Horario) tblHorarios.getSelectionModel().getSelectedItem()).getId()));
            dpInicio.setValue(((Horario) tblHorarios.getSelectionModel().getSelectedItem()).getHorarioInicio());
            dpFinal.setValue(((Horario) tblHorarios.getSelectionModel().getSelectedItem()).getHorarioFinal());
            cbLunes.setSelected((((Horario)tblHorarios.getSelectionModel().getSelectedItem()).getLunes()));
            cbMartes.setSelected((((Horario)tblHorarios.getSelectionModel().getSelectedItem()).getMartes()));
            cbMiercoles.setSelected((((Horario)tblHorarios.getSelectionModel().getSelectedItem()).getMiercoles()));
            cbJueves.setSelected((((Horario)tblHorarios.getSelectionModel().getSelectedItem()).getJueves()));
            cbViernes.setSelected((((Horario)tblHorarios.getSelectionModel().getSelectedItem()).getViernes()));
        //}else{
            //limpiar();
            /*
            txtCarne.setText(null);
            txtNombre.setText(null);
            txtSegundoNombre.setText(null);
            txtTercerNombre.setText(null);
            txtApellido.setText(null);
            txtSegundoApellido.setText(null);
     */
    }
}       
    }
