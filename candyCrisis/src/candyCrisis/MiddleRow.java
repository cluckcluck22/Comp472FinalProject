package candyCrisis;

public class MiddleRow {

	//This function checks how many candies occur an odd number of times in the middle row
	//It increases the heuristic value by 1 for every such candy
	
	public static int getMiddleRowH(String board) {
		
		
		
		String middleRow = board.substring(5,10);
		
		String differentCandies = "rbwygp";
		
		int value = 0;
				
		for(int i=0; i<differentCandies.length();i++) {
			
			//count how many times the candy appears in the middle row
			char candy = differentCandies.charAt(i);
			int counter = 0;
			for(int j=0; j<5; j++) {
				if(middleRow.charAt(j) == candy) counter++;
			}
			if (counter%2 == 1) {value++;}
		
		}
		
		return value;
	}
		
}
