package sv.edu.udb.www.webgestordecasos.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bitacora", schema = "bdd_telecomunicaciones_sv", catalog = "")
public class BitacoraEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_bitacora", nullable = false)
    private int idBitacora;
    @Basic
    @Column(name = "id_caso_bitacora", nullable = true)
    private Integer idCasoBitacora;
    @Basic
    @Column(name = "descripcion", nullable = true, length = 500)
    private String descripcion;
    @Basic
    @Column(name = "fecha", nullable = true)
    private Date fecha;

    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Integer getIdCasoBitacora() {
        return idCasoBitacora;
    }

    public void setIdCasoBitacora(Integer idCasoBitacora) {
        this.idCasoBitacora = idCasoBitacora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
