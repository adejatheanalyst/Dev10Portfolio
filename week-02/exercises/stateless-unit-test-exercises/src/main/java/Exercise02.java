public class Exercise02 {

    // 1. Read the surroundWithTag JavaDocs.
    // 2. Complete the surroundWithTag method. You're only allowed to confirm it's working by running
    // the accompanying test in Exercise02Test.
    // 3. The test is incomplete. It doesn't account for all scenarios. Complete the test to insure
    // surroundWithTag is 100% correct.

    /**
     * Given two Strings: some text and a tag name, return a String that embeds the text in a pseudo-HTML tag.
     * Examples:
     * "abc", "boom" -> "<boom>abc</boom"
     * "Cats are mean.", "fact" -> "<fact>Cats are mean.</fact>"
     * "this is just text", "" -> "this is just text"
     * null, "span" -> "<span></span>"
     * "splendid", null -> splendid
     *
     * @param text    string value to be surrounded by an HTML tag
     * @param tagName the HTML tag name
     * @return string in the form: <tagName>text</tagName>
     */
    static String surroundWithTag(String text, String tagName) {
        if (text == null) {

            return "<" + tagName + "></" + tagName + ">";
        }
        if (tagName == null) {
            return text;
        }
        if (tagName.isEmpty()) {
            return text;
        }
        return "<" + tagName + ">" + text + "</" + tagName + ">";
    }

    }

