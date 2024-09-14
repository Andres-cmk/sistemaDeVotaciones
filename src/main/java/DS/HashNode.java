package DS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HashNode<K, V> {
    private K key;
    private V value;
    private HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

}
