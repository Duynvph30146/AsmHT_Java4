package com.example.day5.Service;

import com.example.day5.Entity.Favorite;
import com.example.day5.Entity.User;
import com.example.day5.HibernateUtil;
import com.example.day5.Entity.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class VideoDAO {
    List<Video> list = new ArrayList<>();
    List<Favorite>listFavorite=new ArrayList<>();

    public List<Video> findAll() {
        EntityManager em = HibernateUtil.createEntityManager();
        String jpql = "SELECT u FROM Video u";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        list = query.getResultList();
        return list;
    }
    public List<Favorite> findAllFavorite() {
        EntityManager em = HibernateUtil.createEntityManager();
        String jpql = "SELECT u FROM Favorite u";
        TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
        listFavorite = query.getResultList();
        return listFavorite;
    }

    public Video findByID(String id) {
        EntityManager em = HibernateUtil.createEntityManager();
        Video video = em.find(Video.class, id);
        em.close();
        return video;
    }
    public User findByIDUser(String id) {
        EntityManager em = HibernateUtil.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }
    public void add(Video video) {
        EntityManager em = HibernateUtil.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(video);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            e.printStackTrace();
        }
    }
    public void like(Favorite m){
        EntityManager em = HibernateUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public void unlike(Favorite m){
        EntityManager em = HibernateUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(m);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public List<Video> searchFavorite(String userId, String keyword) {
        if (keyword == null) keyword = "";
        String jpql = "SELECT DISTINCT o.video FROM Favorite o "
                + " WHERE o.video.title LIKE :keyword and o.user.id like :userId";
        EntityManager em = HibernateUtil.createEntityManager();
        TypedQuery<Video> query = em.createQuery(jpql,Video.class);
        query.setParameter("keyword", "%"+keyword+"%");
        query.setParameter("userId", userId);
        List<Video> list = query.getResultList();
        return list;
    }

    public void update(Video video) {
        EntityManager em = HibernateUtil.createEntityManager();
        em.getTransaction().begin();
       try {
           em.merge(video);
           em.getTransaction().commit();
       }catch (Exception e){
           em.getTransaction().rollback();
           em.close();
           e.printStackTrace();
       }
    }

    public void delete(String id) {
        EntityManager em = HibernateUtil.createEntityManager();
        Video video = findByID(id);
        try {
            if (video != null) {
                em.getTransaction().begin();
                em.remove(video);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    public Boolean checkTon(String id) {
        list=findAll();
        for (Video video : list) {
            if (video.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}

