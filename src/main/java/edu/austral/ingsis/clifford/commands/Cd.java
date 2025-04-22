package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.FileSystem;

public class Cd implements Command {

  //    cambia el directorio actual por el directorio que recibe como parámetro. Falla si el
  // directorio no existe o es un archivo. Tené en cuenta los siguientes casos:
  //
  //    Si el parámetro recibido es "..", se debe mover al directorio padre.
  //    Si el parámetro es ".", se debe quedar en el directorio actual pero no debe fallar.
  //    El parámetro puede ser el nombre de un directorio o una ruta hacia un directorio. Los
  // componentes de una ruta van separadas por el caracter "/".
  //    Si la ruta empieza con "/", tendrán que empezar desde el root del filesystem.

  private String parameter;

  public Cd(String parameter) {
    this.parameter = parameter;
  }

  @Override
  public String execute(FileSystem file) {
    Directory position = file.getCurrentPosition();
    Directory state = null;

    if (parameter.equals(".")) {
      return "You already are in" + position.toString();
    }

    if (parameter.equals("..")) {
      if (position.getParent() != null) {
        state = position.getParent();
      } else {
        return "There is no parent directory";
      }
    } else {
      if (parameter.startsWith("/")) {
        state = file.getRoot();
        parameter = parameter.substring(1);
      } else {
        state = position;
      }
    }

    String[] pathParts = parameter.split("/");

    for (String pathPart : pathParts) {}

    return "";
  }
}
