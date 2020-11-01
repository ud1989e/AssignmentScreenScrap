
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupHtmlExtract {
	public static void main(String args[]) throws IOException
	{
		
		try {
				
				HashMap<String, Integer> singleWordAndFreq= new HashMap<String, Integer>(); //hash map to store the single word with its frequency.
		        HashMap<String, Integer> twoWordsAndFreq= new HashMap<String, Integer>(); //hash map to store the two word with its frequency.
		        String [] stringArrayForDoubleWord = null;
		        String []storeSplitWords = null;
		        Document dom = Jsoup.connect("https://www.314e.com/").get(); //fetches the html content using jsoup API through get request/method.
		        for (Element element : dom.getAllElements()) 
		        { 
		        	if(!element.ownText().isEmpty())
		        	{
		        		storeSplitWords = element.ownText().split(" "); //store the elements html text by splitting as space delimiter and store it in array
		        		stringArrayForDoubleWord = new String[storeSplitWords.length];
				        for(int i=0;i<storeSplitWords.length-1;i++) // put the double word into array by considering adjacent word from single word array
				        {
				        	stringArrayForDoubleWord[i] = new String[1].toString();
				        	stringArrayForDoubleWord[i] = storeSplitWords[i] + " "+storeSplitWords[i+1];
				        }
					
				    }
		        	if(stringArrayForDoubleWord != null)
		        	{
					    for(String doubleWord :stringArrayForDoubleWord) //compare for redundancy if repeated increase count  for double word
					    {
					    	if(twoWordsAndFreq.containsKey(doubleWord)) 
					    	{
					    		twoWordsAndFreq.put(doubleWord,twoWordsAndFreq.get(doubleWord)+1);
					        }
					        else
					        {
					        	twoWordsAndFreq.put(doubleWord, 1);
					        }
					    }
		        	}
		        	if(storeSplitWords != null)
		        	{
					    for(String singleWord :storeSplitWords) //compare for redundancy if repeated increase count  for single word
					    {
					    	if(singleWordAndFreq.containsKey(singleWord)) 
					    	{
					    		singleWordAndFreq.put(singleWord,singleWordAndFreq.get(singleWord)+1);
				            }
					    	else      
				            {
					    		singleWordAndFreq.put(singleWord, 1);
				            }
					    }
		        	}
		       }   
		    //sort hashmap according to value
		    LinkedHashMap<String, Integer> map2 = 
		    		singleWordAndFreq.entrySet()
			       .stream()             
			       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			       .collect(Collectors.toMap(e -> e.getKey(), 
			                                 e -> e.getValue(), 
			                                 (e1, e2) -> null, // or throw an exception
			                                 () -> new LinkedHashMap<String, Integer>()));
		    //sort hashmap according to value
		    LinkedHashMap<String, Integer> map3 = 
		    		twoWordsAndFreq.entrySet()
			       .stream()             
			       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			       .collect(Collectors.toMap(e -> e.getKey(), 
			                                 e -> e.getValue(), 
			                                 (e1, e2) -> null, // or throw an exception
			                                 () -> new LinkedHashMap<String, Integer>()));
		
		 
			 //Display Top 10 single word
			 int topTenIndexforSingle=0;
			 System.out.println("Single Frequent Word");
			 System.out.println("----------------------");
			 for (Map.Entry mapElement : map2.entrySet()) 
			 { 
				
				 String key =
	   			  (String)mapElement.getKey();
	   			  
	   			 int value =
	   			  ((int)mapElement.getValue() );
	   			  if(key!= null) 
	   			  {
	   				 
	   				  System.out.println(key + " : " + value); 
	   				  topTenIndexforSingle=topTenIndexforSingle+1;
	   			  }
	   			  if(topTenIndexforSingle==10) 
	   			  {
	   				  break;
	   			  }
			 }
			 
			 System.out.println("double Frequent Word");
			 System.out.println("----------------------");
			 //Display Top 10 double word
			 int topTenIndexfordouble=0;
			 for (Map.Entry mapElement : map3.entrySet()) 
			 { 
				
				 String key =
		   			  (String)mapElement.getKey();
		   			  
		   			 int value =
		   			  ((int)mapElement.getValue() );
		   			 if(key!= null) 
		   			 {
		   				
		   				 System.out.println(key + " : " + value); 
		   				 topTenIndexfordouble=topTenIndexfordouble+1;
		   			 }
		   			 if(topTenIndexfordouble==10)
		   			 {
		   				 break;
		   			 }		  
			 
			 }
      }
	  catch(Exception e)
	  {
		System.out.println("Exception occured!!!!");
	  }
   }
}
