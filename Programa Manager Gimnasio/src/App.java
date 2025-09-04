import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static Controlador controlador = new Controlador();
    public static Scanner teclado = new Scanner(System.in);

    public static void escribir(String texto){
        System.out.println(texto);
    }

    public static void AddElemento(String tipo_elemento){
        switch (tipo_elemento) {
            case "Miembro":
                String nombre_miembro = "";
                int edad_miembro = 0;
                double peso_actual = 0;
                double peso_ideal = 0;
                String tipo_membresia = "";
                
                escribir("\nIngrese el nombre del nuevo miembro de gimnasio:");
                nombre_miembro = teclado.nextLine().strip();

                escribir("\nIngrese la edad del nuevo miembro:");
                edad_miembro = teclado.nextInt();
                teclado.next();

                escribir("\nIngrese el peso actual del nuevo miembro (en lb):");
                peso_actual = teclado.nextDouble();
                teclado.next();

                escribir("\nIngrese el peso ideal que el nuevo miembro quiere alcanzar (en lb):");
                peso_ideal = teclado.nextDouble();
                teclado.next();

                escribir("\nIngrese el tipo de membresia que el nuevo miembro tiene (puede ser Basica, Premium o Master):");
                tipo_membresia = teclado.nextLine().strip();

                controlador.CreateMiembro(nombre_miembro, edad_miembro, peso_actual, peso_ideal, tipo_membresia);
                escribir("\nNuevo miembro creado exitosamente");
                break;
        
            case "Entrenador":
                String nombre_entrenador = "";
                int edad_entrenador = 0;
                int max_alumnos = 0;

                escribir("\nIngrese el nombre del nuevo entrenador de gimnasio:");
                nombre_entrenador = teclado.nextLine();

                escribir("\nIngrese la edad del nuevo entrenador:");
                edad_entrenador = teclado.nextInt();
                teclado.next();  

                escribir("\nIngrese la cantidad maxima de alumnos que este entrenador puede manejar al mismo tiempo:");
                max_alumnos = teclado.nextInt();
                teclado.next();

                controlador.CreateEntrenador(nombre_entrenador, edad_entrenador, max_alumnos);
                break;

            case "Ejercicio":
                String nombre_ejercicio = "";
                String tipo_ejercicio = "";
                String nivel_cansancio = "";
                int cant_repeticiones = 0;
                double calorias_quemadas = 0;

                escribir("\nIngrese el nombre del nuevo ejercicio del gimnasio");
                nombre_ejercicio = teclado.nextLine();

                escribir("\nIngrese la categoria al que este ejercicio pertenece (puede ser aerobico, musculo, equilibrio o flexibilidad):");
                tipo_ejercicio = teclado.nextLine();

                escribir("\nIngrese el nivel de cansancio que este ejercicio genera (puede ser ligero, mediano o fuerte):");
                nivel_cansancio = teclado.nextLine();

                escribir("\nIngrese la cantidad de veces que este ejercicio debe hacerse en una sesion:");
                cant_repeticiones = teclado.nextInt();
                teclado.next();

                escribir("\nIngrese la cantidad de calorias que este ejercicio ayuda a quemar (teniendo en cuenta el numero de repeticiones):");
                calorias_quemadas = teclado.nextDouble();

                controlador.CreateEjercicio(nombre_ejercicio, tipo_ejercicio, nivel_cansancio, cant_repeticiones, calorias_quemadas);
                escribir("\nEl nuevo ejercicio se ha creado con exito");

            case "Rutina":
                String nombre_rutina = "";
                boolean suficientes_ejercicios = false;
                ArrayList<String> ejercicios_a_asignar = new ArrayList<String>();

                escribir("\nIngrese el nombre de la nueva rutina de ejercicios:");
                nombre_rutina = teclado.nextLine();

                escribir("\nAhora, debe ingresar el nombre de todos los ejercicios que conformaran la rutina");

                while (!suficientes_ejercicios) {
                    String nombre_ejercicio_a_agregar = "";
                    escribir("\nIngrese el nombre de uno de los ejercicios del sistema (en caso no quiera agregar mas ejercicios, presione enter sin escribir nada):");
                    nombre_ejercicio_a_agregar = teclado.nextLine();

                    if (nombre_ejercicio_a_agregar.equals("")) {
                        if (ejercicios_a_asignar.size() == 0) {
                            escribir("No puede crear una rutina sin tener algun ejercicio en ella. Intentelo de nuevo");
                        }
                        else{
                            controlador.CreateRutina(nombre_rutina, ejercicios_a_asignar.toArray(new String[0]));
                            suficientes_ejercicios = true;
                        }
                    }
                    else if (!controlador.ElementExists(nombre_ejercicio_a_agregar, "Ejercicio")) {
                        escribir("Este ejercicio no se encuentra en el sistema. Intentelo de nuevo");
                    }
                    else{
                        ejercicios_a_asignar.add(nombre_ejercicio_a_agregar);
                    }
                }

                escribir("\nRutina de ejercicios creada con exito");
                break;

            default:
                escribir("\nNo se puede anadir un elemento al sistema de este tipo. Ingrese otro tipo de elemento, e intentelo de nuevo");
                break;
        }
    }

    public static void AssignMiembroToEntrenador(){
        String nombre_miembro;
        String nombre_entrenador;
        String nombre_rutina;
        String valor_continuar = "";
        boolean continuar_asignacion = true;
        boolean validado;
        
        while (continuar_asignacion) {
            nombre_miembro = "";
            nombre_entrenador = "";
            nombre_rutina = "";

            escribir("\nEstos son los entrenadores que se encuentran en el sistema: ");
            escribir(controlador.ShowListOfElements("Entrenador"));

            validado = false;
            while (!validado) {
                escribir("\nIngrese el nombre del entrenador al cual se le asignara un miembro");
                
                nombre_entrenador = teclado.nextLine().strip();
                if (controlador.ElementExists(nombre_entrenador, "Entrenador")) {
                    validado = true;
                }
                else{
                    escribir("El nombre ingresado no corresponde a ninguno de los entrenadores que existen en el sistema. Intentelo de nuevo");
                }
            }

            escribir("\nEstos son los miembros de gimnasio que se encuentran en el sistema: ");
            escribir(controlador.ShowListOfElements("Miembro"));

            validado = false;
            while (!validado) {
                escribir("\nIngrese el nombre del miembro a asignar al entrenador: " + nombre_entrenador);
                nombre_miembro = teclado.nextLine().strip();

                if (!controlador.ElementExists(nombre_miembro, "Miembro")) {
                    escribir("El nombre ingresado no corresponde a ningun miembro del sistema, intentelo de nuevo");
                }
                else{
                    validado = true;
                }
            }

            escribir("\nEstas son las rutinas de ejercicios que se encuentran en el sistema:");
            escribir(controlador.ShowListOfElements("Rutina"));

            validado = false;
            while (!validado) {
                escribir("\nIngrese el nombre de la rutina de ejercicios que " + nombre_miembro + "usara con " + nombre_entrenador);
                nombre_rutina = teclado.nextLine().strip();

                if (!controlador.ElementExists(nombre_rutina, "Rutina")) {
                    escribir("El nombre ingresado no corresponde a ninguna rutina del sistema, intentelo de nuevo");
                }
                else{
                    validado = true;
                }
            }

            controlador.AssignMiembroToEntrenador(nombre_miembro, nombre_entrenador, nombre_rutina);
            escribir("\n" + nombre_miembro + " se ha asignado a " + nombre_entrenador + " exitosamente");

            validado = false;
            while (!validado) {
                escribir("\n¿Desea continuar asignando miembros a entrenadores (Y/N)?");
                valor_continuar = teclado.nextLine().toUpperCase();

                switch (valor_continuar) {
                    case "Y":
                        continuar_asignacion = true;
                        validado = true;
                        break;
                
                    case "N":
                        continuar_asignacion = false;
                        validado = true;

                    default:
                        escribir("El valor ingresado debe ser Y o N. Intentelo de nuevo");
                        break;
                }
            }
        }

    }
    
    public static void RemoveMiembroFromEntrenador(){
        boolean continuar_desasignacion = true;
        boolean validado;
        String nombre_miembro;
        String continuar = "";

        while (continuar_desasignacion) {
            nombre_miembro = "";
            validado = false;

            while (!validado) {
                escribir("\nEl sistema cuenta con los siguientes miembros: ");
                escribir(controlador.ShowListOfElements("Entrenador"));
                escribir("Ingrese el nombre del miembro del cual quiere desasignar su entrenador");

                nombre_miembro = teclado.nextLine().strip();

                if (controlador.ElementExists(nombre_miembro, "Miembro")) {
                    if (controlador.RemoveMiembroFromEntrenador(nombre_miembro)){
                        escribir("El miembro " + nombre_miembro + " ya no se encuentra asignado a un entrenador.");
                    }
                    else{
                        escribir("El miembro " + nombre_miembro + " no contaba con un entrenador, por lo que no se ha realizado la desasignacion.");
                    }
                    validado = true;
                }
                else{
                    escribir("El nombre ingresado no corresponde a uno de los miembros ingresados en el sistema. Intentelo de nuevo");
                }
            }

            validado = false;
            while (!validado) {
                escribir("\n¿Desea continuar desasignando entrenadores de miembros? (Y/N)");
                continuar = teclado.nextLine().strip().toUpperCase();

                switch (continuar) {
                    case "Y":
                        continuar_desasignacion = true;
                        validado = true;
                        break;

                    case "N":
                        continuar_desasignacion = false;
                        validado = true;
                
                    default:
                        escribir("El caracter ingresado no corresponde a Y o N. Intentelo de nuevo");
                        break;
                }
            }
            
        }
    }

    public static void AddEjercicioToRutina(){
        String nombre_rutina_a_asignar = "";
        String nombre_ejercicio_a_asignar = "";
        String continuar = "";
        boolean continuar_asignacion = true;
        boolean validado;

        while (continuar_asignacion) {
            nombre_rutina_a_asignar = "";
            nombre_ejercicio_a_asignar = "";
            continuar = "";
            
            validado = false;
            while (!validado) {
                escribir("\nEstas son las rutinas ingresadas en el sistema: ");
                escribir(controlador.ShowListOfElements("Rutina"));

                escribir("\nIngrese el nombre de la rutina a la que quiere asignarle un ejercicio: ");
                nombre_rutina_a_asignar = teclado.nextLine().strip();

                if (!controlador.ElementExists(nombre_rutina_a_asignar, "Rutina")) {
                    escribir("El nombre ingresado no le pertenece a ninguna rutina del sistema. Intentelo de nuevo");
                }
                else{
                    validado = true;
                }
            }

            validado = false;
            while (!validado) {
                escribir("\nEstas son los ejercicios ingresados en el sistema: ");
                escribir(controlador.ShowListOfElements("Ejercicio"));

                escribir("\nIngrese el nombre del ejercicio que sera asignado a la rutina " + nombre_rutina_a_asignar);
                nombre_ejercicio_a_asignar = teclado.nextLine().strip();

                if (!controlador.ElementExists(nombre_ejercicio_a_asignar, "Ejercicio")) {
                    escribir("El nombre ingresado no le pertenece a ningun ejercicio del sistema. Intentelo de nuevo");
                }
                else{
                    validado = true;
                }
            }

            controlador.AddEjercicioToRutina(nombre_ejercicio_a_asignar, nombre_rutina_a_asignar);
            escribir("El ejercicio " + nombre_ejercicio_a_asignar + " se ha anadido a la rutina " + nombre_rutina_a_asignar + ".");
        
            validado = false;
            while (!validado) {
                escribir("\n¿Desea realizar mas asignaciones de ejercicios a rutinas? (Y/N)");
                continuar = teclado.nextLine().strip().toUpperCase();

                switch (continuar) {
                    case "Y":
                        continuar_asignacion = true;
                        validado = true;
                        break;

                    case "N":
                        continuar_asignacion = false;
                        validado = true;
                        break;
                
                    default:
                        escribir("La opcion ingresada no es Y ni N. Intentelo de nuevo");
                        break;
                }
            }
        }
    }

    public static void RemoveEjercicioFromRutina(){
        String nombre_rutina_a_asignar = "";
        String nombre_ejercicio_a_asignar = "";
        String continuar = "";
        boolean continuar_asignacion = true;
        boolean validado;

        while (continuar_asignacion) {
            nombre_rutina_a_asignar = "";
            nombre_ejercicio_a_asignar = "";
            continuar = "";
            
            validado = false;
            while (!validado) {
                escribir("\nEstas son las rutinas ingresadas en el sistema: ");
                escribir(controlador.ShowListOfElements("Rutina"));

                escribir("\nIngrese el nombre de la rutina de la que quiere quitar un ejercicio");
                nombre_rutina_a_asignar = teclado.nextLine().strip();

                if (!controlador.ElementExists(nombre_rutina_a_asignar, "Rutina")) {
                    escribir("El nombre ingresado no le pertenece a ninguna rutina del sistema. Intentelo de nuevo");
                }
                else{
                    validado = true;
                }
            }

            validado = false;
            while (!validado) {
                escribir("\nEstas son los ejercicios ingresados en el sistema: ");
                escribir(controlador.ShowListOfElements("Ejercicio"));

                escribir("\nIngrese el nombre del ejercicio que quiere quitar de la rutina: " + nombre_rutina_a_asignar);
                nombre_ejercicio_a_asignar = teclado.nextLine().strip();

                if (!controlador.ElementExists(nombre_ejercicio_a_asignar, "Ejercicio")) {
                    escribir("El nombre ingresado no le pertenece a ningun ejercicio del sistema. Intentelo de nuevo");
                }
                else{
                    validado = true;
                }
            }

            if (controlador.RemoveEjercicioFromRutina(nombre_ejercicio_a_asignar, nombre_rutina_a_asignar)) {
                escribir("El ejercicio " + nombre_ejercicio_a_asignar + " se ha quitado de la rutina " + nombre_rutina_a_asignar + ".");
            }
            else{
                escribir("El ejercicio " + nombre_ejercicio_a_asignar + " no se ha encontrado en la rutina " + nombre_rutina_a_asignar + ", por lo que no se ha desasignado.");
            }

            validado = false;
            while (!validado) {
                escribir("\n¿Desea realizar mas desasignaciones de ejercicios a rutinas? (Y/N)");
                continuar = teclado.nextLine().strip().toUpperCase();

                switch (continuar) {
                    case "Y":
                        continuar_asignacion = true;
                        validado = true;
                        break;

                    case "N":
                        continuar_asignacion = false;
                        validado = true;
                        break;
                
                    default:
                        escribir("La opcion ingresada no es Y ni N. Intentelo de nuevo");
                        break;
                }
            }
        }    
    }

    public static void ConsultarElemento(){
        boolean validado = false;
        String tipo_elemento = "";

        while (!validado) {
            escribir("\nEl sistema cuenta con los siguientes tipos de elemento: ");
            escribir("- Miembro: Miembro del gimnasio, principal cliente del lugar");
            escribir("- Entrenador: Entrenador del gimnasio, ayuda a los miembros");
            escribir("- Ejercicio: Ejercicio usado por los miembros y entrenadores");
            escribir("- Rutina: Rutina de ejercicios, agrupa varios ejercicios en un orden especifico");

            escribir("\nPor favor, ingrese el nombre de uno de estos elementos para consultar todos los que hay en el sistema de su categoria");
            tipo_elemento = teclado.nextLine().strip();

            switch (tipo_elemento) {
                case "Miembro":
                    escribir("Estos son los miembros de gimnasio que estan ingresados en el sistema:");
                    escribir(controlador.ShowListOfElements("Miembro") + "\n");
                    validado = true;
                    break;

                case "Entrenador":
                    escribir("Estos son los entrenadores que estan ingresados en el sistema:");
                    escribir(controlador.ShowListOfElements("Entrenador") + "\n");
                    validado = true;
                    break;

                case "Ejercicio":
                    escribir("Estos son los ejercicios que estan ingresados en el sistema:");
                    escribir(controlador.ShowListOfElements("Ejercicio") + "\n");
                    validado = true;
                    break;

                case "Rutina":
                    escribir("Estas son las rutinas de ejercicio que estan ingresados en el sistema:");
                    escribir(controlador.ShowListOfElements("Rutina") + "\n");
                    validado = true;
                    break;
            
                default:
                    escribir("El tipo de elemento ingresado no pertenece al sistema. Intentelo de nuevo");
                    break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String opcion = "";
        boolean continuar = true;

        while (continuar) {
            escribir("Bienvenido al menu principal. Por favor, ingrese el numero de una de las siguientes opciones para interactuar con el programa: \n");
            escribir("1. Anadir elemento al sistema");
            escribir("2. Administrar miembros y entrenadores");
            escribir("3. Administrar rutinas de ejercicios");
            escribir("4. Consultar existencias de elemento");
            escribir("5. Calcular estadisticas del sistema");
            escribir("6. Salir");
            
            opcion = teclado.nextLine().strip();

            switch (opcion) {
                case "1":
                    String elemento_a_anadir = "";
                    boolean continuar_anadir = true;
                    while (continuar_anadir){
                        escribir("Por favor, ingrese el nombre del elemento que desea anadir al sistema. Los elementos que puede anadir son:\n");
                        escribir("- Miembro: Miembro del gimnasio, principal cliente del lugar");
                        escribir("- Entrenador: Entrenador del gimnasio, ayuda a los miembros");
                        escribir("- Ejercicio: Ejercicio usado por los miembros y entrenadores");
                        escribir("- Rutina: Rutina de ejercicios, agrupa varios ejercicios en un orden especifico");
                        escribir("Si ha terminado de crear elementos, presione Enter sin ningun otro caracter para salir");

                        elemento_a_anadir = teclado.nextLine().strip();

                        if (elemento_a_anadir.equals("")) {
                            continuar = false;
                        }
                        else{
                            AddElemento(elemento_a_anadir);
                        }
                    }
                    break;

                case "2":
                    boolean continuar_MyE = true;
                    String opcion_MyE = "";

                    escribir("\nBienvenido al administrador de miembros y entrenadores");
                    while (continuar_MyE){
                        escribir("Estas son las opciones que puede realizar:");
                        escribir("1. Asignar miembro a entrenador");
                        escribir("2. Desasignar miembro de entrenador");
                        escribir("3. Salir");

                        escribir("Ingrese el numero de la opcion que desea realizar:");
                        opcion_MyE = teclado.nextLine().strip();

                        switch (opcion_MyE) {
                            case "1":
                                AssignMiembroToEntrenador();
                                break;
                        
                            case "2":
                                RemoveMiembroFromEntrenador();
                                break;

                            case "3":
                                continuar_MyE = false;
                                break;

                            default:
                                escribir("La opcion ingresada no pertenece a ninguna del menu. Intentelo de nuevo");
                                break;
                        }

                    }

                case "3":
                    boolean continuar_EyR = true;
                    String opcion_EyR = "";

                    escribir("\nBienvenido al administrador de ejercicios y rutinas");
                    while (continuar_EyR){
                        escribir("Estas son las opciones que puede realizar:");
                        escribir("1. Asignar ejercicio a rutina");
                        escribir("2. Desasignar ejercicio de rutina");
                        escribir("3. Salir");

                        escribir("Ingrese el numero de la opcion que desea realizar:");
                        opcion_EyR = teclado.nextLine().strip();

                        switch (opcion_EyR) {
                            case "1":
                                AddEjercicioToRutina();
                                break;
                        
                            case "2":
                                RemoveEjercicioFromRutina();
                                break;

                            case "3":
                                continuar_EyR = false;
                                break;

                            default:
                                escribir("La opcion ingresada no pertenece a ninguna del menu. Intentelo de nuevo");
                                break;
                        }

                    }

                case "4":
                    ConsultarElemento();

                case "5":
                    escribir(controlador.ShowStatistics());

                case "6":
                    escribir("\nGracias por usar el programa, esperamos verlo pronto.");
                    continuar = false;

                default:
                    escribir("La opcion ingresada no corresponde a alguna de las opciones del sistema. Intentelo de nuevo \n");
                    break;
            }
        }

    }
}
