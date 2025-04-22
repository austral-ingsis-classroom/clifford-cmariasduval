package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.FileSystem;
import java.nio.file.FileSystemException;

public interface Command {
  public String execute(FileSystem file) throws FileSystemException;
}
