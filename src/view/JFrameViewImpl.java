package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.ControllerViewInteract;

public class JFrameViewImpl extends JFrame implements GUIView {
  private JLabel display;
  private JList
  private JButton button;
  private JTextField input;
  public JFrameViewImpl() {
    // constructor
    super();
    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new CardLayout());
    display = new JLabel("PORTFOLIO APPLICATION");

    JPanel pan = new JPanel();
    pan.add(display);
    pan.add("abv");
    this.add(pan);

    //pack();
    setVisible(true);
  }

  @Override
  public void addFeatures(ControllerViewInteract cviObj) {
  }
}
