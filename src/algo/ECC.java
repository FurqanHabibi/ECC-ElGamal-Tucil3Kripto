package algo;

import java.math.BigInteger;

import algo.Point;

public class ECC {
	public static double EPS = 1e-5;
	// ECC equation: y^2 = x^3 + a*x + b mod p
	public static BigInteger a, b, p;
	// base Point
	public static Point basePoint;
	
	public static void setParam(BigInteger a, BigInteger b, BigInteger p, Point basePoint){
		ECC.a = a;
		ECC.b = b;
		ECC.p = p;
		ECC.basePoint = basePoint;
	}
	
	public static Point add(Point p, Point q){
		Point result = new Point();
		
		if(p.x == q.x && p.y == q.y){
			return ECC.doubles(p);
		}
		
		BigInteger gradient = BigInteger.ZERO;
		
		if(p.x != q.x){
			// System.out.println((q.x - p.x) + " " + ECC.p);
			gradient = q.y.subtract(p.y).multiply( q.x.subtract(p.x).mod(ECC.p).modInverse(ECC.p)).mod(ECC.p);
			// System.out.println("Gradient: " + gradient);
		}
		
		result.x = gradient.multiply(gradient).subtract(p.x).subtract(q.x).mod(ECC.p);
		result.y = gradient.multiply(p.x.subtract(result.x)).subtract(p.y).add(ECC.p).mod(ECC.p);
		
		// System.out.println("Result: " + result);
		return result;
	}
	
	public static Point minus(Point p, Point q) {
		Point qMinus = new Point(q.x,  q.y.multiply(new BigInteger("-1")).mod(ECC.p));
		return ECC.add(p, qMinus);
	}
	
	public static Point doubles(Point a){
		BigInteger gradient = a.x.multiply(new BigInteger("3")).add(ECC.a).multiply(new BigInteger("2").multiply(a.y).modInverse(ECC.p)).mod(ECC.p);
		Point result = new Point();
		
		result.x = gradient.multiply(gradient).subtract(new BigInteger("2").multiply(a.x)).mod(ECC.p);		
		result.y = gradient.multiply(a.x.subtract(result.x)).subtract(a.y).mod(ECC.p);
		
		return result;
	}
	
	public static Point times(BigInteger a, Point b){
		Point result = b;
		
		if(a.equals(BigInteger.ZERO))
			return new Point(BigInteger.ZERO, BigInteger.ZERO);
		else if(a.equals(BigInteger.ONE)){
			return b;
		} else if(a.mod(new BigInteger("2")).equals(BigInteger.ZERO)){
			return ECC.times(a.divide(new BigInteger("2")), ECC.doubles(b));
		} else {
			return ECC.add(ECC.times(a.subtract(BigInteger.ONE), b), b);
		}
		
		/*
		for(BigInteger i=0;i<a-1;i++){
			//System.out.println("i: " + i + ", result: " + result);
			result = ECC.add(result, b);
		}
		
		return result;
		*/
	}
	
	public static BigInteger solveY(BigInteger x) {
		for (BigInteger i = BigInteger.ONE; 
				i.compareTo(ECC.p) < 0; 
				i = i.add(BigInteger.ONE)) {
			if (i.multiply(i).mod(ECC.p).equals(x.multiply(x).multiply(x).add(ECC.a.multiply(x)).add(ECC.b).mod(ECC.p))){
				return i;
			}
		}
		return new BigInteger("-1");
	}
	
	public static BigInteger k = new BigInteger("30");

	public static Point messageToPoint(BigInteger m) {
		BigInteger x,y;
		for (BigInteger i = BigInteger.ONE; 
				i.compareTo(k) < 0; 
				i = i.add(BigInteger.ONE)) {
			x = m.multiply(k).add(i);
			y = ECC.solveY(x);
			if (!y.equals(new BigInteger("-1"))) {
				return new Point (x,y);
			}
		}
		return new Point(new BigInteger("-1"), new BigInteger("-1"));
	}
	
	public static BigInteger pointToMessage(Point p) {
		return p.x.subtract(BigInteger.ONE).divide(new BigInteger(String.valueOf(k)));
	}
	
	public static void main(String args[]){

	}
}
