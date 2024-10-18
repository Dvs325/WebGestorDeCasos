package sv.edu.udb.www.webgestordecasos.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "estado", schema = "bdd_telecomunicaciones_sv", catalog = "")
public class EstadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_estado", nullable = false)
    private int idEstado;
    @Basic
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @OneToMany(mappedBy = "estadoByIdEstadoCaso")
    private Collection<CasosEntity> casosByIdEstado;

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<CasosEntity> getCasosByIdEstado() {
        return casosByIdEstado;
    }

    public void setCasosByIdEstado(Collection<CasosEntity> casosByIdEstado) {
        this.casosByIdEstado = casosByIdEstado;
    }
}
