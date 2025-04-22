package edu.austral.ingsis;

import edu.austral.ingsis.clifford.commands.CommandRunner;
import edu.austral.ingsis.clifford.node.InMemoryFileSystem;
import java.util.ArrayList;
import java.util.List;

public class SimpleFileSystemRunner implements FileSystemRunner {
  private final InMemoryFileSystem fileSystem;
  private final CommandRunner commandRunner;

  public SimpleFileSystemRunner(InMemoryFileSystem fileSystem, CommandRunner commandRunner) {
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
