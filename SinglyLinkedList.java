/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g10.prototipo1;

/**
 *
 * @author jdsae
 */
public class SinglyLinkedList<T> {
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
    private Nodes<T> puntero1=null;
    private Nodes<T> cabeza=null;
    private Nodes<T> cola=null;
    private Node<T> head=null;
    private Node<T> tail=null;
    private int size=0;
    private int cont_5=0;
    private int cont_6=0;
    private Node<T> cursor=null;
    public SinglyLinkedList(){}
    public void reset(){
        puntero1=null;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public T first(){
        if(isEmpty()){
            return null;
        }
        return head.getElement();
    }
    public T last(){
        if(isEmpty()){
            return null;
        }
        return tail.getElement();
    }
    public void addFirst(T e){
        head= new Node<>(e,head);
        if(size==0){
            tail=head;
        }
        size ++;
        //es una persona más que juega a las 5
    }
    public void addLast(T e){
        Node<T> newNode=new Node(e,null);
        if(isEmpty()){
            head=newNode;
        }
        else{
            tail.setNext(newNode);
        }
        tail=newNode;
        size ++;
        
    }
    public void addFirst_N(String name,int rank){
        cabeza= new Nodes(true,name,rank,cabeza);
        if(size==0){
            cola=cabeza;
        }
        size ++;
        cont_5++;//es una persona más que juega a las 5
    }
    public void addLast_N(String name,int rank){
        Nodes<T> newNode=new Nodes(false,name,rank,null);
        if(isEmpty()){
            cabeza=newNode;
        }
        else{
            cola.setNext(newNode);
        }
        cola=newNode;
        size ++;
        cont_6++;//es una persona más que juega a las 6
    }
    public void reset_puntero(){
        cursor=null;
    }
    public void remove_jugador(T e){
        Node puntero=head;
        Boolean seguir=true;
        while((puntero!=null)&seguir){
            if(puntero.next.getElement()==e){
                if(puntero.next.next!=null){
                    puntero.next=puntero.next.next;
                    seguir=false;
                }
                else if(puntero.getElement()==e){
                    head=puntero.next;
                    seguir=false;                          
                }
            }
            //se recorre la lista agregandole elementos a nuestra lista
            puntero=puntero.getNext();
            
        }
        size--;//cambio de ultima hora
        
    }
    public void agregar_ordenado(String nombre,int puesto){
        
        Nodes player=new Nodes(true,nombre,puesto,null);
        if(cabeza==null){           
            cabeza=player;
            cola=player;
            puntero1=player;
        }
        else{
            Boolean seguir=true;
            puntero1=cabeza;
            if(cabeza.next!=null){
                puntero1.next=cabeza.next;
            }
            while(seguir){
                if(puesto>=(cabeza.rank)){
                    player.next=cabeza;
                    cabeza=player;
                    seguir=false;

                }
                else if(puntero1.next==null){
                    cola=player;
                    puntero1.next=player;
                    seguir=false;
                }
                else if(puntero1.next.rank<=player.rank){
                    player.next=puntero1.next;
                    puntero1.next=player;
                    seguir=false;
                }
                puntero1=puntero1.next;
                
            }
        }
        size++;
        
    }
    public void remove_Especial(Boolean izquierda){
        if(izquierda){
            //gano el de la izquierda entonces remueve el de la derecha
            Boolean leer=true;
            if(cursor==null){
                cursor=head;
                cursor.next=cursor.next.next;
                leer=false;
            }
            if(leer){
            if(cursor.next.next.next!=null){
                cursor.next.next=cursor.next.next.next;
            }else{
                cursor.next=tail;
                cursor.next.next=null;
            }
            cursor=cursor.next;
            }
        }else{
            //remueve el de la izquierda
            if(cursor==null){
                cursor=head.next;
                head=cursor;
            }else{
            cursor.next=cursor.next.next;
            cursor=cursor.next;       
            }

        }
        size--;
 
    }
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T answer= head.getElement();
        head=head.getNext();
        size--;
        if(size==0){
            tail=null;
        }
        return answer;
    }
    public Boolean buscar(T e){
        Node puntero=head;
        while(puntero!=null){
            if(puntero.getElement()==e){
                return true;
            }
            //se recorre la lista agregandole elementos a nuestra lista
            puntero=puntero.getNext();
        }
        return false;
    }
    public void imprimir_ranking(){
        Nodes puntero=cabeza;
        while(puntero!=null){
            //se recorre la lista agregandole elementos a nuestra lista
            System.out.println(puntero.element+" Puntaje "+puntero.rank);
            puntero=puntero.getNext();
        }
    }
    public void texto(){
        Node puntero=head;
        while(puntero!=null){
            //se recorre la lista agregandole elementos a nuestra lista
            System.out.println(puntero.getElement());
            puntero=puntero.getNext();
        }
    }
    public void texto_torneo(){
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
    public void texto_cuadros(){
        Nodes puntero=cabeza;
        int i=0;
        int cont_cuadro=1;
        Boolean primera_vez=true;
        System.out.println("Cuadros jugadores de las 5");
        for(int j=0;j<cont_5;j++){
            if(i%4==0){
                System.out.println("cuadro #"+cont_cuadro);
                cont_cuadro++;
            }
            if((primera_vez&((cont_5%4)==1))){
                System.out.println(puntero.element);
                puntero=puntero.getNext();
                primera_vez=false;
                j++;
           
            }
            if(primera_vez&((cont_5%4)==2)){
                i++;
                primera_vez=false;
            }
                
            
            //se recorre la lista agregandole elementos a nuestra lista
            System.out.println(puntero.element);
            puntero=puntero.getNext();
            i++;
            //System.out.println("valor i"+i);
        }
        i=0;
        System.out.println("Cuadros jugadores de las 6");
        for(int j=0;j<cont_6;j++){
            if(i%4==0){
                System.out.println("cuadro #"+cont_cuadro);
                cont_cuadro++;
            }
            if((primera_vez&((cont_6%4)==1))){
                System.out.println(puntero.element);
                puntero=puntero.getNext();
                primera_vez=false;
                j++;
           
            }
            if(primera_vez&((cont_6%4)==2)){
                i++;
                primera_vez=false;
            }
                
            
            //se recorre la lista agregandole elementos a nuestra lista
            System.out.println(puntero.element);
            puntero=puntero.getNext();
            i++;
            //System.out.println("valor i"+i);
        }
    }
}