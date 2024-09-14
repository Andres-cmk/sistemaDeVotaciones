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

    public HashMap(){
        this(1000);
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
        return null;
    }

    @Override
    public int len() {
        return this.size;
    }

    @Override
    public boolean hasKey(K key) {
        return false;
    }

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

