package ui;

import db.TranslatorDatabase;
import managers.TranslateManager;
import model.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by imant
 */
public class TranslatorView {

    JFrame Frame = new JFrame();
    JTextField textField = new JTextField();
    JPanel Panel = new JPanel();
    JList List = new JList();
    JButton translateButton = new JButton("Translate");
    DefaultListModel<Word> listModel = new DefaultListModel<>();

    public TranslatorView() {
        paintView();
        TranslatorDatabase.getInstance().createConnection();
    }

    private void paintView() {
        initPanel();
        initFrame();
        initButtons();
        initListViews();
        initTextViews();
    }

    private void initPanel() {
        Panel.setBounds(20, 20, 360, 70);
        Panel.setBackground(Color.GRAY);
        Panel.setVisible(true);
        Panel.setLayout(null);
    }

    private void initFrame() {
        Frame.setSize(400, 400);
        Frame.setBackground(Color.BLACK);
        Frame.setTitle("Translator");
        Frame.setLocationRelativeTo(null);
        Frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Frame.setLayout(null);

    }

    private void initButtons() {
        translateButton.setBounds(220, 20, 120, 30);
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Thread(() -> {
                    String inputWord = textField.getText();
                    TranslateManager translateManager = new TranslateManager();
                    Word word = translateManager.translateWord(inputWord, "ru", "en");
                    listModel.addElement(word);
                    TranslatorDatabase.getInstance().writeToDB(TranslatorDatabase.getInstance().getConnection(), "ru", "en", inputWord, word);
                }).start();
            }
        });

        Frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(Frame, "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    TranslatorDatabase.getInstance().closeConnection();
                    System.exit(0);
                }
            }
        });
    }

    private void initListViews() {
        List.setModel(listModel);
        List.setVisible(true);
        List.setBounds(20, 110, 360, 250);
        List.setBackground(Color.LIGHT_GRAY);
        List.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    }

    private void initTextViews() {
        textField.setVisible(true);
        textField.setBounds(20, 20, 150, 30);
    }

    public void show() {
        Frame.add(Panel);
        Frame.add(List);
        Panel.add(textField);
        Panel.add(translateButton);
        Frame.setVisible(true);
    }


}
