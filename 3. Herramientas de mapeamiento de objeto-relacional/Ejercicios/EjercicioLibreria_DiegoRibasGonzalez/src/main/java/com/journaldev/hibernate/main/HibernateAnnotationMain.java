package com.journaldev.hibernate.main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Libro;
import util.HibernateUtil;

public class HibernateAnnotationMain {

    public static void main(String[] args) {
        Libro libro = new Libro();
        libro.setName("David");
        libro.setRole("Developer");

        //Get Session
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.save(libro);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Libro ID="+libro.getIdLibro());

        //terminate session factory, otherwise program won't end
        sessionFactory.close();
    }

}