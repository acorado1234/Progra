import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

  /**
   * Lista de usuarios que participan en el sistema.
   * Contiene instancias de la clase {@link User}, que puede incluir tanto estudiantes como tutores.
   */
    private static ArrayList<User> users = new ArrayList<>();
   /**
    * Lista de actividades académicas disponibles en el sistema.
    * Contiene instancias de la clase {@link AcademicActivity}, que representan diferentes actividades académicas.
    */
    private static ArrayList<AcademicActivity> activities = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

   /**
    * Objeto {@link Scanner} utilizado para leer entradas desde la consola.
    * Se utiliza en varios métodos para recibir entradas del usuario.
    */
    public static void main(String[] args) {

     }

  /**
   * Encuentra coincidencias entre estudiantes y tutores en el sistema de emparejamiento.
   * Dependiendo de la opción seleccionada por el usuario, se buscarán coincidencias
   * para un estudiante o un tutor, basadas en su ID.
   * 
   * El método interactúa con el sistema de emparejamiento {@link MatchingSystem} para
   * agregar estudiantes y tutores, y luego busca coincidencias según la opción seleccionada.
   * 
   * Funcionalidad:
   * 1. Buscar coincidencias para un estudiante basado en su ID.
   * 2. Buscar coincidencias para un tutor basado en su ID.
   * 
   * Proceso:
   * - Los usuarios se dividen en estudiantes y tutores.
   * - El sistema solicita al usuario seleccionar una opción y proporciona el ID del estudiante o tutor.
   * - El sistema encuentra las coincidencias y las muestra en la consola.
   * 
   * Excepciones:
   * - Si no se encuentra un estudiante o tutor con el ID proporcionado, se muestra un mensaje de error.
   * 
   * Variables:
   * - `users`: Lista de usuarios (tutores y estudiantes).
   * - `scanner`: Objeto para leer la entrada del usuario desde la consola.
   * 
   * Opción seleccionada:
   * - 1: Buscar coincidencias para un estudiante.
   * - 2: Buscar coincidencias para un tutor.
   */
    private static void findMatches() {
        MatchingSystem matchingSystem = new MatchingSystem();
    
        // Agregar estudiantes y tutores al sistema de emparejamiento
        for (User user : users) {
            if (user instanceof Student) {
                matchingSystem.addStudent((Student) user);
            } else if (user instanceof Tutor) {
                matchingSystem.addTutor((Tutor) user);
            }
        }
    
        // Menú para encontrar coincidencias
        System.out.println("\n--- Encontrar coincidencias ---");
        System.out.println("1. Encontrar coincidencias para un estudiante");
        System.out.println("2. Encontrar coincidencias para un tutor");
        System.out.print("Seleccione una opción: ");

        int option = scanner.nextInt();
        scanner.nextLine(); 
        
        switch (option) {
            case 1:
                // Buscar coincidencias para un estudiante
                System.out.print("Ingrese el ID del estudiante: ");
                String studentId = scanner.nextLine();
                Student student = null;
                for (User user : users) {
                    if (user instanceof Student && user.getId().equals(studentId)) {
                        student = (Student) user;
                        break;
                    }
                }
                if (student != null) {
                    List<Tutor> matches = matchingSystem.findMatchesForStudent(student);
                    System.out.println("Coincidencias encontradas para el estudiante " + student.getName() + ":");
                    for (Tutor match : matches) {
                        System.out.println(match.getName());
                    }
                } else {
                    System.out.println("Estudiante no encontrado.");
                }
                break;
            case 2:
                // Buscar coincidencias para un tutor
                System.out.print("Ingrese el ID del tutor: ");
                String tutorId = scanner.nextLine();
                Tutor tutor = null;
                for (User user : users) {
                    if (user instanceof Tutor && user.getId().equals(tutorId)) {
                        tutor = (Tutor) user;
                        break;
                    }
                }
                if (tutor != null) {
                    List<Student> matches = matchingSystem.findMatchesForTutor(tutor);
                    System.out.println("Coincidencias encontradas para el tutor " + tutor.getName() + ":");
                    for (Student match : matches) {
                        System.out.println(match.getName());
                    }
                } else {
                    System.out.println("Tutor no encontrado.");
                }
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }

    }
  
    private static void enterUsers() {
        System.out.println("\n--- Ingresar Usuarios (Estudiantes/Tutores) ---");
        System.out.print("¿Cuántos usuarios desea ingresar? ");
        int numberOfUsers = scanner.nextInt();
        scanner.nextLine(); 
    
        for (int i = 0; i < numberOfUsers; i++) {
            System.out.print("Ingrese su ID: ");
            String id = scanner.nextLine();
            System.out.print("Ingrese su nombre: ");
            String name = scanner.nextLine();
            System.out.print("Ingrese su email: ");
            String email = scanner.nextLine();
            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();
        }
    }



}
