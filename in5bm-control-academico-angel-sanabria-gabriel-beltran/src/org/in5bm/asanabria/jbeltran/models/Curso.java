/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.in5bm.asanabria.jbeltran.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author informatica
 */
public class Curso {
    IntegerProperty id;
    StringProperty nombreCurso;
    IntegerProperty ciclo;
    IntegerProperty cupoMaximo;
    IntegerProperty cupoMinimo;
    StringProperty carreraTecnicaId;
    IntegerProperty horarioId;
    IntegerProperty instructorId;
    StringProperty salonId;

    public Curso() {
        this.id = new SimpleIntegerProperty();
        this.nombreCurso = new SimpleStringProperty();
        this.carreraTecnicaId = new SimpleStringProperty();
        this.horarioId = new SimpleIntegerProperty();
        this.instructorId = new SimpleIntegerProperty();
        
    }

    public Curso(int id, String nombreCurso, int ciclo, int cupoMaximo, int cupoMinimo, String carreraTecnicaId, int horarioId, int instructorId, String salonId) {
        this.id = new SimpleIntegerProperty(id);
        this.nombreCurso = new SimpleStringProperty();
        this.ciclo = new SimpleIntegerProperty();
        this.cupoMaximo = new SimpleIntegerProperty();
        this.cupoMinimo = new SimpleIntegerProperty();
        this.carreraTecnicaId = new SimpleStringProperty();
        this.horarioId = new SimpleIntegerProperty();
        this.instructorId = new SimpleIntegerProperty();
        this.salonId = new SimpleStringProperty();
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
    
    public StringProperty nombreCurso(){
        return nombreCurso;
    }
    
    public String getNombreCurso(){
        return nombreCurso.get();
    }
    public void setNombreCurso(String nombreCurso){
        this.nombreCurso.set(nombreCurso);
    }
    
    public IntegerProperty ciclo(){
        return ciclo;
    }
    
    public int getCiclo(){
        return ciclo.get();
    }
    public void setCiclo(int ciclo){
        this.ciclo.set(ciclo);
    }
    
    public IntegerProperty cupoMaximo(){
        return cupoMaximo;
    }
    
    public int getCupoMaximo(){
        return cupoMaximo.get();
    }
    public void setCupoMaximo(int cupoMaximo){
        this.cupoMaximo.set(cupoMaximo);
    }
    public IntegerProperty cupoMinimo(){
        return cupoMinimo;
    }
    
    public int getCupoMinimo(){
        return cupoMinimo.get();
    }
    public void setCupoMinimo(int cupoMinimo){
        this.cupoMinimo.set(cupoMinimo);
    }

    public StringProperty carreraTecnicaId(){
        return carreraTecnicaId;
    }
    
    public String getCarreraTecninaId(){
        return carreraTecnicaId.get();
    }
    public void setCarreraTecnicaId(String carreraTecnicaId){
        this.carreraTecnicaId.set(carreraTecnicaId);
    }
    
    public IntegerProperty instructorId(){
        return id;
    }
    public int getInstructorId(){
        return instructorId.get();
    }
    public void setInstructorId(int instructorId){
        this.instructorId.set(instructorId);
    }
    
    public IntegerProperty horarioId(){
        return instructorId;
    }
    
    public int getHorarioId(){
        return horarioId.get();
    }
    public void setHorarioId(int horarioId){
        this.id.set(horarioId);
    }
    
    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", nombreCurso=" + nombreCurso + ", ciclo=" + ciclo + ", cupoMaximo=" + cupoMaximo + ", cupoMinimo=" + cupoMinimo + ", carreraTecnicaId=" + carreraTecnicaId + ", horarioId=" + horarioId + ", instructorId=" + instructorId + ", salonId=" + salonId + '}';
    }
   
    
    
    
    
    
}
