package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.File;
import edu.austral.ingsis.clifford.node.FileSystem;

public class Touch implements Command {

  // crea un archivo vacio con el nombre que recibe como parámetro en el directorio actual.
  // No se puede crear archivos que tengan "/" ni espacio en el nombre.

  private String fileName;

  public Touch(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public String execute(FileSystem file) {
    Directory directory = file.getCurrentPosition();

    if (fileName.contains("/") || fileName.contains(" ")) {
      return "You can't use spaces or '/' in the name of a file ";
    }

    if (directory.hasChildren(fileName)) {
      return "There is already a file named like that";
    }

    File newFile = new File(fileName, directory);
    directory.addChild(newFile);

    return "Success adding the file" + fileName;
  }
}
