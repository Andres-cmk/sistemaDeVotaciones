package DS;

import Logica.Voto;

import java.util.Arrays;
// una cola para procesamiento de votos

public class myQueque{

    private Voto [] list;
    private int size;

    public myQueque(int capacity) {
        this.list = new Voto [capacity];
        this.size = 0;
    }

    public boolean isEmpty() {return (this.size == 0);}

    public int size() {return this.size;}

    public void enqueque(Voto element) {
        if(this.size == this.list.length) {
            list = Arrays.copyOf(this.list, this.list.length * 2);
        }
        this.list[this.size] = element;
        size++;
    }

    public Voto peek(){
        if(this.isEmpty()) throw new NullPointerException();
        return this.list[0];
    }

    public Voto poll(){
        if(this.isEmpty()) throw new NullPointerException();
        Voto temp = this.list[0];
        this.list[0] = this.list[this.size-1];
        this.list[this.size-1] = null;
        size--;
        return temp;
    }

    public StringBuilder getList(){
        StringBuilder sb = new StringBuilder();
        sb.reverse();
        if(this.isEmpty()) return sb.append("[]");
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            if(i == this.size-1){
                sb.append(list[i]).append("]");
                break;
            }
            sb.append(list[i]).append(",");
        }
        return sb;
    }
}
