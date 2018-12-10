package AppHotel;

import Habitaciones.*; //Hola :v
import Usuarios.Usuario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

    public class Main {

        private static ArrayList<Habitacion> habs = new ArrayList<>();    //Base de datos de habitaciones del hotel
        private static ArrayList<Usuario> baseCorreos = new ArrayList<>(); //Base de datos de correos y contraseñas
        private static ArrayList<Habitacion> ocupadas = new ArrayList<>(); //Base de datos de correos y contraseñas
        private static Scanner teclado = new Scanner(System.in);
        private static Scanner leer;
        private static int posicion;

        public static void main(String[] args) throws FileNotFoundException {
            menuPrincipal();
        }

        private static void menuPrincipal() throws FileNotFoundException {
            try {
            for (; ; ) {
                System.out.printf("[1] Tipo de habitaciones");
                System.out.printf("\t[2] Ingresa   ");
                System.out.printf("\t[9] Salir\n> ");

                int opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        llenadoDeBase();
                        tiposDeHabitaciones();
                        break;
                    case 2:
                        llenadoDeBase();
                        login();
                        break;
                    case 9:
                        System.out.printf("\n\n\t\t\tHasta Pronto! ;)\n\n");
                        break;
                    default:
                        System.err.println("Opcion no valida");
                }
                if (opcion == 9) break;
            }
        }catch(
        Exception ingresoOtraCosa) {System.out.println("\t\t\tIngresa un numero Plz");}
    }

        private static void login() throws FileNotFoundException {

            File login = new File("login.dat"); //Cargo el login.dat a una variable File
            leer = new Scanner(login);  //cago la variable 'login' en el scanner
            ArrayList <Usuario> baseCorreos= new ArrayList<>(); //Base de datos de correos y contraseñas

            while (leer.hasNext()){
                //Lleno la base de objetos tipo 'Usuario'
                baseCorreos.add(new Usuario(leer));
            }
            /* Estas variables son para almacenar los datos con
            los que el usuario se va a logear en el sistema
            */
            String correo, password;
            System.out.printf("Ingrese correo >");
                correo ="vicky454@gmail.com";               //teclado.next();
            System.out.printf("Ingrese contraseña >");
                password = "qwerty";                        //teclado.next();

            for (int i = 0; i < baseCorreos.size(); i++) {  //Recorro mi ArrayList
                /*Con este IF comparo lo que ingrese el usuario con cada
                    objeto y sus respectivos correos y contraseñas
                 */
                if (correo.equalsIgnoreCase(baseCorreos.get(i).getCorreo()) &&
                    password.equals(baseCorreos.get(i).getPass()))
                {
                    System.out.println("Bienvenido " + baseCorreos.get(i).getNombre());
                        menuUsuario();
                        posicion = i;
                }

            }
        }

        private static void menuUsuario() {
           try {
                //Menu de usuario logeado en el sistema
                for (; ; ) {

                    System.out.println("Que desea hacer?");
                    System.out.print("[1] Reservar \t [2] Cancelar \t [3] Cerrar Sesion \t \n>");

                    int opcion = teclado.nextInt();

                    switch (opcion) {
                        case 1:
                            seleccionDeHabitacion();
                            pagar();
                            break;
                        case 2:
                            System.out.printf("\t\t\tUsted ha cancelado\n");
                            break;
                        case 3:
                            System.out.printf("\t\t\tHa cerrado sesion \n");
                            break;
                        default:
                            System.out.printf("\t\t\tOpcion no valida!\n");
                            menuUsuario();
                            break;
                    }
                    if (opcion == 3) break;

                }
            }catch (Exception ingresoOtraCosa){
                System.err.println("Ingrese valor numerico");
                menuUsuario();
           }
        }

        private static int seleccionDeHabitacion() {
        int i;
            System.out.println("Escoja tipo de habitacion");
            System.out.println("[0] Economica [1]Tradiconal [2] " +
                    "Ejecutiva [3] Suite [4] Suite Precidencial");
                i = teclado.nextInt();
            do {
                if (i == 0) System.out.println("Ha seleccionado: Economica");
                if (i == 1) System.out.println("Ha seleccionado: Tradicional");
                if (i == 2) System.out.println("Ha seleccionado: Ejecutiva");
                if (i == 3) System.out.println("Ha seleccionado: Suite");
                if (i == 4) System.out.println("Ha seleccionado: Suite presidencial");

            }while (i > 5);

            System.out.println("Pase a pagar: " + habs.get(i).getPrecio());

            return i;
        }

        private static void pagar() {
            System.out.println("ingrese su metodo de pago");
            System.out.println("[1] Tarjeta de Credito\t[2] Efectivo\t[3] Atrás ");
                int opcion = teclado.nextInt();
                switch (opcion){
                    case 1:
                        System.out.println("Ingrese su numero de tarjeta");
                            int tajeta = teclado.nextInt();
                        System.out.println("Ha pagado con: " + tajeta);
                        System.out.printf("\t\t\tUsted ha reservado \n");
                        break;
                    case 2:
                        System.out.printf("\t\t\tUsted ha reservado\n");
                        System.out.println("Pague en hotel");

                }

        }

        private static void tiposDeHabitaciones() {

            for (Habitacion i:habs) {
                System.out.println(i);

            }
        }

        public static void llenadoDeBase() throws FileNotFoundException {
            File habitaciones=new File("Habitacion.txt");
            leer=new Scanner(habitaciones);

            while (leer.hasNext()){
                String tipo=leer.next();
                if (tipo.equalsIgnoreCase("e")) {
                    habs.add(new Economica(leer));
                }
                if (tipo.equalsIgnoreCase("t")) {
                    habs.add(new Tradicional(leer));
                }
                if (tipo.equalsIgnoreCase("ej")) {
                    habs.add(new Ejecutiva(leer));
                }
                if (tipo.equalsIgnoreCase("s")) {
                    habs.add(new Suite(leer));
                }
                if (tipo.equalsIgnoreCase("sp")) {
                    habs.add(new SuitePrecidencial(leer));
                }

            }
        }

}
