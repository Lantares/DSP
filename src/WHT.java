/******************************
	程序说明：沃尔什变换

	最后修改时间：2005-09-16
	
	作者：梁延研
******************************/

import java.awt.*;

public class WHT {

	// Wht变换点数
	int count;
	
	//循环变量
	int  i,j,k;
	
	//中间变量
	int bfsize,p;	
	int r;
	
	double[] w,x1,x2,fd;	
	
	public void setData(double[] td,int r){
	
		this.r = r;
		
		//计算Wht变换的点数
		count = 1<<r;
		
		//分配空间
		x1 = new double[count];
		x2 = new double[count];
		fd = new double[count];
		
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
	
		//重新排序
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
		
		WHT tempWht = new WHT();
		
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
		
		tempWht.setData(td,2);
		fd = tempWht.getData();
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(fd[i]+" ");
		}	
	}
	*/
}