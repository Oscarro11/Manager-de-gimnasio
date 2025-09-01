import java.util.ArrayList;

public class Controlador {
    private ArrayList<Miembro> lista_miembros;
    private ArrayList<Entrenador> lista_entrenadores;
    private ArrayList<Ejercicio> lista_ejercicios;
    private ArrayList<Rutina> lista_rutinas;

    public Controlador(){}

    public void CreateMiembro(String nombre, int edad, double peso_actual, double peso_ideal, String tipo_membresia){
        lista_miembros.add(new Miembro(nombre, edad, peso_actual, peso_ideal, tipo_membresia));
    }

    public void CreateEntrenador(String nombre, int edad, int max_alumnos){
        lista_entrenadores.add(new Entrenador(nombre, edad, max_alumnos));
    }

    public void CreateEjercicio(String nombre, String tipo, String nivel_cansancio, int cant_repeticiones, double calorias_quemadas){
        lista_ejercicios.add(new Ejercicio(nombre, tipo, nivel_cansancio, cant_repeticiones, calorias_quemadas));
    }

    public void CreateRutina(String nombre, String... ejercicios){
        lista_rutinas.add(new Rutina(nombre, ejercicios));
    }

    public String ShowListOfElements(String nombre_lista){
        String mensaje = "";
        switch (nombre_lista) {
            case "Miembro":
                for (Miembro miembro: lista_miembros){
                    mensaje.concat("-" + miembro.getNombre() + "\n");
                }  
                break;
        
            case "Entrenador":
                for (Entrenador entrenador: lista_entrenadores){
                    mensaje.concat("-" + entrenador.getNombre() + "\n");
                }  
                break;

            case "Ejercicio":
                for (Ejercicio ejercicio: lista_ejercicios){
                    mensaje.concat("-" + ejercicio.getNombre() + "\n");
                }  
                break;

            case "Rutina":
                for (Rutina rutina: lista_rutinas){
                    mensaje.concat("-" + rutina.getNombre() + "\n");
                }  
                break;

            default:
                break;
        }

        return mensaje;
    }

    public boolean ElementExists(String nombre_busca, String nombre_lista_buscar){
        boolean elemento_encontrado = false;
        switch (nombre_lista_buscar) {
            case "Miembro":
                for (Miembro miembro: lista_miembros){
                    if (miembro.getNombre().equals(nombre_busca)){
                        elemento_encontrado = true;
                        break;
                    }
                }
                break;
        
            case "Entrenador":
                for (Entrenador entrenador: lista_entrenadores){
                    if (entrenador.getNombre().equals(nombre_busca)){
                        elemento_encontrado = true;
                        break;
                    }
                }
                break;

            case "Ejercicio":
                for (Ejercicio ejercicio: lista_ejercicios){
                    if (ejercicio.getNombre().equals(nombre_busca)){
                        elemento_encontrado = true;
                        break;
                    }
                }
                break;

            case "Rutina":
                for (Rutina rutina: lista_rutinas){
                    if (rutina.getNombre().equals(nombre_busca)){
                        elemento_encontrado = true;
                        break;
                    }
                }
                break;

            default:
                break;
        }

        return elemento_encontrado;
    }

    public void AddEjercicioToRutina(String nombre_ejercicio, String nombre_rutina){
        Rutina rutina_a_asignar = null;

        for (Rutina rutina: lista_rutinas){
            if (rutina.getNombre().equals(nombre_rutina)){
                rutina_a_asignar = rutina;
                break;
            }
        }

        rutina_a_asignar.AddEjercicio(nombre_ejercicio);
    }

    public boolean RemoveEjercicioFromRutina(String nombre_ejercicio, String nombre_rutina){
        Rutina rutina_a_asignar = null;

        for (Rutina rutina: lista_rutinas){
            if (rutina.getNombre().equals(nombre_rutina)){
                rutina_a_asignar = rutina;
                break;
            }
        }
        
        if (rutina_a_asignar.RemoveEjercicio(nombre_ejercicio)){
            return true;
        }
        else{
            return false;
        }
    }

    public void AssignMiembroToEntrenador(String nombre_miembro, String nombre_entrenador, String nombre_rutina){
        Entrenador entrenador_a_asignar = null;
        Miembro miembro_a_asignar = null;

        for (Entrenador entrenador: lista_entrenadores){
            if (entrenador.getNombre().equals(nombre_entrenador)){
                entrenador_a_asignar = entrenador;
                break;
            }
        }

        for (Miembro miembro: lista_miembros){
            if (miembro.getNombre().equals(nombre_miembro)){
                miembro_a_asignar = miembro;
                break;
            }
        }

        entrenador_a_asignar.AddAlumno(nombre_miembro);
        entrenador_a_asignar.AddRutina(nombre_rutina);
        miembro_a_asignar.setRutina_ejercicio(nombre_rutina);
        miembro_a_asignar.setEntrenador(nombre_entrenador);
    }

    public boolean AssignMiembroFromEntrenador(String nombre_miembro, String nombre_entrenador){
        Entrenador entrenador_a_asignar = null;

        for (Entrenador entrenador: lista_entrenadores){
            if (entrenador.getNombre().equals(nombre_entrenador)){
                entrenador_a_asignar = entrenador;
                break;
            }
        }

        if (entrenador_a_asignar.RemoveAlumno(nombre_entrenador)){
            return true;
        }
        else{
            return false;
        }
    }

    public String ShowStatistics(){
        String mensaje = "";
        Entrenador entrenador_mas_popular;
        Entrenador entrenador_menos_popular;
        String nombre_rutina_mas_popular;
        String nombre_rutina_menos_popular;
        int cantidad_rutina_mas_popular;
        int cantidad_rutina_menos_popular;

        try{
            entrenador_mas_popular = lista_entrenadores.get(0);
            entrenador_menos_popular = lista_entrenadores.get(0);
        } 
        catch (IndexOutOfBoundsException e){
            return "No puede mostrarse ninguna estadistica, debido a que no ha ingresado ningun entrenador al sistema.\n Ingrese al menos 1 entrenador al sistema, y vuelva a intentarlo";
        }

        try{
            lista_rutinas.get(0);
            lista_rutinas.get(0);
        } 
        catch (IndexOutOfBoundsException e){
            return "No puede mostrarse ninguna estadistica, debido a que no ha ingresado ninguna rutina al sistema.\n Ingrese al menos 1 rutina al sistema, y vuelva a intentarlo";
        }

        for (Entrenador entrenador: lista_entrenadores){
            if (entrenador.getCantidad_alumnos() > entrenador_mas_popular.getCantidad_alumnos()){
                entrenador_mas_popular = entrenador;
            }
            else if (entrenador.getCantidad_alumnos() < entrenador_menos_popular.getCantidad_alumnos()){
                entrenador_menos_popular = entrenador;
            }
        }

        ArrayList<String> lista_nombre_rutinas = new ArrayList<String>();
        ArrayList<Integer> lista_cantidad_usos_rutinas = new ArrayList<Integer>();

        for (Entrenador entrenador: lista_entrenadores){
            for (int i=0; i<entrenador.getCantidad_rutinas(); i++){
                String nombre_rutina = entrenador.getRutina_by_posicion(i);
                int cant_usos_rutina = entrenador.getNumero_usos_rutina(nombre_rutina);

                if (lista_nombre_rutinas.contains(nombre_rutina)){
                    lista_nombre_rutinas.add(nombre_rutina);
                    lista_cantidad_usos_rutinas.add(cant_usos_rutina);
                }
                else{
                    int indice_rutina = lista_nombre_rutinas.indexOf(nombre_rutina);
                    lista_cantidad_usos_rutinas.set(indice_rutina, lista_cantidad_usos_rutinas.get(indice_rutina) + cant_usos_rutina);
                }
            }
        }

        String[] array_nombre_rutinas = lista_nombre_rutinas.toArray(new String[0]);
        Integer[] array_cantidad_usos_rutinas = lista_cantidad_usos_rutinas.toArray(new Integer[0]);

        nombre_rutina_mas_popular = array_nombre_rutinas[0];
        nombre_rutina_menos_popular = array_nombre_rutinas[0];

        cantidad_rutina_mas_popular = array_cantidad_usos_rutinas[0];
        cantidad_rutina_menos_popular = array_cantidad_usos_rutinas[0];

        for (int i=0; i<array_nombre_rutinas.length; i++){
            if (array_cantidad_usos_rutinas[i] > cantidad_rutina_mas_popular){
                cantidad_rutina_mas_popular = array_cantidad_usos_rutinas[i];
                nombre_rutina_mas_popular = array_nombre_rutinas[i];
            }
            else if (array_cantidad_usos_rutinas[i] < cantidad_rutina_menos_popular){
                cantidad_rutina_menos_popular = array_cantidad_usos_rutinas[i];
                nombre_rutina_menos_popular = array_nombre_rutinas[i];
            }
        }

        double cant_entrenadores_sobrecargados = 0;

        for (Entrenador entrenador: lista_entrenadores){
            if (entrenador.getSobrecargado()){
                cant_entrenadores_sobrecargados++;
            }
        }

        double porcentaje_entrenadores_sobrecargados = (cant_entrenadores_sobrecargados / lista_entrenadores.size()) * 100;

        mensaje.concat("- El entrenador con mas alumnos es: " + entrenador_mas_popular);
        mensaje.concat("- El entrenador con menos alumnos es: " + entrenador_mas_popular);
        mensaje.concat("- La rutina mas popular es: " + nombre_rutina_mas_popular);
        mensaje.concat("- La rutina menos popular es: " + nombre_rutina_menos_popular);
        mensaje.concat("- Un " + porcentaje_entrenadores_sobrecargados + "% de los entrenadores se encuentran sobrecargados");

        return mensaje;
    }
}
