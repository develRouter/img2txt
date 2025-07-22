package com.img2txt;

import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.ColorUIResource;

import com.img2txt.Options;

public class Theme {
  public static void change(String theme) {
    switch (theme) {
      case "red":
        try {
          Options.borderColor = "#a68c0d";
          // Create a custom NimbusLookAndFeel extension
          UIManager.setLookAndFeel(new NimbusLookAndFeel() {
            @Override
            public UIDefaults getDefaults() {
              UIDefaults defaults = super.getDefaults();
              // Customize colors
              defaults.put("control", new ColorUIResource(Color.decode("#240508"))); // Background color
              defaults.put("nimbusBase", new ColorUIResource(Color.decode("#8a0404"))); // Base color for gradients
              defaults.put("nimbusBlueGrey", new ColorUIResource(Color.decode("#b00404"))); // Another color for gradients
              defaults.put("text", new ColorUIResource(Color.decode("#37a106"))); // Text color

              defaults.put("nimbusLightBackground", Color.decode("#260407"));
              defaults.put("nimbusSelectionBackground", Color.decode("#c93038"));
              defaults.put("nimbusSelectedText", Color.GREEN);
              defaults.put("nimbusFocus", Color.decode("#037018"));
            
              // Customize fonts
              defaults.put("Label.font", new Font("Arial", Font.BOLD, 12));
              defaults.put("Button.font", new Font("Dialog", Font.BOLD, 13));

              return defaults;
            }
          });
        } catch (UnsupportedLookAndFeelException e) {
          e.printStackTrace();
       }
       break;
      case "light":
        try {
          Options.borderColor = "#4040f7";
          UIManager.setLookAndFeel(new NimbusLookAndFeel() {
            @Override
            public UIDefaults getDefaults() {
              UIDefaults defaults = super.getDefaults();
              // Customize colors
              defaults.put("control", new ColorUIResource(Color.decode("#c1c1f5"))); // Background color
              defaults.put("nimbusBase", new ColorUIResource(Color.decode("#9b8bf0"))); // Base color for gradients
              defaults.put("nimbusBlueGrey", new ColorUIResource(Color.decode("#7662e3"))); // Another color for gradients
              defaults.put("text", new ColorUIResource(Color.decode("#000000"))); // Text color

              defaults.put("nimbusLightBackground", Color.decode("#cccef0"));
              defaults.put("nimbusSelectionBackground", Color.decode("#4f56e0"));
              defaults.put("nimbusSelectedText", Color.BLACK);
              defaults.put("nimbusFocus", Color.decode("#7d00fa"));
            
              // Customize fonts
              defaults.put("Label.font", new Font("Arial", Font.BOLD, 12));
              defaults.put("Button.font", new Font("Dialog", Font.BOLD, 13));

              return defaults;
            }
          });
        } catch (UnsupportedLookAndFeelException e) {
          e.printStackTrace();
       }
       break;
  }
 }
}
