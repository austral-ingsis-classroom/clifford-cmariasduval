package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.File;
import edu.austral.ingsis.clifford.node.FileSystem;
import edu.austral.ingsis.clifford.node.Node;

public class Rm implements Command {

  // borra el archivo o directorio que recibe como parámetro. Deberá usar la opción --recursive para
  // borrar directorios.
  // Si no se usa la opción --recursive y si quiere borrar un directorio entonces deberá devolver un
  // mensaje de error.

  private boolean recursive;
  private String fileName;

  public Rm(String fileName, boolean recursive) {
    this.recursive = recursive;
    this.fileName = fileName;
  }

  @Override
  public String execute(FileSystem file) {
    Directory directory = file.getCurrentPosition();
    Node nodeToDelete = directory.getChild(fileName);

    if (nodeToDelete == null) {
      return "There's no such file or directory with that name";
    }

    if (nodeToDelete instanceof File) {
      directory.removeChild(nodeToDelete);
      return "The file '" + fileName + "' has been removed";
    }

    if (nodeToDelete instanceof Directory) {
      if (recursive) {
        deleteDirectoryRecursively((Directory) nodeToDelete);
        return "The directory '" + fileName + "' has been removed";
      } else {
        return "The directory '" + fileName + "' cannot be deleted without 'recursive'";
      }
    }
    return "Sorry, it couldn't be deleted due to an unknown error";
  }

  private void deleteDirectoryRecursively(Directory directory) {
    for (Node child : directory.getChildren()) {
      if (child instanceof Directory) {
        deleteDirectoryRecursively((Directory) child);
      }
      directory.removeChild(child);
    }
    directory.getParent().removeChild(directory);
  }
}
