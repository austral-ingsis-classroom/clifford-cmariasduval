package edu.austral.ingsis.clifford.node;

public interface FileSystem {
  Directory getRoot();

  Directory getCurrentPosition();

  void setPosition(Directory directory);

  void createDirectory(String name);

  Directory changeDirectory(String path);

  void createFile(String name);
}
