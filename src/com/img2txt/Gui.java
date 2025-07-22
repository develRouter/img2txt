package com.img2txt;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.ColorUIResource;

import com.img2txt.Convertor;
import com.img2txt.Theme;

public class Gui {

  public static void start() {

        //Theme.change("red");
        Theme.change("light");

        // Create the main window (JFrame)
        JFrame frame = new JFrame("Convertor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        frame.setSize(700, 600); // Set window size
        frame.setLayout(new BorderLayout());
        frame.setResizable(false); // This line prevents resizing
        frame.setLocationRelativeTo(null);

        //ImageIcon ico_img = new ImageIcon(Gui.class.getResource("/assets/devil_router_ico.png"));
        //frame.setIconImage(ico_img.getImage());


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //buttonsPanel.setBackground(Color.decode("#240508"));
        
        JPanel previewPanel = new JPanel();
        previewPanel.setLayout(new FlowLayout());
        //previewPanel.setBackground(Color.decode("#240508"));
        
        JPanel previewField = new JPanel();
        previewField.setLayout(new BorderLayout());
        //previewField.setBackground(Color.decode("#240508"));
        

        JLabel WarnLabel = new JLabel("Warning! It will overwrite output file!");
        WarnLabel.setForeground(Color.RED);

        JLabel emojiPreview = new JLabel(new ImageIcon(Gui.class.getResource("/assets/" + Options.selectedEmoji + ".png")));
        emojiPreview.setPreferredSize(new Dimension(30, 30));
        emojiPreview.setBorder(BorderFactory.createLineBorder(Color.decode(Options.borderColor), 1));


        JTextArea textArea = new JTextArea(10, 10);
        //textArea.setBackground(Color.decode("#240508"));
        

        JButton inputFileButton = new JButton("Input File");
        JButton outputFileButton = new JButton("Output File");

        outputFileButton.setPreferredSize(new Dimension(150, 30));
        inputFileButton.setPreferredSize(new Dimension(150, 30));
        
        
        JLabel previewInputLabel = new JLabel("input preview");
        JLabel previewOutputLabel = new JLabel("output preview");

        JButton ConvertButton = new JButton("Convert!");



        inputFileButton.addActionListener(e -> {
          JFileChooser fileChooser = new JFileChooser();
          FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG Images (*.png)", "png");
          fileChooser.addChoosableFileFilter(pngFilter);
          fileChooser.setFileFilter(pngFilter);
          fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
          int result = fileChooser.showOpenDialog(frame); 
          if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Setting input file to " + selectedFile.getAbsolutePath());
            Options.inputFilePath = selectedFile.getAbsolutePath();
            File file = new File(selectedFile.getAbsolutePath());
            WarnLabel.setText("Warning! It will overwrite " + file.getName().substring(0, file.getName().indexOf(".")) + ".txt" + " file!");
            System.out.println("Setting output file to " + selectedFile.getAbsolutePath().substring(0, selectedFile.getAbsolutePath().indexOf(".")) + ".txt");
            Options.outputFilePath = selectedFile.getAbsolutePath().substring(0, selectedFile.getAbsolutePath().indexOf(".")) + ".txt";
            previewInputLabel.setText("");
            ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());
            Image image = originalIcon.getImage();
            Image resizedImage = image.getScaledInstance(310, 280, Image.SCALE_SMOOTH);  // width height
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            previewInputLabel.setIcon(resizedIcon);
          }
        });



        outputFileButton.addActionListener(e -> {
          JFileChooser fileChooser = new JFileChooser();
          FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text files (*.txt)", "txt");
          fileChooser.addChoosableFileFilter(txtFilter);
          fileChooser.setFileFilter(txtFilter);
          fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
          int result = fileChooser.showOpenDialog(frame); // parentComponent is typically the JFrame or JPanel
          if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Setting output file to " + selectedFile.getAbsolutePath());
            File file = new File(selectedFile.getAbsolutePath());
            WarnLabel.setText("Warning! It will overwrite " + file.getName() + " file!");

            Options.outputFilePath = selectedFile.getAbsolutePath();
          }
        });
      

        ConvertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Options.inputFilePath == "") {
                JOptionPane.showMessageDialog(frame, "Input file is null!", "Error", JOptionPane.ERROR_MESSAGE); // error
                return;
                }
                Convertor.convert();
                textArea.setText(Options.outputString.replace("\n", "\\n\n"));
                JOptionPane.showMessageDialog(frame, "Done!"); // Show a message dialog
            }
        });
        ConvertButton.setPreferredSize(new Dimension(350, 30));

       
        textArea.setText("output text preview");
        textArea.setBorder(BorderFactory.createLineBorder(Color.decode(Options.borderColor), 3));
        textArea.setPreferredSize(new Dimension(100, 100));
        textArea.setEditable(false);

        previewInputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        previewInputLabel.setBorder(BorderFactory.createLineBorder(Color.decode(Options.borderColor), 3));
        previewInputLabel.setPreferredSize(new Dimension(340, 300));

        previewOutputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        previewOutputLabel.setBorder(BorderFactory.createLineBorder(Color.decode(Options.borderColor), 3));
        previewOutputLabel.setPreferredSize(new Dimension(340, 300));

        JComboBox<String> modeList = new JComboBox<>(Options.emojis);

        modeList.addActionListener(new ActionListener() {
          @Override
            public void actionPerformed(ActionEvent e) {
              String selectedItem = (String) modeList.getSelectedItem();
              System.out.println("Setting Emoji to " + selectedItem);
              Options.selectedEmoji = selectedItem;
              if (Gui.class.getResource("/assets/" + Options.selectedEmoji + ".png") != null) {
                emojiPreview.setText("");
                emojiPreview.setIcon(new ImageIcon(Gui.class.getResource("/assets/" + Options.selectedEmoji + ".png")));
              } else {
                emojiPreview.setIcon(null);
                emojiPreview.setForeground(Color.RED);
                emojiPreview.setText("Err");
              }
            }
        });
        
        JComboBox<String> themeList = new JComboBox<>(Options.themes);
        themeList.setPreferredSize(new Dimension(135, 25));

        themeList.addActionListener(new ActionListener() {
          @Override
            public void actionPerformed(ActionEvent e) {
              String selectedItem = (String) themeList.getSelectedItem();
              System.out.println("Setting Theme to " + selectedItem);
              Theme.change(selectedItem);
              emojiPreview.setBorder(BorderFactory.createLineBorder(Color.decode(Options.borderColor), 1));
              textArea.setBorder(BorderFactory.createLineBorder(Color.decode(Options.borderColor), 3));
              previewInputLabel.setBorder(BorderFactory.createLineBorder(Color.decode(Options.borderColor), 3));
              previewOutputLabel.setBorder(BorderFactory.createLineBorder(Color.decode(Options.borderColor), 3));

              SwingUtilities.updateComponentTreeUI(frame);
              frame.revalidate();
              frame.repaint();
            }
        });


        buttonsPanel.add(themeList);
        buttonsPanel.add(inputFileButton);
        buttonsPanel.add(outputFileButton);
        buttonsPanel.add(modeList);
        buttonsPanel.add(emojiPreview);

        previewPanel.add(previewInputLabel, BorderLayout.WEST);
        previewPanel.add(previewOutputLabel, BorderLayout.EAST);
        previewPanel.add(ConvertButton, BorderLayout.SOUTH);

        previewField.add(textArea, BorderLayout.CENTER);
        previewField.add(WarnLabel, BorderLayout.SOUTH);
        
        
        // Add the panel to the frame
        frame.add(buttonsPanel, BorderLayout.NORTH);
        frame.add(previewPanel, BorderLayout.CENTER);
        frame.add(previewField, BorderLayout.SOUTH);
        
        // Make the frame visible
        frame.setVisible(true);
   }
}

/*class GradientButton extends JButton {
  private Color color1;
  private Color color2;

  public GradientButton(String text, Color c1, Color c2) {
    super(text);
    this.color1 = c1;
    this.color2 = c2;
    setContentAreaFilled(false); // Important: Disable default background painting
    setFocusPainted(false); // Optional: Remove focus border
    //setBorderPainted(false); // Optional: Remove button border
  }

  @Override
    protected void paintComponent(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Create a linear gradient from top to bottom
      GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
      g2d.setPaint(gp);
      g2d.fillRect(0, 0, getWidth(), getHeight()); // Fill the button area with the gradient

      super.paintComponent(g); // Call super to paint text and other components
    }
} */
