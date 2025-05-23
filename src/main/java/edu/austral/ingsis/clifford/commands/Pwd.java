package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.InMemoryFileSystem;
import java.nio.file.FileSystemException;
import java.util.List;

public class Pwd implements Command {
  @Override
  public String execute(InMemoryFileSystem fileSystem, List<String> arguments)
      throws FileSystemException {
    Directory current = fileSystem.getCurrentPosition();
    return current.getFullPath();
  }

  // imprime la ruta que tiene el cursor en el file system.

}
