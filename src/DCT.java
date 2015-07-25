/******************************
	程序说明：余弦变换

	最后修改时间：2005-09-16
	
	作者：梁延研
******************************/

import java.awt.*;

public class DCT {

	// Dct点数
	int count;
	
	//循环变量
	int  i,j,k;
	
	//中间变量	
	Complex[] x;	
	double[] fd;
	DFT tempDft;
	
	public void setData(double[] td,int r) {
	
		//Dct的点数
		count = 1<<r;
		
		//分配空间
		x = new Complex[count*2];
		fd = new double[count];
		
		//初始化
		for(i = 0; i < count*2; i++)
		{
			x[i] = new Complex();
		}
		
		for(i = 0; i < count; i++)
		{
			x[i].re = td[i];
			x[i].im = 0;
		}
		
		//调用DFT计算
		tempDft = new DFT();
		tempDft.setData(x,r+1);
		x = tempDft.getData();

	}
	
	public double[] getData() {
	
		//计算fd[0]
		double d = 1/Math.sqrt(count);
		fd[0] = x[0].re*d;
		d *= Math.sqrt(2);
		
		//计算fd[t]
		for(i = 1; i < count; i++)
		{
			fd[i] = (x[i].re*Math.cos(i*Math.PI/(count*2)) + 
					x[i].im*Math.sin(i*Math.PI/(count*2))) * d;
		}
		return fd;
		
	}
	
	//测试
	/*
	public static void main(String[] args) {
	
		double[] td;
		double[] fd;
		int i;
		int k = 4;
		td = new double[k];
		fd = new double[k];
		
		DCT tempDct = new DCT();
		
		for(i = 0; i < k; i++)
		{
			td[i] = 1+i;
		}
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(td[i]+" ");
		}
		
		tempDct.setData(td,2);
		fd = tempDct.getData();
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(fd[i]+" ");
		}	
	}
	*/
}