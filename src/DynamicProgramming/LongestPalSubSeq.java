package DynamicProgramming;

import java.util.*;

public class LongestPalSubSeq {
	public static void main(String[] args) {
		System.out.println (getLongestPalSubSeq("character"));
	}
	static class PalBound {
		int startIndex;
		int endIndex;
		String val;
		
		PalBound(int startIndex, int endIndex, String val) {
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.val = val;
		}
	}
	static String getLongestPalSubSeq(String input) {
		
		List<PalBound> secondLast = new ArrayList<PalBound>();
		List<PalBound> last = new ArrayList<PalBound>();
		secondLast.add(new PalBound(0, 0, "" + input.charAt(0)));
		for (int i = 1; i < input.length(); i++) {
			secondLast.add(new PalBound(i, i, "" + input.charAt(i)));
			if (input.charAt(i) == input.charAt(i-1)) {
				last.add(new PalBound(i - 1, i, input.substring(i -1, i + 1)));
			}
		}
		int maxSize = last.size() > 0 ? last.size() : 1;
		int currentSize = 3;
		String retVal;
		if (maxSize > 1) {
			retVal = last.get(0).val;
		} else {
			retVal = secondLast.get(0).val;
		}
		while ( currentSize < input.length()) {
				List<PalBound> currentList = calculateNext(secondLast, input);
				secondLast = last;
				last = currentList;
				if ((last.size() + secondLast.size()) == 0) {
					break;
				}
				if (last.size() > 0) {
					retVal = last.get(0).val;
				}
				currentSize++;
		}
		return retVal;
	}
	
  private static List<PalBound> calculateNext(List<PalBound> list, String input) {
  	List<PalBound> output = new ArrayList<PalBound>();
  	for(PalBound bound:list) {
  		calculate(bound, input, output);
  	}
  	return output;
  }
  
  private static void calculate(PalBound bound, String input, List<PalBound> output) {
  	int leftIndex = bound.startIndex - 1;
  	while ( leftIndex  >= 0) {
  		int rightIndex = bound.endIndex + 1;
  		while (rightIndex < input.length()) {
  			if (input.charAt(leftIndex) == input.charAt(rightIndex)) {
  				output.add(new PalBound(leftIndex, rightIndex, input.charAt(leftIndex) +  bound.val + input.charAt(rightIndex)));
  			}
  			rightIndex++;
  		}
  		leftIndex--;
  	}
  	
  }
}
