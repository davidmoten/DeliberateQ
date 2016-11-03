package moten.david.util.xml;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Stack;

public class TaggedOutputStream {
    public String indentString = "  ";
    private Stack<String> stack = new Stack<String>();
    private boolean tagOpen = false;
    private boolean prettyPrint = false;
    private int startIndent = 0;
    private PrintWriter out;
    private boolean lastOperationWasCloseTag = false;

    public TaggedOutputStream(OutputStream os, boolean prettyPrint) {
        this.out = new PrintWriter(os);
        this.prettyPrint = prettyPrint;
    }

    public TaggedOutputStream(OutputStream os, boolean prettyPrint, int startIndent) {
        this(os, prettyPrint);
        this.startIndent = startIndent;
    }

    public void startTag(String tag) {
        closeBracket();
        if (this.prettyPrint) {
            this.out.println();
            for (int i = 0; i < this.stack.size() + this.startIndent; i++) {
                this.out.print(this.indentString);
            }
        }
        this.out.print("<" + tag);
        this.stack.push(tag);
        this.tagOpen = true;
        this.lastOperationWasCloseTag = false;
    }

    private void closeBracket() {
        if (this.tagOpen) {
            this.out.print(">");
            this.tagOpen = false;
        }
    }

    public void addAttribute(String key, String value) {
        this.out.print(" " + key + "=\"" + value + "\"");
    }

    public void addAttribute(String key, double d) {
        DecimalFormat df = new DecimalFormat("#.0000000");
        this.out.print(" " + key + "=\"" + df.format(d) + "\"");
    }

    public void closeTag() {
        if (this.tagOpen) {
            this.out.print("/");
            closeBracket();
            this.stack.pop();
        } else {
            if ((this.lastOperationWasCloseTag) && (this.prettyPrint)) {
                this.out.println();
                for (int i = 0; i < this.stack.size() - 1 + this.startIndent; i++) {
                    this.out.print(this.indentString);
                }
            }
            String tag = (String) this.stack.pop();
            this.out.print("</" + tag + ">");
        }
        this.lastOperationWasCloseTag = true;
    }

    public void close() {
        if (this.stack.size() > 0) {
            throw new Error(this.stack.size() + "unclosed tags!");
        }
        this.out.flush();
        this.out.close();
    }

    public void append(String str) {
        closeBracket();
        this.out.print(str);
    }

    public void append(boolean b) {
        append(new Boolean(b).toString());
    }

    public void append(double d) {
        DecimalFormat df = new DecimalFormat("#.0000000");
        append(df.format(d));
    }

    public void append(long d) {
        append(new Long(d).toString());
    }

    public void append(int d) {
        append(new Integer(d).toString());
    }

    public static void main(String[] args) {
        TaggedOutputStream t = new TaggedOutputStream(System.out, true);
        t.startTag("info");
        t.startTag("name");
        t.append("johnno");
        t.closeTag();
        t.startTag("type");
        t.addAttribute("location", "Canberra");
        t.startTag("size");
        t.startTag("range");
        t.append("large");
        t.startTag("distrubution");
        t.append("uniform");
        t.closeTag();
        t.closeTag();
        t.closeTag();
        t.closeTag();
        t.startTag("size");
        t.startTag("range");
        t.append("large");
        t.startTag("distrubution");
        t.append("uniform");
        t.closeTag();
        t.closeTag();
        t.closeTag();
        t.closeTag();
        t.close();
    }
}