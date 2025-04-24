package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.*;

import java.nio.file.FileSystemException;
import java.util.List;

public class Rm implements Command {

  // borra el archivo o directorio que recibe como parámetro. Deberá usar la opción --recursive para
  // borrar directorios.
  // Si no se usa la opción --recursive y si quiere borrar un directorio entonces deberá devolver un
  // mensaje de error.

  private boolean recursive;
  private String toBeDeletedName;

  public Rm(String toBeDeletedName, boolean recursive) {
    this.recursive = recursive;
    this.toBeDeletedName = toBeDeletedName;
  }


    @Override
    public String execute(InMemoryFileSystem fileSystem, List<String> arguments) throws FileSystemException {
        Directory current = fileSystem.getCurrentPosition();
        Node node = getNodeToDelete(current);

        validate(node);
        deleteNode(current, node);
        return "'" + toBeDeletedName + "' removed";
    }

    private Node getNodeToDelete(Directory current) throws FileSystemException {
        Node node = current.getChild(toBeDeletedName);
        if (node == null) {
            throw new FileSystemException("'"+ toBeDeletedName + "' not found");
        }
        return node;
    }

    private void validate(Node node) throws FileSystemException {
      if(node instanceof Directory directory && !recursive && !directory.getChildren().isEmpty()){
          throw new FileSystemException("Directory is not empty");
      }
    }

    private void deleteNode(Directory current, Node node) throws FileSystemException {
        if (node instanceof Directory dir) {
            if (recursive) {
                // delete all children recursively
                for (Node child : List.copyOf(dir.getChildren())) {
                    deleteNode(dir, child);
                }
            }
        }
        current.removeChild(node);
    }
}
