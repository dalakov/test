package pack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.*;

public class Solution {
	
	private static final boolean DEBUG = true;
	private static void debug(String s) { if (DEBUG) logger.log(Level.INFO, s); }
	private static void debug(int[] arr) {
		if (DEBUG) {
			StringBuilder s = new StringBuilder();
			for (int aX : arr) s.append(aX).append(' ');
			logger.log(Level.INFO, s::toString);
		}
	}
	private static void debug(int[] arr, @SuppressWarnings("SameParameterValue") boolean printInd) {
		if (DEBUG) {
			StringBuilder s = new StringBuilder();
			for (int i=0;i<arr.length;++i) {
				if (arr[i]!=0) {
					if (printInd) s.append(i).append(':');
					s.append(arr[i]).append(' ');
				}
			}
			logger.log(Level.INFO,  s::toString);
		}
	}
	
	private static int fN;
	private static int fO;
	private static int fM;
	private static int[] fNUM;
	private static int[] fOP;
	private static int fW;
	private static final int MIN=0;
	private static final int MAX=999;

	private static Logger logger = Logger.getLogger(Solution.class.getName());

	private static void scan() {
		
		Scanner sc =new Scanner(System.in);
		
		if (sc.hasNextInt()) fN = sc.nextInt();
		if (sc.hasNextInt()) fO = sc.nextInt();
		if (sc.hasNextInt()) fM = sc.nextInt();
		debug("fN = "+ fN);
		debug("fO = "+ fO);
		debug("fM = "+ fM);
		
		fNUM = new int[fN];
		fOP = new int[fO];
		
		for (int i = 0; i< fNUM.length; ++i) {
			if (sc.hasNextInt()) fNUM[i] = sc.nextInt();
		}
		for (int i = 0; i< fOP.length; ++i) {
			if (sc.hasNextInt()) fOP[i] = sc.nextInt();
		}

		if (sc.hasNextInt()) fW = sc.nextInt();

		debug(fNUM);
		debug(fOP);
		debug("fW = "+ fW);
	}


    private static int calc0(int[] num, int[] n0) {

        for (int aNUM2 : fNUM) {
            int ind100 = 100 * aNUM2;
            for (int aNUM : fNUM) {
                int ind10 = 10 * aNUM;
                for (int aNUM1 : fNUM) {
                    int ind = ind100 + ind10 + aNUM1;
                    int m;
                    if (ind100 != 0) m = 3;
                    else if (ind10 != 0) m = 2;
                    else m = 1;
                    num[ind] = m;
                    n0[ind] = m;
                    if (fW == ind) return m;
                }
            }
        }

        for (int aNUM : fNUM) {
            int ind10 = 10 * aNUM;
            for (int aNUM1 : fNUM) {
                int ind = ind10 + aNUM1;
                int m;
                if (ind10 != 0) m = 2;
                else m = 1;
                num[ind] = m;
                n0[ind] = m;
                if (fW == ind) return m;
            }
        }

        for (int ind : fNUM) {
            num[ind] = 1;
            n0[ind] = 1;
            if (fW == ind) return 1;
        }

        debug(num,true);
        debug(n0,true);
        return -1;
    }


	private static int calc() {

        int[] num = new int[MAX+1];
        int[] n0 = new int[MAX+1];

        int c0 = calc0(num, n0);
        if (c0!=-1) return c0;

        int qOp = (fM -2)/2;
		debug ("qOp = "+qOp);
				
		for (int m=1; m<=qOp; m++) {
			for (int aOP : fOP) {
				for (int i = 0; i < num.length; i++) {
					if (num[i] != 0)
						for (int j = 0; j < n0.length; j++)
							if (n0[j] != 0) {
								int r = MIN - 1;
								switch (aOP) {
									case 1:
										r = i + j;
										break;
									case 2:
										r = i - j;
										break;
									case 3:
										r = i * j;
										break;
									case 4:
										if (j != 0) r = i / j;
										break;
									default:
										throw new IllegalArgumentException("error flow");
								}
								if (r >= MIN && r <= MAX) {
									int mn = num[i] + n0[j] + 1;
									if (num[r] == 0) num[r] = mn;
									else num[r] = min(num[r], mn);
								}
							}
				}
			}
		}

		debug(num,true);

		if (num[fW]!=0) return num[fW]+1;
		return -1;
	}

	
	static int min(int a, int b) {
		if (a<b) return a;
		return b;
	}
	
	public static void main(String[] arg) {

        long start = System.currentTimeMillis();
		try {
			System.setIn(new FileInputStream("C:\\github_test\\test\\src\\main\\resources\\input.txt"));
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE,e.getMessage());
		}

		scan();
		
		int res = calc();
		debug("res="+res);
		String s = Integer.toString(res);
		logger.log(Level.INFO, s);

        long timeSpent = System.currentTimeMillis() - start;
        debug(" timeSpent " + timeSpent + " ms");
	}
	
}

