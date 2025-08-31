import java.util.ArrayList;

public class Rutina {
    private String nombre;
    private ArrayList<String> lista_ejercicios;

    public Rutina(String nombre, String... ejercicios){
        this.nombre = nombre;

        ArrayList<String> lista_temporal_ejercicios = new ArrayList<String>();
        for (String ejercicio: ejercicios){
            lista_temporal_ejercicios.add(ejercicio);
        }

        this.lista_ejercicios = lista_temporal_ejercicios;
    }

    public void AddEjercicio(String nombre_ejercicio){
        lista_ejercicios.add(nombre_ejercicio);
    }

    public boolean RemoveEjercicio(String nombre_ejercicio){
        if (lista_ejercicios.contains(nombre_ejercicio)){
            lista_ejercicios.remove(nombre_ejercicio);
            return true;
        }
        else{
            return false;
        }
    }

    public String ReadEjercicios(){
        String mensaje = "";

        for (String ejercicio:lista_ejercicios){
            mensaje.concat("- " + ejercicio + "\n");
        }

        return mensaje;
    }

    public ArrayList<String> getLista_ejercicios() {
        return lista_ejercicios;
    }
    public String getNombre() {
        return nombre;
    }
}
