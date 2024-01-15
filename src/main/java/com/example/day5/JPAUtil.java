package com.example.day5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");

    public static EntityManager createEntityManager(){
        return factory.createEntityManager();
    }

    public static void main(String[] args){
        createEntityManager();
    }
}
