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
            public int getRank(){
                return rank;
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
    private Node<T>[] arregloLlaves;
    private Node<T>[] arregloLlavesGanadores;
    private int capacidad=2;
    private int size=0;
    private int cont_5=0;

    public DinamicArray(int Capacidadinicial) {
    arreglo = (Nodes<T>[]) new Nodes[capacidad];
    arregloLlaves = (Node<T>[]) new Node[capacidad];
    size = 0;
}
    public int getCapacidad(){
    return capacidad;
    }

    public int getSize() {
        return size;
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
    
    public void pushBack(T e) {
         if (size == capacidad) {
        int nuevaCapacidad = capacidad * 2;
        Node<T>[] nuevoArreglo = (Node<T>[]) new Node[nuevaCapacidad];
        System.arraycopy(arregloLlaves, 0, nuevoArreglo, 1, size);
        arregloLlaves = nuevoArreglo;
        capacidad = nuevaCapacidad;

        }
        arregloLlaves[size] = new Node(e,null);
        size++; 
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
   
   public void pushFront(T e) {
    if (size == capacidad) {
        int nuevaCapacidad = capacidad * 2;
        Node<T>[] nuevoArreglo = (Node<T>[]) new Node[nuevaCapacidad];
        System.arraycopy(arregloLlaves, 0, nuevoArreglo, 1, size);
        arregloLlaves = nuevoArreglo;
        capacidad = nuevaCapacidad;
    } else {
        System.arraycopy(arregloLlaves, 0, arregloLlaves, 1, size);
    }
    arregloLlaves[0] = new Node(e,null); // Por defecto, establecemos juega como true
    size++;
}

  public void agregar_ordenado(String nombre, int puesto) {
    if (size == capacidad) {
        // Redimensiona el arreglo si es necesario
        int nuevaCapacidad = capacidad * 2;
        Nodes<T>[] nuevoArreglo = (Nodes<T>[]) new Nodes[nuevaCapacidad];
        System.arraycopy(arreglo, 0, nuevoArreglo, 0, size);
        arreglo = nuevoArreglo;
        capacidad = nuevaCapacidad;
    }

    Nodes<T> jugador = new Nodes<T>(true, nombre, puesto, null);

    int inf = 0;
    int sup = size - 1;
    int mitad = 0; // Inicializa mitad con un valor válido
    
    boolean encontrado = false;

    while (inf <= sup) {
        mitad = (inf + sup) / 2;
        
        if (arreglo[mitad] == null) {
            // Manejar el caso donde arreglo[mitad] es null
            System.out.println("No hay nada en la posición " + mitad);
            // Aquí puedes decidir cómo manejar este caso según tus necesidades
        } else {
            if (arreglo[mitad].getRank() == jugador.getRank()) {
                encontrado = true;
                break;
            } else if (arreglo[mitad].getRank() > jugador.getRank()) {
                sup = mitad - 1;
            } else {
                inf = mitad + 1;
            }
        }
    }
    
    if (encontrado) {
        System.arraycopy(arreglo, mitad, arreglo, mitad + 1, size - mitad);
        arreglo[mitad] = jugador;
    } else {
        System.arraycopy(arreglo, inf, arreglo, inf + 1, size - inf);
        arreglo[inf] = jugador;
    }
    
    // Incrementa size después de la inserción
    size++;
}

   public void printArray() {
          for (int i = size - 1; i >= 0; i--){
            System.out.println("Ranking: "+arreglo[i].getRank()+"   Nombre: "+arreglo[i].getElement());
        }
        System.out.println();}

   
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
        cont_cuadro++;}
    }


public void texto_torneo() {
    int index = 0;
    while (index < size - 1) { // Ajustar el límite del índice para evitar el desbordamiento
        // Verifica si el elemento actual no es null antes de acceder a su método
        if (arregloLlaves[index] != null && arregloLlaves[index + 1] != null) {
            System.out.print(arregloLlaves[index].getElement());
            arregloLlaves[index] = arregloLlaves[index + 1];
            System.out.println(" vs " + arregloLlaves[index].getElement());
            arregloLlaves[index] = arregloLlaves[index + 1].getNext();
        } 
        index++;
    }
}

public void insertarEN(int i, T e){
arregloLlaves[i] = new Node(e,null);
}

 public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return arregloLlaves[index].getElement();
    }

 public void removeBack() {
    if (size == 0) {
        // Si el arreglo está vacío, no hay elementos para eliminar
        System.out.println("No hay elementos en el arreglo");
        return; // Salir del método
    }
    
    // Verificar si el último elemento en el arregloLlaves es null
    if (arregloLlaves[size - 1] == null) {
        // Si el último elemento es null, reducir el tamaño del arreglo y salir del método
        size--;
        System.out.println("El último elemento en el arreglo es null. Reduciendo el tamaño del arreglo.");
        return;
    }

    System.out.println(arregloLlaves[size-1].getElement());
    arregloLlaves[size - 1] = null; // Elimina el último elemento asignándolo a null
    size--; // Reduce el tamaño del arreglo
}


 public void remove_Especial(boolean derecha, int i) {
    if (derecha) {
        // Si el lado derecho gana, remueve el elemento de la izquierda
       
            arregloLlaves[i-1]=arregloLlaves[i];
            arregloLlaves[i]=null;
            size--;                  
        
    } else {
        arregloLlaves[i]=null;
            size--;  
    }
}

       
        public void texto(){
    int index = 0;
    while (index < size) {
        // Verifica si el elemento actual no es null antes de acceder a su método
        if (arregloLlaves[index] != null) {
            // Se recorre la lista agregando elementos a nuestra lista
            System.out.println(arregloLlaves[index].getElement());
            arregloLlaves[index] = arregloLlaves[index+1];
        }
        index++;
    }
}

    
    }
    
    /*public void texto_torneo(){
        Node puntero=head;
        while(puntero!=null){
            //se recorre la lista agregandole elementos a nuestra lista
            System.out.print(puntero.getElement());
            puntero=puntero.getNext();
            if(puntero==null){
                break;
            }
            System.out.println(" vs " +puntero.getElement());
            puntero=puntero.getNext();
          
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

    

    public int getCapacity() {
        return capacidad;
    }

    public void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.print(arreglo[i] + " ");
        }
        System.out.println();
    }*/
