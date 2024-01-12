import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {

    private static StandardServiceRegistry ssr;
    private static SessionFactory sf;

    static {
        try{
            if(sf == null){

                StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();

                Map<String, String> dbConf = new HashMap<>();

                dbConf.put(Environment.URL, "jdbc:mysql://localhost:3306/dbsms?createDatabaseifNotExist=true");

                dbConf.put(Environment.USER, "root");
                dbConf.put(Environment.PASS, "1234");
                dbConf.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                dbConf.put(Environment.DIALECT, "org.hibernate.dialect.MYSQL57Dialect");

                ssrb.applySettings(dbConf);

                StandardServiceRegistry ssr = ssrb.build();

                MetadataSources mds = new MetadataSources();

                Metadata md = mds.getMetadataBuilder().build();

                sf = md.getSessionFactoryBuilder().build();

            }
        }
        catch (Exception e){
            if (ssr != null){
                StandardServiceRegistryBuilder.destroy(ssr);
            }
        }
    }

    public static SessionFactory getSessionFactory(){

        return sf;
    }



}
