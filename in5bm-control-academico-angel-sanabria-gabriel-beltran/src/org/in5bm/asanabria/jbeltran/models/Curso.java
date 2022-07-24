package org.in5bm.asanabria.jbeltran.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:12:25
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class Curso {

    private IntegerProperty id;
    private StringProperty nombreCurso;
    private IntegerProperty ciclo;
    private IntegerProperty cupoMaximo;
    private IntegerProperty cupoMinimo;
    private StringProperty carreraTecnicaId;
    private IntegerProperty horarioId;
    private IntegerProperty instructorId;
    private StringProperty salonId;

    public Curso() {
        this.id = new SimpleIntegerProperty();
        this.nombreCurso = new SimpleStringProperty();
        this.ciclo = new SimpleIntegerProperty();
        this.cupoMaximo = new SimpleIntegerProperty();
        this.cupoMinimo = new SimpleIntegerProperty();
        this.carreraTecnicaId = new SimpleStringProperty();
        this.horarioId = new SimpleIntegerProperty();
        this.instructorId = new SimpleIntegerProperty();
        this.salonId = new SimpleStringProperty();
    }

    public Curso(int id, String nombreCurso, int ciclo, int cupoMaximo, int cupoMinimo, String carreraTecnicaId, int horarioId, int instructorId, String salonId) {
        this.id = new SimpleIntegerProperty(id);
        this.nombreCurso = new SimpleStringProperty(nombreCurso);
        this.ciclo = new SimpleIntegerProperty(ciclo);
        this.cupoMaximo = new SimpleIntegerProperty(cupoMaximo);
        this.cupoMinimo = new SimpleIntegerProperty(cupoMinimo);
        this.carreraTecnicaId = new SimpleStringProperty(carreraTecnicaId);
        this.horarioId = new SimpleIntegerProperty(horarioId);
        this.instructorId = new SimpleIntegerProperty(instructorId);
        this.salonId = new SimpleStringProperty(salonId);
    }

    public IntegerProperty id() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public StringProperty nombreCurso() {
        return nombreCurso;
    }

    public String getNombreCurso() {
        return nombreCurso.get();
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso.set(nombreCurso);
    }

    public IntegerProperty ciclo() {
        return ciclo;
    }

    public int getCiclo() {
        return ciclo.get();
    }

    public void setCiclo(int ciclo) {
        this.ciclo.set(ciclo);
    }

    public IntegerProperty cupoMaximo() {
        return cupoMaximo;
    }

    public int getCupoMaximo() {
        return cupoMaximo.get();
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo.set(cupoMaximo);
    }

    public IntegerProperty cupoMinimo() {
        return cupoMinimo;
    }

    public int getCupoMinimo() {
        return cupoMinimo.get();
    }

    public void setCupoMinimo(int cupoMinimo) {
        this.cupoMinimo.set(cupoMinimo);
    }

    public StringProperty carreraTecnicaId() {
        return carreraTecnicaId;
    }

    public String getCarreraTecnicaId() {
        return carreraTecnicaId.get();
    }

    public void setCarreraTecnicaId(String carreraTecnicaId) {
        this.carreraTecnicaId.set(carreraTecnicaId);
    }

    public IntegerProperty horarioId() {
        return horarioId;
    }

    public int getHorarioId() {
        return horarioId.get();
    }

    public void setHorarioId(int horarioId) {
        this.horarioId.set(horarioId);
    }

    public IntegerProperty instructorId() {
        return instructorId;
    }

    public int getInstructorId() {
        return instructorId.get();
    }

    public void setInstructorId(int instructorId) {
        this.instructorId.set(instructorId);
    }

    public StringProperty salonId() {
        return salonId;
    }

    public String getSalonId() {
        return salonId.get();
    }

    public void setSalonId(String salonId) {
        this.salonId.set(salonId);
    }

    @Override
    public String toString() {
        return id.get() + " | " + nombreCurso.get();
    }
}
