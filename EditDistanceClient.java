import java.util.Scanner;

public class EditDistanceClient {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the first string:");
		String first = scanner.next();
		System.out.println("Please enter the second string:");
		String second = scanner.next();
		System.out.println("The Levenshtein Distance from " + first + " to "
			+ second + " is " + LevenshteinDistance.memoizedEditDistance(first, second) + ".");
		scanner.close();
	}

}
