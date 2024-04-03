/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package g10.prototipo1;
import com.github.javafaker.Faker;
import java.util.Scanner;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
//import java.io.FileReader;
//import java.nio.file.Files;
//import java.nio.file.Paths;

public class Prototipo1 {
    

    public static void main(String[] args) throws IOException{
            cuadros_segun_hora();
    }
    public static void cuadros_segun_hora(){
         int cont_5=0;
         int cont_6=0;
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
                ArregloDinamico1.pushFront(nombre,random.nextInt(10000 + 1) + 0 );
                cont_5++;
            }else{
                ArregloDinamico2.pushFront(nombre,random.nextInt(10000 + 1) + 0 );
                cont_6++;
            }    
        }
        System.out.println("5");
        ArregloDinamico1.texto_cuadros();
        System.out.println("6");
        ArregloDinamico2.texto_cuadros();
        long duration=(System.nanoTime()-start)/1000000;
        System.out.println(duration + "ms");

    }
   /*public static void imprimir_ranking(){
        
        Faker faker=new Faker();
        Random random=new Random();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        long start=System.nanoTime();
        //SinglyLinkedList lista=new SinglyLinkedList();
        //ingresa la cantidad de jugadores
        for(int i=0;i<cantidad;i++){
            if(i%10000==0){
                System.out.println(i);
                long duration=(System.nanoTime()-start)/1000000;
                System.out.println(duration + "ms");
            }
            //lista.reset_puntero();
            String nombre=faker.name().fullName();
            //lista.agregar_ordenado(nombre,random.nextInt(10000 + 1) + 0 );
        }
        //lista.imprimir_ranking();
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

        //SinglyLinkedList lista=new SinglyLinkedList();
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
        System.out.println("ronda 2");
        lista.texto_torneo();
        cont_ronda++;
        while(cont_ronda<=(rondas-2)){
            System.out.println("ronda "+(cont_ronda+2));
            
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
        
}*/
}
