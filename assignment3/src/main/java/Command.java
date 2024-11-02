import java.util.Objects;

/**
 * Class Command represents a command we received from the user's input
 */
public class Command {
  private boolean generateEmail;
  private boolean generateLetter;
  private boolean valid;
  private String emailTemplateFile;
  private String letterTemplateFile;
  private String csvFile;
  private String outputDir;
  private final String EMPTY_STRING = "";

  /**
   * Construct a default Command instance, with false value for boolean fields and empty String
   */
  public Command(){
    generateEmail = false;
    generateLetter = false;
    valid = false;
    emailTemplateFile = EMPTY_STRING;
    letterTemplateFile = EMPTY_STRING;
    csvFile = EMPTY_STRING;
    outputDir = EMPTY_STRING;
  }

  /**
   * Indicates this command will generate email or not
   * @return generate email
   */
  public boolean isGenerateEmail() {
    return this.generateEmail;
  }

  /**
   * set the generate email fields
   * @param generateEmail input argument
   */
  public void setGenerateEmail(boolean generateEmail) {
    this.generateEmail = generateEmail;
  }

  /**
   * Indicates this command will generate letter or not
   * @return generate letter
   */
  public boolean isGenerateLetter() {
    return this.generateLetter;
  }

  /**
   * set the generate letter fields
   * @param generateLetter input argument
   */
  public void setGenerateLetter(boolean generateLetter) {
    this.generateLetter = generateLetter;
  }

  /**
   *
   * @return valid or not
   */
  public boolean isValid() {
    return this.valid;
  }

  /**
   * set valid or not for this command
   * @param valid input boolean
   */
  public void setValid(boolean valid) {
    this.valid = valid;
  }

  /**
   *
   * @return email template file path
   */
  public String getEmailTemplateFile() {
    return this.emailTemplateFile;
  }

  /**
   * set the email template file path
   * @param emailTemplateFile file path
   */
  public void setEmailTemplateFile(String emailTemplateFile) {
    this.emailTemplateFile = emailTemplateFile;
  }

  /**
   *
   * @return letter template file path
   */
  public String getLetterTemplateFile() {
    return this.letterTemplateFile;
  }

  /**
   * set letter template file path
   * @param letterTemplateFile file path
   */
  public void setLetterTemplateFile(String letterTemplateFile) {
    this.letterTemplateFile = letterTemplateFile;
  }

  /**
   *
   * @return csv file path
   */
  public String getCsvFile() {
    return this.csvFile;
  }

  /**
   * set csv file path
   * @param csvFile file path
   */
  public void setCsvFile(String csvFile) {
    this.csvFile = csvFile;
  }

  /**
   *
   * @return output file path
   */
  public String getOutputDir() {
    return this.outputDir;
  }

  /**
   * set output file path
   * @param outputDir file path
   */
  public void setOutputDir(String outputDir) {
    this.outputDir = outputDir;
  }

  @Override
  public String toString() {
    return "command [ " + "generate email: " + this.isGenerateEmail()
        + "generate letter: " + this.isGenerateLetter()
        + "email template path: " + this.getEmailTemplateFile()
        + "letter template path: " + this.getLetterTemplateFile()
        + "csv file path: " + this.getCsvFile()
        + "output dir path: " + this.getOutputDir() + " ]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Command command = (Command) o;
    return generateEmail == command.generateEmail && generateLetter == command.generateLetter
        && Objects.equals(emailTemplateFile, command.emailTemplateFile)
        && Objects.equals(letterTemplateFile, command.letterTemplateFile)
        && Objects.equals(csvFile, command.csvFile) && Objects.equals(outputDir,
        command.outputDir);
  }

  @Override
  public int hashCode() {
    return Objects.hash(generateEmail, generateLetter, emailTemplateFile, letterTemplateFile,
        csvFile, outputDir);
  }
}

