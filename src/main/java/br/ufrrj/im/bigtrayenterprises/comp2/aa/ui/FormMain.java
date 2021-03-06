package br.ufrrj.im.bigtrayenterprises.comp2.aa.ui;

import br.ufrrj.im.bigtrayenterprises.comp2.aa.Engine;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.IOSource;
import br.ufrrj.im.bigtrayenterprises.comp2.aa.choices.Choice;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class FormMain implements IOSource, ActionListener {
    // There might be syncronization issues related to the way I'm duct taping
    // this GUI into the IOSource system.
    // but hopefully those were fixed by the semaphores

    public static void main(String[] args) {
        JFrame frame = new JFrame("FormMain");
        FormMain frm = new FormMain();
        frame.setContentPane(frm.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900, 600);


        Engine eng = new Engine();
        Engine.source = frm;
        eng.run();
    }

    public FormMain() {
        chosenChoice = 0;
        chosenSemaphore = new Semaphore(1);

        // needed because IDEA is stupid
        buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.Y_AXIS));


        // needed because the stupid design decisions never seem to end in this language
        DefaultCaret caret = (DefaultCaret) outputArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    @Override
    public void printString(String str) {
        // HACK(victor): JTextAreas do word wrapping, so leave the newlining to them
        outputArea.append(str.replace("\n", "") + "\n");
    }

    @Override
    public void addChoice(Choice choice) {
        JButton btn = new JButton(choice.getDescription());
        btn.addActionListener(this);
        btn.setActionCommand(Integer.toString(choice.getNumber())); // this is actually a terrible idea
        buttonsPane.add(btn);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setVisible(true);

        buttonsPane.revalidate();
        buttonsPane.repaint();

        chosenSemaphore.tryAcquire();
    }

    @Override
    public int getChoice() {
        try {
            chosenSemaphore.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return chosenChoice;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        chosenChoice = Integer.parseInt(actionEvent.getActionCommand());

        buttonsPane.removeAll();
        buttonsPane.revalidate();
        buttonsPane.repaint(); // this is swing/awt/whatever being stupid

        chosenSemaphore.release();
    }

    private Semaphore chosenSemaphore;
    private int chosenChoice;


    private JPanel mainPanel;
    private JTextArea outputArea;
    private JPanel buttonsPane;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        scrollPane1.setViewportView(outputArea);
        buttonsPane = new JPanel();
        buttonsPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(buttonsPane, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
