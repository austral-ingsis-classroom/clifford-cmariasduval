package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.FileSystem;

public class Mkdir implements Command {

  // crea un directorio vacio con el nombre que recibe como parámetro en el directorio actual.
  // No se puede crear directorios que tengan "/" ni espacio en el nombre.

  private String directoryName;

  public Mkdir(String directoryName) {
    this.directoryName = directoryName;
  }

  @Override
  public String execute(FileSystem file) {
    Directory directory = file.getCurrentPosition();

    if (directoryName.contains("/") || directoryName.contains(" ")) {
      return "The directory name must not contain spaces or '/'";
    }

    if (directory.hasChildren(directoryName)) {
      return "There already exists a directory with the name '" + directoryName + "'";
    }

    Directory newDirectory = new Directory(directoryName, directory);
    directory.addChild(newDirectory);

    return "'" + directoryName + "' directory created";
  }
}
