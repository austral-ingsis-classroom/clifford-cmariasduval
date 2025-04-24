package edu.austral.ingsis.clifford.node;

import java.util.ArrayList;
import java.util.List;

public class Directory extends Node {

  private List<Node> children = new ArrayList<>();

  public Directory(String name, Directory parent) {
    super(name, parent);
  }

  public Node getChild(String name) {
    for (Node child : children) {
      if (child.toString().equals(name)) {
        return child;
      }
    }
    return null;
  }

  public void addChild(Node child) {
    children.add(child);
  }

  public void removeChild(Node child) {
    children.remove(child);
  }

  public List<Node> getChildren() {
    return children;
  }

  public String getFullPath() {
    // System.out.println("getting full path...");
    StringBuilder path = new StringBuilder();
    Directory current = this;

    while (current.getParent() != null) {
      path.insert(0, "/" + current.toString());
      current = current.getParent();
    }

    return path.length() == 0 ? "/" : path.toString();
  }

  public void createDirectory(String name) {
    if (getChild(name) != null) {
      throw new RuntimeException("Directory '" + name + "' already exists");
    }
    addChild(new Directory(name, this));
  }
}
