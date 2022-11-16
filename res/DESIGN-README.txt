DESIGN DETAILS

This application is designed in a Model View Controller design pattern.
The model, view and the controller are in three different packages with their corresponding same name.
Also, there are two controllers, one that interacts with the model (will call it by CM) 
and another one that interacts with the view (will call it by CV).
This way, it is easy to maintain the code for the controller.

The overall connection between model, view and controller will look like 
MODEL <-- CM <-- CV --> VIEW.

The main function sits seperate in a Main class outside of all these packages and starts the program by creating
an object for the CV and passing the input and output streams and then by calling the start function of the CV.

Any input to the application will go to the CV, the CV will determine what to based on the input.
If the input only needs to render some data to the user, it will tell the view to display such view.
If the input needs to perform some operations, then CV will inform the model via CM which in-turn will go to the model.

The Model only exposes one interface with one method and an enum that tells the types of actions the model can perform.
The CM will send commands to the model by calling this one method and passing the type of actions as an argument.
The CM will receive the command from the CV based on the user inputs.
Similarly, the view exposes only one interface with one methond and an enum that tells the types of views that it has.
The CV will send commands to the view by calling this method and passing the type of view as an argument.

The application makes use of APIs to fetch the real time data of the stock when the user tries to buy the stocks.
But the application uses a pseudo-random value generator approach to generate the value of the stocks/shares when the user 
wants to view the value of the portfolio on a certain date.

All the data that are stored are in CSV format. The stock related data are stored in a file called 'StockData.csv' under
the 'data' directory. The supported stocks are stored in a file called 'SupportedStocks.csv' under data directory.

The portfolio details are stocked in a directory called 'userdata/user1'. 
The naming convention of the portfolio files is that the filename has the prefix 'pf_' followed by the name of the portfolio.
The portfolio details are also save as CSV.


DETAILED FEATURE DESCRIPTION

1. CREATE A PORTFOLIO

As the program starts, the application will display the main screen. Once the user clicks the create portfolio option,
the controller will inform the view to display the option for user to enter the name. If the name is already taken by a portfolio,
then the controller will inform the view to display the override option.
Once the user enters a valid name, an empty portfolio will be created. The user will then be prompted to buy shares to be added to the portfolio.
When the user chooses the same, and the stock which he/she wants to buy, the CV will pass the action to the CM, 
which will instruct the model to perform the API call. The model will fetch the data of that stock using the API and will
store the result in the StockData.csv file. At any point in time, this file will have information about only one stock which
was last queried or which is being queried right now.
The CV will now inform the view to display this data to the user. The user can enter the number of shares he/she would like to buy.
As the user buys the share, the CV will instruct model to keep updating the portfolio of the user.
Once the user is done buying shares, the CV will instruct the view to display the success message.
If the user goes back to main menu or exit the program without buying any shares, then the empty portfolio that was created
will be deleted by the model by the instruction of CV. 


2. VIEW/COMPOSITION OF THE PORTFOLIO

When the user buys a stock, model stock more than stock data to the portfolio. The portfolio file will have the following details.
- portfolio name
- unique ID for the portfolio
- stock purchase timestamp
- purchase ID
- timestamp of the last known value of the stock (the timestamp of the stock data that was shown to the user while buying)
- stock ticker symbol
- name of the stock
- number of shares purchased
- price of the share at the time of purchase

With all these data stored, it is really easy for the controller to fetch the data while showing the details of the portfolio.
In the main menu, if the user clicks the 'View Portfolio' option, the list of all the available portfolio's will be displayed
to the user. When the user chooses a particular portfolio, a screen as mentioned below will be displayed. The view basically shows
all the data about that portfolio and the price details at the time of purchase.

NAME PORTFOLIO COMPOSITION - Created on 2022-11-02

Name (Symbol)    Quantity        Price of each share     Total Value

Microsoft (MSFT)         123     $227.85         $28025.55
Apple (AAPL)     231     $150.38         $34737.78
Tesla (TSLA)     365     $227.1  $82891.5
JPMorgan Chase (JPM)     231     $128.25         $29625.75

Total Portfolio Value as on 2022-11-02: $175280.58



3. VALUE OF PORTFOLIO ON CERTAIN DATE

When the user chooses this option in the main menu, the list of portfolio's will be displayed from which user can choose one.
When the user chooses one, the user will be prompted to enter the date for which he/she wants to view the value. The supported
dates by the application is from Jan 1st 2000 till the current date. Any dates before the year 2000 or future dates will be
considered invalid. The application uses data time with format validator for this. If the date entered is same as the date at
which the stocks were bought, the application will fetch the data from the portfolio and will display the same.
If the date is different, then the application uses a pseudo-random approach to determine the value of the stocks and shows 
the data to the user. A pseudo-random approach meaning, the values are random but will always be same for the given date.
This intelligence is instilled to the application by the authors themselves.
