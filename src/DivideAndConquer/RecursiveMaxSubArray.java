package DivideAndConquer;

import java.util.Arrays;

public class RecursiveMaxSubArray {
	/*
	 * @param input int[] a containing integers
	 * @return int[] which is a subArray of input having maximum Sum
	 */
	static class Result {
		int startIndex;
		int endIndex;
		int maxSum;
		Result(int startIndex, int endIndex, int maxSum) {
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.maxSum = maxSum;
		}
	} // end class Result
	
	public static int[] getMaxSubArray(int[] input) {
		Result result = getRecursiveMaxSubArray(input, 0, input.length);
		return Arrays.copyOfRange(input, result.startIndex,result.endIndex);
	}
	
	private static Result getRecursiveMaxSubArray(int[] input,int startIndex,
			int endIndex) {
		if (startIndex == endIndex) {
			return new Result(startIndex, endIndex, input[startIndex]);
		}
		
		int mid = (startIndex + endIndex) / 2;
		
		Result leftResult = getRecursiveMaxSubArray(input, startIndex, mid);
		Result rightResult = getRecursiveMaxSubArray(input, mid + 1,endIndex);
		Result crossingResult = getCrossingMaxSubArray(input, startIndex, mid, endIndex);
		
		return compareResults(leftResult, rightResult, crossingResult);
	}
	
	private static Result getCrossingMaxSubArray(int[] input, int startIndex, int mid, int endIndex) {
		int leftStartIndex = mid;
		int leftBestSum = input[mid];
		int leftSum = leftBestSum;
		for (int i = leftStartIndex; i >= startIndex; i--) {
			leftSum += input[i];
			if (leftSum > leftBestSum) {
				leftBestSum = leftSum;
				leftStartIndex = i;
			}
		}
		int rightStartIndex = mid + 1;
		int rightBestSum = input[mid + 1];
		int rightSum = rightBestSum;
		for (int i = rightStartIndex; i <= endIndex; i++) {
			rightSum += input[i];
			if (rightSum > rightBestSum) {
				rightBestSum = rightSum;
				rightStartIndex = i;
			}
		}
		return new Result(leftStartIndex, rightStartIndex, leftBestSum + rightBestSum);
	}
	
	private static Result compareResults (Result first, Result second, Result third) {
		if (first.maxSum >= Math.max(second.maxSum, third.maxSum)) {
			return first;
		}
		if (second.maxSum >= Math.max(first.maxSum, third.maxSum)) {
			return second;
		}
		return third;
	}

} //end class recursiveMaxSubArray
