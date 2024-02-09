/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.hibernatemaven;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author a22jesusbm
 */
public class HibernateMaven {

    private static final SessionFactory factoria;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            factoria = configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("MENÚ");
            System.out.println("1. Inserción de nuevas filas");
            System.out.println("    a. Inserción autor");
            System.out.println("    b. Inserción libro");
            System.out.println("2. Borrado de filas");
            System.out.println("    a. Borrado libro");
            System.out.println("    b. Borrado autor");
            System.out.println("3. Consultas");
            System.out.println("    a. Visualizar datos de un libro por título");
            System.out.println("    b. Visualizar libros de un autor por nombre");
            System.out.println("    c. Visualizar todos los libros");
            System.out.println("    d. Visualizar todos los autores con sus libros");
            System.out.println("4. Fin");

            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("1. Inserción de nuevas filas");
                    System.out.print("   Seleccione a. para Inserción autor o b. para Inserción libro: ");
                    char subOpcion1 = scanner.nextLine().charAt(0);

                    if (subOpcion1 == 'a') {
                        insertarAutor();
                    } else if (subOpcion1 == 'b') {
                        insertarLibro();
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;

                case 2:
                    System.out.println("2. Borrado de filas");
                    System.out.print("   Seleccione a. para Borrado libro o b. para Borrado autor: ");
                    char subOpcion2 = scanner.nextLine().charAt(0);

                    if (subOpcion2 == 'a') {
                        borrarLibro();
                    } else if (subOpcion2 == 'b') {
                        borrarAutor();
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;

                case 3:
                    System.out.println("3. Consultas");
                    System.out.print("   Seleccione a. para Visualizar datos de un libro por título,");
                    System.out.println(" b. para Visualizar libros de un autor por nombre,");
                    System.out.println(" c. para Visualizar todos los libros,");
                    System.out.println(" d. para Visualizar todos los autores con sus libros:");

                    char subOpcion3 = scanner.nextLine().charAt(0);
                    switch (subOpcion3) {
                        case 'a':
                            System.out.print("Ingrese el título del libro: ");
                            String tituloLibro = scanner.nextLine();
                            visualizarDatosLibroPorTitulo(tituloLibro);
                            break;
                        case 'b':
                            System.out.print("Ingrese el nombre del autor: ");
                            String nombreAutor = scanner.nextLine();
                            // visualizarLibroNombreAutor(nombreAutor);
                            break;
                        case 'c':
                            visualizarTodosLibro();
                            break;
                        case 'd':

                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                    break;

                case 4:
                    factoria.close();
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private static void insertarAutor() {
        Scanner scanner = new Scanner(System.in);

        Session session = factoria.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            autor1 autor = new autor1();

            System.out.print("Ingrese el DNI del autor: ");
            autor.setDniAutor(scanner.nextLine());

            System.out.print("Ingrese el nombre del autor: ");
            autor.setNombre(scanner.nextLine());

            System.out.print("Ingrese la nacionalidad del autor: ");
            autor.setNacionalidad(scanner.nextLine());
            session.save(autor);
            transaction.commit();
            System.out.println("Autor insertado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void insertarLibro() {
        Scanner scanner = new Scanner(System.in);

        Session session = factoria.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            libro1 libro = new libro1();

            System.out.print("Ingrese el título del libro: ");
            libro.setTitulo(scanner.nextLine());

            System.out.print("Ingrese el precio del libro: ");
            libro.setPrecio(Double.parseDouble(scanner.nextLine()));

            session.save(libro);

            transaction.commit();
            System.out.println("Libro insertado correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            scanner.close();
        }
    }

    private static void borrarAutor() {
        Scanner scanner = new Scanner(System.in);
        Session session = factoria.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.print("Ingrese el DNI del autor a borrar: ");
            String dniAutor = scanner.nextLine();

            autor1 autor = (autor1) session.get(autor1.class, dniAutor);
            if (autor != null) {
                // Borrar el autor
                session.delete(autor);
                System.out.println("Autor borrado correctamente.");
            } else {
                System.out.println("No se encontró el autor con el DNI especificado.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            scanner.close();
        }
    }

    private static void borrarLibro() {
        Session session = factoria.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.print("Ingrese el ID del libro a borrar: ");
            String idLibroStr = new Scanner(System.in).nextLine();

            // Convertir la cadena a Integer
            Integer idLibro;
            try {
                idLibro = Integer.parseInt(idLibroStr);
            } catch (NumberFormatException e) {
                System.out.println("Error: El ID del libro debe ser un número válido.");
                return;
            }

            libro1 libro = (libro1) session.get(libro1.class, idLibro);
            if (libro != null) {
                // Borrar el libro
                session.delete(libro);
                System.out.println("Libro borrado correctamente.");
            } else {
                System.out.println("No se encontró el libro con el ID especificado.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void visualizarDatosLibroPorTitulo(String tituloLibro) {
        Session session = factoria.openSession();

        try {
            libro1 libro = (libro1) session.createQuery(
                    "FROM libro1 la WHERE la.Titulo = :titulo")
                    .setParameter("titulo", tituloLibro)
                    .uniqueResult();

            if (libro != null) {
                System.out.println("Datos del libro con título '" + tituloLibro + "':");
                System.out.println("ID: " + libro.getIdLibro());
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Precio: " + libro.getPrecio());
            } else {
                System.out.println("No se encontró el libro con el título especificado.");
            }
        } finally {
            session.close();
        }
    }

    private static void visualizarTodosLibro() {
        Session session = factoria.openSession();

        try {
            List<libro1> libros = session.createQuery(
                    "FROM libro1 la").list();
            for (libro1 libro : libros) {
                System.out.println("ID: " + libro.getIdLibro());
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Precio: " + libro.getPrecio());
            }
        } finally {
            session.close();
        }
    }



}
