package Others;

public class Power{
	public double power(double x, int n) throws Exception{
		if(n == 0) return 1;
		if(x == 0) return 0;
		else if(x == 1) return 1;
		else if(x == -1) return n%2 == 0 ? 1 : -1;

		boolean is_n_positive = n > 0;
		boolean is_x_positive = x > 0;

		n = Math.abs(n);
		x = Math.abs(x);

		double res = powerHelper(x, n);

		if(!is_n_positive){
			return res == 0 ? 0 : 1/res;
		}

		if(!is_x_positive){
			return n%2 == 0 ? res : (-1) * res;
		}

		return res;
	}

	public double powerHelper(double x, int n) throws Exception{
		if(x == 0) return 0;
		else if(n == 1) return x;
		else if(n % 2 == 0){
			if(x > 1 && x > Double.MAX_VALUE / x) throw new Exception("Error");
			return powerHelper(x * x, n/2);
		}
		else{
			if(x > 1 && x > Double.MAX_VALUE/ (x * x)) throw new Exception("Error");
			return powerHelper(x * x, n/2) * x;
		}
	}
}