package Logica;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Usuario")

public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int usu_id;

    String usu_nombre;
    String usu_apellido;
    String usu_correo;
    String usu_password;
    int usu_NumeroDocumento;
    String usu_rol;
   // private String key;


    /*
     mappeBy me deice quien es el dueño de la relacion y evita que ambas tablas muestren sus id primarias
    */

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    Voto usu_vot_id;



    public Usuario(String usu_nombre, String usu_apellido, String usu_correo, String usu_password, int usu_NumeroDocumento, String usu_rol) {
        this.usu_nombre = usu_nombre;
        this.usu_apellido = usu_apellido;
        this.usu_correo = usu_correo;
        this.usu_password = usu_password;
        this.usu_NumeroDocumento = usu_NumeroDocumento;
        this.usu_rol = usu_rol;
        //this.key = key;
    }

    public Usuario(Voto usu_vot_id){
        this.usu_vot_id = usu_vot_id;
    }

    public Usuario() {}

}