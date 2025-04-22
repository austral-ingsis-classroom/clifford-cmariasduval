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

  public boolean hasChildren(String name) {
    for (Node child : children) {
      if (child.toString().equals(name)) {
        return true;
      }
    }
    return false;
  }

  public String getFullPath() {
    StringBuilder path = new StringBuilder();
    Directory directory = this;
    while (directory != null) {
      path.insert(0, "/" + directory.toString());
      directory = directory.getParent();
    }
    return path.toString();
  }

  public void createDirectory(String name) {
    if (getChild(name) != null) {
      throw new RuntimeException("Directory '" + name + "' already exists");
    }
    addChild(new Directory(name, this));
  }
}
