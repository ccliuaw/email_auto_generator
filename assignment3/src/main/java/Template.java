import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a Template
 */
public class Template {

  private String template;
  private int uniqueFileId;
  private final String EMPTY_STRING = "";
  private final String FILE_PREFIX = "/data_";
  private final String FILE_SUFFIX = ".txt";
  private final String PLACEHOLDER_PREFIX = "[[";
  private final String PLACEHOLDER_SUFFIX = "]]";
  /**
   * Default template instance with empty content (empty string), unique file ID is 1
   */
  public Template() {
    this.template = EMPTY_STRING;
    this.uniqueFileId = 1;
  }

  /**
   *
   * @return current template string
   */
  public String getTemplate() {
    return this.template;
  }

  /**
   * set the template for this Template instance
   * @param templateFilePath the file path
   * @throws IOException throw exceptions
   */
  private void setTemplate(String templateFilePath) throws IOException {
    this.template = new String(Files.readAllBytes(Paths.get(templateFilePath)));
  }

  /**
   * precess a template, and save the output file to designated directory
   *
   * @param templateFilePath template's path
   * @param csvData          csv data of all the customers
   * @param outputDir        output file directory
   * @throws IOException throws exception
   */
  // Method to fill the template using the CSV data and template file path
  public void processTemplate(String templateFilePath, List<List<String>> csvData, String outputDir)
      throws IOException {
    // set the template from the file
    this.setTemplate(templateFilePath);
    // check outputDir has directory
    File newDirectory = new File(outputDir);
    newDirectory.mkdirs();

    // The first row in the CSV data is headers
    List<String> headers = csvData.get(0);

    // Iterate over each customer (starting from the second row)
    for (int i = 1; i < csvData.size(); i++) {
      List<String> customerData = csvData.get(i);
//      System.out.println(Arrays.toString(customerData.toArray()));
      Map<String, String> replacements = new HashMap<>(); // k=header, v=each row info in csv

      // find the corresponding value from customer to the headers
      for (int j = 0; j < headers.size(); j++) {
        //System.out.println(j);
        replacements.put(headers.get(j), csvData.get(i).get(j));
      }

      // Fill the template with customer-specific values
      String filledTemplate = fillTemplate(replacements);
//      System.out.println(filledTemplate + "\n");
      // save the filled content to the output directory, using unique file id as naming
      String outputFilePath = outputDir + FILE_PREFIX + this.uniqueFileId + FILE_SUFFIX;
      Path filePath = Paths.get(outputFilePath);
      Files.writeString(filePath, filledTemplate);
      this.uniqueFileId++;
    }
  }

  /**
   * replace the placeholder in the template with real csv data
   *
   * @param replacements real csv data, key = csv headers, value = customer information
   * @return a completed content
   */
  // Method to replace placeholders in the template with customer-specific values
  private String fillTemplate(Map<String, String> replacements) {
    String res = this.template;
    // Replace each placeholder [[header]] with the corresponding value
    for (Map.Entry<String, String> entry : replacements.entrySet()) {
      String placeholder = PLACEHOLDER_PREFIX + entry.getKey() + PLACEHOLDER_SUFFIX;
      res = res.replace(placeholder, entry.getValue());
    }
    return res;
  }

  @Override
  public String toString() {
    return "Now the unique file id is: " + this.uniqueFileId + "\n" + this.template;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Template template1 = (Template) o;
    return uniqueFileId == template1.uniqueFileId && Objects.equals(template,
        template1.template);
  }

  @Override
  public int hashCode() {
    return Objects.hash(template, uniqueFileId);
  }
}
