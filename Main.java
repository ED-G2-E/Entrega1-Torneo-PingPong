import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

class Jugador {
    // datos que se le piden al Jugador
    String nombre;
    String correo;
    String torneosj;
    String estado;
    long telefono;
    int cc;
    int deudas;
    int puntaje;
    int faltas;
    Jugador siguiente;
    // para cuando se inicie un torneo se agrege a cada jugador
    public void agregarTorneo(String nombreTorneo) {
        if (this.torneosj.isEmpty()) {
            this.torneosj = nombreTorneo;
        } else {
            this.torneosj += "," + nombreTorneo; // Agrega el torneo separado por coma.
        }
    }

    public int puntos(){
        return this.puntaje;
    }
    
    //cracion de un jugador
    public Jugador(String nombre, String correo, String torneosj, String estado, long telefono, int cc,  int deudas, int puntaje, int faltas ) {
        this.nombre = nombre;
        this.correo = correo;
        this.torneosj = torneosj;
        this.estado = estado;
        this.telefono = telefono;
        this.cc = cc;
        this.deudas = deudas;
        this.puntaje = puntaje;
        this.faltas = faltas;    
        this.siguiente = null;
    }
}

class Ranker {
    // datos que se le piden al Jugador
    String nombre;
    int puntaje;

    Ranker siguiente;
    // para cuando se inicie un torneo se agrege a cada jugador
   

    public int puntos(){
        return this.puntaje;
    }
    
    //cracion de un jugador
    public Ranker(String nombre,  int puntaje) {
        this.nombre = nombre;
       
        this.puntaje = puntaje;
       
        this.siguiente = null;
    }
}

class Ranking{
    Ranker frente, fin;
    public Ranking() {
        this.frente = this.fin = null;
       
    }
    // obtener numjugadores
    
    void encolar(String nombre,  int puntaje) {
        Ranker nuevoRanker = new Ranker(nombre, puntaje);

        if (this.frente == null) {
            this.frente = this.fin = nuevoRanker;
            return;
        }

        if (puntaje > this.frente.puntaje) {
            nuevoRanker.siguiente = this.frente;
            this.frente = nuevoRanker;
            return;
        }

        Ranker actual = this.frente;
        while (actual.siguiente != null && actual.siguiente.puntaje >= puntaje) {
            actual = actual.siguiente;
        }

        nuevoRanker.siguiente = actual.siguiente;
        actual.siguiente = nuevoRanker;
        if (nuevoRanker.siguiente == null) {
            this.fin = nuevoRanker;
        }

    }

    void desencolar(String nombre) {
        if (this.frente == null) {
            System.out.println("La cola está vacía.");
            return;
        }
        Ranker temp = this.frente, prev = null;
        while (temp != null && !temp.nombre.equals(nombre)) {
            prev = temp;
            temp = temp.siguiente;
        }
        if (temp == null) {//si temp no se asigna no exite
           // System.out.println("Jugador no encontrado.");
            return;
        }
        if (prev == null) {//si es ta en el frente
            this.frente = this.frente.siguiente;
        } else {//si está en cualquier parte que no sea inicio o fin de la cola 
            prev.siguiente = temp.siguiente;
        }
        if (temp == this.fin) {
            this.fin = prev;//si el JUgador esta al final de la cola
        }
        //System.out.println("Jugador " + nombre + " ha sido quitado de la cola.");
        
    }

    void imprimirRanking(int top) {
        if (frente == null) {
            System.out.println("La ranking está vacía.");
        } else {
            Ranker actual = frente;
            System.out.println("ranking de Jugadores:");
            int ctd = 0;
            while (actual != null && ctd < top) {
                System.out.println("Nombre: " + actual.nombre +   ", puntaje: " + actual.puntaje );
                actual = actual.siguiente;
                ctd++;
            }
        }
    }

}

class Colaj {
    //define un jugador al inicio de la cola(frente) y el final(fin)
    Jugador frente, fin;
    //contador de jugadores
    int numJugadores;
    //definir que numjugadores es 0 cuando frente igual a fin
    public Colaj() {
        this.frente = this.fin = null;
        this.numJugadores = 0; 
    }
    // obtener numjugadores
    public int getNumJugadores() {
        return numJugadores;
    }
    //imprimir toda la cola (el mas demorado)
    void imprimirColaj() {
        if (frente == null) {
            System.out.println("La cola está vacía.");
        } else {
            Jugador actual = frente;
            System.out.println("Cola de Jugadores:");
            while (actual != null) {
                System.out.println("Nombre: " + actual.nombre +", correo: " + actual.correo + ", torneos jugados: " + actual.torneosj + ", estado: " + actual.estado + ", telefono: " + actual.telefono + ", cc: " + actual.cc + ", correo: " +  ", deudas: " + actual.deudas + ", puntaje: " + actual.puntaje + ", faltas: " + actual.faltas);
                actual = actual.siguiente;
            }
        }
    }
    
    //bucar a alguien en esecifico
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
    // contiene la creacion de un nuevo jugador que se encolara.
    void encolarj(String nombre, String correo, String torneosj, String estado, long telefono, int cc,  int deudas, int puntaje, int faltas) {
        Jugador nuevoJugador = new Jugador(nombre, correo, torneosj, estado, telefono, cc,  deudas, puntaje, faltas);

        if (this.fin == null) {
            this.frente = this.fin = nuevoJugador;
            return;
        }

        this.fin.siguiente = nuevoJugador;
        this.fin = nuevoJugador;
        numJugadores++;
    }

    // edicion modificada para poder medir el tiempo de respuesta en milisegundos
    void Editarjugador(String nombre, String correo, String torneosj, String estado, long telefono,  int deudas, int puntaje, int faltas) {
        Jugador actual = frente;
        boolean encontrado = false;
        while(actual != null){
            if(actual.nombre.equalsIgnoreCase(nombre)){
                encontrado = true;
                Scanner scanner = new Scanner(System.in);
                actual.correo = correo;
                actual.torneosj = torneosj;
                actual.estado = estado;
                actual.telefono = telefono;
                actual.deudas = deudas;
                actual.puntaje = puntaje;
                actual.faltas = faltas;
                System.out.println("Datos del Jugador actualizados.");
                break;
            }
            actual = actual.siguiente;
        }
        if(!encontrado){
            System.out.println("jugador no encontrado");
        }
    }
    //elminar de la cola realizando una busqueda poniendo 
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
        if (temp == null) {//si temp no se asigna no exite
            System.out.println("Jugador no encontrado.");
            return;
        }
        if (prev == null) {//si es ta en el frente
            this.frente = this.frente.siguiente;
        } else {//si está en cualquier parte que no sea inicio o fin de la cola 
            prev.siguiente = temp.siguiente;
        }
        if (temp == this.fin) {
            this.fin = prev;//si el JUgador esta al final de la cola
        }
        System.out.println("Jugador " + nombre + " ha sido quitado de la cola.");
        numJugadores--;
    }

    boolean agregarTorneoJugador(String nombre, String nombreTorneo){//añadir torneo a jugadores en la cola
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
    static String[] ganadores;//para usar con funcioes
    public static void main(String[] args) {
        Colaj colaj = new Colaj();//creacion de cola
        Ranking ranking = new Ranking();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inicio programa.");

        //medicion de tiempo de respuesta
        long startTime;
        long endTime;
        long elapsedTime;

        //leer documento.txt para obtener datos inscritos anteriormente
        startTime = System.currentTimeMillis();
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
                colaj.encolarj(nombre, correo, torneosj, estado, telefono, cc, deudas, puntaje, faltas);
                ranking.encolar(nombre, puntaje);
            }
            System.out.println("Datos de los jugadores cargados desde el archivo 'jugadores.txt'.");

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime; //tiempo de respuesta
        System.out.println("El programa ha tardado " + elapsedTime + " milisegundos en leer datos.\n");
        
        //inerfaz de consola
        String comando;
        
        do {
            System.out.print("Ingrese 'E' para encolar,'R' ver rankind, 'F' para editar informacion, 'H'para ver numero de jugadores y de datos, 'D' para desencolar, 'B' para buscar un jugador, 'T'para iniciar torneo, 'I'para ver toda la cola o 'S' para salir: ");
            comando = scanner.nextLine().trim();

            switch (comando.toUpperCase()) {
                case "E"://encolar
                    //ingresar datos para nuevo jugador
                    System.out.print("Ingrese el nombre del jugador: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el correo del jugador: ");
                    String correo = scanner.nextLine();
                    System.out.print("Ingrese la torneos del jugador: ");
                    String torneosj = scanner.nextLine();
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
                    startTime = System.currentTimeMillis();
                    scanner.nextLine(); 
                    
                    colaj.encolarj(nombre, correo, torneosj,  estado, telefono, cc,  deudas, puntaje, faltas);
                    ranking.encolar(nombre, puntaje);
                    System.out.println("Jugador agregado a la cola.");
                    endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime; //tiempo de respuesta
                    System.out.println("El programa ha tardado " + elapsedTime + " milisegundos en responder.\n");
                    break;
                case "R":
                    System.out.println("ingrese longitud para ver el Ranking: ");
                    int largo =scanner.nextInt();
                    startTime = System.currentTimeMillis();
                    ranking.imprimirRanking(largo);
                    endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime; //tiempo de respuesta
                    System.out.println("El programa ha tardado " + elapsedTime + " milisegundos en responder.\n");
                    break;
                case "D"://desencolar
                    if (colaj.frente == null) {
                        System.out.println("La cola está vacía.");
                        break;
                    }
                    System.out.print("Ingrese el nombre del jugador a quitar: ");
                    nombre = scanner.nextLine();
                    startTime = System.currentTimeMillis();
                    colaj.desencolarj(nombre);
                    ranking.desencolar(nombre);
                    endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime;
                    System.out.println("El programa ha tardado " + elapsedTime + " milisegundos en responder.\n");
                    break;
                case "I"://imprimir toda la cola
                    startTime = System.currentTimeMillis();
                    colaj.imprimirColaj();
                    endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime;
                    System.out.println("El programa ha tardado " + elapsedTime + " milisegundos en responder.\n");
                    break;
                case "B"://buscar en la cola
                    if (colaj.frente == null) {
                        System.out.println("La cola está vacía.");
                        break;
                    }
                    System.out.print("Ingrese el nombre del jugador a buscar: ");
                    nombre = scanner.nextLine();
                    startTime = System.currentTimeMillis();
                    colaj.buscarcolaj(nombre);
                    endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime;
                    System.out.println("El programa ha tardado " + elapsedTime + " milisegundos en responder.\n");
                    break;
                case "F"://editar a un jugador de la cola
                    if (colaj.frente == null) {
                    System.out.println("La cola está vacía.");
                    break;
                    }
                    System.out.print("Ingrese el nombre del jugador a editar: ");
                    String nombreedit = scanner.nextLine();
                    startTime = System.currentTimeMillis();
                    System.out.print("datos actuales");
                    colaj.buscarcolaj(nombreedit);endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime;
                    System.out.println("El programa ha tardado " + elapsedTime + " milisegundos en responder busqueda.\n");
                    System.out.print("Ingrese el nuevo correo del jugador: ");
                    String correoedit = scanner.nextLine();
                    System.out.print("Ingrese los nuevos torneos jugados por el jugador: ");
                    String torneosjedit = scanner.nextLine();
                    System.out.print("Ingrese el nuevo estado del jugador: ");
                    String estadoedit = scanner.nextLine();
                    System.out.print("Ingrese el nuevo número de teléfono del jugador: ");
                    long telefonoedit = Long.parseLong(scanner.nextLine());
                    System.out.print("Ingrese las nuevas deudas del jugador: ");
                    int deudasedit = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ingrese el nuevo puntaje del jugador: ");
                    int puntajeedit = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ingrese las nuevas faltas del jugador: ");
                    int faltasedit = Integer.parseInt(scanner.nextLine());
                    startTime = System.currentTimeMillis();
                    ranking.desencolar(nombreedit);
                    colaj.Editarjugador(nombreedit, correoedit, torneosjedit, estadoedit, telefonoedit, deudasedit, puntajeedit, faltasedit);
                    ranking.encolar(nombreedit, puntajeedit);
                    endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime;
                    System.out.println("el programa se ha demorado " + elapsedTime + " milisegundos en resalizar la edicion. \n");
                    break;
                case "T"://iniciar torneo (basado en funciones y no metodos)
                    iniciarTorneo(colaj, scanner);
                    break;
                case "H"://ver numero de datos y jugadores actuales en la cola
                    startTime = System.currentTimeMillis();
                    System.out.println("Número de jugadores registrados: " + (colaj.getNumJugadores()+1));
                    System.out.println("Número de datos registrados: " + ((colaj.getNumJugadores() + 1) * 9 ));
                    endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime;
                    System.out.println("El programa ha tardado " + elapsedTime + " milisegundos en responder.\n");
                    break;
                case "S"://salir (usar para poder guardar en .txt)
                    System.out.println("Saliendo...");
                    scanner.close(); 
                    break;
                default://no comando
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

    //funcion para hacer torneo
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

    //funcion para generar llaves
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

    //para que las llavez sean aleatorias
    static void mezclarParticipantes(String[] participantes, int numParticipantes) {
        Random rnd = new Random();
        for (int i = numParticipantes - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String temp = participantes[index];
            participantes[index] = participantes[i];
            participantes[i] = temp;
        }
    }

    //enfrentamiento por cuadras 
     public static void generarEnfrentamientosCuadros(String[] participantes) {
    System.out.println("\nEnfrentamientos del torneo:");
    int numGrupos = participantes.length / 4;
    String[][] grupos = new String[numGrupos][4];

    // Llenar los grupos con los participantes
    for (int i = 0; i < participantes.length; i++) {
        grupos[i / 4][i % 4] = participantes[i];
    }

    // Generar enfrentamientos para cada grupo
    for (int grupo = 0; grupo < numGrupos; grupo++) {
        System.out.println("Grupo " + (grupo + 1) + ":");
        for (int i = 0; i < grupos[grupo].length; i++) {
            System.out.println("Participante: " + grupos[grupo][i]);
        }
        System.out.println("Enfrentamientos:");
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                System.out.println(grupos[grupo][i] + " vs " + grupos[grupo][j]);
            }
        }
        System.out.println(); // Espacio extra para separar los grupos
    }
}
}
