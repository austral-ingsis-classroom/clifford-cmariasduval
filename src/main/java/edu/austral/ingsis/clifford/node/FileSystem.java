package edu.austral.ingsis.clifford.node;

public interface FileSystem {
  Directory getRoot();

  Directory getCurrentPosition();

  void setPosition(Directory directory);
}
