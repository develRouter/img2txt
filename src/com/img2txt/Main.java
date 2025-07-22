package com.img2txt;
import  com.img2txt.Gui;
import com.img2txt.Convertor;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0){
      Gui.start();
    } else if (args[0].equals("help")) {
      System.out.println("Usage: java -jar img2txt.jar <input.png> <emoji> <output.txt>");
      System.out.println("Run without arguments to start gui.");
      System.out.println("   Available emojis are:");
      for (int i = 0; i < Options.emojis.length; i++) {
        System.out.println("     " + Options.emojis[i]);
      }
      System.out.println("WARNING! if output file is not specified, then it will be same as input, but png will be replaced with txt.\n  It can overwrite it! (if input = a.png, output will be a.txt)");
      System.exit(1);
    } else if (args.length < 2) {
      System.out.println("Wrong arguments! run with 'help' to get help.");
      System.exit(1);
    } else {
      Options.inputFilePath = args[0];
      Options.selectedEmoji = args[1];
      if (args.length == 3)
        Options.outputFilePath = args[2];
      else
        Options.outputFilePath = args[0].substring(0, args[0].indexOf(".")) + ".txt";

      Convertor.convert();
    }
  }
}
