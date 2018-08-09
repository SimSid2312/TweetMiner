package Model;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.Application;
import play.libs.Json;
import play.test.Helpers;
import services.TweetsService;
import services.TwitterApi;
import services.TwitterObject;
import static play.inject.Bindings.bind;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import mockClasses.PseudoTwitterHappy;
import static junit.framework.TestCase.assertEquals;

import static org.junit.Assert.*;
/**
 * @author pranav
 * This class test the various scenarios of TweetWords Model Class
 * @throws Exception
 */
public class TweetWordsModelUnitTest {
	
	static Application testApp;
    /**
     * @author pranav
     * Creates the environment for testing the TweetWords Model Class
     * by setting the testCase variable of TwitterObject as true
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
    	 TwitterObject.testCase = true;
    	 

    }
    /**
     * @author pranav
     *  Sets the testcase variable as false
     * @throws Exception
     */
    @AfterClass
    public static void tearDown() throws Exception {
    	TwitterObject.testCase = false;
    	TwitterObject.emotion=0;
    	Helpers.stop(TwitterObject.testApp);
    }

    /**
     * @author pranav
     * Tests that the method returns the word level statistics
     * @throws Exception 
     */
    @Test
    public void tweetWords() throws Exception {
    	
    	TwitterObject.emotion=1; //Making call to PseudoHappy class
    	CompletableFuture<ArrayNode> testNodeFuture = TweetsService.getTweets("dermicool", 10);
    	ArrayNode testNode = testNodeFuture.get();
    	Map<String,Long> actualMap=TweetWordsModel.tweetWords(testNode);
    	assertEquals(4, actualMap.size());
    	
       /* ArrayNode arrayNode = Json.newArray();


        ObjectNode tempTweetsObjectNode1 = Json.newObject();
        tempTweetsObjectNode1.put("tweetsText", "I am happy 🙂");
        ObjectNode tempTweetsObjectNode2 = Json.newObject();
        tempTweetsObjectNode2.put("tweetsText", "I am happy 🙂");

        arrayNode.add(tempTweetsObjectNode1);
        arrayNode.add(tempTweetsObjectNode2);


        Map<String, Long> expectedMap = new HashMap();
        expectedMap.put("I",Integer.toUnsignedLong(1));
        expectedMap.put("am",Integer.toUnsignedLong(1));
        expectedMap.put("happy",Integer.toUnsignedLong(1));
        expectedMap.put("🙂",Integer.toUnsignedLong(1));*/


        //Map<String, Long> actualMap = TweetWordsModel.tweetWords(arrayNode);
        
    }

    /**
     *      @author pranav
     *      Tests that the method computes word level statistics
     */
  /*  @Test
    public void findWordLevelStatistic() {
        List<String> tweetsList = Arrays.asList("I am happy 🙂", "🙂 I", "happy");

        Map<String, Long> expectedMap = new HashMap();
        expectedMap.put("I",Integer.toUnsignedLong(2));
        expectedMap.put("am",Integer.toUnsignedLong(1));
        expectedMap.put("happy",Integer.toUnsignedLong(2));
        expectedMap.put("🙂",Integer.toUnsignedLong(2));


        Map<String, Long> actualMap = TweetWordsModel.findWordLevelStatistic(tweetsList);
        assertEquals(4, actualMap.size());
    }*/
}