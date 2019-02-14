import java.util.Scanner;

public class Receipt {

    private double executedTime;
    private double price = 0;
    private final double totalPerSecond = 2.25;
    private Scanner console = new Scanner(System.in);

    /**
     * Gets the executed time and the status of free ride when a receipt is created,
     * then based on whether or not free ride is enabled calculates the total for a ride, the default being 0,
     * and finishes with the user giving an input to continue.
     */
    public Receipt(double executedTime, boolean freeRide){
        this.executedTime = executedTime;

        if(!freeRide) {
            price = executedTime * totalPerSecond;
        }

        System.out.println(printReceipt(freeRide));
        console.nextLine();
        System.out.println();

    }

    /**
     * Returns a String with the receipt text, including the time, price and total of a ride,
     * adds the phrase "FREE RIDE" if this has been enabled on a taxi.
     */
    private String printReceipt(boolean freeRide){
        if(freeRide){
            System.out.println("---FREE RIDE---");
        }
        return "\nDamn Fast Taxis \n" +
                "--------------- \n" +
                "Time: " + executedTime + "\n" +
                "Price per second: " + totalPerSecond + " dollars \n" +
                "Total Price: " + price + "\n" +
                "Press any key to continue...";
    }
}