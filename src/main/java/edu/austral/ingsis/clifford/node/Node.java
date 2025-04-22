package edu.austral.ingsis.clifford.node;

public abstract class Node {
  protected String name;
  protected Directory parent;

  public Node(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
  }

  public String toString() {
    return getName();
  }

  public String getName() {
    return name;
  }

  public Directory getParent() {
    return parent;
  }

  public void setParent(Directory parent) {
    this.parent = parent;
  }

  public String getPath() {
    if (parent == null) {
      return "/";
    }
    return parent.getPath() + "/" + name;
  }
}
