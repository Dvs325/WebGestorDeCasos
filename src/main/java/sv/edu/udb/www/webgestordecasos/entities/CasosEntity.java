package sv.edu.udb.www.webgestordecasos.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "casos", schema = "bdd_telecomunicaciones_sv", catalog = "")
public class CasosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_casos", nullable = false, length = 15)
    private String idCasos;
    @Basic
    @Column(name = "fecha_registro", nullable = true)
    private Date fechaRegistro;
    @Basic
    @Column(name = "fecha_vencimiento", nullable = true)
    private Date fechaVencimiento;
    @Basic
    @Column(name = "id_estado_caso", nullable = false)
    private int idEstadoCaso;
    @Basic
    @Column(name = "info_rechazo", nullable = true, length = -1)
    private String infoRechazo;
    @Basic
    @Column(name = "detalles", nullable = false, length = -1)
    private String detalles;
    @Basic
    @Column(name = "progreso", nullable = true, precision = 0)
    private Double progreso;
    @Basic
    @Column(name = "id_usuario_caso", nullable = true, length = 8)
    private String idUsuarioCaso;
    @Basic
    @Column(name = "id_usuario_programador", nullable = true, length = 8)
    private String idUsuarioProgramador;
    @Basic
    @Column(name = "id_usuario_tester", nullable = true, length = 8)
    private String idUsuarioTester;

    @ManyToOne
    @JoinColumn(name = "id_estado_caso", referencedColumnName = "id_estado", nullable = false)
    private EstadoEntity estadoByIdEstadoCaso;

    public String getNombreEstado() {
        if (estadoByIdEstadoCaso!= null) {
            return estadoByIdEstadoCaso.getEstado();
        } else {
            return "No hay estado";
        }
    }


    public String getIdCasos() {
        return idCasos;
    }

    public void setIdCasos(String idCasos) {
        this.idCasos = idCasos;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getIdEstadoCaso() {
        return idEstadoCaso;
    }

    public void setIdEstadoCaso(int idEstadoCaso) {
        this.idEstadoCaso = idEstadoCaso;
    }

    public String getInfoRechazo() {
        return infoRechazo;
    }

    public void setInfoRechazo(String infoRechazo) {
        this.infoRechazo = infoRechazo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Double getProgreso() {
        return progreso;
    }

    public void setProgreso(Double progreso) {
        this.progreso = progreso;
    }

    public String getIdUsuarioCaso() {
        return idUsuarioCaso;
    }

    public void setIdUsuarioCaso(String idUsuarioCaso) {
        this.idUsuarioCaso = idUsuarioCaso;
    }

    public String getIdUsuarioProgramador() {
        return idUsuarioProgramador;
    }

    public void setIdUsuarioProgramador(String idUsuarioProgramador) {
        this.idUsuarioProgramador = idUsuarioProgramador;
    }

    public String getIdUsuarioTester() {
        return idUsuarioTester;
    }

    public void setIdUsuarioTester(String idUsuarioTester) {
        this.idUsuarioTester = idUsuarioTester;
    }


    public EstadoEntity getEstadosByIdEstadoCaso() {
        return estadoByIdEstadoCaso;
    }

    public void setEstadosByIdEstadoCaso(EstadoEntity estadoByIdEstadoCaso) {
        this.estadoByIdEstadoCaso = estadoByIdEstadoCaso;
    }

}//