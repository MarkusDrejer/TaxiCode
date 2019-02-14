import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Scanner console = new Scanner(System.in);
    private ArrayList<Taxi> taxis = new ArrayList();

    /**
    * The contructor for the menu class which starts by adding all taxis to the arraylist,
    * then starts a do-while loop which takes an input from the user which defines where the what program does next,
    * it also keeps the program running until a certain input is registered. (in this case -1)
    */
    public Menu(){
        for(int i = 0; i < 10; i++){
            taxis.add(new Taxi());
        }

        int menuChoice;

        do {
            System.out.println("Welcome to Damn Fast Taxis \n");
            System.out.println("(1) Start a taxi");
            System.out.println("(2) Stop a taxi");
            System.out.println("(3) Pause a ride");
            System.out.println("(4) Ask for price");
            System.out.println("(5) Free ride");
            System.out.println("(6) Add taxi(s)");
            System.out.println("(0) Exit \n");
            System.out.print("Choose a <number> and hit “enter”: ");

            menuChoice = console.nextInt();                          //menuChoice is used later in modifyTaxi method in order to call the correct method.

            System.out.print("\n\n");

            if(menuChoice < 1 || menuChoice > 6){
                System.out.println("Choice is invalid \n");

            } else if(menuChoice == 6){
                System.out.println("How many taxis do you wish to add?");
                int amount = console.nextInt();
                addTaxi(amount);

            } else {
                modifyTaxi(menuChoice);
            }
        } while (menuChoice != 0);

    }

    /**
     * This method is accessed via the users choice in the constructor.
     * The list of all taxis in the arraylist is printed out first,
     * then another user-input is needed to specify which taxi will change state, based on the fist choice,
     * this is done by using a switch.
     */
    private void modifyTaxi(int menuChoice){
        for(int i = 0; i < taxis.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + taxis.get(i));
        }
        System.out.println("(0) Go Back \n");

        System.out.print("Choose a taxi: ");
        int taxiChoice = console.nextInt();                         //User chooses a taxi from the list. 0 to return to main menu.

        if(taxiChoice < 0 || taxiChoice > taxis.size()){
            System.out.println("Invalid taxi choice \n");
            modifyTaxi(menuChoice);

        } else if(taxiChoice == 0){
            System.out.println();

        } else {

            switch (menuChoice){                                    //Switch case statement that leads to a single method call based on earlier menuChoice
                case 1:
                    taxis.get(taxiChoice - 1).startTaxi();
                    break;
                case 2:
                    taxis.get(taxiChoice - 1).stop_getPrice(menuChoice);
                    break;
                case 3:
                    taxis.get(taxiChoice - 1).pauseTaxi();
                    break;
                case 4:
                    taxis.get(taxiChoice - 1).stop_getPrice(menuChoice);
                    break;
                case 5:
                    taxis.get(taxiChoice - 1).enableFreeRide();
                    break;
            }
        }
    }

    /**
     * Small method to add more taxis to the arralist via a for loop and an input.
     */
    private void addTaxi(int amount){
        for(int i = 0; i < amount; i++){
            taxis.add(new Taxi());
        }
    }
}