package util;

import model.Autor;
import model.Libro;
import org.hibernate.Session;
import javax.persistence.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class HibernateManager {
    private static SessionFactory sf = HibernateUtil.getSessionFactory();

    public void insertar() {
        Scanner sc = new Scanner(System.in);
        Session session = sf.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("¿Qué desea insertar?");
            System.out.println("1. Autor");
            System.out.println("2. Libro");
            int opcion = Integer.parseInt(sc.nextLine());

            if (opcion == 1) {
                Autor autor = new Autor();

                System.out.print("Introduzca el DNI del autor: ");
                autor.setDniautor(sc.nextLine());

                System.out.print("Introduzca el nombre del autor: ");
                autor.setNombre(sc.nextLine());

                System.out.print("Introduzca la nacionalidad del autor: ");
                autor.setNacionalidad(sc.nextLine());

                session.save(autor);
                System.out.println("Autor insertado correctamente.");
            }
            else if (opcion == 2) {
                Libro libro = new Libro();

                System.out.print("Ingrese el título del libro: ");
                libro.setTitulo(sc.nextLine());

                System.out.print("Ingrese el precio del libro: ");
                libro.setPrecio(Double.parseDouble(sc.nextLine()));

                session.save(libro);
                System.out.println("Libro insertado correctamente.");
            }
            else {
                System.out.println("Opción no válida.");
            }
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
            sc.close();
        }
    }

    public void borrar() {
        Scanner sc = new Scanner(System.in);
        Session session = sf.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("¿Qué desea borrar?");
            System.out.println("1. Autor");
            System.out.println("2. Libro");
            int opcion = Integer.parseInt(sc.nextLine());

            if (opcion == 1) {
                System.out.print("Ingrese el DNI del autor a borrar: ");
                String dniAutor = sc.nextLine();

                Autor autor = (Autor) session.get(Autor.class, dniAutor);
                if (autor != null) {
                    session.delete(autor);
                    System.out.println("Autor borrado correctamente.");
                }
                else {
                    System.out.println("No se encontró el autor con el DNI especificado.");
                }
            }
            else if (opcion == 2) {
                System.out.print("Ingrese el ID del libro a borrar: ");
                int idLibro = Integer.parseInt(sc.nextLine());

                Libro libro = (Libro) session.get(Libro.class, idLibro);
                if (libro != null) {
                    session.delete(libro);
                    System.out.println("Libro borrado correctamente.");
                }
                else {
                    System.out.println("No se encontró el libro con el ID especificado.");
                }
            }
            else {
                System.out.println("Opción no válida.");
            }
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
            sc.close();
        }
    }

    public void consultar() {
        Scanner sc = new Scanner(System.in);
        Session session = sf.openSession();

        try {
            System.out.println("¿Qué desea consultar?");
            System.out.println("1. Datos de un libro por título\n"
                    + "2. Todos los libros\n"
                    + "3. Todos los libros de un autor(NO FUNCIONA)\n"
                    + "4. Todos los autores con sus libros(NO FUNCIONA)");
            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String tituloLibro = sc.nextLine();

                    Libro libro = (Libro) session.createQuery(
                                    "FROM Libro l WHERE l.titulo = :titulo")
                            .setParameter("titulo", tituloLibro)
                            .uniqueResult();

                    if (libro != null) {
                        System.out.println("Datos del libro con título '" + tituloLibro + "':");
                        System.out.println("ID: " + libro.getIdLibro()
                                + "Título: " + libro.getTitulo()
                                + "Precio: " + libro.getPrecio());
                    }
                    else {
                        System.out.println("No se encontró el libro con el título especificado.");
                    }
                    break;

                case 2:
                    List<Libro> todosLibros = session.createQuery("FROM Libro").list();
                    if (!todosLibros.isEmpty()) {
                        System.out.println("Lista de todos los libros:\n"
                                + "------------------------------------");

                        for (Libro libroIter : todosLibros) {
                            System.out.println("ID: " + libroIter.getIdLibro()
                                    + "\nTítulo: " + libroIter.getTitulo()
                                    + "\nPrecio: " + libroIter.getPrecio());
                            System.out.println("===============================================");
                        }
                    }
                    else {
                        System.out.println("No hay libros en la base de datos.");
                    }
                    break;

                /**
                case 3:
                    System.out.print("Ingrese el nombre del autor: ");
                    String nombreAutor = sc.nextLine();

                    List<Libro> librosAutor = session.createQuery(
                                    "SELECT l FROM Libro l JOIN l.autores a WHERE a.nombre = :nombreAutor")
                            .setParameter("nombreAutor", nombreAutor)
                            .getResultList();

                    if (!librosAutor.isEmpty()) {
                        System.out.println("Libros del autor '" + nombreAutor + "':");
                        for (Libro libroAutor : librosAutor) {
                            System.out.println("ID: " + libroAutor.getIdLibro());
                            System.out.println("Título: " + libroAutor.getTitulo());
                            System.out.println("Precio: " + libroAutor.getPrecio());
                        }
                    }
                    else {
                        System.out.println("No se encontraron libros del autor '" + nombreAutor + "'.");
                    }
                    break;

                case 4:
                    List<Autor> autores = session.createQuery("FROM Autor").list();
                    if (!autores.isEmpty()) {
                        for (Autor autor : autores) {
                            System.out.println("Autor: " + autor.getNombre());
                            Set<Libro> libros = autor.getLibros();
                            if (!libros.isEmpty()) {
                                System.out.println("Libros:");
                                for (Libro libro : libros) {
                                    System.out.println("   - Título: " + libro.getTitulo());
                                    System.out.println("     Precio: " + libro.getPrecio());
                                }
                            }
                            else {
                                System.out.println("El autor no tiene libros registrados.");
                            }
                            System.out.println();
                        }
                    }
                    else {
                        System.out.println("No hay autores registrados en la base de datos.");
                    }
                    break;
                 */

                default:
                    System.out.println("Opción no válida.");
            }
        }
        finally {
            session.close();
            sc.close();
        }
    }
}
