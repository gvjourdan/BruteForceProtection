package no.trawling;

import java.util.HashMap;

import org.eclipse.jdt.core.compiler.InvalidInputException;

public class StringDirection extends Direction{
	
	HashMap<String, Long> values;
	
	public StringDirection(int windowSize, int maxHits, int timePenalty){
		
		values = new HashMap<String,Long>();
		this.windowSize = windowSize;
		this.maxHits = maxHits;
		this.timePenalty = timePenalty;
	}
	
	public boolean isBlocked(String key) throws InvalidInputException, IllegalStateException{
		boolean isBlocked = false;
		if (0 == windowSize || 0 == maxHits){
			//These values can't be zero
			throw new IllegalStateException();
		}
		else if(null == key || key.equals("")){
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
	
	private boolean updateRecord(String key){
		
		boolean isBlocked = false;
		Long currentTime = System.currentTimeMillis() / 1000l;
		Long lastHit = 0l;
		Long windowTail = currentTime - windowSize;
		Long windowSlide = (long) (windowSize / maxHits);
		
		if (values.containsKey(key)){
			lastHit = values.get(key);
			//Tile is still within window
			if (lastHit + windowSlide > windowTail && ((lastHit + windowSlide) < currentTime)){
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
				isBlocked = false;
			}
		}
		else{
			values.put(key, currentTime - windowSize + windowSlide);
			isBlocked = false;
		}
		
		return isBlocked;
	}
}
