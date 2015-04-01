package algo;

import algo.Point;

public class ECC {
	public static double EPS = 1e-5;
	// ECC equation: y^2 = x^3 + a*x + b mod p
	public static long a, b, p;
	// base Point
	public static Point basePoint;
	
	public static void setParam(long a, long b, long p, Point basePoint){
		ECC.a = a;
		ECC.b = b;
		ECC.p = p;
		ECC.basePoint = basePoint;
	}
	
	public static Point add(Point p, Point q){
		Point result = new Point();
		
		// System.out.println((q.x - p.x) + " " + ECC.p);
		long gradient = ECC.mod(((q.y - p.y) * inv_mod((q.x - p.x), ECC.p)), ECC.p); 
		// System.out.println("Gradient: " + gradient);
				
		result.x = ECC.mod((gradient * gradient - p.x - q.x), ECC.p);
		result.y = ECC.mod((gradient * (p.x - result.x) - p.y + ECC.p), ECC.p);
		
		// System.out.println("Result: " + result);
		return result;
	}
	
	public static long mod(long a, long b){
		return (a % b + b) % b;
	}
	
	public static long inv_mod(long a, long b){
		boolean isNegative = false;
		if(a == 1){
			return a;
		} else if(a == -1){
			return ECC.mod((b - 1) * 1, b);
		} else if(a < 0){
			isNegative = true;
			a = Math.abs(a);
		} 
		
		// 1/a mod b
		long vars[][] = new long[3][2];
		
		// init
		vars[0][0] = b;
		vars[0][1] = 0;
		vars[1][0] = a;
		vars[1][1] = 1;
		
		// System.out.println(vars[0][0] + " " + vars[1][1]);
		// System.out.println(vars[1][0] + " " + vars[1][1]);
				
		while(true){
			// System.out.println("inv mod");
			long d = vars[0][0] / vars[1][0];
			vars[2][0] = vars[0][0] - d * vars[1][0];
			vars[2][1] = vars[0][1] - d * vars[1][1];
			
			// System.out.println(vars[2][0] + " " + vars[2][1]);
			if(vars[2][0] == 1){ // done
				break;
			}
			
			// move 2 to 1, move 3 to 2
			vars[0][0] = vars[1][0];
			vars[0][1] = vars[1][1];
			
			vars[1][0] = vars[2][0];
			vars[1][1] = vars[2][1];
		}
		
		if(isNegative){
			return ECC.mod((b - 1) * vars[2][1], b);
		} else {
			return vars[2][1];
		}
	}
	
	public static Point doubles(Point a){
		long gradient = ((a.x * 3 + ECC.a) * inv_mod(2 * a.y, ECC.p)) % ECC.p;
		Point result = new Point();
		
		result.x = (gradient * gradient - 2 * a.x) % ECC.p;
		result.y = (gradient * (a.x - result.x) - a.y) % ECC.p;
		
		return result;
	}
	
	public static Point times(long a, Point b){
		Point result = b;
		
		if(a > 1){
			result = ECC.doubles(result);
		}
		
		for(long i=0;i<a-2;i++){
			result = ECC.add(result, b);
		}
		
		return result;
	}
	
	public static void main(String args[]){
		// System.out.println(ECC.inv_mod(970, 5));
	}
}
