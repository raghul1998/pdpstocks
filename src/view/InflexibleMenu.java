package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class for all inflexible menu functions.
 */
public class InflexibleMenu extends JFrame {
  private JPanel inflexibleMenu;
  private JButton viewCompositionButton;
  private JButton viewTotalValueButton;
  private JButton viewPortfolioPerformanceButton;
  private JButton goBackToTheButton;

  InflexibleMenu() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(inflexibleMenu);
    this.setTitle("Inflexible Portfolio Menu");
    this.pack();
    this.setSize(1000, 500);
    this.setLocationRelativeTo(null);
    viewCompositionButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the view composition button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        DisplayResult displayResult = new DisplayResult(2);
        displayResult.setVisible(true);
        InflexibleMenu.this.dispose();
      }
    });
    viewTotalValueButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the view total value button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        DisplayResult displayResult = new DisplayResult(3);
        displayResult.setVisible(true);
        InflexibleMenu.this.dispose();
      }
    });
    viewPortfolioPerformanceButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the portfolio performance button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        DisplayResult displayResult = new DisplayResult(4);
        displayResult.setVisible(true);
        InflexibleMenu.this.dispose();
      }
    });
    goBackToTheButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the back to menu button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame menu = new MainMenu();
        menu.setVisible(true);
        InflexibleMenu.this.dispose();
      }
    });
  }

}
