package com.jack.sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.sun.speech.freetts.*;

public class TextToSpeechApp extends JFrame {
    private JTextArea textArea;
    private JButton speakButton;
    private Voice voice;

    public TextToSpeechApp() {
        setTitle("文字朗读程序");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        speakButton = new JButton("朗读");
        speakButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speakText();
            }
        });
        add(speakButton, BorderLayout.SOUTH);

        initializeVoice();
    }

    private void initializeVoice() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
        } else {
            System.out.println("无法找到语音。请确保FreeTTS正确安装。");
        }
    }

    private void speakText() {
        String text = textArea.getText();
        if (voice != null && !text.isEmpty()) {
            voice.speak(text);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TextToSpeechApp().setVisible(true);
            }
        });
    }
}