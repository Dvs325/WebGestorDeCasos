package sv.edu.udb.www.webgestordecasos.model;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import sv.edu.udb.www.webgestordecasos.entities.AreafuncionalEntity;
import sv.edu.udb.www.webgestordecasos.entities.TipousuarioEntity;
import sv.edu.udb.www.webgestordecasos.entities.UsuarioEntity;


import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdministradorModel {

    private SessionFactory factory = HibernateUtil.getSessionFactory();

    public int totalUsuarios() {
        try (Session session = factory.openSession()) {
            String hql = "SELECT COUNT(u.idUsuario) FROM UsuarioEntity u";
            Query<Long> query = session.createQuery(hql, Long.class);
            return query.uniqueResult().intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int totalAreasFuncionales() {
        try (Session session = factory.openSession()) {
            String hql = "SELECT COUNT(a.idArea) FROM AreafuncionalEntity a";
            Query<Long> query = session.createQuery(hql, Long.class);
            return query.uniqueResult().intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<UsuarioEntity> listarUsuarios() {
        try (Session session = factory.openSession()) {
            String hql = "SELECT u FROM UsuarioEntity u " +
                    "LEFT JOIN FETCH u.areafuncionalByIdAreaFuncional a " +
                    "LEFT JOIN FETCH u.tipousuarioByIdTipoUsuario t " +
                    "LEFT JOIN FETCH u.usuarioByIdJefeUsuario j";
            Query<UsuarioEntity> query = session.createQuery(hql, UsuarioEntity.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public void actualizarUsuario(UsuarioEntity usuarioActualizado) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            // Carga el usuario existente
            UsuarioEntity usuarioExistente = session.get(UsuarioEntity.class, usuarioActualizado.getIdUsuario());
            if (usuarioExistente != null) {

                // Actualiza los campos básicos del usuario
                usuarioExistente.setNombre(usuarioActualizado.getNombre());
                usuarioExistente.setApellido(usuarioActualizado.getApellido());
                usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
                usuarioExistente.setDui(usuarioActualizado.getDui());
                usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
                usuarioExistente.setContrasenia(usuarioActualizado.getContrasenia());

                // Cargar la entidad del jefe (si existe) antes de asignarla
                if (usuarioActualizado.getIdJefeUsuario() != null) {
                    UsuarioEntity jefeUsuario = session.get(UsuarioEntity.class, usuarioActualizado.getIdJefeUsuario());
                    usuarioExistente.setUsuarioByIdJefeUsuario(jefeUsuario); // Asigna la entidad del jefe
                } else {
                    usuarioExistente.setUsuarioByIdJefeUsuario(null); // En caso de que no tenga jefe
                }

                // Cargar la entidad del tipo de usuario antes de asignarla
                if (usuarioActualizado.getIdTipoUsuario() != 0) { // Asume que 0 es valor inválido
                    TipousuarioEntity tipoUsuario = session.get(TipousuarioEntity.class, usuarioActualizado.getIdTipoUsuario());
                    usuarioExistente.setTipousuarioByIdTipoUsuario(tipoUsuario);
                } else {
                    usuarioExistente.setTipousuarioByIdTipoUsuario(null); // En caso de que no tenga tipo de usuario
                }

                // Cargar la entidad del área funcional antes de asignarla
                if (usuarioActualizado.getIdAreaFuncional() != 0) { // Asume que 0 es valor inválido
                    AreafuncionalEntity areaFuncional = session.get(AreafuncionalEntity.class, usuarioActualizado.getIdAreaFuncional());
                    usuarioExistente.setAreafuncionalByIdAreaFuncional(areaFuncional);
                } else {
                    usuarioExistente.setAreafuncionalByIdAreaFuncional(null); // En caso de que no tenga área funcional
                }

                // Actualiza el usuario en la base de datos
                session.update(usuarioExistente);
                transaction.commit(); // Confirma la transacción
            } else {
                System.out.println("Usuario no encontrado con ID: " + usuarioActualizado.getIdUsuario());
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Revierte la transacción en caso de error
            }
            e.printStackTrace();
        }
    }

    public void nuevoUsuario(UsuarioEntity nuevoUsuario) {
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            // Si es necesario manejar relaciones de entidades, como el jefe del usuario,
            // debes cargar las entidades correspondientes antes de insertar el nuevo usuario.

            // Manejo del jefe del usuario (si está relacionado con otra entidad)
            if (nuevoUsuario.getIdJefeUsuario() != null) {
                UsuarioEntity jefe = session.get(UsuarioEntity.class, nuevoUsuario.getIdJefeUsuario());
                nuevoUsuario.setUsuarioByIdJefeUsuario(jefe);
            }

            // Manejo del área funcional
            if (nuevoUsuario.getIdAreaFuncional() > 0) {
                AreafuncionalEntity area = session.get(AreafuncionalEntity.class, nuevoUsuario.getIdAreaFuncional());
                nuevoUsuario.setAreafuncionalByIdAreaFuncional(area);
            }

            // Manejo del tipo de usuario
            if (nuevoUsuario.getIdTipoUsuario() > 0) {
                TipousuarioEntity tipoUsuario = session.get(TipousuarioEntity.class, nuevoUsuario.getIdTipoUsuario());
                nuevoUsuario.setTipousuarioByIdTipoUsuario(tipoUsuario);
            }

            // Guarda el nuevo usuario
            session.save(nuevoUsuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }






    public void eliminarUsuario(String codigoUsuario) {
        try (Session session = factory.openSession()) {
            UsuarioEntity usuario = session.get(UsuarioEntity.class, codigoUsuario);
            if (usuario != null) {
                Transaction transaction = session.beginTransaction();
                session.delete(usuario);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TipousuarioEntity> listarTipoUsuario() {
        try (Session session = factory.openSession()) {
            String hql = "FROM TipousuarioEntity";
            Query<TipousuarioEntity> query = session.createQuery(hql, TipousuarioEntity.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String areaUsuario(int codigoArea) {
        try (Session session = factory.openSession()) {
            String hql = "SELECT a.area FROM AreafuncionalEntity a WHERE a.idArea = :codigoArea";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("codigoArea", codigoArea);

            // Si esperas un único resultado
            return query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    //Metodo para obtener los jefes de desarrollo
    public List<UsuarioEntity> obtenerJefeArea(String areaFuncional) {
        try (Session session = factory.openSession()) {
            String hql = "SELECT u FROM UsuarioEntity u WHERE u.areafuncionalByIdAreaFuncional.area = :areaFuncional AND u.tipousuarioByIdTipoUsuario.idTipoUsuario = 2";
            Query<UsuarioEntity> query = session.createQuery(hql, UsuarioEntity.class);
            query.setParameter("areaFuncional", areaFuncional);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public UsuarioEntity obtenerUsuario(String codigoUsuario) {
        try (Session session = factory.openSession()) {
            String hql = "SELECT u FROM UsuarioEntity u " +
                    "LEFT JOIN FETCH u.areafuncionalByIdAreaFuncional a " +
                    "LEFT JOIN FETCH u.tipousuarioByIdTipoUsuario t " +
                    "LEFT JOIN FETCH u.usuarioByIdJefeUsuario j " +
                    "WHERE u.idUsuario = :codigoUsuario";
            Query<UsuarioEntity> query = session.createQuery(hql, UsuarioEntity.class);
            query.setParameter("codigoUsuario", codigoUsuario);
            return query.uniqueResult(); // Se espera un único resultado
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // CRUD DE AREAS FUNCIONALES


    public List<AreafuncionalEntity> listarAreasFuncionales() {
        try (Session session = factory.openSession()) {
            String hql = "FROM AreafuncionalEntity";
            Query<AreafuncionalEntity> query = session.createQuery(hql, AreafuncionalEntity.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public void insertAreaFuncional(AreafuncionalEntity areafuncionalEntity) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(areafuncionalEntity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AreafuncionalEntity obtenerAreaFuncional(int codigo) {
        try (Session session = factory.openSession()) {
            return session.get(AreafuncionalEntity.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizarAreaFuncional(AreafuncionalEntity areaFuncionalEntity) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(areaFuncionalEntity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarAreaFuncional(int codigoArea) {
        try (Session session = factory.openSession()) {
            AreafuncionalEntity areaFuncional = session.get(AreafuncionalEntity.class, codigoArea);
            if (areaFuncional != null) {
                Transaction transaction = session.beginTransaction();
                session.delete(areaFuncional);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}