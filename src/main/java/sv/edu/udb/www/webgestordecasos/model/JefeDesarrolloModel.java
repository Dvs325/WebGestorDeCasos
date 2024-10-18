package sv.edu.udb.www.webgestordecasos.model;


import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import sv.edu.udb.www.webgestordecasos.entities.AreafuncionalEntity;
import sv.edu.udb.www.webgestordecasos.entities.CasosEntity;
import sv.edu.udb.www.webgestordecasos.entities.TipousuarioEntity;
import sv.edu.udb.www.webgestordecasos.model.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@Component
public class JefeDesarrolloModel {

    private SessionFactory factory = HibernateUtil.getSessionFactory();

    // Método para obtener el total de casos
    public int totalCasos(String nAreaFuncional) {
        int result = 0;
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "SELECT COUNT(c) FROM CasosEntity c WHERE c.idCasos LIKE :nAreaFuncional ";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("nAreaFuncional", nAreaFuncional + "%");//coincide cualquier cosa que siga a las tres primeras letras
            result = query.uniqueResult().intValue();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return result;
    }

    // Método para obtener el total de casos pendientes
    public int totalCasosPendientes(String nombreAreaFuncional) {
        int result = 0;
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            //String hql = "SELECT COUNT(c) FROM CasosEntity c WHERE idEstadoCaso = 1 AND c.idUsuarioCaso = UsuarioEntity";AND  c.idCasos LIKE :nombreAreaFuncional
            String hql ="SELECT COUNT(c) FROM CasosEntity c WHERE c.idEstadoCaso =  1 AND  c.idCasos LIKE :nombreAreaFuncional";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("nombreAreaFuncional", nombreAreaFuncional + "%");
            //query.setParameter("nombreAreaFuncional", nombreAreaFuncional);
            result = query.uniqueResult().intValue();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return result;
    }


    public int totalCasosProbadores(String nombreAreaFuncional) {
        int result = 0;
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "SELECT COUNT(c) FROM CasosEntity c WHERE c.idEstadoCaso =  4 AND  c.idCasos LIKE :nombreAreaFuncional";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("nombreAreaFuncional", nombreAreaFuncional + "%");
            result = query.uniqueResult().intValue();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return result;
    }



    // Método para obtener un listado de casos
    public List<CasosEntity> obtenerRegistroCasos(String areaUsuario) {
        List<CasosEntity> casos = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "SELECT c FROM CasosEntity c " +
                    "JOIN FETCH c.idEstadoCaso e " +
                    "JOIN FETCH c.usuario u " +
                    "LEFT JOIN FETCH c.programador p " +
                    "LEFT JOIN FETCH c.tester t " +
                    "WHERE u.areaFuncional.area = :areaUsuario";
            Query<CasosEntity> query = session.createQuery(hql, CasosEntity.class);
            query.setParameter("areaUsuario", areaUsuario);
            casos = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return casos;
    }
/*
    // Método para obtener casos pendientes
    public List<CasoEntity> obtenerCasosPendientes(String areaUsuario) {
        List<CasoEntity> casos = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "SELECT c FROM CasoEntity c " +
                    "JOIN FETCH c.usuario u " +
                    "WHERE u.areaFuncional.area = :areaUsuario AND c.estado.id = 1";
            Query<CasoEntity> query = session.createQuery(hql, CasoEntity.class);
            query.setParameter("areaUsuario", areaUsuario);
            casos = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return casos;
    }

    // Método para obtener caso por su código
    public CasoEntity obtenerCaso(String codigo) {
        CasoEntity caso = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM CasoEntity c WHERE c.id = :codigo";
            Query<CasoEntity> query = session.createQuery(hql, CasoEntity.class);
            query.setParameter("codigo", codigo);
            caso = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return caso;
    }

    // Método para aprobar un caso
    public int aprobarCaso(CasoEntity caso) {
        int result = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            caso.setEstado(new EstadoEntity(3));  // Estado aprobado
            session.update(caso);
            transaction.commit();
            result = 1;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return result;
    }

    // Método para rechazar un caso
    public int rechazarCaso(String codigo, String informacion) {
        int result = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CasoEntity caso = session.get(CasoEntity.class, codigo);
            if (caso != null) {
                caso.setEstado(new EstadoEntity(2));  // Estado rechazado
                caso.setInfoRechazo(informacion);
                session.update(caso);
                transaction.commit();
                result = 1;
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return result;
    }*/
}//
