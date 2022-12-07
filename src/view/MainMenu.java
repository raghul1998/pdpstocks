package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class for the main menu of the gui.
 */
public class MainMenu extends JFrame {
  private JPanel mainMenuPanel;
  private JButton createPortfolioButton;
  private JButton importPortfolioButton;
  private JButton flexibleButton;
  private JButton inflexibleButton;
  private JLabel importLabel;
  private JButton passiveInvestmentPortfolioButton;

  /**
   * Constructor for the main menu class.
   */
  public MainMenu() {
    importLabel.setVisible(false);
    inflexibleButton.setVisible(false);
    flexibleButton.setVisible(false);
    createPortfolioButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame createPortfolio = new StockDetails();
        createPortfolio.setVisible(true);
        MainMenu.this.dispose();
      }
    });
    this.setTitle("Main Menu");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(mainMenuPanel);
    this.pack();
    this.setSize(500, 200);
    this.setLocationRelativeTo(null);
    importPortfolioButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        importLabel.setVisible(true);
        inflexibleButton.setVisible(true);
        flexibleButton.setVisible(true);
      }
    });
    inflexibleButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the inflexible button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame inflexible = new InflexibleMenu();
        inflexible.setVisible(true);
        MainMenu.this.dispose();
      }
    });
    flexibleButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the Flexible button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame flexible = new FlexibleMenu();
        flexible.setVisible(true);
        MainMenu.this.dispose();
      }
    });
    passiveInvestmentPortfolioButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the passive Investment button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame passive = new PassiveInvestment(null);
        passive.setVisible(true);
        MainMenu.this.dispose();
      }
    });
  }
}
