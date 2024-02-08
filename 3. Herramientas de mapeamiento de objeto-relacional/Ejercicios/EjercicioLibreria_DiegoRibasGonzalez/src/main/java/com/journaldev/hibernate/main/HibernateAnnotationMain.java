package com.journaldev.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Libro;
import util.HibernateUtil;

public class HibernateAnnotationMain {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        sessionFactory.close();
    }
}