package DS;


import java.util.ArrayList;


public class MaxHeap {

    private ArrayList<Integer> heap;

    // Constructor
    public MaxHeap() {
        heap = new ArrayList<>();
    }

    // Devuelve el índice del padre de un nodo
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // Devuelve el índice del hijo izquierdo
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // Devuelve el índice del hijo derecho
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // Inserta un elemento en el heap
    public void insert(int value) {
        heap.add(value);
        siftUp(heap.size() - 1);  // Ajusta el heap hacia arriba
    }

    // Ajusta el heap hacia arriba después de la inserción
    private void siftUp(int index) {
        while (index > 0 && heap.get(parent(index)) < heap.get(index)) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    // Elimina el elemento máximo (raíz) y lo devuelve
    public int extractMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap está vacío");
        }
        int max = heap.get(0);
        int lastIndex = heap.size() - 1;
        heap.set(0, heap.get(lastIndex));  // Mueve el último elemento a la raíz
        heap.remove(lastIndex);  // Elimina el último elemento
        siftDown(0);  // Ajusta el heap hacia abajo
        return max;
    }

    // Ajusta el heap hacia abajo después de eliminar el máximo
    private void siftDown(int index) {
        int maxIndex = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < heap.size() && heap.get(left) > heap.get(maxIndex)) {
            maxIndex = left;
        }

        if (right < heap.size() && heap.get(right) > heap.get(maxIndex)) {
            maxIndex = right;
        }

        if (index != maxIndex) {
            swap(index, maxIndex);
            siftDown(maxIndex);
        }
    }

    // Devuelve el máximo (raíz) sin eliminarlo
    public int getMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap está vacío");
        }
        return heap.get(0);
    }

    // Verifica si el heap está vacío
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Intercambia dos elementos en el heap
    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    // Devuelve el tamaño del heap
    public int size() {
        return heap.size();
    }


    // Imprime el heap para verificar su estado
    public void printHeap() {
        System.out.println(heap);
    }


}
