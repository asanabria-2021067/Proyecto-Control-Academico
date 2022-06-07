/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.in5bm.asanabria.jbeltran.models;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author informatica
 */
public class AsignacionAlumno {
    private IntegerProperty id;
    private StringProperty alumnoId;
    private IntegerProperty cursoId;
    private ObjectProperty <LocalDate> fechaAsignacion;

    public AsignacionAlumno() {
        this.id = new SimpleIntegerProperty();
        this.alumnoId = new SimpleStringProperty();
        this.cursoId = new SimpleIntegerProperty();
        this.fechaAsignacion = new SimpleObjectProperty<>();
    }

    public AsignacionAlumno(int id, String alumnoId, int cursoId, LocalDate fechaAsignacion){
        this.id = new SimpleIntegerProperty(id);
        this.alumnoId = new SimpleStringProperty(alumnoId);
        this.cursoId = new SimpleIntegerProperty(cursoId);
        this.fechaAsignacion = new SimpleObjectProperty(fechaAsignacion); 
    }
    
    public IntegerProperty id(){
        return id;
    }
    
    public int getId(){
        return id.get();
    }
    public void setId(int id){
        this.id.set(id);
    }
    
    public StringProperty alumnoId(){
        return alumnoId;
    }
    
    public String getAlumnoId(){
        return alumnoId.get();
    }
    public void setAlumnoId(String alumnoId){
        this.alumnoId.set(alumnoId);
    }
    
    public IntegerProperty cursoId(){
        return cursoId;
    }
    
    public int getCursoId(){
        return cursoId.get();
    }
    public void setCursoId(int cursoId){
        this.cursoId.set(cursoId);
    }
    public ObjectProperty fechaAsignacion(){
        return fechaAsignacion;
    }
    
    public LocalDate getFechaAsignacion(){
        return fechaAsignacion.get();
    }
    public void setFechaAsignacion(LocalDate fechaAsignacion){
        this.fechaAsignacion.set(fechaAsignacion);
    }
}
