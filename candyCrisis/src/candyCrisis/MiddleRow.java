package candyCrisis;

public class MiddleRow {

	//This function checks how many candies occur an odd number of times in the middle row
	//It increases the heuristic value by 1 for every such candy
	
	public static int getMiddleRowH(String board) {
		
		String middleRow = board.substring(5,10);
		
		//Hardcoding differentCandies would also work and may be more efficient
		String differentCandies = "";
		for(int i=0; i<5;i++) {
			if(middleRow.charAt(i)!='_' &&  !differentCandies.contains(String.valueOf(middleRow.charAt(i)))) {
				differentCandies+=middleRow.charAt(i);
			}
		}
		//////////////////////////////////////////////////////////////////////
		
		int value = 0;
		for(int i=0; i<differentCandies.length();i++) {
			//System.out.println(differentCandies.charAt(i) + " occurs " + characterCount(differentCandies.charAt(i), middleRow) + " times");
			if(characterCount(differentCandies.charAt(i), middleRow) % 2 == 1) { //if candy appears odd number of times in middle row
				value++;
			}
		}
		
		return value;
	}
	
	private static int characterCount(char c, String s) {
		int counter = 0;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == c) counter++;
		}
		return counter;
	}
	
}
