import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<AcademicActivity> activities = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String CSV_FILE = "users.csv";
    public static User currentUser = null;

    public static void main(String[] args) {
       
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {

            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                String name = values[1];
                String email = values[2];
                String password = values[3];
                if(values[4].equals("Tutor")){
                    String subject = values[5];
                    String[] SpecializationsArray = scanner.nextLine().split(",");
                    List<String> specialization = Arrays.asList(SpecializationsArray);
                    Tutor tutor = new Tutor(id, name, email, password, subject, specialization);
                    users.add(tutor);
                } else{
                    String major = values[5];
                    String[] interestsArray = scanner.nextLine().split(",");
                    List<String> interests = Arrays.asList(interestsArray);
                    Student alumno = new Student(id, name, email, password, major, interests);
                    users.add(alumno);
                }
            }
        
        }catch (IOException e){
            System.out.println("Error");
        }
        
            System.out.println("\n--- Sistema de Tutorías ---");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.print("Seleccione una opción: ");
        
            int option = scanner.nextInt();
            scanner.nextLine(); 
        
            switch (option) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        while (true) { 
            
            System.out.println("1. Ver historial de actividades");
            System.out.println("2. Encontrar coincidencias");
            System.out.println("3. Realizar examen de opción múltiple");  // Nueva opción para el examen
            System.out.println("4. Crear Actividad");
            System.out.println("5. Unirse a una actividad");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int option2 = scanner.nextInt();
            scanner.nextLine();
            switch (option2) {
                case 1:
                showActivityHistory();
                    break;
                case 2:
                findMatches();
                    break;
                case 3:
                System.out.println("¿Que tema desea repasar? \n1.algebra \n2.funciones \n3.logaritmos \n4.trigonometria");
                int tema = scanner.nextInt();
                scanner.nextLine();
                switch (tema) {
                    case 1:
                    takeTest("algebra.csv");
                    break;
                    case 2:
                    takeTest("funciones.csv");
                    break;
                    case 3:
                    takeTest("logaritmos.csv"); 
                    break;
                    case 4:
                    takeTest("trigonometria.csv");
                    break;
                    default:
                    System.out.println("Opción no válida.");
                    
                }
                    break;
                case 4:
                    addActivity();
                    break;
                case 5:
                    joinActivity();
                    break;
                case 6:
                    return;
                default:
                 System.out.println("Opción no válida.");
            }
        }
    }


    private static void takeTest(String archivo) {
        Test examen = new Test();
        examen.cargarPreguntasDesdeCSV(archivo);  // Carga las preguntas desde el archivo CSV
        examen.realizarExamen();
        System.out.println("Respuestas correctas: " + examen.getRespuestasCorrectas() + "/" + examen.obtenerNumeroPreguntas());
    }

    private static void login() {
        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        User user = authenticateUser(email, password);

        if (user != null) {
            System.out.println("Bienvenido " + user.getName());
            userMenu(user);
        } else {
            System.out.println("Credenciales incorrectas.");
        }
        currentUser = user;
    }

    private static void saveUserToCSV(User user) {
        try (FileWriter writer = new FileWriter(CSV_FILE, true)) {
            System.out.println("Guardando usuario en el archivo CSV...");
            if (user instanceof Student) {
                Student student = (Student) user;
                writer.append(student.getId()).append(",")
                      .append(student.getName()).append(",")
                      .append(student.getEmail()).append(",")
                      .append(student.getPassword()).append(",")
                      .append("Estudiante").append(",")
                      .append(student.getMajor()).append(",")
                      .append(String.join(";", student.getInterests())).append("\n");
            } else if (user instanceof Tutor) {
                Tutor tutor = (Tutor) user;
                writer.append(tutor.getId()).append(",")
                      .append(tutor.getName()).append(",")
                      .append(tutor.getEmail()).append(",")
                      .append(tutor.getPassword()).append(",")
                      .append("Tutor").append(",")
                      .append(tutor.getSubjectExpertise()).append(",")
                      .append(String.join(";", tutor.getSpecializations())).append("\n");
            }
            System.out.println("Usuario guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario en el archivo CSV: " + e.getMessage());
        }
    }
    

    private static User authenticateUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }else{
                return null;
            }
        }
        return null;
    }


    private static void register() {
        System.out.print("Ingrese su ID: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese su nombre: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();
        System.out.print("¿Es estudiante o tutor? (1. Estudiante, 2. Tutor): ");
        int userType = scanner.nextInt();
        //scanner.nextLine(); 

        if (userType == 1) {
            System.out.print("Ingrese su carrera: ");
            String major = scanner.nextLine();
            String[] interestsArray = scanner.nextLine().split(",");
            List<String> interests = Arrays.asList(interestsArray);
            Student student = new Student(id, name, email, password, major, interests);
            users.add(student);
            saveUserToCSV(student);
            System.out.println("Estudiante registrado exitosamente.");
            currentUser = student;
        } else if (userType == 2) {
            System.out.print("Ingrese su área de especialización: ");
            String subjectExpertise = scanner.nextLine();
            String[] specializationsArray = scanner.nextLine().split(",");
            List<String> specializations = Arrays.asList(specializationsArray);
            Tutor tutor = new Tutor(id, name, email, password, subjectExpertise, specializations);
            users.add(tutor);
            saveUserToCSV(tutor);
            System.out.println("Tutor registrado exitosamente.");
            currentUser = tutor;
        } else {
            System.out.println("Opción no válida.");
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
            System.out.print("¿Es estudiante o tutor? (1. Estudiante, 2. Tutor): ");
            int userType = scanner.nextInt();
            scanner.nextLine(); 

            if (userType == 1) {
                System.out.print("Ingrese su carrera: ");
                String major = scanner.nextLine();
                String[] interestsArray = scanner.nextLine().split(",");
                List<String> interests = Arrays.asList(interestsArray);
                users.add(new Student(id, name, email, password, major,interests));
                System.out.println("Estudiante registrado exitosamente.");
            } else if (userType == 2) {
                System.out.print("Ingrese su área de especialización: ");
                String subjectExpertise = scanner.nextLine();
                String[] specializationsArray = scanner.nextLine().split(",");
                List<String> specializations = Arrays.asList(specializationsArray);
                users.add(new Tutor(id, name, email, password, subjectExpertise,specializations));
                System.out.println("Tutor registrado exitosamente.");
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

   
    private static void showActivityHistory() {
        System.out.println("\n--- Historial de Actividades ---");
        for (AcademicActivity activity : activities) {
            System.out.println(activity.toString());
        }
    }

    
    private static void userMenu(User user) {
        System.out.println("\n--- Bienvenido, " + user.getName() + " ---");
       
    }

    private static void findMatches() {
        MatchingSystem matchingSystem = new MatchingSystem();
        
        for (User  user : users) {
            if (user instanceof Student) {
                matchingSystem.addStudent((Student) user);
            } else if (user instanceof Tutor) {
                matchingSystem.addTutor((Tutor) user);
            }
        }
    
        System.out.println("\n--- Encontrar coincidencias ---");
        System.out.println("1. Encontrar coincidencias para un estudiante");
        System.out.println("2. Encontrar coincidencias para un tutor");
        System.out.print("Seleccione una opción: ");
    
        int option = scanner.nextInt();
        scanner.nextLine(); 
    
        switch (option) {
            case 1:
                System.out.print("Ingrese el ID del estudiante: ");
                String studentId = scanner.nextLine();
                Student student = null;
                for (User  user : users) {
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
                System.out.print("Ingrese el ID del tutor: ");
                String tutorId = scanner.nextLine();
                Tutor tutor = null;
                for (User  user : users) {
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
        }
    }
    public static void addActivity(){

        System.out.print("Ingrese el nombre del evento: ");
        String name = scanner.nextLine();
        System.out.println("Ingrese una descripcion del evento: ");
        String description = scanner.nextLine();
        System.out.print("Ingrese la capacidad maxima del evento: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la fecha del evento (AAAA-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Ingrese la hora de inicio del evento (HH:MM:SS): ");
        String time = scanner.nextLine();
        System.out.print("Ingrese la hora de fin del evento (HH:MM:SS): ");
        String endTime = scanner.nextLine();

        String horaInicioStr = date + "T" + time;
        String horaFinStr = date + "T" + endTime;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime horaInicio = LocalDateTime.parse(horaInicioStr, formatter);
        LocalDateTime horaFin = LocalDateTime.parse(horaFinStr, formatter);

        AcademicActivity actividad = new AcademicActivity((Tutor) currentUser, name, description, capacity, horaInicio, horaFin);
        activities.add(actividad);
        saveActivityToCSV(actividad);
        System.out.println("Actividad agregada con exito!");

    }
    public static void joinActivity(){
        System.out.print("Ingrese su Id de estudiante ");
        String studentId = scanner.nextLine();
        Student student = null;
        for (User  user : users) {
            if (user instanceof Student && user.getId().equals(studentId)) {
                student = (Student) user;
            }
        }
        for (AcademicActivity activity : activities){
            System.out.println("Id de la actividad: " + activity.getId() + "Nombre de la actividad: " + activity.getTitle());
        }
        System.out.print("Ingrese el Id de la actividad que desea unirse: ");
        String activityId = scanner.nextLine();
        for (AcademicActivity activity : activities){
            if (activity.getId().equals(activityId)){
                activity.addStudent(student);
                System.out.println("Se ha unido a la actividad con exito!");
            }else{
                System.out.println("No se ha encontrado la actividad");
            }
        }
    }

    /**
     * Guarda una actividad académica en un archivo CSV.
     *
     * Este método agrega una nueva línea al archivo `activities.csv` con los datos de la actividad,
     * incluyendo el ID del tutor, título, descripción, capacidad máxima, fecha y hora de inicio, 
     * y fecha y hora de fin. 
     *
     * @param activity La actividad académica que se va a guardar en el archivo CSV.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     * 
     * Ejemplo de formato CSV:
     * tutorId,titulo,descripcion,capacidadMaxima,fechaInicio,fechaFin
     */
    public static void saveActivityToCSV(AcademicActivity activity) {
        // Formato deseado para fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try (FileWriter writer = new FileWriter("activities.csv", true)) {
            System.out.println("Guardando actividad en el archivo CSV...");
            writer.append(activity.getTutor().getId()).append(",")
                  .append(activity.getTitle()).append(",")
                  .append(activity.getDescription()).append(",")
                  .append(String.valueOf(activity.getMaxCapacity())).append(",")
                  .append(activity.getStartDateTime().format(formatter)).append(",")
                  .append(activity.getEndDateTime().format(formatter)).append("\n");
        } catch (IOException e) {
            System.out.println("Error al guardar la actividad en el archivo CSV: " + e.getMessage());
        }
        System.out.println("Actividad guardada exitosamente.");
    }

    /**
     * Carga las actividades académicas desde un archivo CSV.
     *
     * El método lee cada línea del archivo `activities.csv`, procesa los datos de la actividad,
     * busca al tutor correspondiente usando su ID, y crea una instancia de {@link AcademicActivity}.
     * 
     * Si el tutor no es encontrado, muestra un mensaje indicando el problema.
     * 
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     * 
     * Ejemplo de formato CSV:
     * tutorId,titulo,descripcion,capacidadMaxima,fechaInicio,fechaFin
     */

    public static void loadActivitiesFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("activities.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String tutorId = data[0];
                String title = data[1];
                String description = data[2];
                int capacity = Integer.parseInt(data[3]);
                String startDateTimeStr = data[4];
                String endDateTimeStr = data[5];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeStr, formatter);
                LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeStr, formatter);
                Tutor tutor = findTutor(tutorId);
                if (tutor != null) {
                    AcademicActivity activity = new AcademicActivity(tutor, title, description, capacity, startDateTime, endDateTime);
                    activities.add(activity);
                } else {
                    System.out.println("No se ha encontrado el tutor con el cual corresponde a la actividad");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar actividades desde el archivo CSV: " + e.getMessage());
        }
    }

    /**
     * Carga los usuarios (estudiantes y tutores) desde un archivo CSV.
     *
     * El método lee cada línea del archivo `users.csv`, procesa los datos y crea instancias
     * de {@link Tutor} o {@link Student} según corresponda. 
     * Los usuarios se agregan a la lista global `users`.
     *
     * Ejemplo de formato CSV:
     * id,nombre,email,contraseña,tipoUsuario,especialidad/carrera,especializaciones/intereses
     *
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     */
    public static void loadUsersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                String name = values[1];
                String email = values[2];
                String password = values[3];
                
                if (values[4].equals("Tutor")) {
                    String subject = values[5];
                    String[] specializationsArray = scanner.nextLine().split(",");
                    List<String> specialization = Arrays.asList(specializationsArray);
                    Tutor tutor = new Tutor(id, name, email, password, subject, specialization);
                    users.add(tutor);
                }else {
                    String major = values[5];
                    String[] interestsArray = scanner.nextLine().split(",");
                    List<String> interests = Arrays.asList(interestsArray);
                    Student alumno = new Student(id, name, email, password, major, interests);
                    users.add(alumno);
                }
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
    }



}
