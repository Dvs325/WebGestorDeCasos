package sv.edu.udb.www.webgestordecasos.entities;

import javax.persistence.*;

@Entity
@Table(name = "areafuncional", schema = "bdd_telecomunicaciones_sv", catalog = "")
public class AreafuncionalEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_area", nullable = false)
    private int idArea;
    @Basic
    @Column(name = "area", nullable = true, length = 20)
    private String area;

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
