package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.FileSystem;
import edu.austral.ingsis.clifford.node.InMemoryFileSystem;

import java.nio.file.FileSystemException;
import java.util.List;

public interface Command {
  public String execute(InMemoryFileSystem fileSystem, List<String> arguments) throws FileSystemException;
}
