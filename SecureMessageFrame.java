/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tut.ac.za;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import za.ac.tut.encryption.MessageEncrypter;

public class SecureMessageFrame extends JFrame {

    // File options
    private JMenuBar menuBar;
    private JMenu fileMenu;

    private JMenuItem openFile;
    private JMenuItem encryptMenuItem;
    private JMenuItem saveEncryptMenuItem;
    private JMenuItem clearMenuItem;
    private JMenuItem exitMenuItem;

    // Text areas and panels
    private JPanel headingPanel;
    private JPanel mainPanel;
    private JPanel textAreaPanel;
    private JTextArea plainMessTxt;
    private JTextArea encryptedMessTxt;

    private JLabel headingLabel;

    private JScrollPane plainScrollPane;
    private JScrollPane encryptedScrollPane;

    public SecureMessageFrame() {
        setTitle("Secure Messages");
        setSize(1200, 600);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Menu bar setup
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        openFile = new JMenuItem("Open file...");
        openFile.addActionListener(new openFileLIstener());
        encryptedMessTxt =new JTextArea();
            
        encryptMenuItem = new JMenuItem("Encrypt message...");
        encryptMenuItem.addActionListener(new EncryptListener());
        
        
        saveEncryptMenuItem = new JMenuItem("Save encrypted message...");
        saveEncryptMenuItem.addActionListener(new SaveEncryptedMessageListener());
        
        clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(new clearListener());
        
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ExitListener() );

        fileMenu.add(openFile);
        fileMenu.add(encryptMenuItem);
        fileMenu.add(saveEncryptMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(clearMenuItem);
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Heading 
        headingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headingLabel = new JLabel("Message Encryptor");
        headingPanel.add(headingLabel);

        headingLabel.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        headingLabel.setForeground(Color.BLUE);
        Border border = new LineBorder(Color.WHITE, 1);
        headingLabel.setBorder(border);

        // Text areas setup
        plainMessTxt = new JTextArea(10, 30);
        encryptedMessTxt = new JTextArea(10, 30);

        // Text area borders
        plainScrollPane = new JScrollPane(plainMessTxt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        encryptedScrollPane = new JScrollPane(encryptedMessTxt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        plainScrollPane.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Plain Message"));
        encryptedScrollPane.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Encrypted Message"));

        // Paneltext areas 
        textAreaPanel = new JPanel(new GridLayout(1, 2, 0, 0));
        textAreaPanel.add(plainScrollPane);
        textAreaPanel.add(encryptedScrollPane);

        // Main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainPanel.add(textAreaPanel, BorderLayout.CENTER);

        add(mainPanel);
        pack();
        setVisible(true);
    }
    public class openFileLIstener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("plainText Files", "txt"));
            
            int returnValue=fileChooser.showOpenDialog(SecureMessageFrame.this);
            
            if(returnValue==JFileChooser.APPROVE_OPTION)
            {
                File file =fileChooser.getSelectedFile().getAbsoluteFile();
                
                String fileContent="";
                try
                {
                    BufferedReader rd=new BufferedReader(new FileReader(file));
                    String line=" ";
                    
                    while((line = rd.readLine())!=null )
                    {
                        fileContent+=line + "\n";
                    }
                    plainMessTxt.setText(fileContent);
                }
                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(SecureMessageFrame.this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
                    
        }
        
    }
    private class EncryptListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String plainText=plainMessTxt.getText();
            String encryptedText =MessageEncrypter.encryptMessage(plainText);
            encryptedMessTxt.setText(encryptedText);

        }
    }
    private class SaveEncryptedMessageListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
            int returnValue = fileChooser.showSaveDialog(SecureMessageFrame.this);
            if (returnValue == JFileChooser.APPROVE_OPTION)
            {
                try 
                {
                    Path filePath = fileChooser.getSelectedFile().toPath();
                    Files.writeString(filePath, encryptedMessTxt.getText());
                }
                catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(SecureMessageFrame.this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    private class clearListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
           plainMessTxt.setText("");
           encryptedMessTxt.setText("");
        }
        
    }
    
    private class ExitListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(1);
        }
        
    }
    
}
