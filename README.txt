README

Please run the jar file (java -jar 'filename') as an administrator (possibly in a windows machine).
The system on which the application is running should have internet connection.
If you want to run the program using the submitted code, please go to the folder 
'Code written by us for this assignment' load the project using IntelliJ and
run the main function.

Rebalancing the portfolio feature has been implemented completely. The rebalance feature can be accessed
via both the text based interface and GUI. 
I have to notify this design restriction. Whenever a buy or sell
transaction is performed, a new portfolio is created. So, when a portfolio is rebalanced, the value of the newly
created portfolio needs to be checked instead of the old one. Also, the design maintains the buy history but not
the sell history. Because of this, if a share is sold, and if the value of the portfolio is checked on the date
before it is sold, it will not show the number of that was available before sale. It will only show the stocks that
are available after the sale.

CHANGE SUMMARY

There are two different controllers for text based interface and the GUI.

TEXT BASED INTERFACE CHANGES
1. The MVCCommandController.java is the file that acts as a controller for the text based interface.
2. Under the 'goToMenuOrCompo' method, added a new case for Rebalancing the portfolio. When the user inputs the rebalance
   option, this switch case will first call the existing method for composition of that portfolio on a certain data. This is to
   give insights to the user about the composition before rebalancing.
3. A new method called reBalancePortfolio(), is implemented in the controller, that will take care of taking in and validating
   the user inputs for the stock proportions.
4. A new helper method called isThereOnlyOneStockOnThisDate() is added in the controller that will tell if there is only one
   stock available on that data.
5. After the rebalance is done, the success message is shown to the user.

GUI CHANGES
1. As per original design, the controller for GUI is implemented in the VIEW itself.
2. In the FlexibleMenu.java file, added a new button for 'Rebalance Portfolio' and added a action listener for that button.
3. When the button is clicked, the action listener takes the control to DisplayResults.java file. The DisplayResults.java 
   file in the VIEW is responsible for dispatching to different files/features for the GUI based on the user input.
   In this file, based on the user input, the program will call the view composition menu and then the new rebalance object
   will be called.
4. The view composition is shown to the user for information purpose before rebalance so that the user knows what stocks are
   available.
5. To rebalance, a new file called Rebalance.java is created in which the rebalance feature is implemented. The class exposes
   certain functions which is called by the DisplayResults.java file.
6. This Rebalance.java takes in fields like portfolio path, date and total value in the constructor. 
   The setBalance public function is exposed which when called displays a new panel for getting the user data for proportions
   of each stock.
7. Once the user enters the stock proportios, the input is validated and a private method rebalanceFinal is called to complete
   the balancing process.
   
OTHER CHANGES AND BUG FIXES RELATED TO THIS FEATURE
1. In the DisplayResults.java, in the existing the view composition function, there was a bug where the number of shares 
   and the total value displayed was not giving right values all the time.
2. The sellPortfolio() method in PortfolioImpl.java file has been modified to take in number of stocks to be sold as double
   instead of an integer. Its corresponding interface is also modified. Another function related to sell stocks called 
   removeDateNum() in the file Portfolio.java has been modified the same as above.
3. The commission fee was hardcoded to 1 dollar and is not shown to the user. This is modifed to 0 so that there is no confusion
   while displaying the total value and composition of a portfolio.
4. When a sell is made, at a particular case, runtime error was thrown and this was due to not handling the for loop index in the
   removeDateNum() in Portfolio.java file. This has been fixed.
5. The saveFile() method is CreateXML.java file is modified to return a string value (name of the new portfolio). Earlier it was returing
   void. This is useful for GUI to display the new portfolio name.
6. While displaying the value and composition, the proportion column was displaying wrong values. Fixed them in examinePortfolioComposition()
   method and viewCompo() method in PortfolioCompositionExaminer.java and DisplayResult.java files respectively.
