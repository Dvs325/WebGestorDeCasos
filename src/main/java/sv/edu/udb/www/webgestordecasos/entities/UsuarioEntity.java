package sv.edu.udb.www.webgestordecasos.entities;

import javax.persistence.*;
import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario", schema = "bdd_telecomunicaciones_sv", catalog = "")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_usuario", nullable = false, length = 8)
    private String idUsuario;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;
    @Basic
    @Column(name = "telefono", nullable = false, length = 9)
    private String telefono;
    @Basic
    @Column(name = "dui", nullable = false, length = 30)
    private String dui;
    @Basic
    @Column(name = "correo", nullable = false, length = 40)
    private String correo;
    @Basic
    @Column(name = "contrasenia", nullable = false, length = 100)
    private String contrasenia;

    //@Basic
    //@Column(name = "id_area_funcional", nullable = false)
    //private int idAreaFuncional;

    //@Basic
    //@Column(name = "id_area_funcional", insertable = false, updatable = false, nullable = true)
    //private int idAreaFuncional;
    @Transient
    @Column(name = "id_area_funcional", nullable = false)
    private int idAreaFuncional;
    @Basic
    @Column(name = "id_tipo_usuario", nullable = false)
    private int idTipoUsuario;
    @Basic
    @Column(name = "id_jefe_usuario", nullable = true, length = 8)
    private String idJefeUsuario;
    @Basic
    @Column(name = "id_caso_bitacora", nullable = true)
    private Integer idCasoBitacora;

    //relaciones

    @OneToMany(mappedBy = "usuarioByIdUsuarioCaso")
    private Collection<CasosEntity> casosByIdUsuario;

    @OneToMany(mappedBy = "usuarioByIdUsuarioProgramador")
    private Collection<CasosEntity> casosByIdUsuario_0;

    @OneToMany(mappedBy = "usuarioByIdUsuarioTester")
    private Collection<CasosEntity> casosByIdUsuario_1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_area_funcional", referencedColumnName = "id_area", insertable = false, updatable = false, nullable = false)
    private AreafuncionalEntity areafuncionalByIdAreaFuncional;

    @ManyToOne //(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_usuario", referencedColumnName = "id_tipo_usuario", nullable = false)
    private TipousuarioEntity tipousuarioByIdTipoUsuario;
    @ManyToOne //(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jefe_usuario", referencedColumnName = "id_usuario")
    private UsuarioEntity usuarioByIdJefeUsuario;
    @OneToMany(mappedBy = "usuarioByIdJefeUsuario")
    private Collection<UsuarioEntity> usuariosByIdUsuario;

    // metodos

    // Método para obtener las siguientes propiedades generadas a partir de las relaciones

    public String getNombreAreaFuncional() {
        if (areafuncionalByIdAreaFuncional != null) {
            return areafuncionalByIdAreaFuncional.getArea();
        } else {
            return "Área no asignada";
        }
    }

    public String getNombreTipoUsuario() {
        if (tipousuarioByIdTipoUsuario != null) {
            return tipousuarioByIdTipoUsuario.getNombreTipoUsuario();
        } else {
            return "Tipo Usuario no asignado";
        }
    }

    public String getNombreJefeUsuario() {
        if (usuarioByIdJefeUsuario != null) {
            return usuarioByIdJefeUsuario.getNombre() + " " + usuarioByIdJefeUsuario.getApellido();
        } else {
            return "Sin jefe asignado";
        }
    }



    //getters setters

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
/* da error: Repeated column in mapping for entity: sv.edu.udb.www.webgestordecasos.entities.UsuarioEntity column: id_area_funcional (should be mapped with insert="false" update="false")
 */
    public int getIdAreaFuncional() {
        return idAreaFuncional;
    }

    public void setIdAreaFuncional(int idAreaFuncional) {
        this.idAreaFuncional = idAreaFuncional;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getIdJefeUsuario() {
        return idJefeUsuario;
    }

    public void setIdJefeUsuario(String idJefeUsuario) {
        this.idJefeUsuario = idJefeUsuario;
    }

    public Integer getIdCasoBitacora() {
        return idCasoBitacora;
    }

    public void setIdCasoBitacora(Integer idCasoBitacora) {
        this.idCasoBitacora = idCasoBitacora;
    }

    public Collection<CasosEntity> getCasosByIdUsuario() {
        return casosByIdUsuario;
    }

    public void setCasosByIdUsuario(Collection<CasosEntity> casosByIdUsuario) {
        this.casosByIdUsuario = casosByIdUsuario;
    }

    public Collection<CasosEntity> getCasosByIdUsuario_0() {
        return casosByIdUsuario_0;
    }

    public void setCasosByIdUsuario_0(Collection<CasosEntity> casosByIdUsuario_0) {
        this.casosByIdUsuario_0 = casosByIdUsuario_0;
    }

    public Collection<CasosEntity> getCasosByIdUsuario_1() {
        return casosByIdUsuario_1;
    }

    public void setCasosByIdUsuario_1(Collection<CasosEntity> casosByIdUsuario_1) {
        this.casosByIdUsuario_1 = casosByIdUsuario_1;
    }

    public AreafuncionalEntity getAreafuncionalByIdAreaFuncional() {
        return areafuncionalByIdAreaFuncional;
    }

    public void setAreafuncionalByIdAreaFuncional(AreafuncionalEntity areafuncionalByIdAreaFuncional) {
        this.areafuncionalByIdAreaFuncional = areafuncionalByIdAreaFuncional;
    }

    public TipousuarioEntity getTipousuarioByIdTipoUsuario() {
        return tipousuarioByIdTipoUsuario;
    }

    public void setTipousuarioByIdTipoUsuario(TipousuarioEntity tipousuarioByIdTipoUsuario) {
        this.tipousuarioByIdTipoUsuario = tipousuarioByIdTipoUsuario;
    }

    public UsuarioEntity getUsuarioByIdJefeUsuario() {
        return usuarioByIdJefeUsuario;
    }

    public void setUsuarioByIdJefeUsuario(UsuarioEntity usuarioByIdJefeUsuario) {
        this.usuarioByIdJefeUsuario = usuarioByIdJefeUsuario;
    }


    public Collection<UsuarioEntity> getUsuariosByIdUsuario() {
        return usuariosByIdUsuario;
    }

    public void setUsuariosByIdUsuario(Collection<UsuarioEntity> usuariosByIdUsuario) {
        this.usuariosByIdUsuario = usuariosByIdUsuario;
    }
}
