package com.img2txt;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Convertor {
  public static void convert() {
    String outputString = "";
    String emoji = "";

    System.out.println("Convertor: starting converting...");

    switch(Options.selectedEmoji) {
      case "router":
        emoji = "\uF88E";
        break;
      case "salt":
        emoji = "\uF8ED";
        break;
      case "snow":
        emoji = "\uF8EC";
        break;
      case "core":
        emoji = "\uF867";
        break;
      case "dark_sand":
        emoji = "\uF8F0";
        break;
      case "ice":
        emoji = "\uF8EB";
        break;
      case "moss":
        emoji = "\uF8D4";
        break;
      case "copper":
        emoji = "\uF8C4";
        break;
      case "book":
        emoji = "\uE801";
        break;
      case "file":
        emoji = "\uF15B";
        break;
      case "github_cat":
        emoji = "\uF308";
        break;
      case "sharp":
        emoji = "\uF029";
        break;
      default:
        System.out.println("Convertor: warning: invalid emoji");
        emoji = Options.selectedEmoji;
        break;
    }

    try {
      File imageFile = new File(Options.inputFilePath);
      System.out.println("Convertor: reading '" + Options.inputFilePath + "' file...");
      BufferedImage image = ImageIO.read(imageFile);

      if (image == null) {
        System.out.println("Convertor: error: image == null. Could not read input image.");
        return;
      }
      
      FileWriter writer = new FileWriter(Options.outputFilePath);

      int width = image.getWidth();
      int height = image.getHeight();

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int pixel = image.getRGB(x, y);

          // Extract ARGB components
          int alpha  = (pixel >> 24) & 0xFF;
          int red    = (pixel >> 16) & 0xFF;
          int green  = (pixel >> 8) & 0xFF;
          int blue   = (pixel >> 0) & 0xFF;

          String hexAlpha = String.format("%02x", alpha);
          String hexRed = String.format("%02x", red);
          String hexGreen = String.format("%02x", green);
          String hexBlue = String.format("%02x", blue);

          //System.outputString.println("Pixel at (" + x + ", " + y + "): ARGB = #" + hexAlpha + hexRed + hexGreen + hexBlue);
          outputString += "[#" + hexRed + hexGreen + hexBlue + hexAlpha + "]" + emoji; // RGBA
        }
            outputString += "\n";
      }

      //System.outputString.println(outputString);
      System.out.println("Convertor: writing '" + Options.outputFilePath + "' file...");
      writer.write(outputString);
      writer.close();
      System.out.println("Convertor: Finished Writing.");
      Options.outputString = outputString;
      System.out.println("Convertor: Finished.");

    } catch (IOException e) {
        e.printStackTrace();
    }
  }
}
