package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.FileSystem;
import edu.austral.ingsis.clifford.node.Node;
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
  public String execute(FileSystem file) {
    Directory current = file.getCurrentPosition();
    List<Node> children = current.getChildren();

    // Si el directorio está vacio, devolverá una linea vacia.
    if (children.isEmpty()) {
      return "";
    }

    // En caso de que no se agregue un parámetro, los elementos tendrán que ser listados en el orden
    // en el que fueron creados.
    if (ord.isPresent()) {
      if (ord.get() == LsOptions.ASC) {
        children.sort(Comparator.comparing(Node::getName));
      } else if (ord.get() == LsOptions.DESC) {
        children.sort(Comparator.comparing(Node::getName));
      }
      // si no esta ni ASC ni DESC van a mantenerse en el orden en el q fueron creados
    }
    // listar los elementos del directorio actual
    return children.stream().map(Node::toString).collect(Collectors.joining(" "));
  }
}
