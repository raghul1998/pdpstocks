package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class for flexible menu functions.
 */
public class FlexibleMenu extends JFrame {
  private JButton buySharesButton;
  private JButton sellSharesButton;
  private JButton viewCompositionButton;
  private JButton viewTotalValueButton;
  private JButton viewPortfolioPerformanceButton;
  private JPanel flexibleMenu;
  private JButton investDollarCostAveragingButton;
  private JButton goBackToTheButton;
  private JButton reBalancePortfolioButton;

  FlexibleMenu() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(flexibleMenu);
    this.setTitle("Flexible Portfolio Menu");
    this.pack();
    this.setSize(800, 600);
    this.setLocationRelativeTo(null);
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
        FlexibleMenu.this.dispose();
      }
    });
    buySharesButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the buy button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        DisplayResult displayResult = new DisplayResult(0);
        displayResult.setVisible(true);
        FlexibleMenu.this.dispose();
      }
    });
    sellSharesButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the sell button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        DisplayResult displayResult = new DisplayResult(1);
        displayResult.setVisible(true);
        FlexibleMenu.this.dispose();
      }
    });
    viewCompositionButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the view compostion button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        DisplayResult displayResult = new DisplayResult(2);
        displayResult.setVisible(true);
        FlexibleMenu.this.dispose();
      }
    });
    viewPortfolioPerformanceButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the view portfolio performance button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        DisplayResult displayResult = new DisplayResult(4);
        displayResult.setVisible(true);
        FlexibleMenu.this.dispose();
      }
    });
    investDollarCostAveragingButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the dollar cost averaging button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        DisplayResult displayResult = new DisplayResult(5);
        displayResult.setVisible(true);
        FlexibleMenu.this.dispose();
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
        FlexibleMenu.this.dispose();
      }
    });
    reBalancePortfolioButton.addActionListener(new ActionListener() {
      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        DisplayResult displayResult = new DisplayResult(6);
        displayResult.setVisible(true);
        FlexibleMenu.this.dispose();
      }
    });
  }
}
