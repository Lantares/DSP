/***********************************
	程序说明：斜变换(一次U系统)变换

	最后修改时间：2005-09-16
	
	作者：梁延研
***********************************/

import java.awt.*;

public class SLT {

	// Ust变换点数
	int count;
	
	//循环变量
	int  i,j,k;
	
	//中间变量
	int bfsize,p;	
	int r;
	
	double[] a,b,x1,x2,fd;	
	
	public void setData(double[] td,int r){
	
		this.r = r;
		
		// Ust加权系数coe
		double coe;
		
		//计算Ust变换的点数
		count = 1<<r;
		
		//分配空间
		a = new double[r-1];
		b = new double[r-1];
		x1 = new double[count];
		x2 = new double[count];
		fd = new double[count];
		
		//计算加权系数
		for(i = 0; i < (r-1); i++)
		{
			coe = 2<<i;
			a[i] = Math.sqrt(3/(4-(1/coe)*(1/coe)));
			b[i] = Math.sqrt((1-(1/coe)*(1/coe))/(4-(1/coe)*(1/coe)));
		}
		
		//将实域点写入x1
		for(i = 0; i < count; i++)
		{
			x1[i] = td[i];
		}
	}
	
	public double[] getData(){
	
		//蝶形运算
		for(k = 0; k < r; k++)
		{
			for(j = 0; j < 1<<k; j++)
			{
				bfsize = 1<<(r-k);
				for(i = 0; i < bfsize/2; i++)
				{			
					p = j*bfsize;
					x2[i+p] = x1[i+p]+x1[i+p+bfsize/2];
					x2[i+p+bfsize/2] = x1[i+p]-x1[i+p+bfsize/2];
				}
			}
			
			for(i = 0; i < count; i++)
			{
				x1[i] = x2[i];
			}
		}
	
		//U系统系数运算
		for(j = 0; j < (r-1); j++)
		{
		
			for(i = 0; i < count; i++)
			{	
				x2[i] = x1[i];
			}

			for(i = 0; i < 1<<(r-j-2); i++)
			{	
				bfsize = 4<<j;
				//x2[i*bfsize+1] = x1[i*bfsize+bfsize/2]*a[j] + x1[i*bfsize+1]*b[j];
				//x2[i*bfsize+bfsize/2] = x1[i*bfsize+1]*a[j] - x1[i*bfsize+bfsize/2]*b[j];
				x2[i*bfsize+bfsize/4] = x1[i*bfsize+bfsize/4]*a[j] - x1[i*bfsize+bfsize/2]*b[j];
				x2[i*bfsize+bfsize/2] = x1[i*bfsize+bfsize/2]*a[j] + x1[i*bfsize+bfsize/4]*b[j];
			}
			
			for(i = 0; i < count; i++)
			{
				x1[i] = x2[i];
			}
		
		}
			
		for(j = 0; j < count; j++)
		{
			
			p = 0;
			for(i = 0; i < r; i++)
			{
				if((j&(1<<i)) != 0)
					p += 1<<(r-i-1);
			}
			
			fd[j] = x1[p];
		}
		
		return fd;
		
	}

	//测试
	/*
	public static void main(String[] args){
		
		double[] td;
		double[] fd;
		int i;
		int k = 4;
		td = new double[k];
		fd = new double[k];
		
		SLT tempSLT = new SLT();
		
		for(i = 0; i < k; i++)
		{
			td[i] = i+1;
		}
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(td[i]+" ");
		}
		
		tempSLT.setData(td,2);
		fd = tempSLT.getData();
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(fd[i]+" ");
		}	
	}
	*/
}