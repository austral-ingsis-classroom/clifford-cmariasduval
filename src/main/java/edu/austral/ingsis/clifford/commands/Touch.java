package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.File;
import edu.austral.ingsis.clifford.node.InMemoryFileSystem;
import java.nio.file.FileSystemException;
import java.util.List;

public class Touch implements Command {

  // crea un archivo vacio con el nombre que recibe como parámetro en el directorio actual.
  // No se puede crear archivos que tengan "/" ni espacio en el nombre.

  private String fileName;

  public Touch(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public String execute(InMemoryFileSystem fileSystem, List<String> arguments)
      throws FileSystemException {
    Directory current = fileSystem.getCurrentPosition();

    if (existsInDirectory(current, fileName)) {
      throw new FileSystemException("Directory already exists");
    }

    File newFile = new File(fileName, current);
    current.addChild(newFile);
    return "'" + fileName + "' file created";
  }

  private boolean existsInDirectory(Directory current, String fileName) {
    return current.getChildren().stream().anyMatch(child -> child.getName().equals(fileName));
  }
}
