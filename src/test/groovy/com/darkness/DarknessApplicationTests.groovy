package com.darkness

import com.darkness.db.MapDB
import com.darkness.db.MapRepo
import com.darkness.utils.DarknessUtils
import org.junit.Test
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import spock.lang.Specification

class DarknessApplicationTests extends Specification{

    DarknessUtils dUtils

    //MapDB mockMapDB
    //MapRepo mapRepo
    def setup(){
        dUtils = new DarknessUtils()
    }

    def "get me a good name"() {
        given:
            def someName = "some guy"
            //def testName = ""

        when:
           def key = giveMeKey()
           def testName = dUtils.generateRandomString(8)

        then:
           println("give me blah:" + key)
           println("generate make:" + testName)
           assert testName != key
    }
    def giveMeKey(){
        String alphabet = (('A'..'N')+('P'..'Z')+('a'..'k')+('m'..'z')).join()
        def length = 8
        def key = new Random().with {
            (1..length).collect { alphabet[ nextInt( alphabet.length() ) ] }.join()
        }
        return key
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
