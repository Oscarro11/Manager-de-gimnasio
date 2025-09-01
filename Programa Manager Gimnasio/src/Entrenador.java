import java.util.ArrayList;

public class Entrenador {
    private String nombre;
    private int edad;
    private ArrayList<String> lista_alumnos;
    private ArrayList<ContadorEjercicio> lista_rutinas;
    private int max_alumnos;
    private boolean sobrecargado = false;

    public Entrenador(String nombre, int edad, int max_alumnos){
        this.nombre = nombre;
        this.edad = edad;
        this.max_alumnos = max_alumnos;
    }

    public void AddAlumno(String nombre_alumno){
        lista_alumnos.add(nombre_alumno);
        
        if (lista_alumnos.size() > max_alumnos) {
            sobrecargado = true;
        }
    }

    public boolean RemoveAlumno(String nombre_alumno){
        if (lista_alumnos.contains(nombre_alumno)){
            lista_alumnos.remove(nombre_alumno);

            if (lista_alumnos.size() <= max_alumnos) {
                sobrecargado = false;
            }

            return true;
        }
        else{
            return false;
        }
    }

    public void AddRutina(String nombre_rutina){
        boolean rutina_existe = false;
        for (int i=0; i<lista_rutinas.size(); i++){
            ContadorEjercicio rutina_a_comparar = lista_rutinas.get(i);
            if (rutina_a_comparar.getNombre_ejercicio().equals(nombre_rutina)){
                rutina_a_comparar.setCantidad_usos(rutina_a_comparar.getCantidad_usos() + 1);
                rutina_existe = true;
                break;
            }
        }

        if (!rutina_existe){
            lista_rutinas.add(new ContadorEjercicio(nombre_rutina));
        }
    }

    public boolean removeRutina(String nombre_rutina){
        boolean rutina_existe = false;
        for (int i=0; i<lista_rutinas.size(); i++){
            ContadorEjercicio rutina_a_comparar = lista_rutinas.get(i);
            if (rutina_a_comparar.getNombre_ejercicio().equals(nombre_rutina)){
                rutina_a_comparar.setCantidad_usos(rutina_a_comparar.getCantidad_usos() - 1);
                rutina_existe = true;

                if (rutina_a_comparar.getCantidad_usos() == 0){
                    rutina_a_comparar = null;
                }

                break;
            }
        }

        if (!rutina_existe){
            return false;
        }
        else{
            return true;
        }
    }

    public int getCantidad_alumnos(){
        return lista_alumnos.size();
    }

    public int getNumero_usos_rutina(String nombre_rutina){
        int cant_usos_rutina = 0;
        for (ContadorEjercicio rutina_ejercicio: lista_rutinas){
            if (rutina_ejercicio.getNombre_ejercicio().equals(nombre_rutina)){
                cant_usos_rutina = rutina_ejercicio.getCantidad_usos();
                break;
            }
        }
        return cant_usos_rutina;
    }

    public int getCantidad_rutinas(){
        return lista_rutinas.size();
    }

    public String getRutina_by_posicion(int posicion){
        Rutina[] lista_rutinas_temp = lista_rutinas.toArray(new Rutina[0]);
        return lista_rutinas_temp[posicion].getNombre();
    }


    public void setMax_alumnos(int max_alumnos) {
        this.max_alumnos = max_alumnos;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEdad() {
        return edad;
    }
    public ArrayList<String> getLista_alumnos() {
        return lista_alumnos;
    }
    public ArrayList<ContadorEjercicio> getLista_rutinas() {
        return lista_rutinas;
    }
    public int getMax_alumnos() {
        return max_alumnos;
    }
    public String getNombre() {
        return nombre;
    }
    public boolean getSobrecargado() {
        return sobrecargado;
    }
}
