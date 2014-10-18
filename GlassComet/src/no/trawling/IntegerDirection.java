package no.trawling;

import java.util.HashMap;

import org.eclipse.jdt.core.compiler.InvalidInputException;

public class IntegerDirection extends Direction {
	
	HashMap<Integer, Long> values;
	
	public IntegerDirection(int windowSize, int maxHits, int timePenalty){
		
		values = new HashMap<Integer,Long>();
		this.windowSize = windowSize;
		this.maxHits = maxHits;
		this.timePenalty = timePenalty;
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
	
	private boolean updateRecord(Integer key){
		
		boolean isBlocked = false;
		Long currentTime = System.currentTimeMillis() / 1000l;
		Long lastHit = 0l;
		Long windowTail = currentTime - windowSize;
		Long windowSlide = (long) (windowSize / maxHits);
		
		if (values.containsKey(key)){
			lastHit = values.get(key);
			//Tile is still within window
			if (lastHit > windowTail && ((lastHit + windowSlide) < currentTime)){
				values.put(key, lastHit + windowSlide);
				isBlocked = false;
			}
			//Tile is behind the window
			else if (lastHit <= windowTail){
				values.put(key, (currentTime - windowSize) + windowSlide);
				isBlocked = false;
			}
			//Tile is past the window
			else{
				isBlocked = true;
			}
		}
		else{
			values.put(key, currentTime);
			isBlocked = false;
		}
		
		return isBlocked;
	}
}
