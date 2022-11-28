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
    Deque<String> tags = new ArrayDeque<>();
    Deque<String> contents = new ArrayDeque<>();


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
            findOpeningTag(line);
        }
        for (String tagAndContent : tagsWithContents.values()) {
            System.out.println(tagAndContent);
        }


        System.out.println("finished");


    }

    public String findOpeningTag(String line) {
        StringBuilder content = new StringBuilder();


        String openingTag = "";
        String closingTag;

        for (int i = 0; i < line.length(); i++) {
            String tempTag = "";
            if (line.charAt(i) == TAG_OPENER.charAt(0)) {
                contents.push(content.toString());
                content = new StringBuilder();
                try {
                    int j;
                    for (j = i; j < line.length() && line.charAt(j) != TAG_CLOSER.charAt(0); j++, i++) {
                        tempTag += line.charAt(j);
                    }
                    tempTag += ">";
                    //sus
                    if (j >= line.length()) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.err.println("NO TAG CLOSER FOUND IN: " + line);
                    return "FAULTY LINE";
                }
                if (tempTag.charAt(1) == '/') {
                    if (tags.peek() != null && createClosingTag(tags.peek()).equals(tempTag)) {
                        tagsWithContents.put(tagsWithContents.size() + tags.pop(), contents.pop()); //tags.peek() + contents.pop() + tempTag
                        if (tagsWithContents.get(tagsWithContents.size()-1) == null){ // .length() <= tempTag.length()*2 - 1
                            log(tagsWithContents.get(tagsWithContents.size()-1), false);
                        }
                    }
                    //tags is empty or a false tag is there
                    else {
                        log(tempTag, true);
                    }
                }

                else {
                    tags.push(tempTag);
                }

            } else {
                content.append(line.charAt(i));
            }
        }


        //if finished and there are no more tags in the line
        return "TEST";
    }


    private String createClosingTag(String openingTag) {
        return openingTag.replace(TAG_OPENER, ENDTAG_OPENER);
    }

    private void log(String tagName, boolean isFaulty) {
        String message = isFaulty ? "!!Error at Tag: " + tagName + " at " + System.currentTimeMillis()
                : ">> Empty Tag: " + tagName + " at " +  System.currentTimeMillis();
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
