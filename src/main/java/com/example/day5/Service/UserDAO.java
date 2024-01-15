package com.example.day5.Service;

import com.example.day5.Entity.Favorite;
import com.example.day5.HibernateUtil;
import com.example.day5.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    List<User> list = new ArrayList<>();
    List<Favorite> listFavorite = new ArrayList<>();

    public void insert(User user) {
        EntityManager em = HibernateUtil.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(User u) {
        EntityManager em = HibernateUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public User findByID(String id) {
        EntityManager em = HibernateUtil.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    public Favorite findByIDFavorite(Long id) {
        EntityManager em = HibernateUtil.createEntityManager();
        Favorite favorite = em.find(Favorite.class, id);
        em.close();
        return favorite;
    }

    public List<User> findAll() {
        EntityManager em = HibernateUtil.createEntityManager();
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> list = query.getResultList();
        return list;
    }

    public int count() {
        return findAll().size();
    }

    public boolean checkLogin(String username, String password) {
        User user = findByID(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean phanQuyen(String fullname) {
        User user = findByID(fullname);
        if (user != null && user.getAdmin()) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkTon(String id) {
        list = findAll();
        for (User user : list) {
            if (user.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Boolean checkTonKhoa(User user) {
        for (Favorite favorite : listFavorite) {
            if (favorite.getUser().getId().equals(user.getId())){
                return true;
            }
        }
        return false;
    }

    public void delete(String id) {
        EntityManager em = HibernateUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = findByID(id);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            e.printStackTrace();
        }
    }

    public void deleteFavorite(Long id) {
        EntityManager em = HibernateUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            Favorite favorite = findByIDFavorite(id);
            if (favorite != null) {
                em.remove(favorite);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            e.printStackTrace();
        }
    }
}
