/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g10.prototipo1;

public class DinamicArray<T> {
    private static class Nodes<T>{
            private Boolean juega;
            private String element;
            public int rank;
            private Nodes<T> next;
            public Nodes(Boolean juega5,String name,int ranki,Nodes<T> n){
                juega=juega5;
                element=name;
                rank=ranki;
                next=n;
            }
            public String getElement(){
                return element;
            }
            public Nodes<T> getNext(){
                return next;
            }
            public void setNext(Nodes<T> n){
                next=n;
            }
    }
    private static class Node<T>{
            private T element;
            private Node<T> next;
            public Node(T e,Node<T> n){
                element=e;
                next=n;
            }
            public T getElement(){
                return element;
            }
            public Node<T> getNext(){
                return next;
            }
            public void setNext(Node<T> n){
                next=n;
            }
        }
    
   
    // Implementación
    private Nodes<T>[] arreglo;
    private int capacidad=2;
    private int size=0;
    private int cont_5=0;
    private int cont_6=0;

    public DinamicArray(int Capacidadinicial) {
    arreglo = (Nodes<T>[]) new Nodes[capacidad];
    size = 0;
}

    public void pushBack(String nombre, int rank) {
         if (size == capacidad) {
            int nuevaCapacidad = capacidad * 2;
            Nodes<T>[] nuevoArreglo = (Nodes<T>[]) new Nodes[nuevaCapacidad];
            System.arraycopy(arreglo, 0, nuevoArreglo, 0, size);
            arreglo = nuevoArreglo;
            capacidad = nuevaCapacidad;
        }
        arreglo[size] = new Nodes(true, nombre, rank, null);
        size++;
        cont_5++; 
    }
    
   public void pushFront(String nombre, int rank) {
    if (size == capacidad) {
        int nuevaCapacidad = capacidad * 2;
        Nodes<T>[] nuevoArreglo = (Nodes<T>[]) new Nodes[nuevaCapacidad];
        System.arraycopy(arreglo, 0, nuevoArreglo, 1, size);
        arreglo = nuevoArreglo;
        capacidad = nuevaCapacidad;
    } else {
        System.arraycopy(arreglo, 0, arreglo, 1, size);
    }
    arreglo[0] = new Nodes(true, nombre, rank, null); // Por defecto, establecemos juega como true
    size++;
}

   
   
    public void texto_cuadros() {
    int cont_cuadro = 1;
    int index = 0;

    System.out.println("Cuadros jugadores");
    while (index < size && index < cont_5) {
        System.out.println("cuadro #" + cont_cuadro);
        int jugadoresRestantes = Math.min(4, cont_5 - index);
        for (int j = 0; j < jugadoresRestantes; j++) {
            System.out.println(arreglo[index].getElement());
            index++;
        }
        cont_cuadro++;
    }
    }

    }
    
    
    
    
    
    
   

   /* public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return arreglo[index];
    }

    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false; // Índice fuera de rango
        }
        // Desplaza los elementos hacia la izquierda para llenar el espacio del elemento eliminado
        for (int i = index; i < size - 1; i++) {
            arreglo[i] = arreglo[i + 1];
        }
        arreglo[size - 1] = 0; // Limpia el último elemento
        size--;
        return true;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacidad;
    }

    public void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.print(arreglo[i] + " ");
        }
        System.out.println();
    }*/

