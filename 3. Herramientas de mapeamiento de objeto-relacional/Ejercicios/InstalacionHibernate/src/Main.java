import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        printDateTime();
        MysqlVersion();

    }

    public static void MysqlVersion(){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Object obj = session.createNativeQuery("SELECT VERSION()").getSingleResult();

            System.out.println(obj);

        }
    }

    public static void printDateTime(){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Object obj = session.createNativeQuery("SELECT NOW()").getSingleResult();

            System.out.println(obj);
        }
    }
}