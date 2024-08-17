package Logica;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.util.Date;



@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Voto")

public class Voto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idVoto;



   @OneToOne
   @JoinColumn(name = "usuario_usu_id", referencedColumnName = "usu_id")
   Usuario usuario;

    /*
    * (referencedColumnName = "usu_id") hace que la columna usuario_usu_id referencie
    * a cada id del usuario registrado en la tabla. Cuando crea la tabla,  usuario_usu_id
    * queda relacionado con la primary key de Usuario.
    * */

    @ManyToOne
    @JoinColumn(name = "eleccion_vot_id", referencedColumnName = "ele_id")
    Eleccion eleccion;

    @ManyToOne
    @JoinColumn(name = "candidato_vot_id", referencedColumnName = "can_id")
    Candidato candidato;


    @Temporal(TemporalType.DATE)
    Date vot_fechavDeVoto;

    public Voto(){}
}