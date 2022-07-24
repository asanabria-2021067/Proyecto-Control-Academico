package org.in5bm.asanabria.jbeltran.models;
/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:10:17
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class Usuario {
    static String user;
    static String pass;
    static String nombre;
    static int rol_id;

    public Usuario() {
    }

    public Usuario(String user, String pass, String nombre, int rol_id) {
        this.user = user;
        this.pass = pass;
        this.nombre = nombre;
        this.rol_id = rol_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }
    
    
}
