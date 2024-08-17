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
@Table(name = "Candidato")

public class Candidato{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int can_id;

    String can_nombre;
    String can_rol;

    @Temporal(TemporalType.DATE)
    Date can_fechaDeRegistro;


    @OneToMany(mappedBy = "candidato", fetch = FetchType.LAZY)
    List<Voto> voto;

    public Candidato(){}

}