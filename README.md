# DeliBelli
This app was created to allow a user to track spending habits by allowing deposits(credits) and payments(debits) to be made and provide a menu show all transactions or filter by type. It also allows for specific reports to be ran such as Month to date, Year to Date, Previous year and even results filtered by the vendor.

### Setup
Instructions on how to set up and run the project using IntelliJ.

IntelliJ IDEA( Community Edition):
Running the Application in IntelliJ
Follow these steps to get your application running within IntelliJ IDEA:

- Open IntelliJ IDEA.
- Select "Open" and navigate to the directory where you cloned or downloaded the project.
- After the project opens, wait for IntelliJ to index the files and set up the project.
- Find the main class with the public static void main(String[] args) method.
- Right-click on the file and select 'Run 'YourMainClassName.main()'' to start the application.
- Technologies Used : Java 17.0.14

### User Stories

- As a user, I want a detailed menu with available options so I can craft my sandwich as desired, and I want the ability to cancel and restart my order at any time.
- HOME CLASS
    - create a user interface that has all menu options and takes info from the user directly
    - create a new order object for each option's selection
    - take care of checkout and calculate prices by size/toppings etc
    - displayUserInterface();


- As a user, I would like to have the ability to Customize My Sandwich, pick toppings and know the price based on what I am putting on it
- SANDWICH CLASS
    - takes in toppings, bread, toasted, toppings and extras
    - calculates price accordingly based on size
    - addToppings();
    - calculatePrice();


- As a user, I would like to have the option to choose my Drink and Chips and directly add them to my order with ease because it shouldn't be overcomplicated.
- DRINK/CHIP CLASSES
    - take in brand for chips | name and size for drink
    - getChipPrice();
    - getDrinkPrice();


- As a user, I would like to have my items stored somewhere like a cart for my New Order items so that I can easily keep track of what I have added and how much it costs
- ORDER CLASS
    - create lists to hold the results of each object //sandwiches,drinks,chips
    - loop through the stored items and return the total price for each one based on size
    - calculate the total price for all items, even duplicates
    - addSandwich();
    - getToasted();
    - addToppings
    - addDrink(); //Sprite, Juice, Water
    - addChips(); //Doritos, Lays, Cheetos
    - checkout();
    - cancelOrder();


- As a user, I would like an itemized receipt to be printed so that I can see the price, change, time/date of the transactions and all additions.
- ORDER FILE MANAGER CLASS
    - loop through and display all instances of an item and their prices
    - display detailed information like toppings and toasted
    - file writer to create a new file for each order and add content accordingly
    - format the info displayed onto the receipt file itself
    - saveOrder();

### Demo
Main Menu
![Application Screenshot](https://i.imgur.com/nEYMRLJ.png)
 
### Future Work
- Allow typos but not super invalid text (Chddar, but not Chfgr)


### Resources

- Professor (Raymond Maroun)'s GutHub
- Potato Sensei🥔


### Special Thank You
- to my professor Raymond for continuous support and guidance
- to my peers for providing academic support and companionship
- to myself 
