package basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

  public boolean passowrd(String password) {
    boolean hasEightCharacters = password.length() >= 8;
    boolean containsLowerCase = Pattern.matches(".*[a-z]{1,}.*", password);
    boolean containsUpperCase = Pattern.matches(".*[A-Z]{1,}.*", password);
    boolean containsNumber = Pattern.matches(".*[0-9]{1,}.*", password);
    boolean containsSpecialCharacter = Pattern.matches(
      ".*['!@#$%¨¬&*()_+=§´`{}ª^~º|,<>;:/?°\"\\.\\-\\[\\]\\çÇáéíóúãõüä]{1}.*",
      password
    );

    return hasEightCharacters
      && containsLowerCase
      && containsUpperCase
      && containsNumber
      && containsSpecialCharacter;
  }

  public boolean findFirstOccurrence(String regEx, String value) {
    Pattern pattern = Pattern.compile(regEx);
    Matcher macther = pattern.matcher(value);
    return macther.find();
  }

}
