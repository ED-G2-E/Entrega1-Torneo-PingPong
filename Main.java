/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package g10.prototipo1;
import com.github.javafaker.Faker;
import java.util.Scanner;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
//import java.io.FileReader;
//import java.nio.file.Files;
//import java.nio.file.Paths;

public class Prototipo1 {
    

    public static void main(String[] args) throws IOException{
        crear_llave();
    }
    public static void cuadros_segun_hora(){
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
   public static void imprimir_ranking(){
        
        Faker faker=new Faker();
        Random random=new Random();
        Scanner scan=new Scanner(System.in);
        System.out.print("Cual es la cantidad de jugadores: ");
        int cantidad=scan.nextInt();
        long start=System.nanoTime();
        DinamicArray ArregloDinamico1 = new DinamicArray(2);
        for(int i=0;i<cantidad;i++){
            if(i%10000==0){
                System.out.println(i);
                long duration=(System.nanoTime()-start)/1000000;
                System.out.println(duration + "ms");
            }
            String nombre=faker.name().fullName();
            ArregloDinamico1.agregar_ordenado(nombre,random.nextInt(10000 + 1) + 0 );
        }
        ArregloDinamico1.printArray();
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

        DinamicArray ArregloDinamico1 = new DinamicArray(2);
        int cont=0;
        String[] Llave1=new String[(int)Math.pow(2,rondas)];
        System.out.println("Llave1 "+ Math.pow(2,rondas));
        int n = Llave1.length;
        for(int i=0;i<cantidad;i++){
            int index = (2*i+1)%(n);
            Llave1[index]= faker.name().fullName();    
            cont++;
            if(i%10000==0){
                System.out.println(i);
                long duration=(System.nanoTime()-start)/1000000;
                System.out.println(duration + "ms");
            }
        }
        
        System.out.println("Llave1 " + Arrays.toString(Llave1));
        int i=1;
        while(cont<cantidad){           
            cont=cont+2;
            Llave1[i]=faker.name().fullName();
            Llave1[Llave1.length-i]=faker.name().fullName();
            i=i+2;                
            if (cantidad % 2 != 0){
                 Llave1[Llave1.length - 1] = faker.name().fullName();
                break;
            }
        }
        System.out.println("ronda 1");
        for(int j=0;j<(Llave1.length);j=j+2){
            System.out.print(Llave1[j]);
            System.out.println(" vs "+Llave1[j+1] );
        }
        int cont_ronda=0;
        for (int j=0;j<(Llave1.length);j=j+2){
            if (random.nextBoolean()){
                if(Llave1[j]==null){
                    ArregloDinamico1.pushBack(Llave1[j+1]);
                }else{
                    ArregloDinamico1.pushBack(Llave1[j]);
                }
            }
            else{
                if(Llave1[j]==null){
                    ArregloDinamico1.pushBack(Llave1[j+1]);
                }else{
                    ArregloDinamico1.pushBack(Llave1[j]);
                }
            }
        }
        System.out.println("ronda 2");
        ArregloDinamico1.texto_torneo();
        cont_ronda++;
        System.out.println("Capacidad arrwglo "+ArregloDinamico1.getCapacidad());
        while(cont_ronda<=(rondas-2)){
            System.out.println("ronda "+(cont_ronda+2));
            int cont_G=0;
             
           for (int j = ArregloDinamico1.getSize(); j > 3; j -= 2) {
            if (random.nextBoolean()){//el verdadero representa que gano el de la izquierda
   
                ArregloDinamico1.remove_Especial(true,j);
            }
            else{
                ArregloDinamico1.remove_Especial(false,j);
            }
            cont_G++;
            }
            cont_ronda++;
            
            ArregloDinamico1.texto_torneo();           
            
        }
        System.out.println("ganador es: ");
        if(random.nextBoolean()){
            ArregloDinamico1.removeBack();
        }
        else{
            ArregloDinamico1.removeBack();
            ArregloDinamico1.texto();
        }
        long duration=(System.nanoTime()-start)/1000000;
        System.out.println(duration + "ms");
        
}
    }
