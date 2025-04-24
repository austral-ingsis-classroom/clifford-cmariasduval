package edu.austral.ingsis.clifford.node;

public class InMemoryFileSystem implements FileSystem {
  private Directory root;
  private Directory currentPosition;

  public InMemoryFileSystem() {
    this.root = new Directory("", null);
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
}
