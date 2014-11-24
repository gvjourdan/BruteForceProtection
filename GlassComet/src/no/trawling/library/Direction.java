package no.trawling.library;

public class Direction {
	//time in seconds
	int windowSize = 0;
	//Number of hits that can occur in @value windowSize
	int maxHits = 0;
	int countHits = 0;
	int countBlocks = 0;
	int timePenalty = 0;
	
	public int getHitsCount(){
		return countHits;
	}
	
	public int getBlocksCount(){
		return countBlocks;
	}
}
