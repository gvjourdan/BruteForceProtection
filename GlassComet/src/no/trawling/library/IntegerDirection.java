package no.trawling.library;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jdt.core.compiler.InvalidInputException;

public class IntegerDirection extends Direction {
	
	private ConcurrentHashMap<Integer, Long> values;
	
	public IntegerDirection(int windowSize, int maxHits, int timePenalty){
		
		values = new ConcurrentHashMap<Integer,Long>();
		this.windowSize = windowSize;
		this.maxHits = maxHits;
		this.timePenalty = timePenalty;
	}
	
	public int getNumberOfRecords(){
		return values.size();
	}
	
	public boolean isBlocked(Integer key) throws InvalidInputException, IllegalStateException{
		boolean isBlocked = false;
		if (0 == windowSize || 0 == maxHits){
			//These values can't be zero
			throw new IllegalStateException();
		}
		else if(null == key){
			//Value supplied isn't valid
			throw new InvalidInputException();
		}
		countHits += 1;
		isBlocked = updateRecord(key);
		if (isBlocked == true){
			countBlocks += 1;
		}
		return isBlocked;
	}
	
	public void cleanUp(){
		Iterator<Entry<Integer, Long>> it = values.entrySet().iterator();
		Long currentTime = System.currentTimeMillis() / 1000l;
		Long windowTail = currentTime - windowSize;
		
		while (it.hasNext()) {
	        Map.Entry<Integer, Long> pairs = (Map.Entry<Integer, Long>)it.next();
	        if ((Long)pairs.getValue() < windowTail){
	        	values.remove(pairs.getKey());
		        it.remove(); // avoids a ConcurrentModificationException
	        }
	    }
	}
	
	private boolean updateRecord(Integer key){
		
		boolean isBlocked = false;
		Long currentTime = System.currentTimeMillis() / 1000l;
		Long lastHit = 0l;
		Long windowTail = currentTime - windowSize;
		Long windowSlide = (long) (windowSize / maxHits);
		
		if (values.containsKey(key)){
			lastHit = values.get(key);
			if (null == lastHit){
				values.put(key, currentTime - windowSize + windowSlide);
				isBlocked = false;
			}
			//Tile is still within window
			else if (lastHit > windowTail && ((lastHit + windowSlide) < currentTime)){
				values.put(key, lastHit + windowSlide);
				isBlocked = false;
			}
			//Tile is behind the window
			else if (lastHit <= windowTail){
				values.put(key, currentTime - windowSize + windowSlide);
				isBlocked = false;
			}
			//Tile is past the window
			else if(lastHit > currentTime){
				isBlocked = true;
			}
			//will be blocked
			else{
				values.put(key, currentTime + timePenalty);
				isBlocked = true;
			}
		}
		else{
			values.put(key, currentTime - windowSize + windowSlide);
			isBlocked = false;
		}
		
		return isBlocked;
	}
}
