package sv.edu.udb.www.webgestordecasos.model;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sv.edu.udb.www.webgestordecasos.entities.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;
import sv.edu.udb.www.webgestordecasos.model.HibernateUtil;
import org.springframework.stereotype.Component;

@Component
public class LoginModel {

    private SessionFactory factory = HibernateUtil.getSessionFactory();



    // Método para verificar el usuario en la base de datos
    public UsuarioEntity verificarUsuario(String codigoUsuario, String contraseniaUsuario) {
        // Lógica para consultar la base de datos y autenticar al usuario

        // Suponiendo que tienes una sesión Hibernate abierta o un repositorio para hacer la consulta
        try (Session session = factory.openSession()) {
            // HQL query para buscar el usuario por su código
            String hql = "FROM UsuarioEntity WHERE idUsuario = :codigoUsuario";
            Query<UsuarioEntity> query = session.createQuery(hql, UsuarioEntity.class);
            query.setParameter("codigoUsuario", codigoUsuario);

            // Intenta obtener el usuario
            UsuarioEntity usuario = query.uniqueResult();

            // Si el usuario existe y la contraseña coincide, retorna el usuario
            if (usuario != null && usuario.getContrasenia().equals(contraseniaUsuario)) {
                return usuario;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Si no se encuentra el usuario o la contraseña no coincide, retorna null
        return null;
    }



    public int verificarUsuarios(String codigoUsuario, String contraseniaUsuario) {
        Session ses = factory.openSession();
        try {
            String hql = "FROM UsuarioEntity WHERE idUsuario = :codigoUsuario";
            Query<UsuarioEntity> query = ses.createQuery(hql, UsuarioEntity.class);
            query.setParameter("codigoUsuario", codigoUsuario);

            UsuarioEntity usuario = query.uniqueResult();

            if (usuario != null && usuario.getContrasenia().equals(contraseniaUsuario)) {
                return usuario.getIdTipoUsuario();
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace(); //
            return 0;
        } finally {
            ses.close();
        }
    }


    public void insertarUsuario(UsuarioEntity usuario) {
        Session ses = factory.openSession();
        try {
            Transaction tran = ses.beginTransaction();
            ses.save(usuario);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones más robusto puede ser implementado aquí
        } finally {
            ses.close();
        }
    }

    public List<UsuarioEntity> listarUsuarios() {
        Session ses = factory.openSession();
        try {
            String hql = "FROM UsuarioEntity";
            Query<UsuarioEntity> query = ses.createQuery(hql, UsuarioEntity.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace(); //
            return null;
        } finally {
            ses.close();
        }
    }

    public UsuarioEntity obtenerUsuario(String codigoUsuario) {
        Session ses = factory.openSession();
        try {
            return ses.get(UsuarioEntity.class, codigoUsuario);
        } catch (Exception e) {
            e.printStackTrace(); //
            return null;
        } finally {
            ses.close();
        }
    }

    public void modificarUsuario(UsuarioEntity usuario) {
        Session ses = factory.openSession();
        try {
            Transaction tran = ses.beginTransaction();
            ses.update(usuario);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace(); //
        } finally {
            ses.close();
        }
    }

    public void eliminarUsuario(String idUsuario) {
        Session ses = factory.openSession();
        try {
            UsuarioEntity usuario = ses.get(UsuarioEntity.class, idUsuario);
            if (usuario != null) {
                Transaction tran = ses.beginTransaction();
                ses.delete(usuario);
                tran.commit();
            }
        } catch (Exception e) {
            e.printStackTrace(); //
        } finally {
            ses.close();
        }
    }


}//
