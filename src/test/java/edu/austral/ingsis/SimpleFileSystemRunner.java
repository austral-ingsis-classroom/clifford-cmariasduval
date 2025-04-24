package edu.austral.ingsis;

import edu.austral.ingsis.clifford.node.InMemoryFileSystem;
import edu.austral.ingsis.clifford.node.Runner;
import java.util.ArrayList;
import java.util.List;

public class SimpleFileSystemRunner implements FileSystemRunner {
  private final InMemoryFileSystem fileSystem;
  private final Runner commandRunner;

  public SimpleFileSystemRunner(InMemoryFileSystem fileSystem, Runner commandRunner) {
    this.fileSystem = fileSystem;
    this.commandRunner = commandRunner;
  }

  @Override
  public List<String> executeCommands(List<String> commands) {
    List<String> result = new ArrayList<>();

    for (String command : commands) {
      result.add(commandRunner.runCommand(command, fileSystem));
    }

    return result;
  }
}
