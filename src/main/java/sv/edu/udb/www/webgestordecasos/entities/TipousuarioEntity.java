package sv.edu.udb.www.webgestordecasos.entities;

import javax.persistence.*;

@Entity
@Table(name = "tipousuario", schema = "bdd_telecomunicaciones_sv", catalog = "")
public class TipousuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_tipo_usuario", nullable = false)
    private int idTipoUsuario;
    @Basic
    @Column(name = "nombre_tipo_usuario", nullable = false, length = 50)
    private String nombreTipoUsuario;

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombreTipoUsuario() {
        return nombreTipoUsuario;
    }

    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }
}
