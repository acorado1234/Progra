import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test{
  private ArrayList<Question> preguntas;
  private int respuestasCorrectas;

  public Test(){
    this.preguntas = new ArrayList<>();
    this.respuestasCorrectas = 0;
  }
  public void agregarPregunta(Question pregunta) {
    preguntas.add(pregunta);
    }
}
