import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a CSV parser
 */
public class CSVParser {

  private List<List<String>> data;
  private final String SPLIT_REGEX = "\"([^\"]*)\"|([^,]+)";  // 2 groups, first is find the content inside " "; second is for non-quoted fields (the content without quotes but with , )
  private final String EMPTY_STRING = "";
  private final int GROUP_1 = 1;
  private final int GROUP_2 = 2;

  /**
   * default constructor
   */
  public CSVParser() {
    this.data = new ArrayList<>();
  }

  /**
   * @return all the customers' information with corresponding headers
   */
  public List<List<String>> getData() {
    return this.data;
  }

  /**
   * converts the csv file path to a csv data list
   *
   * @param csvFilePath csv file path
   * @return all the customers' information with corresponding headers
   * @throws IOException throws exception
   */
  public List<List<String>> parse(String csvFilePath) throws IOException {
    this.data = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
      String line;

      while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {

        List<String> fields = new ArrayList<>();

        // Regular expression to match fields that are either quoted or non-quoted
        Pattern pattern = Pattern.compile(SPLIT_REGEX);
        Matcher matcher = pattern.matcher(line);

        // Extract each field from the CSV line
        while (matcher.find()) {
          if (matcher.group(GROUP_1) != null) {
            // Quoted value (without the quotes)
            fields.add(matcher.group(GROUP_1).trim());
          } else if (matcher.group(GROUP_2) != null) {
            // Non-quoted value
            fields.add(matcher.group(GROUP_2).trim());
          } else{
            fields.add(EMPTY_STRING);
          }
        }
//        String[] fields = line.split(SPLIT_REGEX);

        // Clean and add fields
//        List<String> row = new ArrayList<>(Arrays.asList(fields));
        this.data.add(fields); // Add the row (either header or customer data) to the list
      }
    }

    return this.data;
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    for (List<String> row : data) {
      for (String cell : row) {
        res.append(cell).append(",");
      }
      res.append("\n");
    }
    return res.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CSVParser csvParser = (CSVParser) o;
    return Objects.equals(data, csvParser.data);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(data);
  }
}
