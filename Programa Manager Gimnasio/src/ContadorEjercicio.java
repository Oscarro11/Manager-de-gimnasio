public class ContadorEjercicio {
    private String nombre_ejercicio;
    private int cantidad_usos = 0;
    
    public ContadorEjercicio(String nombre_ejercicio){
        this.nombre_ejercicio = nombre_ejercicio;
    }

    
    public void setCantidad_usos(int cantidad_usos) {
        this.cantidad_usos = cantidad_usos;
    }
    public void setNombre_ejercicio(String nombre_ejercicio) {
        this.nombre_ejercicio = nombre_ejercicio;
    }

    public int getCantidad_usos() {
        return cantidad_usos;
    }
    public String getNombre_ejercicio() {
        return nombre_ejercicio;
    }
}