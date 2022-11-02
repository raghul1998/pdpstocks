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