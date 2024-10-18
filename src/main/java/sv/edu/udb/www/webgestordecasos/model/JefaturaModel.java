package sv.edu.udb.www.webgestordecasos.model;
import org.hibernate.query.Query;
import sv.edu.udb.www.webgestordecasos.entities.AreafuncionalEntity;
import sv.edu.udb.www.webgestordecasos.entities.CasosEntity;
import sv.edu.udb.www.webgestordecasos.entities.EstadoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sv.edu.udb.www.webgestordecasos.entities.UsuarioEntity;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


    @Repository
    public class JefaturaModel {


        private SessionFactory factory = HibernateUtil.getSessionFactory();
/*
        // Obtener casos de un usuario mediante HQL
        public List<CasosEntity> obtenerCasos(String codigoUsuario) {
            try (Session session = factory.openSession()) {
                String hql = "FROM CasosEntity c WHERE c.idUsuarioCaso = :codigoUsuario";
                TypedQuery<CasosEntity> query = session.createQuery(hql, CasosEntity.class);
                query.setParameter("codigoUsuario", codigoUsuario);
                return query.getResultList();
            }
        }*/

        public List<CasosEntity> obtenerCaso(String codigoUsuario) {
            try (Session session = factory.openSession()) {
                String hql = "SELECT c FROM CasosEntity c WHERE c.idUsuarioCaso = :codigoUsuario";
                Query<CasosEntity> query = session.createQuery(hql, CasosEntity.class);
                query.setParameter("codigoUsuario", codigoUsuario);
                return query.list();
            }
        }

        public List<CasosEntity> obtenerCasos(String codigoUsuario) {
            try (Session session = factory.openSession()) {
                String hql = "SELECT c FROM CasosEntity c " +
                        "JOIN FETCH c.estadoByIdEstadoCaso e " +
                        "WHERE c.idUsuarioCaso = :codigoUsuario";
                Query<CasosEntity> query = session.createQuery(hql, CasosEntity.class);
                query.setParameter("codigoUsuario", codigoUsuario);
                return query.list();
            }
        }


      /*  public String obtenerEstadoPorId(int idEstado) {
            try (Session session = factory.openSession()) {
                String hql = "SELECT e.estado FROM EstadoEntity e WHERE e.idEstado = :idEstado";
                TypedQuery<String> query = session.createQuery(hql, String.class);
                query.setParameter("idEstado", idEstado);
                return query.getSingleResult();
            }
        }*/


        public void insertarCaso(CasosEntity caso) {
            Transaction transaction = null;
            try (Session session = factory.openSession()) {
                transaction = session.beginTransaction();
                session.save(caso);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }

        public void insertarCasos(CasosEntity casoEntity) {
            try (Session session = factory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(casoEntity);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Obtener los detalles de un caso específico mediante HQL
        public CasosEntity obtenerDetallesCaso(String idCaso) {
            try (Session session = factory.openSession()) {
                String hql = "FROM CasosEntity c WHERE c.idCasos = :idCaso";
                TypedQuery<CasosEntity> query = session.createQuery(hql, CasosEntity.class);
                query.setParameter("idCaso", idCaso);
                return query.getSingleResult();
            }
        }

        // Actualizar un caso mediante HQL
        public void actualizarCaso(CasosEntity casoActualizado) {
            Transaction transaction = null;
            try (Session session = factory.openSession()) {
                transaction = session.beginTransaction();
                String hql = "UPDATE CasosEntity c SET c.detalles = :detalles, c.idEstadoCaso = :idEstadoCaso, c.infoRechazo = :infoRechazo WHERE c.idCasos = :idCasos";
                session.createQuery(hql)
                        .setParameter("detalles", casoActualizado.getDetalles())
                        .setParameter("idEstadoCaso", casoActualizado.getIdEstadoCaso())
                        .setParameter("infoRechazo", casoActualizado.getInfoRechazo())
                        .setParameter("idCasos", casoActualizado.getIdCasos())
                        .executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }


}
