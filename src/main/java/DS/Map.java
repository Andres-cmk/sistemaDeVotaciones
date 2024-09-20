package DS;

public interface Map <K, V> {
    void put(K key, V value);
    V get(K key);
    V remove(K key) throws Exception;
    int len();
    boolean hasKey(K key);
    boolean isEmpty();
    void rehashing();
}
