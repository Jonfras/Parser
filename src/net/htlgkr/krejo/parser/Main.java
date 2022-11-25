package net.htlgkr.krejo.parser;

public class Main {

    public static void main(String[] args) {
        Parser p = new Parser("tag_files/simple1.txt");
        p.readDocument();

    }
}
