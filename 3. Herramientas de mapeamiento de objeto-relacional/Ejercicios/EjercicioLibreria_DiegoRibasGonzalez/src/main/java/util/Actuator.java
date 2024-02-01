package util;

import model.Autor;
import model.Libro;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class Actuator {
    private Session session;
    private final String AUTORES_CLASS = "model.Autor";
    private final String LIBROS_CLASS = "model.Libro";

    public Actuator() {
    }

    public void endSession() {
        HibernateUtil.getSessionFactory().close();
    }

    public void insert(Object obj) {
        try {
            this.startSession();
            this.session.beginTransaction();
            this.session.save(obj);
            this.session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Object obj){
        try{
            this.startSession();
            this.session.beginTransaction();
            this.session.delete(obj);
            this.session.getTransaction().commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public Autor getDni(String dni) {
        this.startSession();

        String sql = "from Autores where DniAutor = :dni";

        Query query = this.session.createQuery(sql);
        query.setParameter("dni", dni);
        Autor autor = (Autor) query.uniqueResult();

        return autor;
    }

    public Libro getIdLibro(int id){
        this.startSession();

        String sql = "from Libros where IdLibro = :id";

        Query query = this.session.createQuery(sql);
        query.setParameter("id", id);
        Libro libro = (Libro) query.uniqueResult();

        return libro;
    }

    public Libro getLibroByTitulo(String titulo){
        this.startSession();

        String hql = "from Libros where Titulo = :titulo";

        Query query = this.session.createQuery(hql);
        query.setParameter("titulo", titulo);
        Libro libro = (Libro) query.uniqueResult();

        return libro;
    }

    public List<Libro> getLibroByAutor(String nombre){
        this.startSession();

        String hql = "select l from Libros l join l.autor a where a.nombre = :nombre";

        Query query = this.session.createQuery(hql);
        query.setParameter("nombre", nombre);
        List<Libro> libros = query.list();

        return libros;
    }

    public List<Libro> getAllLibros() {
        this.startSession();

        String hql = "from Libros";

        Query query = this.session.createQuery(hql);
        List<Libro> libros = query.list();

        return libros;
    }

    public List<Autor> getAllAutoresWithLibros() {
        this.startSession();

        String hql = "select a from Autores a left join fetch a.libros";

        Query query = this.session.createQuery(hql);
        List<Autor> autores = query.list();

        return autores;
    }

    private void startSession() {
        if (this.session == null || !this.session.isOpen()) {
            this.session = HibernateUtil.getSessionAnnotationFactory().openSession();
        }
    }
}
