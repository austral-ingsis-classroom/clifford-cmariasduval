package edu.austral.ingsis.clifford.node;

public class InMemoryFileSystem implements FileSystem {
  private Directory root;
  private Directory currentPosition;

  public InMemoryFileSystem() {
    this.root = new Directory("root", null);
    this.currentPosition = this.root;
  }

  @Override
  public Directory getRoot() {
    return root;
  }

  @Override
  public Directory getCurrentPosition() {
    return currentPosition;
  }

  @Override
  public void setPosition(Directory directory) {
    this.currentPosition = directory;
  }

  @Override
  public Directory changeDirectory(String path) {
    return currentPosition;
  }

  @Override
  public void createFile(String name) {
    currentPosition.addChild(new File(name, null));
  }

  @Override
  public void createDirectory(String name) {
    currentPosition.addChild(new Directory(name, null));
  }
}
