package com.darkness


import com.darkness.utils.DarknessUtils
import spock.lang.Specification

class DarknessApplicationTests extends Specification {

    DarknessUtils dUtils

    //MapDB mockMapDB
    //MapRepo mapRepo
    def setup() {
        dUtils = new DarknessUtils()
    }

    def "get me a good name"() {
        given:
        def someName = "some guy"
        //def testName = ""

        when:
        def key = dUtils.giveMeAname()
        def testName = dUtils.generateRandomString(8)

        then:
        println("give me blah:" + key)
        println("generate make:" + testName)
        assert testName != key
    }
/*
	 * @Test public void CountMaps() { /*methods temp = mock(methods.class); Integer
	 * countMaps = temp.CountMaps();
	 * 
	 * Iterable<mapDB> iterator = mapRepo.findAll(); mapDB list = mock(mapDB.class);
	 * Object object = mock(Object.class);
	 * 
	 * when(iterator.iterator()).toString(); //this is to mock list with one
	 * element, adjust accordingly //when(iterator).hasNext()).thenReturn(true,
	 * false); //when(iterator).next().thenReturn(object);
	 * 
	 * 
	 * //String result = ""; Integer count = 0; //for(mapDB mapDB :
	 * mapRepo.findAll()) //{ // count++; //} System.out.println("our count is:" +
	 * count + " second count " + countMaps); }
	 */

}
