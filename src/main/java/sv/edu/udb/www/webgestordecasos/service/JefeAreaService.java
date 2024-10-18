package sv.edu.udb.www.webgestordecasos.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sv.edu.udb.www.webgestordecasos.dto.JefeAreaDTO;
import sv.edu.udb.www.webgestordecasos.entities.UsuarioEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import sv.edu.udb.www.webgestordecasos.model.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class JefeAreaService {

    private SessionFactory factory = HibernateUtil.getSessionFactory();

    public JefeAreaService() {
        this.factory = factory;
    }

    public List<JefeAreaDTO> obtenerJefeArea(String areaFuncional) {
        try (Session session = factory.openSession()) {
            String hql = "SELECT u FROM UsuarioEntity u " +
                    "JOIN u.areafuncionalByIdAreaFuncional a " +
                    "JOIN u.tipousuarioByIdTipoUsuario t " +
                    "WHERE a.area = :areaFuncional " +
                    "AND t.idTipoUsuario = 2";

            Query<UsuarioEntity> query = session.createQuery(hql, UsuarioEntity.class);
            query.setParameter("areaFuncional", areaFuncional);

            List<UsuarioEntity> usuarios = query.list();
            List<JefeAreaDTO> dtos = new ArrayList<>();

            // Mapear cada UsuarioEntity a JefeAreaDTO
            for (UsuarioEntity usuario : usuarios) {
                dtos.add(new JefeAreaDTO(usuario.getIdUsuario(), usuario.getNombre(), usuario.getApellido()));
            }

            return dtos;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
