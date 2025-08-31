public class Ejercicio {
    private String nombre;
    private String tipo;
    private String nivel_cansancio;
    private int cant_repeticiones;
    private double calorias_quemadas;

    public Ejercicio(String nombre, String tipo, String nivel_cansancio, int cant_repeticiones, double calorias_quemadas){
        this.nombre = nombre;
        this.tipo = nombre;
        this.nivel_cansancio = nivel_cansancio;
        this.cant_repeticiones = cant_repeticiones;
        this.calorias_quemadas = calorias_quemadas;
    }

    public double getCalorias_quemadas() {
        return calorias_quemadas;
    }
    public int getCant_repeticiones() {
        return cant_repeticiones;
    }
    public String getNivel_cansancio() {
        return nivel_cansancio;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTipo() {
        return tipo;
    }
}
