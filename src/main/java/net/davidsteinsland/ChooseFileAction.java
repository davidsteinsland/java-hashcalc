package net.davidsteinsland;

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;

public class ChooseFileAction extends AbstractAction {

  private JTextField input;

  public ChooseFileAction() {
    super("...");
  }

  public void setInput(JTextField input) {
    this.input = input;
  }

  public void actionPerformed(ActionEvent e) {
    JFileChooser chooser = new JFileChooser();
    int returnVal = chooser.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      input.setText(chooser.getSelectedFile().toString());
    }
  }
}
