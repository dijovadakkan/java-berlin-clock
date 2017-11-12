package com.ubs.opsit.interviews;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for {@link BerlinClockTimeConverter}
 */
public class BerlinClockTimeConverterTest {

    private static TimeConverter timeConverter;

    @BeforeClass
    public static void setup(){
        timeConverter = new BerlinClockTimeConverter();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertTimeForNull(){
      timeConverter.convertTime(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertTimeForInvalidString(){
         timeConverter.convertTime("00:00:00:00");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertTimeForInvalidHour(){
        timeConverter.convertTime("24:00:00");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertTimeForInvalidMin(){
        timeConverter.convertTime("00:60:00");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertTimeForInvalidSec(){
        timeConverter.convertTime("00:00:60");
    }

    @Test
    public void testConvertTimeForMidnight(){
        String midnight = "Y\r\n" +
                "OOOO\r\n" +
                "OOOO\r\n" +
                "OOOOOOOOOOO\r\n" +
                "OOOO";
        String berlinClockTime= timeConverter.convertTime("00:00:00");
        assertEquals(midnight,berlinClockTime);
    }

    @Test
    public void testConvertTimeForMidAfterNoon(){
        String midAfterNoon = "O\r\n" +
                "RROO\r\n" +
                "RRRO\r\n" +
                "YYROOOOOOOO\r\n" +
                "YYOO";
        String berlinClockTime= timeConverter.convertTime("13:17:01");
        assertEquals(midAfterNoon,berlinClockTime);
    }

    @Test
    public void testConvertTimeForBeforeMidnight(){
        String beforeMidNight = "O\r\n" +
                "RRRR\r\n" +
                "RRRO\r\n" +
                "YYRYYRYYRYY\r\n" +
                "YYYY";
        String berlinClockTime= timeConverter.convertTime("23:59:59");
        assertEquals(beforeMidNight,berlinClockTime);
    }


    @AfterClass
    public static void tearDown(){
        timeConverter = null;
    }
}
