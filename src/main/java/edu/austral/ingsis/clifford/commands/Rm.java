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
  private String fileName;

  public Rm(String fileName, boolean recursive) {
    this.recursive = recursive;
    this.fileName = fileName;
  }


    @Override
    public String execute(InMemoryFileSystem fileSystem, List<String> arguments) throws FileSystemException {
        return "";
    }
}
