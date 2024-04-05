/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package g10.prototipo1;
import com.github.javafaker.Faker;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
// import org.json.JSONObject;
import java.util.Random;
// import org.json.JSONArray;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author jdsae
 */
public class Prototipo1 {

    public static void main(String[] args) throws IOException{
            Pila_crear_llave_Stack();
    }
    
    public static void Pilas_cuadros_segun_hora(){
        Faker faker=new Faker();
        Random random=new Random();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        long start=System.nanoTime();
        
        Stack<Nodes> pila = new Stack<>(cantidad); // Crear la pila con el tamaño especificado
    
        
        for(int i=0;i<cantidad;i++){
            if(i%10000==0){
                System.out.println(i);
                long duration=(System.nanoTime()-start)/1000000;
                System.out.println(duration + "ms");
            }
            String nombre=faker.name().fullName();
            Boolean juega_5=random.nextBoolean();
            // System.out.println ("Nombre: "+ nombre + "\n"+"Juega a las 5? "+ juega_5);
            
            pila.Apilar(juega_5,nombre,random.nextInt(10000 + 1) + 0 );
            
        }
        pila.texto_cuadros();
        long duration=(System.nanoTime()-start)/1000000;
        System.out.println(duration + "ms");

    }
    
    public static void Pilas_imprimir_ranking_Stacks(){
        
        Faker faker=new Faker();
        Random random=new Random();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        long start=System.nanoTime();
        
        Stack<Nodes> pila = new Stack<>(cantidad);
        
        //ingresa la cantidad de jugadores
        for(int i=0;i<cantidad;i++){
            if(i%10000==0){
                System.out.println(i);
                long duration=(System.nanoTime()-start)/1000000;
                System.out.println(duration + "ms");
            }
            //lista.reset_puntero();
            String nombre=faker.name().fullName();
             pila.Apilar(true, nombre,random.nextInt(10000 + 1) + 0  );
        }
        pila.imprimirRanking();
        long duration=(System.nanoTime()-start)/1000000;
        System.out.println(duration + "ms");
        
    }
    
    public static void Pila_crear_llave_Stack() {
        Random random = new Random();
        Faker faker = new Faker();
        Scanner scan = new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad = scan.nextInt();
        long start = System.nanoTime();
        int rondas = (int) (Math.ceil(Math.log(cantidad) / Math.log(2)));

        Stack<Nodes<String>> pila = new Stack<>(cantidad);
        int cont = 0;
        
        String[] Llave1 = new String[(int) Math.pow(2, rondas)];

        for (int i = 0; i < Llave1.length; i = i + 2) {
            Llave1[i] = faker.name().fullName();
            cont++;
            //pila.Apilar(random.nextBoolean(), Llave1[i], i);
            //System.out.println("Apilando: " + Llave1[i]);
            if (i % 10000 == 0) {
                System.out.println(i);
                long duration = (System.nanoTime() - start) / 1000000;
                System.out.println(duration + "ms");
            }
        }
        int i=1;
        while(cont<cantidad){           
            cont=cont+2;
            Llave1[i]=faker.name().fullName();
            Llave1[Llave1.length-i]=faker.name().fullName();
            i=i+2;                
            if (cont+1==cantidad){
                Llave1[i]=faker.name().fullName();
                cont++;
            }
        }
        System.out.println("ronda 1");
        for(int j=0;j<Llave1.length;j=j+2){
            System.out.print(Llave1[j]);
            System.out.println(" vs "+Llave1[j+1] );
        }
        
        int cont_ronda = 1;
        for (int j = 0; j < (Llave1.length); j = j + 2) {
            if (random.nextBoolean()) {
                if (Llave1[j] == null) {
                    pila.Apilar(true, Llave1[j + 1], j);
                   // System.out.println("ronda 0 null: " + Llave1[j+1]);
                } else {
                    pila.Apilar(true, Llave1[j], j);
                  //  System.out.println("ronda 0: " + Llave1[j]);
                }
            } else {
                if (Llave1[j] == null) {
                    pila.Apilar(true, Llave1[j + 1], j);
                 //   System.out.println("1ronda 0 null: " + Llave1[j+1]);
                } else {
                    pila.Apilar(true, Llave1[j], j);
                  //  System.out.println("1ronda 0: " + Llave1[j]);
                }
            }
        }
        //
        cont_ronda++;
        
        Stack<Nodes<String>> pila2 = new Stack<>((int)Math.ceil(Math.pow(2, rondas))/4);
        
        
        
        Boolean seguir= true;
        while(seguir){
            
            //System.out.println("Valor "+pila.top);
            
            if(pila2.EstaVacia()){
                System.out.println("\nRonda: "+ cont_ronda);
                Stack.imprimirJugadores(pila);
                
                
                System.out.println("\n ronda " + cont_ronda);
                
                if (pila.top == 1 || pila.top == 0){
                seguir = false;
                }
                
                int valortop1 = pila.DevolverTop();
                
                
                
                
                for(int j=0; j < valortop1/2 ; j++ ){

                
                    
                        
                    
                    //System.out.println("Valor2: "+pila.top);
                    if(random.nextBoolean()){
                       Nodes Jugador   = pila.Desapilar();
                       //System.out.println("Valor3: "+pila.top);
                       //System.out.println(Jugador);
                        //pila2.Apilar(pila.Desapilar());
                       pila2.Apilar(true,Jugador.element,Jugador.rank);
                       pila.Desapilar();
                    }else{
                       pila.Desapilar();
                       Nodes Jugador = pila.Desapilar();
                       //System.out.println("Valor3_2: "+pila.top);
                       //System.out.println(Jugador);
                       //pila2.Apilar(pila.Desapilar());
                       pila2.Apilar(true,Jugador.element,Jugador.rank);
                    }
                }
                
            }else{
                System.out.println("Ronda: "+ cont_ronda);
                Stack.imprimirJugadores(pila2);
                //System.out.println("\n ronda" + cont_ronda);
                
                if (pila.top == 1 || pila.top == 0){
                seguir = false;
                }
                
                
                
                int valortop2 = pila2.DevolverTop() ;
                for(int j=0; j < valortop2/2 ; j++ ){
                    
                    if(random.nextBoolean()){
                       Nodes Jugador   = pila2.Desapilar();
                       //System.out.println(Jugador);
                        //pila2.Apilar(pila.Desapilar());
                       pila.Apilar(true,Jugador.element,Jugador.rank);
                       pila2.Desapilar();
                    }else{
                       pila2.Desapilar();
                       Nodes Jugador = pila2.Desapilar();
                       //System.out.println(Jugador);
                       //pila2.Apilar(pila.Desapilar());
                       pila.Apilar(true,Jugador.element,Jugador.rank);
            }
        }
        }

    }
        
        System.out.println("\nRonda Final");
            if (pila2.EstaVacia()){
            Stack.imprimirJugadores(pila);
            }
            else{
            Stack.imprimirJugadores(pila2);
            }
            
        // Imprime el ganador
        System.out.println("\n Ganador es: ");
        if (random.nextBoolean()) {
            if (pila2.EstaVacia()){
            System.out.println(pila.Desapilar().getElement());
            }
            else{
            System.out.println(pila2.Desapilar().getElement());
            }
        } else {
            if (pila2.EstaVacia()){
            System.out.println(pila.Desapilar().getElement());
            }
            else{
            System.out.println(pila2.Desapilar().getElement());
            }
            //System.out.println(pila2.Desapilar().getElement());
            // No hay método texto(), debes adaptar este bloque según lo que quieras hacer aquí
        }
        long duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }


    
    ///////////////ARRIBA/////////PILAS////////////STACKS/////////ARRIBA//////////////////
    
    
    
    
    public static void cuadros_segun_hora(){
        Faker faker=new Faker();
        Random random=new Random();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        long start=System.nanoTime();
        SinglyLinkedList lista=new SinglyLinkedList();
        for(int i=0;i<cantidad;i++){
            if(i%10000==0){
                System.out.println(i);
                long duration=(System.nanoTime()-start)/1000000;
                System.out.println(duration + "ms");
            }
            lista.reset_puntero();
            String nombre=faker.name().fullName();
            Boolean juega_5=random.nextBoolean();
            if (juega_5){
                lista.addFirst_N(nombre,random.nextInt(10000 + 1) + 0 );
            }else{
                lista.addLast_N(nombre,random.nextInt(10000 + 1) + 0 );
            }    
        }
        lista.texto_cuadros();
        long duration=(System.nanoTime()-start)/1000000;
        System.out.println(duration + "ms");

    }
    public static void imprimir_ranking(){
        
        Faker faker=new Faker();
        Random random=new Random();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        long start=System.nanoTime();
        SinglyLinkedList lista=new SinglyLinkedList();
        //ingresa la cantidad de jugadores
        for(int i=0;i<cantidad;i++){
            if(i%10000==0){
                System.out.println(i);
                long duration=(System.nanoTime()-start)/1000000;
                System.out.println(duration + "ms");
            }
            lista.reset_puntero();
            String nombre=faker.name().fullName();
            lista.agregar_ordenado(nombre,random.nextInt(10000 + 1) + 0 );
        }
        lista.imprimir_ranking();
        long duration=(System.nanoTime()-start)/1000000;
        System.out.println(duration + "ms");
        
    }
    public static void crear_llave(){
        Random random=new Random();
        Faker faker=new Faker();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        long start=System.nanoTime();
        //calcula el numero de rondas con el logaritmo base 2, pues se descartan de a 2 jugadores por llave
        int rondas=(int) (Math.ceil(Math.log(cantidad)/Math.log(2)));

        SinglyLinkedList lista=new SinglyLinkedList();
        int cont=0;
        String[] Llave1=new String[(int)Math.pow(2,rondas)];
        for(int i=0;i<Llave1.length;i=i+2){
            Llave1[i]= faker.name().fullName();    
            cont++;
            if(i%10000==0){
                System.out.println(i);
                long duration=(System.nanoTime()-start)/1000000;
                System.out.println(duration + "ms");
            }
        }
        int i=1;
        while(cont<cantidad){           
            cont=cont+2;
            Llave1[i]=faker.name().fullName();
            Llave1[Llave1.length-i]=faker.name().fullName();
            i=i+2;                
            if (cont+1==cantidad){
                Llave1[i]=faker.name().fullName();
                cont++;
            }
        }
        System.out.println("ronda 1");
        for(int j=0;j<Llave1.length;j=j+2){
            System.out.print(Llave1[j]);
            System.out.println(" vs "+Llave1[j+1] );
        }
        int cont_ronda=0;
        for (int j=0;j<(Llave1.length);j=j+2){
            if (random.nextBoolean()){
                if(Llave1[j]==null){
                    lista.addFirst(Llave1[j+1]);
                }else{
                    lista.addFirst(Llave1[j]);
                }
            }
            else{
                if(Llave1[j]==null){
                    lista.addFirst(Llave1[j+1]);
                }else{
                    lista.addFirst(Llave1[j]);
                }
            }
        }
        lista.texto_torneo();
        cont_ronda++;
        while(cont_ronda<=(rondas-2)){
            System.out.println("ronda "+(cont_ronda+3));
            
            int tamaño_lista=lista.size();
            for (int j=0;j<(tamaño_lista/2);j++){
            if (random.nextBoolean()){//el verdadero representa que gano el de la izquierda
                lista.remove_Especial(true);
            }
            else{
                lista.remove_Especial(false);
            }
            }
            cont_ronda++;
            
            lista.texto_torneo();
            lista.reset_puntero();
            
            
        }
        System.out.println("ganador es: ");
        if(random.nextBoolean()){
            System.out.println(lista.removeFirst());
        }
        else{
            lista.removeFirst();
            lista.texto();
        }
        long duration=(System.nanoTime()-start)/1000000;
        System.out.println(duration + "ms");
        
}

    private static Boolean Jugador(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}