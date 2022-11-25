package net.htlgkr.krejo.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Parser {
    private final CharSequence TAG_OPENER = "<";
    private final CharSequence TAG_CLOSER = ">";
    private final CharSequence ENDTAG_OPENER = "</";

    Map<String, String> tagsWithContents = new TreeMap<>();


    private final String filename;
    private List<String> logList = new ArrayList<>();
    //private static List<String> tagLines = new ArrayList<>();

    public Parser(String filename) {
        this.filename = filename;
    }

    public void readDocument() {
        Scanner s;
        try {
            s = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            String err = "!!File was not found...";
            logList.add(err);
            System.err.println(err);
            System.out.println("File wasnt found");
            return;
        }

        for (int i = 0; s.hasNext(); i++) {
            String line = s.nextLine();
            System.out.println(findOpeningTag(line, 0));
        }


        System.out.println("finished");


    }

    public String findOpeningTag(String line, int depth) {
        StringBuilder temp = new StringBuilder();

        String openingTag;
        String closingTag;

        for (int i = 0; i < line.length(); i++) {
            
        }


        //if finished and there are no more tags in the line
        return
    }


    private String createClosingTag(String openingTag) {
        return openingTag.replace(TAG_OPENER, ENDTAG_OPENER);
    }

    private void log(String tagName, boolean isEmpty) {
        String message = isEmpty ? ">> Empty at Tag: " + tagName + System.currentTimeMillis()
                : "!!Error at Tag: " + tagName + System.currentTimeMillis();
        logList.add(message);
        System.out.println(message);
    }

    /**
     *  temp.append(line.charAt(i));
     *
     *             if (line.charAt(i) == TAG_OPENER.charAt(0) && line.charAt(i + 1) != '/') {
     *                 try {
     *                     openingTag = line.substring(i, line.indexOf(TAG_CLOSER.toString()) + 1);
     *                 } catch (Exception e) {
     *                     System.err.println("!!No Tag Closer at Tag: " + temp);
     *                     return "!!faulty Tag";
     *                 }
     *                 System.out.println("New Opening Tag: " + openingTag);
     *
     *                 closingTag = createClosingTag(openingTag);
     *
     *                 if (line.contains(closingTag)) {
     *                     temp.delete(0,0);
     *                     temp.append(openingTag);
     *
     *                     System.out.println("Matching Closing Tag found: " + closingTag);
     *
     *                     if (line.length() <= openingTag.length() + closingTag.length()) {
     *                         log(openingTag, true);
     *                         temp.append(closingTag);
     *                         return temp.toString();
     *                     }
     *
     *                     // int startIdx = (temp.length() == 1) ? openingTag.length() : temp.length + openingTag.length
     *
     *
     *                     depth++;
     *                     System.out.println("Entering Depth " + depth);
     *
     *                     String extracted = line.substring(openingTag.length() + temp.length() - 1, line.lastIndexOf(closingTag));
     *                     System.out.println("Looking for Tags in: " + extracted);
     *
     *
     *                     i += extracted.length();
     *                     returnValue = findOpeningTag(extracted);
     *                     tagsWithContents.put(openingTag, )
     *                     System.out.println("No more Tags found returning to Depth " + --depth);
     *                     //recursive method call is finished
     *
     *                 } else {
     *                     log(openingTag, false);
     *                 }
     *
     *
     *             }
     *
     *         }
     *
     *         tagLines.add(temp.toString());
     *         return temp.toString();
     *
     *
     */
}
