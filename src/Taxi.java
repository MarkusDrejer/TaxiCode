public class Taxi {

    private int drivingState = 0; // 0 = stop, 1 = driving, 2 = pause
    private long startTime;
    private double savedExecutionTime;
    private boolean wasPaused;
    private boolean freeRide;

    /**
     * Starts a taxi by setting the variable startTime to the current time in milliseconds,
     * and setting the driving state to "Driving".
     * If the taxi is already driving prints an error.
     */
    public void startTaxi(){
        if(drivingState == 1){
            System.out.println("Taxi is already driving");

        } else{
            startTime = System.currentTimeMillis();
            drivingState = 1;
            System.out.println("Started Taxi \n");
        }
    }

    /**
     * Used for both stopping a taxi and asking for a price,
     * checks if the taxi was ever paused and whether the taxi is still paused and calculates accordingly,
     * lastly if stop was chosen from the main menu, the attributes including the timer will be reset.
      */
    public void stop_getPrice(int menuChoice){
        if(drivingState == 0){
            System.out.println("Error, Taxi is stopped \n");
            return;
        }

        if(wasPaused){
            if(drivingState != 2){
                savedExecutionTime += calcExecution();
                startTime = System.currentTimeMillis();
            }
            new Receipt(savedExecutionTime, freeRide);

        } else {
            new Receipt(calcExecution(), freeRide);
        }

        if(menuChoice == 2) {
            drivingState = 0;
            startTime = 0;
            savedExecutionTime = 0;
            wasPaused = false;
            freeRide = false;
        }
    }

    /**
     * Alters the state of the taxi to it having been paused,
     * and calculates time and saves into savedExecutionTime,
     * sets driving state to "Paused".
     */
    public void pauseTaxi(){
        if(drivingState == 0){
            System.out.println("Cannot pause a Stopped Taxi \n");

        } else {
            savedExecutionTime += calcExecution();
            wasPaused = true;
            drivingState = 2;
            System.out.println("Paused Taxi \n");
        }
    }

    /**
     * Sets endTime to the current time in milliseconds and calculates the difference between start- and endTime.
     * Then in order to convert the milliseconds to seconds with 1 decimal, divides the difference by 100,
     * and performs another division by 10 while casting the result to a double.
     */
    private double calcExecution(){
        long endTime = System.currentTimeMillis();
        long temp = (endTime - startTime)/ 100;

        return (double) temp/ 10;
    }

    /**
     * FreeRide enable and sets to true if taxi isn't driving.
     */
    public void enableFreeRide(){
        if(drivingState != 0){
            System.out.println("Taxi is not currently eligible for free ride \n");
        } else {
            freeRide = true;
        }
    }

    /**
     * Overrides the Java toString method to write something specific when a taxi is printed.
     */
    @Override
    public String toString(){
        if(drivingState == 0){
            return "Stopped --- " + freeRide;
        } else if(drivingState == 1){
            return "Driving --- " + freeRide;
        } else {
            return "Paused --- " + freeRide;
        }
    }
}