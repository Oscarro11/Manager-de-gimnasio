public class Miembro {
    private String nombre;
    private int edad;
    private double peso_actual;
    private double peso_ideal;
    private String tipo_membresia;
    private String rutina_ejercicio;
    private String entrenador;

    public Miembro(String nombre, int edad, double peso_actual, double peso_ideal, String tipo_membresia){
        this.nombre = nombre;
        this.edad = edad;
        this.peso_actual = peso_actual;
        this.peso_ideal = peso_ideal;
        this.tipo_membresia = tipo_membresia;
    }

    public boolean AssignEntrenador(String nombre_entrenador){
        if (entrenador != null){
            return false;
        }
        else{
            entrenador = nombre_entrenador;
            return true;
        }
    }

    public boolean RemoveEntrenador(){
        if (entrenador == null){
            return false;
        }
        else{
            entrenador = null;
            return true;
        }
    }


    public void setPeso_actual(double peso_actual) {
        this.peso_actual = peso_actual;
    }
    public void setPeso_ideal(double peso_ideal) {
        this.peso_ideal = peso_ideal;
    }
    public void setTipo_membresia(String tipo_membresia) {
        this.tipo_membresia = tipo_membresia;
    }
    public void setRutina_ejercicio(String rutina_ejercicio) {
        this.rutina_ejercicio = rutina_ejercicio;
    }
    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public int getEdad() {
        return edad;
    }
    public String getEntrenador() {
        return entrenador;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPeso_actual() {
        return peso_actual;
    }
    public double getPeso_ideal() {
        return peso_ideal;
    }
    public String getRutina_ejercicio() {
        return rutina_ejercicio;
    }
    public String getTipo_membresia() {
        return tipo_membresia;
    }

}
