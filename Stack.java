/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g10.prototipo1;

//import java.util.Arrays;
//import java.util.Random;

/**
 *
 * @author stw01
 */
// Clase Nodes con características deseadas
class Nodes<T> {
    Boolean juega;
    public String element;
    public int rank;
    private Nodes<T> next;

    public Nodes(Boolean juega, String name, int ranki, Nodes<T> n) {
        this.juega = juega;
        this.element = name;
        this.rank = ranki;
        this.next = n;
    }

    public String getElement() {
        return element;
    }

    public Nodes<T> getNext() {
        return next;
    }

    public void setNext(Nodes<T> n) {
        next = n;
    }
}

public class Stack<T> {
    public int top;
    private int topH5;
    private int topH6;
    public Nodes<T>[] arreglo;
    private T[] TurnoH5;
    private T[] TurnoH6;
    
    //private int mitad ;
    //private int mitadExtra;

    public Stack(int cantidad) {
        top = 0;
        arreglo = (Nodes<T>[]) new Nodes[cantidad];
        TurnoH5 = (T[]) new Nodes[cantidad];
        TurnoH6 = (T[]) new Nodes[cantidad];
        
        
        //mitad = cantidad /2;
        //mitadExtra = cantidad % 2 == 0 ? mitad : mitad + 1;
    }

    // Métodos que devuelven valores
    public boolean EstaVacia() {
        return top <= 0;
    }

    public boolean EstaLlena() {
        return top >= arreglo.length;
    }

    public Nodes<T> Desapilar() {
        if (EstaVacia())
            throw new RuntimeException("La pila está vacía");
        top--;
        return arreglo[top];
    }
    
    public int DevolverTop(){
        return top;
    }
    
    int cuentaH5;
    int cuentaH6;
    // Método sin valor de retorno
    public void Apilar(Boolean Eleccion,String nombre, int rank) {
        

    if (EstaLlena()) {
        throw new RuntimeException("Stack is full");
    }
    
    Nodes<T> newNode = new Nodes<T>(Eleccion, nombre, rank, null); // Crear el nuevo nodo con el valor de juega_5
    
    if (newNode.juega) {
        if (topH5 > TurnoH5.length) {
            throw new RuntimeException("TurnoH5 is full");
        }
        TurnoH5[topH5] = (T) newNode;
        topH5++;
        cuentaH5++;
    } 
    
    else {
        if (topH6 > TurnoH6.length) {
            throw new RuntimeException("TurnoH6 is full");
        }
        TurnoH6[topH6] = (T) newNode; // Aquí se agrega el nodo al arreglo TurnoH6
        topH6++;
        cuentaH6++;
    }

    arreglo[top] = newNode;
    top++;
    
    
    
}
    
    
    public void texto_cuadros() {
    int cuadroActual = 1;
    int jugadoresEnCuadro = 0;
    int maxJugadoresPorCuadro = 4;
    System.out.println("\n Cuadros jugadores de las 5");
    for (int i = 0; i < cuentaH5 ; i++) {
        if (jugadoresEnCuadro == 0) {
            System.out.println("\n cuadro #" + cuadroActual+"\n");
            cuadroActual++;
        }
        Nodes<T> current = (Nodes<T>) TurnoH5[i];
        
        System.out.println(current.element );
        //System.out.println(current.getElement() + " - Rank: " + current.rank);

        jugadoresEnCuadro++;

        if (jugadoresEnCuadro == maxJugadoresPorCuadro || i == TurnoH5.length - 1) {
            jugadoresEnCuadro = 0;
        }
    }

    cuadroActual = 1;
    jugadoresEnCuadro = 0;

    System.out.println("\n Cuadros jugadores de las 6");
    for (int i = 0; i < cuentaH6; i++) {
        if (jugadoresEnCuadro == 0) {
            System.out.println("\n cuadro #" + cuadroActual+"\n");
            cuadroActual++;
        }

        Nodes<T> current = (Nodes<T>) TurnoH6[i];
        System.out.println(current.getElement() );

        jugadoresEnCuadro++;

        if (jugadoresEnCuadro == maxJugadoresPorCuadro || i == TurnoH6.length - 1) {
            jugadoresEnCuadro = 0;
        }
    } 
}

    
// System.arraycopy

    
public void organizarPorRank() {
    // Encontrar el rango máximo
    int maxRank = 0;
    for (int i = 0; i < top; i++) {
        if (arreglo[i].rank > maxRank) {
            maxRank = arreglo[i].rank;
        }
    }

    // Crear un arreglo auxiliar para contar los elementos por rango
    int[] count = new int[maxRank + 1];
    Nodes<T>[] output = new Nodes[top];

    // Contar la frecuencia de cada rango
    for (int i = 0; i < top; i++) {
        count[arreglo[i].rank]++;
    }

    // Calcular la posición de inicio de cada rango
    for (int i = 1; i <= maxRank; i++) {
        count[i] += count[i - 1];
    }

    // Construir el arreglo ordenado
    for (int i = top - 1; i >= 0; i--) {
        output[count[arreglo[i].rank] - 1] = arreglo[i];
        count[arreglo[i].rank]--;
    }

    // Copiar el arreglo ordenado de vuelta al arreglo original
    System.arraycopy(output, 0, arreglo, 0, top);
}

public void imprimirRanking() {
    // Organizar el arreglo por rango
    organizarPorRank();

    // Imprimir el arreglo en orden inverso (de mayor a menor rango)
    for (int i = top - 1; i >= 0; i--) {
        System.out.println("Nombre: " + arreglo[i].element + ", Rango: " + arreglo[i].rank);
    }
}

public static void imprimirJugadores(Stack<Nodes<String>> Pila) {
    for (int i = Pila.top - 1; i > 0; i=i-2) {
        
        System.out.print(Pila.arreglo[i].element);
        System.out.println(" vs "+Pila.arreglo[i-1].element);
        
    }
}


}
