package DS;

import java.util.Objects;

public class HashMap<K,V> implements Map<K,V> {

    private HashNode<K,V>[] table; // creamos la lista.
    private int size; // longitud
    private int capacity; // capacidad de la tabla hash


    public HashMap(int capacity) {
        this.capacity = capacity;
        this.table = new HashNode[this.capacity];
        this.size = 0;
    }

    @Override
    public void rehashing (){
        if ((this.size) >= (capacity * 0.9)) {
            this.size = 0; // se inicia en 0 para evitar errores en el size cuando la longitu crece.
            int newCapacity = capacity * 2;
            HashNode<K, V>[] temp = table;
            table = new HashNode[newCapacity];
            for (HashNode<K, V> kvHashNode : temp) {
                HashNode<K, V> chain = kvHashNode;
                while (chain != null) {
                    this.put(chain.getKey(), chain.getValue());
                    chain = chain.getNext();
                }
            }
        }
    }

    public K getKeyByValue(V value) {
        for (int i = 0; i < table.length; i++) {
            HashNode<K, V> node = table[i];
            while (node != null) {
                if (node.getValue().equals(value)) {
                    return node.getKey(); // Retorna la llave correspondiente al valor
                }
                node = node.getNext(); // Avanza al siguiente nodo
            }
        }
        return null; // Retorna null si no se encuentra el valor
    }


    public HashMap(){
        this(10); // capacity inicial.
    }

    @Override
    public void put(K key, V value) {
        int index = this.index(this.hashCode(key)); // calcule el indice.

        HashNode<K, V> chain = this.table[index];
        HashNode<K, V> head = this.table[index];

        while ( chain != null){
            if(chain.getKey().equals(key)){
                chain.setValue(value);
                return;
            }
            chain = chain.getNext();
        }

        HashNode<K, V> nuevo = new HashNode<>(key,value);
        nuevo.setNext(head);
        this.table[index] = nuevo;
        size++;
        rehashing();
    }

    private int index(int hash) {
        int i =  hash % capacity;
        i = i < 0 ? i * -1 : i;
        return i;
    }

    @Override
    public V get(K key) {
        int index = this.index(this.hashCode(key));
        HashNode<K, V> node = this.table[index];
        while (node != null){
            if(node.getKey().equals(key)) return node.getValue();
            node = node.getNext();
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = this.index(this.hashCode(key));
        HashNode<K, V> chain = this.table[index];
        HashNode<K, V> head = this.table[index];
        HashNode<K, V> prev = null;
        while (chain != null) {
            if(chain.getKey().equals(key)) {
                break;
            }
            prev = chain;
            chain = chain.getNext();
        }
        V value;
        if(chain == null) //We don't find the value in the table
            return null;
        else if(prev != null) { // We delete in the middle
            value = chain.getValue();
            prev.setNext(chain.getNext());
        } else { // We delete the head
            value = chain.getValue();
            this.table[index] = head.getNext();
        }
        size--;
        return value;
    }

    @Override
    public int len() {return this.size;}

    @Override
    public boolean hasKey(K key) {
        int index = this.index(this.hashCode(key));
        HashNode<K, V> chain = this.table[index];
        while (chain != null){
            if(chain.getKey().equals(key)) return true;
            chain = chain.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {return table.length == 0;}


    // regresa el hascode de la llave para luego ser almacenado en un indice de la lista.
    public int hashCode(K key){
        return Objects.hashCode(key);
    }

    public void printHashMap(){
        for (int i = 0; i < table.length; i++){
            HashNode<K, V> node = table[i];
            if(node != null){
                System.out.print("Bucket " + i + ": ");
                while (node != null){
                    System.out.print("{" + node.getKey() + ":" + node.getValue() + "}");
                    node = node.getNext();
                }
                System.out.println();
            }
        }
    }

}

