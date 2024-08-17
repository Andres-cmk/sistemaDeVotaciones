package Logica;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Eleccion")

public class Eleccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ele_id;

    private String ele_nombre;

    @Temporal(TemporalType.DATE)
    private Date ele_fechaInicio;

    @Temporal(TemporalType.DATE)
    private Date ele_fechaFinal;

    private String ele_estado;


   @OneToMany(mappedBy = "eleccion", fetch = FetchType.LAZY)
   private List<Voto> vot;

   public Eleccion(){}
}