package com.journaldev.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Libro;
import util.HibernateUtil;

public class HibernateAnnotationMain {

    public static void main(String[] args) {
        Libro libro = new Libro();
        libro.setTitulo("Danza de dragones");
        libro.setPrecio(28.40f);

        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        //Save the Model object
        session.save(libro);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Libro ID=" + libro.getIdlibro());

        //terminate session factory, otherwise program won't end
        sessionFactory.close();
    }

}