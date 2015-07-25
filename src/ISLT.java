/**************************************
	程序说明：斜变换(一次U系统)反变换

	最后修改时间：2005-09-16
	
	作者：梁延研
**************************************/

import java.awt.*;

public class ISLT {

	// ISLT变换点数
	int count;
	
	//循环变量
	int  i,j,k;
	
	//中间变量
	int bfsize,p;	
	int r;
	double xtemp,ytemp;
	
	double[] a,b,x1,x2,td;	
	
	public void setData(double[] fd,int r){
	
		this.r = r;
		
		// ISLT加权系数coe
		double coe;
		
		//计算ISLT变换的点数
		count = 1<<r;
		
		//分配空间
		a = new double[r-1];
		b = new double[r-1];
		x1 = new double[count];
		x2 = new double[count];
		td = new double[count];
		
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
			x2[i] = fd[i];
		}
	}
	
	public double[] getData(){
		
		//调整顺序
		for(j = 0; j < count; j++)
		{
			
			p = 0;
			for(i = 0; i < r; i++)
			{
				if((j&(1<<i)) != 0)
					p += 1<<(r-i-1);
			}
			
			x1[j] = x2[p];
		}
		
		//U系统系数运算
		for(j = (r-2); j >= 0; j--)
		{
		
			for(i = 0; i < count; i++)
			{	
				x2[i] = x1[i];
			}

			for(i = ((1<<(r-j-2))-1); i >= 0; i--)
			{	
				bfsize = 4<<j;
				xtemp = (x1[i*bfsize+bfsize/4]*a[j] + x1[i*bfsize+bfsize/2]*b[j])/((a[j]*a[j]+b[j]*b[j]));
				ytemp = (x1[i*bfsize+bfsize/2]*a[j] - x1[i*bfsize+bfsize/4]*b[j])/((a[j]*a[j]+b[j]*b[j]));
				x2[i*bfsize+bfsize/4] = xtemp;
				x2[i*bfsize+bfsize/2] = ytemp;
			}
			
			for(i = 0; i < count; i++)
			{
				x1[i] = x2[i];
			}
		
		}
		
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
		
		for(i = 0; i < count; i++)
		{	
			td[i] = x1[i]/count;
		}
		
		return td;
		
	}

	//测试
	/*
	public static void main(String[] args){
		
		double[] fd;
		double[] td;
		int i;
		int k = 8;
		fd = new double[k];
		td = new double[k];
		
		ISLT tempISLT = new ISLT();
		
		fd[0] = 36;
		fd[1] = -18.33030277982336;			
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(fd[i]+" ");
		}
		
		tempISLT.setData(fd,3);
		td = tempISLT.getData();
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(td[i]+" ");
		}	
	}
	*/
}