package com.ubs.opsit.interviews;

/**
 * Converts the given time to Berlin Uhr(clock) standard.
 */
public class BerlinClockTimeConverter implements TimeConverter {



    /**
     * Method is responsible to convert the given time to different clock standard.
     * @param aTime {@link String} of given format HH:MM:SS
     * @return converted clock time of {@link String}
     */
    @Override
    public String convertTime(String aTime) {
if(isValidTime(aTime)){
    Time time = populateTime(aTime);
    StringBuilder berlinClock = new StringBuilder();
String blinker = getBlinkerStatus(time.getSeconds());
String hoursOfDay = getBerlinHours(time.getHours());
String minutes = getBerlinMinutes(time.getMinutes());
    berlinClock.append(blinker).append(Constants.NEW_LINE_WINDOWS).append(hoursOfDay).append(Constants.NEW_LINE_WINDOWS).append(minutes);
    return berlinClock.toString();
}else{
    throw new IllegalArgumentException("Invalid time, check the input value.");
}
    }

    /**
     * To get the minute representation value for the berlin clock
     * @param minutes Minute value of time
     * @return Uhr representation of minutes
     */
    private String getBerlinMinutes(int minutes) {
        StringBuilder finalString =new StringBuilder();
        String firstRow = Constants.DEFAULT_BC_MIN_FIRST_ROW;
        String secondRow = Constants.DEFAULT_BC_MIN_SEC_ROW;
        int quotient = minutes / 5;
        int remainder = minutes%5;
        for(int i=1;i<=quotient;i++){
            if(i%3 == 0){
                firstRow =  firstRow.replaceFirst("O","R");
            }else {
                firstRow = firstRow.replaceFirst("O", "Y");
            }
        }
        for(int i=0;i<remainder;i++){
            secondRow =  secondRow.replaceFirst("O","Y");
        }
        return finalString.append(firstRow).append(Constants.NEW_LINE_WINDOWS).append(secondRow).toString();
    }

    /**
     * To get the hour representation value for the berlin clock
     * @param hours Hour value of the time
     * @return string value of first two rows of berlin clock
     */
    private String getBerlinHours(int hours) {
        StringBuilder finalString =new StringBuilder();
        String firstRow = Constants.DEFAULT_BC_HOUR;
        String secondRow = Constants.DEFAULT_BC_HOUR;
        int quotient = hours / 5;
        int remainder = hours%5;
      for(int i=0;i<quotient;i++){
          firstRow =  firstRow.replaceFirst("O","R");
      }
        for(int i=0;i<remainder;i++){
            secondRow =  secondRow.replaceFirst("O","R");
        }
        finalString.append(firstRow).append(Constants.NEW_LINE_WINDOWS).append(secondRow);
       return finalString.toString();
    }

    /**
     * To get the blinker value for the clock
     * @param seconds Seconds value of time
     * @return "Y" or "O"
     */
    private String getBlinkerStatus(int seconds) {

        if(seconds ==0 || seconds%2 ==0){
            return "Y";
        }else{
            return "O";
        }
    }

    /**
     * Validates if the given time is of correct syntax, hh:mm:ss
     * @param time string value
     * @return true if valid string else false
     */
    private boolean isValidTime(String time) {
        //Check for empty or null string value
        if(time==null || time.isEmpty()){
            return false;
        }else {
            String[] strArray = time.split(Constants.COLON_SYMBOL);
            //check if all the hour, minute and seconds value are present.
            if(strArray.length!=3){
                return false;
            }else {
                if(Integer.valueOf(strArray[0]) >23 || Integer.valueOf(strArray[0]) <0){
                    return false;
                }else if(Integer.valueOf(strArray[1]) >59 || Integer.valueOf(strArray[0]) <0){
                    return false;
                }else if (Integer.valueOf(strArray[2]) >59 || Integer.valueOf(strArray[0]) <0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Populates the Time object from the given String
     * @param aTime given time string
     * @return populated @{@link Time} object.
     */
    private Time populateTime(String aTime){
        Time time = new Time();
        String[] strArray = aTime.split(Constants.COLON_SYMBOL);
        time.setHours(Integer.valueOf(strArray[0]));
        time.setMinutes(Integer.valueOf(strArray[1]));
        time.setSeconds(Integer.valueOf(strArray[2]));
        return time;
    }
}
