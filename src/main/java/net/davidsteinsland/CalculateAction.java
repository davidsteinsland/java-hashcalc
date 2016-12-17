package net.davidsteinsland;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.awt.event.ActionEvent;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

import java.util.Map;
import java.util.HashMap;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class CalculateAction extends AbstractAction implements PropertyChangeListener {

  public static final int FILE = 0;

  public static final int STRING = 1;

  private JTextField input;

  private JCheckBox[] checkboxes;

  private JTextField[] fields;

  private int type = FILE;

  public CalculateAction() {
    super("Calculate");
  }

  public void setInputs(JTextField input, JCheckBox[] checkboxes, JTextField[] fields) {
    this.input = input;
    this.checkboxes = checkboxes;
    this.fields = fields;
  }

  public void setInputType(int type) {
    this.type = type;
  }

  private byte[] readFile(String file) {
    try (FileInputStream fis = new FileInputStream(new File(file))) {
      byte[] result = new byte[fis.available()];
      fis.read(result);
      return result;
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public void actionPerformed(ActionEvent e) {
    setEnabled(false);

    String inputText = input.getText();

    if (inputText.length() == 0) {
      System.err.println("Input text is empty");
      setEnabled(true);
      return;
    }

    byte[] plaintext;

    if (type == STRING) {
      plaintext = input.getText().getBytes();
    } else {
      plaintext = readFile(input.getText());
    }

    if (plaintext == null || plaintext.length == 0) {
      System.err.println("Byte array is empty");
      setEnabled(true);
      return;
    }

    Map<String, JTextField> map = new HashMap<>();

    for (int i = 0; i < checkboxes.length; i++) {
      if (!checkboxes[i].isSelected()) {
        continue;
      }
      String t = checkboxes[i].getText();
      map.put(t, fields[i]);
    }

    if (map.size() == 0) {
      System.err.println("No algorithms selected");
      setEnabled(true);
      return;
    }

    HashTask task = new HashTask(plaintext, map);
    task.addPropertyChangeListener(this);
    task.execute();
  }

  public  void propertyChange(PropertyChangeEvent evt) {
    if ("state".equals(evt.getPropertyName())) {
      if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
        setEnabled(true);
      }
    }
  }
}
