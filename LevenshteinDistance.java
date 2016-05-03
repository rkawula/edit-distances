// JAVA

public class LevenshteinDistance {
	
	public static int editDistance(String a, String b) {
		int i = a.length();
		int j = b.length();
		if (i == 0) {
			return j;
		}
		if (j == 0) {
			return i;
		}
		int subCost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
		int insert = editDistance(a, b.substring(0, j - 1)) + 1;
		int delete = editDistance(a.substring(0, i - 1), b) + 1;
		int substitute = editDistance(a.substring(0, i - 1),
				b.substring(0, j - 1)) + subCost;
		return Math.min(insert, Math.min(delete, substitute));
	}
	
	public static int memoizedEditDistance(String a, String b) {
		int[][] distanceTable = new int[a.length() + 1][b.length() + 1];
		for (int i = 0; i < distanceTable.length; i++) {
			for (int j = 0; j < distanceTable[i].length; j++) {
				distanceTable[i][j] = -1; // Sentinel value to indicate uncalculated entry.
			}
		}
		int output = lookupMemoizedEditDistance(distanceTable, a, b, a.length(), b.length());
		return output;
	}
	
	private static int lookupMemoizedEditDistance(int[][] d, String a, String b, int i, int j) {
		// Lookup
		if (d[i][j] > -1) {
			return d[i][j];
		}
		// Base cases
		if (i == 0) {
			d[i][j] = j;
		} else if (j == 0) {
			d[i][j] = i;
		// Recurse
		} else {
			int subCost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
			int delete = lookupMemoizedEditDistance(d,
					a, b, i - 1, j) + 1;
			int insert = lookupMemoizedEditDistance(d,
					a, b, i, j - 1) + 1;
			int substitute = lookupMemoizedEditDistance(d,
					a, b, i - 1, j - 1) + subCost;
			d[i][j] = Math.min(delete, Math.min(insert, substitute));
		}
		return d[i][j];
	}

}
