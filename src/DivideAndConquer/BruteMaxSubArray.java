package DivideAndConquer;

import java.util.Arrays;

public class BruteMaxSubArray {
	/*
	 * @param input int[] a containing integers
	 * @return int[] which is a subArray of input having maximum Sum
	 */
	public static int[] getMaxSubArray(int[] input) {
		int length = input.length;
		int maxSubArrayStartIndex = -1;
		int maxSubArrayEndIndex = -1;
		int maxSum = Integer.MIN_VALUE;
		// Loop Invariant: Before the loop gets evaluated for index i
		// input[maxSubArrayStartIndex..maxSubArrayEndIndex] 
		// is the maxSubArray of input[0..i-1]
		// Terminates when i = length of input array.
		for (int i = 0; i < length; i++) {
			int currentMaxSum = input[i];
			int sum = currentMaxSum;
			int currentEndIndex =  i;
			// Loop Invariant: Before the loop gets evaluated for the index j
			// input[i..currentEndIndex] is the maxSubArray of the array input[i..j-1]
			// with the restriction that startIndex of maxSubArray is fixed to i.
			// Terminates when j == length.
			for (int j = i + 1; j < length; j++) {
				sum += input[j];
				if (sum >= currentMaxSum) {
					currentMaxSum = sum;
					currentEndIndex = j;
				}
			} //end inner for
			if (currentMaxSum >= maxSum) {
				maxSubArrayStartIndex = i;
				maxSubArrayEndIndex = currentEndIndex;
				maxSum = currentMaxSum;
			}
		} //end outer for
		return Arrays.copyOfRange(input, maxSubArrayStartIndex, maxSubArrayEndIndex);
	}

}
