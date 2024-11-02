import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Class ConductCommand using doCommands to conduct the Command instruction
 */
public class ConductCommand {
  private final String SENDING_EMAILS = "[stub] Sending generated emails to clients.";
  private final String SENDING_LETTERS = "[stub] Printing generated letters to send to clients.";
  /**
   * Conduct the Command's instruction
   * @param command input command
   * @throws IOException throws exception
   */
  public void doCommands(Command command) throws IOException {
    // Parse the CSV file into a list of lists (first row = headers, rest = customer data)
    CSVParser csvParser = new CSVParser();
    List<List<String>> csvData = csvParser.parse(command.getCsvFile());

    // Determine the template and output directory
    String emailTemplateFile = command.getEmailTemplateFile();
    String letterTemplateFile = command.getLetterTemplateFile();
    String outputDir = command.getOutputDir();

    // Process emails or/and letters
    Template templateProcessor = new Template();

    if (command.isGenerateEmail()) {
      templateProcessor.processTemplate(emailTemplateFile, csvData, outputDir);
      System.out.println(SENDING_EMAILS);
    }
    if (command.isGenerateLetter()) {
      templateProcessor.processTemplate(letterTemplateFile, csvData, outputDir);
      System.out.println(SENDING_LETTERS);
    }
  }

}
