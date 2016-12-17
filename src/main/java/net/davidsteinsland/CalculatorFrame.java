/*
 * Created by JFormDesigner on Wed Dec 14 17:48:14 CET 2016
 */

package net.davidsteinsland;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author David Steinsland
 */
public class CalculatorFrame extends JFrame {
  public CalculatorFrame() {
    initComponents();
  }

  private void dataFormatActionPerformed(ActionEvent e) {
    JComboBox cb = (JComboBox)e.getSource();
    String selected = (String)cb.getSelectedItem();

    if (selected.equals("String")) {
      contentPanel.remove(fileChooseButton);
    } else {
      contentPanel.add(fileChooseButton, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
    }

    ((CalculateAction)calculateAction).setInputType(selected.equals("String") ? CalculateAction.STRING : CalculateAction.FILE);

    contentPanel.revalidate();
    contentPanel.repaint();
  }

  private void initComponents() {
    String[] hashes = new String[]{
      "MD2",
      "MD4",
      "MD5",
      "SHA1",
      "SHA224",
      "SHA256",
      "SHA384",
      "SHA512",
      "SHA3",
      "RIPEMD128",
      "RIPEMD160",
      "RIPEMD256",
      "RIPEMD320",
      "Whirlpool",
      "Tiger",
      "GOST3411"
    };

    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - David Steinsland
    dialogPane = new JPanel();
    contentPanel = new JPanel();
    label1 = new JLabel();
    label2 = new JLabel();
    dataFormat = new JComboBox<>();
    dataInputField = new JTextField();
    fileChooseButton = new JButton();
    separator1 = new JSeparator();
    buttonBar = new JPanel();
    calculateButton = new JButton();
    quitButton = new JButton();
    calculateAction = new CalculateAction();
    quitAction = new QuitAction();
    chooseFileAction = new ChooseFileAction();

    //======== this ========
    setMinimumSize(new Dimension(1150, 680));
    setTitle("HashCalc");
    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());

    //======== dialogPane ========
    {
      dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

      // JFormDesigner evaluation mark
      dialogPane.setBorder(new javax.swing.border.CompoundBorder(
        new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
          "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
          javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
          java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

      dialogPane.setLayout(new BorderLayout());

      //======== contentPanel ========
      {
        contentPanel.setLayout(new GridBagLayout());
        // ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
        // ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
        // ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("Data Format");
        contentPanel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
          GridBagConstraints.CENTER, GridBagConstraints.BOTH,
          new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("Data:");
        contentPanel.add(label2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
          GridBagConstraints.CENTER, GridBagConstraints.BOTH,
          new Insets(0, 0, 5, 5), 0, 0));

        //---- dataFormat ----
        dataFormat.setModel(new DefaultComboBoxModel<>(new String[] {
          "File",
          "String"
        }));
        dataFormat.addActionListener(e -> dataFormatActionPerformed(e));
        contentPanel.add(dataFormat, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
          GridBagConstraints.CENTER, GridBagConstraints.BOTH,
          new Insets(0, 0, 5, 5), 0, 0));
        contentPanel.add(dataInputField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
          GridBagConstraints.CENTER, GridBagConstraints.BOTH,
          new Insets(0, 0, 5, 5), 0, 0));

        //---- fileChooseButton ----
        fileChooseButton.setAction(chooseFileAction);
        contentPanel.add(fileChooseButton, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
          GridBagConstraints.CENTER, GridBagConstraints.BOTH,
          new Insets(0, 0, 5, 0), 0, 0));
        contentPanel.add(separator1, new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0,
          GridBagConstraints.CENTER, GridBagConstraints.BOTH,
          new Insets(0, 0, 5, 0), 0, 0));
      }
      dialogPane.add(contentPanel, BorderLayout.CENTER);

      //======== buttonBar ========
      {
        buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
        buttonBar.setLayout(new GridBagLayout());
        ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
        ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

        //---- calculateButton ----
        calculateButton.setAction(calculateAction);
        buttonBar.add(calculateButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
          GridBagConstraints.CENTER, GridBagConstraints.BOTH,
          new Insets(0, 0, 0, 5), 0, 0));

        //---- quitButton ----
        quitButton.setAction(quitAction);
        buttonBar.add(quitButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
          GridBagConstraints.CENTER, GridBagConstraints.BOTH,
          new Insets(0, 0, 0, 0), 0, 0));
      }
      dialogPane.add(buttonBar, BorderLayout.SOUTH);
    }
    contentPane.add(dialogPane, BorderLayout.CENTER);
    pack();
    setLocationRelativeTo(getOwner());
    // JFormDesigner - End of component initialization  //GEN-END:initComponents

    JCheckBox[] hashCheckboxes = new JCheckBox[hashes.length];
    JTextField[] hashFields = new JTextField[hashes.length];

    int rowStart = 3;
    for (int i = 0; i < hashes.length; i++) {

      JCheckBox a = new JCheckBox(hashes[i]);
      a.setSelected(true);
      JTextField b = new JTextField();
      b.setEditable(false);

      contentPanel.add(a, new GridBagConstraints(0, rowStart + i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
      contentPanel.add(b, new GridBagConstraints(1, rowStart + i, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

      hashCheckboxes[i] = a;
      hashFields[i] = b;
    }

    ((CalculateAction)calculateAction).setInputs(dataInputField, hashCheckboxes, hashFields);
    ((ChooseFileAction)chooseFileAction).setInput(dataInputField);

  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner Evaluation license - David Steinsland
  private JPanel dialogPane;
  private JPanel contentPanel;
  private JLabel label1;
  private JLabel label2;
  private JComboBox<String> dataFormat;
  private JTextField dataInputField;
  private JButton fileChooseButton;
  private JSeparator separator1;
  private JPanel buttonBar;
  private JButton calculateButton;
  private JButton quitButton;
  private CalculateAction calculateAction;
  private QuitAction quitAction;
  private ChooseFileAction chooseFileAction;
  // JFormDesigner - End of variables declaration  //GEN-END:variables
}
