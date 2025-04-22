package edu.austral.ingsis.clifford.commands;

import static java.util.Arrays.copyOfRange;

import edu.austral.ingsis.clifford.node.FileSystem;
import java.nio.file.FileSystemException;
import java.util.Optional;

public class CommandRunner {

  public String runCommand(String command, FileSystem fileSystem) {
    String[] parts = command.trim().split("\\s+");
    if (parts.length == 0) {
      return "";
    }
    String name = parts[0];
    String[] arguments = copyOfRange(parts, 1, parts.length);
    Command command2;
    try {
      switch (name) {
        case "cd":
          if (arguments.length != 1) {
            return "Wrong usage! ";
          }
          command2 = new Cd(arguments[0]);
          break;

        case "touch":
          if (arguments.length != 1) {
            return "";
          }
          command2 = new Touch(arguments[0]);
          break;

        case "mkdir":
          if (arguments.length != 1) {
            return "";
          }
          command2 = new Mkdir(arguments[0]);
          break;

        case "ls":
          command2 = new Ls(Optional.empty());
          break;

        case "pwd":
          command2 = new Pwd();
          break;
        case "rm":
          boolean recursive = false;
          String fileName = null;
          for (String arg : arguments) {
            if (arg.equals("-recursive")) {
              recursive = true;
            } else {
              fileName = arg;
            }
          }
          if (fileName == null) {
            return "Wrong usage! ";
          }
          command2 = new Rm(fileName, recursive);
          break;

        default:
          return "Unknown command!";
      }
      return command2.execute(fileSystem);
    } catch (FileSystemException e) {
      return e.getMessage();
    } catch (Exception e) {
      return e.getMessage();
    }
  }
}
