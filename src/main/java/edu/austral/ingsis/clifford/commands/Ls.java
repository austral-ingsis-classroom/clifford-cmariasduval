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

import static java.util.Collections.sort;


public class Ls implements Command {

  // ls
  //Descripción: listar los elementos del directorio actual. Puede recibir el flag --ord que puede recibir 2 parámetros asc o desc según se quiera un orden ascendente o descendente. En caso de que no se agregue un parámetro, los elementos tendrán que ser listados en el orden en el que fueron creados. Si el directorio está vacio, devolverá una linea vacia.
  //
  //Parámetros: ninguno
  //
  //Opciones:
  //
  //"--ord": define el algoritmo que tiene que usar para ordenar el output de los elements. Puede recibir 2 valores: asc o desc.
  //Output: Los elementos de primer nivel del directorio.


  private final Optional<LsOptions> ord; // parámetro

  public Ls(Optional<LsOptions> ord) {
    this.ord = ord;
  }


    @Override
    public String execute(InMemoryFileSystem fileSystem, List<String> arguments) throws FileSystemException {
        List<Node> children = fileSystem.getCurrentPosition().getChildren();
        List<String> sortedDirectoryNames = getSortedNames(children);
        return String.join(" ", sortedDirectoryNames);
    }

    private List<String> getSortedNames(List<Node> children) {
      if(ord.isPresent()) {
          Comparator<Node> comparator = getComparatorForOrder(ord.get());
          return children.stream().sorted(comparator).map(Node::getName).collect(Collectors.toList());

      }
      else{
          return children.stream().map(Node::getName).collect(Collectors.toList());
      }
    }

    private Comparator<Node> getComparatorForOrder(LsOptions lsOptions) {
      if(lsOptions == LsOptions.ASC){
          return Comparator.comparing(Node::getName);
      }
      else{
          return Comparator.comparing(Node::getName).reversed();
      }
    }



}
