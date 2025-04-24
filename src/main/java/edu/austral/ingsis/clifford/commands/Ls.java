package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.FileSystem;
import edu.austral.ingsis.clifford.node.InMemoryFileSystem;
import edu.austral.ingsis.clifford.node.Node;

import java.nio.file.FileSystemException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ls implements Command {

  // --ord que puede recibir 2 parámetros asc o desc según se quiera un orden ascendente o
  // descendente.
  private final Optional<LsOptions> ord; // parámetro

  public Ls(Optional<LsOptions> ord) {
    this.ord = ord;
  }


    @Override
    public String execute(InMemoryFileSystem fileSystem, List<String> arguments) throws FileSystemException {
        return "";
    }
}
