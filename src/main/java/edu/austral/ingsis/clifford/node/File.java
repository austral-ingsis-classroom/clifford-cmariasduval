package edu.austral.ingsis.clifford.node;

public class File extends Node {

  private final String name;
  private final Directory parent;

  public File(String name, Directory parent) {
    super(name, parent);
    this.name = name;
    this.parent = parent;
  }

  @Override
  public Directory getParent() {
    return parent;
  }

  @Override
  public String toString() {
    return name;
  }
}
