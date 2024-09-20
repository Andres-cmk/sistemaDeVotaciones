package DS;

import Logica.Voto;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

// una cola para procesamiento de votos
public class myQueque implements Iterable<Voto>{


    private Voto [] list;
    private int size;

    public myQueque(int capacity) {
        this.list = new Voto [capacity];
        this.size = 0;
    }

    public myQueque(){
        this(10);
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
        if(this.isEmpty()) return sb.append("[]");
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            if(i == this.size-1){
                sb.append(list[i].getVot_fechavDeVoto()).append("]");
                break;
            }
            sb.append(list[i].getVot_fechavDeVoto()).append(",");
        }
        return sb;
    }

    @NotNull
    @Override
    public Iterator<Voto> iterator() {
        int size = this.size;
        Voto [] list = this.list;
        return new Iterator<Voto>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < size ;
            }

            @Override
            public Voto next() {
                return list[i++];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Voto> action) {
        Iterable.super.forEach(action);
    }
}
