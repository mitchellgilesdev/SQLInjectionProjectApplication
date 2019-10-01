/*
 * Created by JinH on Aug 14, 2018
 * Filter - filter metachacters from SQL query parameters
 */

/**
 * @author JinH
 */
public class Filter {

    public Filter() {
        // none right now
    }

    char metaChars[] = new char[]{'\'', '\"', '\0', '\b', '\n', '\r', '\t', '\\', '&', ';', '`', '|',
            '*', '?', '~', '<', '>', '^', '(', ')', '[', ']', '{', '}', '$', '!', '-', '#', ' '};

    public String filterMetaChars(String s) {

        StringBuilder sb = new StringBuilder();
        boolean canAdd = true;

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < metaChars.length; j++) {
                if (s.charAt(i) == metaChars[j]) {
                    canAdd = false;
                }
            }

            if (canAdd) {
                sb.append(s.charAt(i));

            }
            canAdd = true;
        }

        return sb.toString();
    }
}
