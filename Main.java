import java.util.Scanner;
import java.util.Random;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


import java.io.BufferedReader;
import java.io.FileReader;

class Jugador {
    String nombre;
    String correo;
    String torneosj;
    //int edad;
    String estado;
    long telefono;
    int cc;
    int deudas;
    int puntaje;
    int faltas;
    Jugador siguiente;

    public void agregarTorneo(String nombreTorneo) {
        if (this.torneosj.isEmpty()) {
            this.torneosj = nombreTorneo;
        } else {
            this.torneosj += "," + nombreTorneo; // Agrega el torneo separado por coma.
        }
    }
    

    public Jugador(String nombre, String correo, String torneosj, /int edad,/ String estado, long telefono, int cc,  int deudas, int puntaje, int faltas ) {
        this.nombre = nombre;
        this.correo = correo;
        this.torneosj = torneosj;
        //this.edad = edad;
        this.estado = estado;
        this.telefono = telefono;
        this.cc = cc;
        this.deudas = deudas;
        this.puntaje = puntaje;
        this.faltas = puntaje;    
        this.siguiente = null;
    }

}

class Colaj {
    Jugador frente, fin;

    public Colaj() {
        this.frente = this.fin = null;
    }
    void imprimirColaj() {
        if (frente == null) {
            System.out.println("La cola está vacía.");
        } else {
            Jugador actual = frente;
            System.out.println("Cola de Jugadores:");
            while (actual != null) {
                System.out.println("Nombre: " + actual.nombre + actual.correo + ", torneos jugados: " + actual.torneosj + /* ", Edad: " + actual.edad +*/ ", estado: " + actual.estado + ", telefono: " + actual.telefono + ", cc: " + actual.cc + ", correo: " +  ", deudas: " + actual.deudas + ", puntaje: " + actual.puntaje + ", faltas: " + actual.faltas);
                actual = actual.siguiente;
            }
        }
    }
    void buscarcolaj(String nombre){
        Jugador actual = frente;
        boolean encontrado = false;
        
        while (actual != null) {
            if (actual.nombre.equalsIgnoreCase(nombre)) {
                encontrado = true;
                System.out.println("Jugador encontrado:");
                System.out.println("Nombre: " + actual.nombre);
                System.out.println("Correo: " + actual.correo);
                System.out.println("Torneos jugados: " + actual.torneosj);
                //System.out.println("Edad: " + actual.edad);
                System.out.println("Estado: " + actual.estado);
                System.out.println("Teléfono: " + actual.telefono);
                System.out.println("Documento: " + actual.cc);
                System.out.println("Deudas: " + actual.deudas);
                System.out.println("Puntaje: " + actual.puntaje);
                System.out.println("Faltas: " + actual.faltas);
                break; // Salir del bucle una vez que se ha encontrado el jugador
            }
            actual = actual.siguiente;
        }
        
        if (!encontrado) {
            System.out.println("Jugador no encontrado.");
        }
    }
    void encolarj(String nombre, String correo, String torneosj,/int edad,/ String estado, long telefono, int cc,  int deudas, int puntaje, int faltas) {
        Jugador nuevoJugador = new Jugador(nombre, correo, torneosj,/edad,/ estado, telefono, cc,  deudas, puntaje, faltas);

        if (this.fin == null) {
            this.frente = this.fin = nuevoJugador;
            return;
        }

        this.fin.siguiente = nuevoJugador;
        this.fin = nuevoJugador;
    }

    void desencolarj(String nombre) {
        if (this.frente == null) {
            System.out.println("La cola está vacía.");
            return;
        }

        Jugador temp = this.frente, prev = null;
        while (temp != null && !temp.nombre.equals(nombre)) {
            prev = temp;
            temp = temp.siguiente;
        }

        if (temp == null) {
            System.out.println("Jugador no encontrado.");
            return;
        }

        if (prev == null) {
            this.frente = this.frente.siguiente;
        } else {
            prev.siguiente = temp.siguiente;
        }

        if (temp == this.fin) {
            this.fin = prev;
        }

        System.out.println("Jugador " + nombre + " ha sido quitado de la cola.");
    
    }


    void editarjugador(String nombre){
        Jugador actual = frente;
        boolean encontrado = false;
        
        while(actual != null){
            if(actual.nombre.equalsIgnoreCase(nombre)){
                encontrado = true;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Jugador encontrado. Ingrese los nuevos datos:");
                System.out.print("Ingrese el nuevo correo del jugador: ");
                actual.correo = scanner.nextLine();
                System.out.print("Ingrese los nuevos torneos jugados por el jugador: ");
                actual.torneosj = scanner.nextLine();
                System.out.print("Ingrese el nuevo estado del jugador: ");
                actual.estado = scanner.nextLine();
                System.out.print("Ingrese el nuevo número de teléfono del jugador: ");
                actual.telefono = Long.parseLong(scanner.nextLine());
                System.out.print("Ingrese las nuevas deudas del jugador: ");
                actual.deudas = Integer.parseInt(scanner.nextLine());
                System.out.print("Ingrese el nuevo puntaje del jugador: ");
                actual.puntaje = Integer.parseInt(scanner.nextLine());
                System.out.print("Ingrese las nuevas faltas del jugador: ");
                actual.faltas = Integer.parseInt(scanner.nextLine());
                System.out.println("Datos del jugador actualizados.");
                break;
    
            }
            actual = actual.siguiente;
        }

        if(!encontrado){
            System.out.println("jugador no encontrado");
        }
    }

    boolean agregarTorneoJugador(String nombre, String nombreTorneo){
        Jugador actual = frente;
        while(actual != null){
            if (actual.nombre.equalsIgnoreCase(nombre)) {
                actual.agregarTorneo(nombreTorneo);
                System.out.println("Torneo " + nombreTorneo + " añadido a " + nombre);
                return true; 
            }
            actual = actual.siguiente;
        }
        System.out.println("Jugador " + nombre + " no encontrado en la cola.");
        return false; 

    }


  
}

public class Main {
    static String[] ganadores;
    public static void main(String[] args) {
        Colaj colaj = new Colaj();

        Scanner scanner = new Scanner(System.in);

        try (BufferedReader reader = new BufferedReader(new FileReader("jugadores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String nombre = line.trim();
                String correo = reader.readLine().trim();
                String torneosj = reader.readLine().trim();
                String estado = reader.readLine().trim();
                long telefono = Long.parseLong(reader.readLine().trim());
                int cc = Integer.parseInt(reader.readLine().trim());
                int deudas = Integer.parseInt(reader.readLine().trim());
                int puntaje = Integer.parseInt(reader.readLine().trim());
                int faltas = Integer.parseInt(reader.readLine().trim());
        
                // Aquí puedes crear un nuevo jugador con los datos leídos y añadirlo a la cola.
                colaj.encolarj(nombre, correo, torneosj, estado, telefono, cc, deudas, puntaje, faltas);
            }
            System.out.println("Datos de los jugadores cargados desde el archivo 'jugadores.txt'.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        

        String comando;
       
        do {
            System.out.print("Ingrese 'E' para encolar, 'F' para editar informacion, 'D' para desencolar, 'B' para buscar un jugador, 'T'para iniciar torneo, 'I'para ver toda la cola o 'S' para salir: ");
            comando = scanner.nextLine().trim();

            switch (comando.toUpperCase()) {
                     
                case "E":
                    System.out.print("Ingrese el nombre del jugador: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el correo del jugador: ");
                    String correo = scanner.nextLine();
                    System.out.print("Ingrese la torneos del jugador: ");
                    String torneosj = scanner.nextLine();
                    //System.out.print("Ingrese la edad del jugador: ");
                    //int edad = scanner.nextInt();
                    System.out.println("Ingrese el estado del jugador:ACTIVO "  );
                    String estado = "ACTIVO"; //scanner.nextLine();

                    System.out.print("Ingrese el numero de telefono del jugador: ");
                    long telefono = scanner.nextLong();

                    System.out.print("Ingrese el documento del jugador: ");
                    int cc = scanner.nextInt();
                    
                    System.out.print("Ingrese deudas del jugador: ");
                    int deudas = scanner.nextInt();
                    System.out.print("Ingrese el puntaje del jugador: ");
                    int puntaje = scanner.nextInt();
                    System.out.print("Ingrese las faltas del jugador: ");
                    int faltas = scanner.nextInt();

                    scanner.nextLine(); // Limpiar el buffer del scanner

                    colaj.encolarj(nombre, correo, torneosj, /edad,/ estado, telefono, cc,  deudas, puntaje, faltas);
                    System.out.println("Jugador agregado a la cola.");
                    break;
                case "D":
                    if (colaj.frente == null) {
                        System.out.println("La cola está vacía.");
                        break;
                    }
                    System.out.print("Ingrese el nombre del jugador a quitar: ");
                    nombre = scanner.nextLine();
                    colaj.desencolarj(nombre);
                    break;
                case "I":
                    colaj.imprimirColaj();
                    break;
                case "B":
                    if (colaj.frente == null) {
                        System.out.println("La cola está vacía.");
                        break;
                    }
                    System.out.print("Ingrese el nombre del jugador a buscar: ");
                    nombre = scanner.nextLine();
                    colaj.buscarcolaj(nombre);
                    break;
                case "F":
                    if (colaj.frente == null) {
                    System.out.println("La cola está vacía.");
                    break;
                    }
                    System.out.print("Ingrese el nombre del jugador a editar: ");
                    String nombreedit = scanner.nextLine();
                    System.out.print("datos actuales");
                    colaj.buscarcolaj(nombreedit);
                    colaj.editarjugador(nombreedit);
                    break;
                case "T":
                    iniciarTorneo(colaj, scanner);
                    break;
                case "S":
                    System.out.println("Saliendo...");
                    scanner.close(); 
                    break;
                default:
                    System.out.println("Comando no reconocido.");
                    
                    break;
            }
        } while (!comando.equalsIgnoreCase("S"));

        // Guardar datos de los jugadores en el archivo después de terminar de ejecutar el programa
        try (PrintWriter writer = new PrintWriter(new FileWriter("jugadores.txt"))) {
            Jugador actual = colaj.frente;
            while (actual != null) {
                writer.println(actual.nombre);
                writer.println(actual.correo);
                writer.println(actual.torneosj);
                writer.println(actual.estado);
                writer.println(actual.telefono);
                writer.println(actual.cc);
                writer.println(actual.deudas);
                writer.println(actual.puntaje);
                writer.println(actual.faltas);
                actual = actual.siguiente;
            }
            System.out.println("Datos de los jugadores guardados en el archivo 'jugadores.txt'.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    static void iniciarTorneo(Colaj colaj, Scanner scanner) {
        if (colaj.frente == null) {
            System.out.println("No hay jugadores en la cola.");
            return;
        }
    
        System.out.print("Nombre del torneo: ");
        String nombreTorneo = scanner.nextLine();
    
        System.out.print("Número de participantes: ");
        int numParticipantes = Integer.parseInt(scanner.nextLine());

        String[] participantes = new String[numParticipantes];
        int participantesValidos = 0;
    
        for (int i = 0; i < numParticipantes; i++) {
            System.out.print("Nombre del jugador #" + (i + 1) + ": ");
            String nombreJugador = scanner.nextLine();
    
            // Verificar si el jugador está en la cola y añadir el torneo a su lista
            if (colaj.agregarTorneoJugador(nombreJugador, nombreTorneo)) {
                participantes[participantesValidos++] = nombreJugador;
            } else {
                System.out.println("Jugador no encontrado, ingrese jugadores existentes.");
                i--; // Solicitar nuevamente un nombre válido.
            }
         
        }

        mezclarParticipantes(participantes, participantesValidos);
        generarEnfrentamientos(participantes);


    }

    static void generarEnfrentamientos(String[] participantes) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnfrentamientos del torneo:");

        // Inicializar el arreglo de ganadores con los participantes
        ganadores = new String[participantes.length];
        System.arraycopy(participantes, 0, ganadores, 0, participantes.length);

        int numGanadores = ganadores.length;
        int ronda = 1;

        while (numGanadores > 1) {

            System.out.println("Ronda " + ronda + ":");
            
            int numNuevosGanadores = 0;
            String[] nuevosGanadores = new String[numGanadores / 2];

            if(ronda == 1){

            }

            for (int i = 0; i < numGanadores; i += 2) {
                String participante1 = ganadores[i];
                String participante2 = (i + 1 < numGanadores) ? ganadores[i + 1] : "";

                System.out.println(participante1 + " vs " + participante2);
                System.out.print("Ingrese el nombre del ganador ('empate' si es empate): ");
                String ganador = scanner.nextLine();

                if (!ganador.equals("empate")) {
                    nuevosGanadores[numNuevosGanadores++] = ganador;
                }
            }

            // Actualizar el arreglo de ganadores con los nuevos ganadores
            ganadores = nuevosGanadores;
            numGanadores = numNuevosGanadores;
            ronda++;
        }

        System.out.println("El ganador del torneo es: " + ganadores[0]);
    }


static void mezclarParticipantes(String[] participantes, int numParticipantes) {
    Random rnd = new Random();
    for (int i = numParticipantes - 1; i > 0; i--) {
        int index = rnd.nextInt(i + 1);
        String temp = participantes[index];
        participantes[index] = participantes[i];
        participantes[i] = temp;
    }
}


}