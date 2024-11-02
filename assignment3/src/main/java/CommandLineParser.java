import java.util.HashSet;
import java.util.Set;

/**
 * Class CommandLineParser converts user input to Command
 */
public class CommandLineParser {
  private Set<String> commandSet;
  // check valid command
  private final String COMMAND_EMAIL = "--email";
  private final String COMMAND_LETTER = "--letter";
  private final String COMMAND_EMAIL_TEMPLATE = "--email-template";
  private final String COMMAND_LETTER_TEMPLATE = "--letter-template";
  private final String COMMAND_CSV_FILE = "--csv-file";
  private final String COMMAND_OUTPUT_DIRECTORY = "--output-dir";
  // check file input argument
  private final int NEXT_STEP = 1;
  private final String EMPTY_STRING = "";
  // exception massages
  private final String EXCEPTION_NO_INPUT = "No arguments provided.";
  private final String EXCEPTION_NO_EMAIL_TEMPLATE = "Missing email template filename.";
  private final String EXCEPTION_NO_LETTER_TEMPLATE = "Missing letter template filename.";
  private final String EXCEPTION_NO_CSV_FILE_PATH = "Missing CSV file path.";
  private final String EXCEPTION_NO_OUTPUT_DIR = "Missing output directory.";
  private final String EXCEPTION_UNKNOW = "Unknown argument: ";
  private final String EXCEPTION_NO_EMAIL_TEMPLATE_COMMAND =
      COMMAND_EMAIL + " provided but no " + COMMAND_EMAIL_TEMPLATE + " was given.";
  private final String EXCEPTION_NO_LETTER_TEMPLATE_COMMAND =
      COMMAND_LETTER + " provided but no " + COMMAND_LETTER_TEMPLATE + " was given.";
  private final String EXCEPTION_NULL_CSV_FILE_PATH = "no " + COMMAND_CSV_FILE + " was given.";
  private final String EXCEPTION_NULL_OUTPUT_DIR = "no " + COMMAND_OUTPUT_DIRECTORY + " was given.";
  private final String ERROR = "ERROR: ";
  private final String HELP = """
      Usage:
       --email only generate email messages
       --email-template <file> accept a filename that holds the email template.
       Required if --email is used
         --letter only generate letters
         --letter-template <file> accept a filename that holds the email template.
       Required if --letter is used
         --output-dir <path> accept the name of a folder, all output is placed in this folder
       --csv-file <path> accept the name of the csv file to process
      Examples:
       --email --email-template email-template.txt --output-dir emails --csv-file customer.csv
       --letter --letter-template letter-template.txt --output-dir letters --csv-file customer.csv""";


  /**
   * default constructor
   */
  public CommandLineParser() {
    commandSet = new HashSet<>();
    this.addCommandToSet();
  }

  /**
   * Converts user's input to Command
   *
   * @param args user's input String array
   * @return Command instance
   */
  public Command parse(String[] args) {
    Command command = new Command();

    if (args.length == 0) {
      System.out.println(ERROR + EXCEPTION_NO_INPUT);
      System.out.println(HELP);
      return command;
    }


    // traverse the input String array, setting the Command by each input argument
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case COMMAND_EMAIL:
          command.setGenerateEmail(true);
          break;
        case COMMAND_LETTER:
          command.setGenerateLetter(true);
          break;
        case COMMAND_EMAIL_TEMPLATE:
          if (i + NEXT_STEP < args.length && !this.commandSet.contains(args[i + NEXT_STEP])) {
            command.setEmailTemplateFile(args[++i]);
          } else {
            System.out.println(ERROR + EXCEPTION_NO_EMAIL_TEMPLATE);
            System.out.println(HELP);
            return command;
          }
          break;
        case COMMAND_LETTER_TEMPLATE:
          if (i + NEXT_STEP < args.length && !this.commandSet.contains(args[i + NEXT_STEP])) {
            command.setLetterTemplateFile(args[++i]);
          } else {
            System.out.println(ERROR + EXCEPTION_NO_LETTER_TEMPLATE);
            System.out.println(HELP);
            return command;
          }
          break;
        case COMMAND_CSV_FILE:
          if (i + NEXT_STEP < args.length && !this.commandSet.contains(args[i + NEXT_STEP])) {
            command.setCsvFile(args[++i]);
          } else {
            System.out.println(ERROR + EXCEPTION_NO_CSV_FILE_PATH);
            System.out.println(HELP);
            return command;
          }
          break;
        case COMMAND_OUTPUT_DIRECTORY:
          if (i + NEXT_STEP < args.length && !this.commandSet.contains(args[i + NEXT_STEP])) {
            command.setOutputDir(args[++i]);
          } else {
            System.out.println(ERROR + EXCEPTION_NO_OUTPUT_DIR);
            System.out.println(HELP);
            return command;
          }
          break;
        default:
          System.out.println(ERROR + EXCEPTION_UNKNOW + args[i]);
          System.out.println(HELP);
          return command;
      }
    }

    if (command.isGenerateEmail() && command.getEmailTemplateFile().equals(EMPTY_STRING)) {
      System.out.println(ERROR + EXCEPTION_NO_EMAIL_TEMPLATE_COMMAND);
      System.out.println(HELP);
      return command;
    }

    if (command.isGenerateLetter() && command.getLetterTemplateFile().equals(EMPTY_STRING)) {
      System.out.println(ERROR + EXCEPTION_NO_LETTER_TEMPLATE_COMMAND);
      System.out.println(HELP);
      return command;
    }

    if (command.getCsvFile().equals(EMPTY_STRING)) {
      System.out.println(ERROR + EXCEPTION_NULL_CSV_FILE_PATH);
      System.out.println(HELP);
      return command;
    }

    if (command.getOutputDir().equals(EMPTY_STRING)) {
      System.out.println(ERROR + EXCEPTION_NULL_OUTPUT_DIR);
      System.out.println(HELP);
      return command;
    }

    command.setValid(true);
    return command;
  }

  /**
   * adding all the valid commands to commandSet
   */
  private void addCommandToSet() {
    this.commandSet.add(COMMAND_EMAIL);
    this.commandSet.add(COMMAND_LETTER);
    this.commandSet.add(COMMAND_EMAIL_TEMPLATE);
    this.commandSet.add(COMMAND_LETTER_TEMPLATE);
    this.commandSet.add(COMMAND_CSV_FILE);
    this.commandSet.add(COMMAND_OUTPUT_DIRECTORY);
  }
}
