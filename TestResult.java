/**
* Es la clase que calcula y almacena los resultados de un examen 
  */
public class TestResult {
  private Student alumno;
  private int respuestasCorrectas;
  private int totalPreguntas; 
/**
* Constructor de la clase
 *@param alumno el alumno que esta realizando el examen
 *@param respuestasCorrectas el num de respuestas correctas
 *@param totalPreguntas el num de preguntas en el examen 
 */
public TestResult(Student alumno, int respuestasCorrectas, int totalPreguntas) {
 this.alumno = alumno;
 this.respuestasCorrectas = respuestasCorrectas;
 this.totalPreguntas = totalPreguntas;
}

/**
 * Muestra los resultados
 */

public void mostrarResultado() {
 System.out.println("Alumno: " + alumno.getName());
 
 
 
 
