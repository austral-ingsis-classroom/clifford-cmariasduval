package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.FileSystem;

public class Pwd implements Command {

  // imprime la ruta que tiene el cursor en el file system.

  @Override
  public String execute(FileSystem file) {
    Directory position = file.getCurrentPosition();
    return position.getFullPath();
  }
}
