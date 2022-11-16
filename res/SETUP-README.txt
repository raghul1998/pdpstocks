SETUP FILE

Please run the jar file as an administrator (possibly in a windows machine).
The system on which the application is running should have internet connection.
No other dependencies.

NOTE: 
All the inputs to the application are in numbers unless specified.
If there are no 'more options' specified on a particular screen just press enter for more options.
The application will not create an empty portfolio.


STEPS TO CREATE A PORTFOLIO WITH 3 DIFFERENT STOCKS STOCKS

1. In the Main Menu, enter '1' to create a portfolio.
2. You will be prompted to enter the name for the portfolio. If the name already exists, the application will ask you if you want to override the already existing portfolio.
3. Once the portfolio is created, press '1' to buy stocks. If you don't buy any stocks and return to main menu or exit the application, the empty portfolio will be deleted.
4. You will be provided with a list of supported stocks. Choose one by inputting the number of that stock.
5. The application now will fetch the data of the stock like current price, the date/time at which the value is last known of. You can only buy shares only from that last known value.
   Now enter the number of shares you would like to buy (can only buy from 1 to 2147483647 shares).
   Application will not allow you to buy 0 shares or shares that are more than 2147483647.
6. Once the shares are bought, the application will ask you if you want to buy more shares. If you press 'Y', then the process repeats from step 4. 
   If you press 'N', then the application will show the portfolio is created message and take you to the Main Menu.
   Press 'Y' and repeat step 4 and 5 two times for different stocks.
   Once 3 different stocks are bought, press 'N' to exit to the Menu Menu and the portfolio will be created.


STEPS TO CREATE A PORTFOLIO WITH 2 DIFFERENT STOCKS STOCKS
The steps are the same as mentioned above but buy two different stocks.

COMPOSITON OF PORTFOLIO
1. In the Main Menu, press '2' to check the contents of any portfolio that was created.
2. The application will display the list of available portfolio's and will ask you for the input for which you would like to
   check the contents of. Input the number accordingly.
3. Then the application will show all the stocks in that portfolio along with the number of shares, it's price at the time
   of purchase and the total value of the portfolio at the time of purchase.
   

VALUE OF PORTFOLIO ON A CERTAIN DATE
1. In the Main Menu, press '3' to check the value of the portfolio on a certain date.
2. Once pressed, the application will display the list of available portfolio's and will ask you for the input for which you would like to
   check the contents of. Input the number accordingly.
3. Then the application will ask you the date for which you would like to know the value of. The supported date ranges are
   (from Jan 1st 2000 to the current date). Enter the date in the format YYYY-MM-DD as mentioned in the screen.
4. The application will show the details of the stocks and it's shares, it's price and the total value of the portfolio on the
   date mentioned by the user.
   

STOCKS SUPPORTED BY THE APPLICATION

1.  Apple 			(AAPL)
2.  Amazon 			(AMZN)
3.  Microsoft 		(MSFT)
4.  Tesla 			(TSLA)
5.  Meta 			(META)
6.  Walmart 		(WMT)
7.  Johnson 		(JNJ)
8.  JPMorgan Chase 	(JPM)
9.  Google 			(GOOG)
10. UnitedHealth 	(UNH)

The last known value of these stocks are detemined by the API call and will be displayed in the screen for the user.