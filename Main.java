
package g10.prototipo1;
import com.github.javafaker.Faker;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import java.util.Random;
import org.json.JSONArray;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Autor: Unos cracks
 */
public class Prototipo1 {

    public static void main(String[] args) throws IOException{
        int opcion=0;
        System.out.println(" Bienvenido a PongNet su organizador de torneos ");
        Scanner scan=new Scanner(System.in);
        while (opcion!=4){
            System.out.println("");
            System.out.println("Opcion 1:crear cuadros segun la hora");
            System.out.println("Opcion 2:crear ranking de jugadores");
            System.out.println("Opcion 3:crear llaves del torneo");
            System.out.println("Opcion 4:salir");
            System.out.println("Digite que opción quiere ejecutar: ");

            opcion=scan.nextInt();
            switch (opcion) {
                case 1 -> cuadros_segun_hora();
                case 2 -> Pilas_imprimir_ranking_Stacks();
                case 3 -> crear_llave();
                case 4 -> System.out.println(" Gracias por tu visita vuelve pronto <3");
                default -> {
                }
            }
        }
    }
     public static void cuadros_segun_hora_AD(){
            Faker faker=new Faker();
        Random random=new Random();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        long start=System.nanoTime();
        DinamicArray ArregloDinamico1 = new DinamicArray(2);
        DinamicArray ArregloDinamico2 = new DinamicArray(2);
        
        for(int i=0;i<cantidad;i++){
            if(i%10000==0){
                System.out.println(i);
                long duration=(System.nanoTime()-start)/1000000;
                System.out.println(duration + "ms");
            }
            String nombre=faker.name().fullName();
            Boolean juega_5=random.nextBoolean();
            if (juega_5){
                ArregloDinamico1.pushBack(nombre,random.nextInt(10000 + 1) + 0 );
               
            }else{
                ArregloDinamico2.pushBack(nombre,random.nextInt(10000 + 1) + 0 );
            
            }    
        }
        System.out.println("5");
        ArregloDinamico1.texto_cuadros();
        System.out.println("6");
        ArregloDinamico2.texto_cuadros();
        long duration=(System.nanoTime()-start)/1000000;
        System.out.println(duration + "ms");

    }
    public static void cuadros_con_pilas(){
        Faker faker=new Faker();
        Random random=new Random();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        long start=System.nanoTime();
        for(int i=0;i<cantidad;i++){
            if(i%10000==0){
                System.out.println(i);
                long duration=(System.nanoTime()-start)/1000000;
                System.out.println(duration + "ms");
            }
        String nombre=faker.name().fullName();
        Boolean juega_5=random.nextBoolean();
        if (juega_5){
                //pila.apilar(nombre,random.nextInt(10000 + 1) + 0 );
            }else{
                //pila.addLast_N(nombre,random.nextInt(10000 + 1) + 0 );
            }  
        
    }
    }
    public static void cuadros_segun_hora(){
        Faker faker=new Faker();
        Random random=new Random();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        SinglyLinkedList lista=new SinglyLinkedList();
        for(int i=0;i<cantidad;i++){
           // lista.reset_puntero();
           System.out.println("Ingrese el nombre del jugador: ");
            String nombre=faker.name().fullName();
            System.out.println(nombre); 
            System.out.println("El jugador juega a las 5 de la tarde? ");
            Boolean juega_5=true;
            String respuesta=scan.next();
            juega_5=respuesta.equalsIgnoreCase("si");
            if (juega_5){
                System.out.println("El jugador juega a las 5 de la tarde? ");
                lista.addFirst_N(nombre,random.nextInt(10000 + 1) + 0 );
            }else{
                lista.addLast_N(nombre,random.nextInt(10000 + 1) + 0 );
            }    
        }
        System.out.println("");
        lista.texto_cuadros();

    }
    public static void Pilas_imprimir_ranking_Stacks(){
        
        Faker faker=new Faker();
        Random random=new Random();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        
        Stack<Nodes> pila = new Stack<>(cantidad);
        
        //ingresa la cantidad de jugadores
        for(int i=0;i<cantidad;i++){

            //lista.reset_puntero();
            System.out.println("Ingrese el nombre del jugador: ");
            String nombre=faker.name().fullName();
            System.out.println(nombre); 
            System.out.println("Ingrese el puntaje del jugador: ");
            int puntaje=scan.nextInt();
             pila.Apilar(true, nombre,puntaje );
        }
        System.out.println("");
        System.out.println("Ranking jugadores :");
        pila.imprimirRanking();
        
    }
    public static void crear_llave(){
        Random random=new Random();
        Faker faker=new Faker();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        //calcula el numero de rondas con el logaritmo base 2, pues se descartan de a 2 jugadores por llave
        int rondas=(int) (Math.ceil(Math.log(cantidad)/Math.log(2)));

        SinglyLinkedList lista=new SinglyLinkedList();
        int cont=0;
        String[] Llave1=new String[(int)Math.pow(2,rondas)];
        for(int i=0;i<Llave1.length;i=i+2){
            System.out.println("Ingrese el nombre del jugador: ");
            Llave1[i]= faker.name().fullName();    
            System.out.println(Llave1[i]);
            cont++;
        }
        int i=1;
        if (cont+1==cantidad){
                System.out.println("Ingrese el nombre del jugador: ");
                Llave1[i]=faker.name().fullName();       
                System.out.println(Llave1[i]);
                cont++;
            }

        while(cont<cantidad){           
            cont=cont+2;
            System.out.println("Ingrese el nombre del jugador: ");
            Llave1[i]=faker.name().fullName();            
            System.out.println(Llave1[i]);
            System.out.println("Ingrese el nombre del jugador: ");
            Llave1[Llave1.length-i]=faker.name().fullName();
            System.out.println(Llave1[Llave1.length-i]);
            i=i+2;                
            if (cont+1==cantidad){
                Llave1[i]=faker.name().fullName();
                cont++;
            }
        }
        System.out.println("");
        System.out.println("Ronda 1");
        for(int j=0;j<Llave1.length;j=j+2){
            System.out.print(Llave1[j]);
            System.out.println(" vs "+Llave1[j+1] );
        }
        
        
        int cont_ronda=0;
        for (int j=0;j<(Llave1.length);j=j+2){
            System.out.println("En la llave #"+(j/2+1)+" gano el primer jugador? ");
            String respuesta=scan.next();
            if (respuesta.equalsIgnoreCase("si")){
                if(Llave1[j]==null){
                    lista.addFirst(Llave1[j+1]);
                }else{
                    lista.addFirst(Llave1[j]);
                }
            }
            else{
                if(Llave1[j+1]==null){
                    lista.addFirst(Llave1[j]);
                }else{
                    lista.addFirst(Llave1[j+1]);
                }
            }
        }
        System.out.println("Ronda 2");
        lista.texto_torneo();
        cont_ronda++;
        while(cont_ronda<=(rondas-2)){
            int tamaño_lista=lista.size();
            for (int j=0;j<(tamaño_lista/2);j++){
            System.out.println("En la llave #"+(j+1)+" gano el primer jugador? ");
            String respuesta=scan.next();
            if (respuesta.equalsIgnoreCase("si")){//el verdadero representa que gano el de la izquierda
                lista.remove_Especial(true);
            }
            else{
                lista.remove_Especial(false);
            }
            }
            cont_ronda++;
            System.out.println("Ronda "+(cont_ronda+2));
            lista.texto_torneo();
            lista.reset_puntero();
            
            
        }
        System.out.println( "gano el primer jugador? ");
        String respuesta=scan.next();
        System.out.println("El ganador es: ");
        if(respuesta.equalsIgnoreCase("si")){
            System.out.println(lista.removeFirst());
        }
        else{
            lista.removeFirst();
            lista.texto();
        }
        
}
        public static void crear_llave_AD(){
        Random random=new Random();
        Faker faker=new Faker();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        long start=System.nanoTime();
        //calcula el numero de rondas con el logaritmo base 2, pues se descartan de a 2 jugadores por llave
        int rondas=(int) (Math.ceil(Math.log(cantidad)/Math.log(2)));

        int cont=0;
        String[] Llave1=new String[(int)Math.pow(2,rondas)];
        for(int i=0;i<Llave1.length;i=i+2){
            Llave1[i]= faker.name().fullName();    
            cont++;
        }
        int i=1;
        if (cont+1==cantidad){
                Llave1[i]=faker.name().fullName();
                cont++;
            }

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
        int cont_agregar=0;
        String[] Llave_n=new String[Llave1.length/2];
        for (int j=0;j<(Llave1.length);j=j+2){
            if (random.nextBoolean()){
                if(Llave1[j]==null){
                    Llave_n[cont_agregar]=Llave1[j+1];
                }else{
                    Llave_n[cont_agregar]=Llave1[j];
                }
            }
            else{
                if(Llave1[j+1]==null){
                    Llave_n[cont_agregar]=Llave1[j];
                }else{
                    Llave_n[cont_agregar]=Llave1[j+1];
                }
            }
            cont_agregar++;
        }
        System.out.println("ronda 2");
        cont_ronda++;
        for(int j=0;j<Llave_n.length;j=j+2){
            System.out.print(Llave_n[j]);
            System.out.println(" vs "+Llave_n[j+1] );
        }
        while(cont_ronda<=(rondas-2)){
            int cont_g=0;
            for(int j=0;j<(Llave_n.length/(Math.pow(2,cont_ronda-1)));j=j+2){              
                if(random.nextBoolean()){
                    Llave_n[cont_g]=Llave_n[j];
                }
                else{
                    Llave_n[cont_g]=Llave_n[j+1];
                }
                cont_g++;
            }
            System.out.println("ronda "+(cont_ronda+2));
            for(int j=0;j<Llave_n.length/(Math.pow(2,cont_ronda));j=j+2){
            System.out.print(Llave_n[j]);
            System.out.println(" vs "+Llave_n[j+1] );
            }
            cont_ronda++;
        }
        
        System.out.println("ganador es: ");
        if(random.nextBoolean()){
            System.out.println(Llave_n[0]);
        }
        else{
            System.out.println(Llave_n[1]);            
        }
        long duration=(System.nanoTime()-start)/1000000;
        System.out.println(duration + "ms");
        
}
}
