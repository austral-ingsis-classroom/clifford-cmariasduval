package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.FileSystem;
import edu.austral.ingsis.clifford.node.InMemoryFileSystem;

import java.nio.file.FileSystemException;
import java.util.List;

public class Mkdir implements Command {

  // crea un directorio vacio con el nombre que recibe como parámetro en el directorio actual.
  // No se puede crear directorios que tengan "/" ni espacio en el nombre.

  private String directoryName;

  public Mkdir(String directoryName) {
    this.directoryName = directoryName;
  }


    @Override
    public String execute(InMemoryFileSystem fileSystem, List<String> arguments) throws FileSystemException {
        Directory currentDirectory = fileSystem.getCurrentPosition();
        validateName(directoryName);
        checkIfExists(currentDirectory, directoryName);
        createDirectory(currentDirectory, directoryName);
        return "'" + directoryName + "' directory created";
    }

    private void validateName(String directoryName) throws FileSystemException {
      if(directoryName.contains("/") || directoryName.contains(" ")) {
          throw new FileSystemException("It can't have any '/' or spaces");
      }
    }

    private void checkIfExists(Directory currentDirectory, String directoryName) throws FileSystemException {
      if(currentDirectory.getChild(directoryName) != null) {
          throw new FileSystemException("Directory already exists");
      }
    }

    private void createDirectory(Directory currentDirectory, String directoryName) throws FileSystemException {
      currentDirectory.createDirectory(directoryName);
    }
}
