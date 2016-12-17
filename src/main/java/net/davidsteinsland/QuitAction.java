package net.davidsteinsland;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class QuitAction extends AbstractAction {

  public QuitAction() {
    super("Quit");
  }

  public void actionPerformed(ActionEvent e) {
    System.exit(0);
  }
}
