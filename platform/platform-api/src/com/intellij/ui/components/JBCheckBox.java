package com.intellij.ui.components;

import com.intellij.ui.ComponentWithAnchor;

import javax.swing.*;
import java.awt.*;

/**
 * @author evgeny.zakrevsky
 */
public class JBCheckBox extends JCheckBox implements ComponentWithAnchor {
  private JComponent anchor;

  public JBCheckBox() {
    super();
  }

  public JBCheckBox(Icon icon) {
    super(icon);
  }

  public JBCheckBox(Icon icon, boolean selected) {
    super(icon, selected);
  }

  public JBCheckBox(String text) {
    super(text);
  }

  public JBCheckBox(Action a) {
    super(a);
  }

  public JBCheckBox(String text, boolean selected) {
    super(text, selected);
  }

  public JBCheckBox(String text, Icon icon) {
    super(text, icon);
  }

  public JBCheckBox(String text, Icon icon, boolean selected) {
    super(text, icon, selected);
  }


  @Override
  public JComponent getAnchor() {
    return anchor;
  }

  @Override
  public void setAnchor(JComponent anchor) {
    if (anchor != this) {
      this.anchor = anchor;
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return anchor == null ? super.getPreferredSize() : anchor.getPreferredSize();
  }
}
