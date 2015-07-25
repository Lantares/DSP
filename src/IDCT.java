/******************************
	程序说明：余弦反变换

	最后修改时间：2005-09-16
	
	作者：梁延研
******************************/

import java.awt.*;

public class IDCT {

	// IDct点数
	int count;
	
	//循环变量
	int  i,j,k;
	
	//中间变量	
	Complex[] x;	
	double[] td;
	IDFT tempIDft;
	
	double d0,r0;
	
	public void setData(double[] fd,int r) {
		
		d0 = fd[0];
		r0 = r;
		
		//IDct的点数
		count = 1<<r;
		
		//分配空间
		x = new Complex[count*2];
		td = new double[count];
		
		//初始化
		for(i = 0; i < count*2; i++)
		{
			x[i] = new Complex();
		}
		
		for(i = 0; i < count; i++)
		{
			x[i].re = fd[i]*Math.cos(i*Math.PI/(count*2));
			x[i].im = fd[i]*Math.sin(i*Math.PI/(count*2));
		}
		
		//调用IDFT计算
		tempIDft = new IDFT();
		tempIDft.setData(x,r+1);
		x = tempIDft.getData();

	}
	
	public double[] getData() {
		
		double d = Math.sqrt(2)/Math.sqrt(count);
		d0 = (1/Math.sqrt(count)-d)*d0;
		
		//计算fd[t]
		for(i = 0; i < count; i++)
		{
			td[i] = d0+x[i].re*d*2*count;
		}
		return td;
		
	}
	
	//测试
	/*
	public static void main(String[] args) {
	
		double[] fd;
		double[] td;
		int i;
		int k = 4;
		fd = new double[k];
		td = new double[k];
		
		IDCT tempIDct = new IDCT();		
		
		fd[0] = 5;
		fd[1] = -2.230442497387663;
		fd[2] = 0;
		fd[3] = -0.15851266778110668;		
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(fd[i]+" ");
		}
		
		tempIDct.setData(fd,2);
		td = tempIDct.getData();
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(td[i]+" ");
		}	
	}
	*/
}