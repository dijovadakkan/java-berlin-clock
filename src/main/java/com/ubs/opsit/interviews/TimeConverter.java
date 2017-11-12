package com.ubs.opsit.interviews;

public interface TimeConverter {

    /**
     * Method is responsible to convert the given time to different clock standard.
     * @param aTime {@link String} of given format HH:MM:SS
     * @return converted clock time of {@link String}
     */
    String convertTime(String aTime);

}
