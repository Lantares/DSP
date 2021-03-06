/*****************************************************************
	程序说明：定义复数

	最后修改时间：2005-09-16
	
	作者：梁延研 澳门科技大学
*****************************************************************/

class Complex {
	
	public double re;
	public double im;
	
	Complex(){
		this.re = 0;
		this.im = 0;
	}
	Complex(double re){
		this.re = re;
		this.im = re;
	}
	Complex(double re,double im){
		this.re = re;
		this.im = im;
	}
	
	public void setRE(double re){
		this.re = re;
	}
	public void setIM(double im){
		this.im = im;
	}
	public double getRE(){
		return this.re;
	}
	public double getIM(){
		return this.im;
	}
	
	//复数加法	
	Complex Add(Complex c1,Complex c2){
		Complex c = new Complex(0,0);
		c.re = c1.re+c2.re;
		c.im = c1.im+c2.im;
		return c;
		}
	//复数减法
	Complex Sub(Complex c1,Complex c2){
		Complex c = new Complex(0,0);
		c.re = c1.re-c2.re;
		c.im = c1.im-c2.im;
		return c;
	}
	//复数乘法
	Complex Mul(Complex c1,Complex c2){
		Complex c = new Complex(0,0);
		c.re = c1.re*c2.re - c1.im*c2.im;
		c.im = c1.re*c2.im + c2.re*c1.im;
		return c;
	}
	
}