import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/*

10
01
= NO

0101
1111
0101
0111
= YES

as long as each 1-cell has a complete row or column
except 1-cells in the last position (row or column)

0100
1110
0101
0111
= NO

 */

public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			char[][] mat = new char[n][];
			for (int i = 0; i < n; i++) {
				mat[i] = fs.next().toCharArray();
			}
			boolean[][] visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(visited[i], false);
			}
			for (int i = n - 1; i >= 0; i--) {
				for (int j = n - 1; j >= 0; j--) {
					if (mat[i][j] == '1') {
						boolean rowChecker = true;
						//check current row
						for (int k = j + 1; k < n; k++) {
							if (visited[i][k]) {
								break;
							}
							if (mat[i][k] != '1') {
								rowChecker = false;
								break;
							}
						}
						boolean colChecker = true;
						//check current column
						for (int k = i + 1; k < n; k++) {
							if (visited[k][j]) {
								break;
							}
							if (mat[k][j] != '1') {
								colChecker = false;
								break;
							}
						}
						if (rowChecker || colChecker) {
							for (int k = j; k >= 0; k--) {
								if (mat[i][k] == '1') {
									visited[i][k] = true;
								}
							}
							for (int k = i; k >= 0; k--) {
								if (mat[k][j] == '1') {
									visited[k][j] = true;
								}
							}
						} else {
							visited[i][j] = false;
						}
					}
				}
			}
			boolean possible = true;
			outer: for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (mat[i][j] == '1' && !visited[i][j]) {
						possible = false;
						break outer;
					}
				}
			}
			System.out.println(possible ? "YES" : "NO");
		}
		out.close();
	}

	static final Random rnd = new Random();
	
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
