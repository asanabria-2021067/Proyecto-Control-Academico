/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.in5bm.asanabria.jbeltran.models;

import java.time.LocalDate;
import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Angel Sanabria
 * @date 6/06/2022
 * @time 22:25:09
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class Instructor {
    IntegerProperty id;
    StringProperty nombre1;
    StringProperty nombre2;
    StringProperty nombre3;
    StringProperty apellido1;
    StringProperty apellido2;
    StringProperty direccion;
    StringProperty email;
    StringProperty telefono;
    ObjectProperty <Date> fechaNacimiento;

    public Instructor() {
        this.id = new SimpleIntegerProperty();
        this.nombre1 = new SimpleStringProperty();
        this.nombre2 = new SimpleStringProperty();
        this.nombre3 = new SimpleStringProperty();
        this.apellido1 = new SimpleStringProperty();
        this.apellido2 = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.telefono = new SimpleStringProperty();
        this.fechaNacimiento = new SimpleObjectProperty<>();
    }
    public Instructor(int id, String nombre1, String nombre2, String nombre3, String apellido1, String apellido2, String direccion, String email, String telefono, Date fechaNacimiento) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre1 = new SimpleStringProperty(nombre1);
        this.nombre2 = new SimpleStringProperty(nombre2);
        this.nombre3 = new SimpleStringProperty(nombre3);
        this.apellido1 = new SimpleStringProperty(apellido1);
        this.apellido2 = new SimpleStringProperty(apellido2);
        this.direccion = new SimpleStringProperty(direccion);
        this.email = new SimpleStringProperty(email);
        this.telefono = new SimpleStringProperty(telefono);
        this.fechaNacimiento = new SimpleObjectProperty<>(fechaNacimiento);
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
    
    public StringProperty nombre1(){
        return nombre1;
    }
    
    public String getNombre1(){
        return nombre1.get();
    }
    public void setNombre1(String nombre1){
        this.nombre1.set(nombre1);
    }
    public StringProperty nombre2(){
        return nombre1;
    }
    
    public String getNombre2(){
        return nombre2.get();
    }
    public void setNombre2(String nombre2){
        this.nombre2.set(nombre2);
    }
    public StringProperty nombre3(){
        return nombre3;
    }
    
    public String getNombre3(){
        return nombre3.get();
    }
    public void setNombre3(String nombre3){
        this.nombre3.set(nombre3);
    }
    public StringProperty apellido1(){
        return apellido1;
    }
    
    public String getApellido1(){
        return apellido1.get();
    }
    public void setApellido1(String apellido1){
        this.apellido1.set(apellido1);
    }
    public StringProperty apellido2(){
        return apellido2;
    }
    
    public String getApellido2(){
        return apellido2.get();
    }
    public void setApellido2(String apellido2){
        this.apellido2.set(apellido2);
    }
    public StringProperty direccion(){
        return direccion;
    }
    
    public String getDireccion(){
        return direccion.get();
    }
    public void setDireccion(String direccion){
        this.direccion.set(direccion);
    }
    public StringProperty email(){
        return email;
    }
    
    public String getEmail(){
        return email.get();
    }
    public void setEmail(String email){
        this.email.set(email);
    }
    public StringProperty telefono(){
        return telefono;
    }
    
    public String getTelefono(){
        return telefono.get();
    }
    public void setTelefono(String telefono){
        this.telefono.set(telefono);
    }
    public ObjectProperty fechaNacimiento(){
        return fechaNacimiento;
    }
    
    public Date getFechaNacimiento(){
        return fechaNacimiento.get();
    }
    public void setFechaNacimiento(Date fechaNacimiento){
        this.fechaNacimiento.set(fechaNacimiento);
    }

    @Override
    public String toString() {
        return id.get() +" | " + " " + nombre1.get() + " " + apellido1.get();
    }
    
    /*
    public boolean validarEmail(String email) {
        Pattern pat = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
        
        Matcher mather = pat.matcher(email);
        
        return mather.find();
    }
    */
    
}
