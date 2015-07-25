/******************************
	程序说明：傅立叶变换

	最后修改时间：2005-09-16
	
	作者：梁延研
******************************/

import java.awt.*;

public class DFT {

	// Dft变换点数
	int count;
	
	//循环变量
	int  i,j,k;
	
	//中间变量
	int bfsize,p;	
	int r;
	
	Complex[] w,x1,x2,fd;
	
	public void setData(Complex[] td,int r) {
	
		this.r = r;
		
		//角度
		double angle;
		
		//计算Dft变换的点数
		count = 1<<r;
		
		//分配空间
		w = new Complex[count/2];
		x1 = new Complex[count];
		x2 = new Complex[count];
		fd = new Complex[count];
		
		//初始化
		for(i = 0; i < count/2; i++)
		{
			w[i] = new Complex();
		}
		
		for(i = 0; i < count; i++)
		{
			x1[i] = new Complex();
			x2[i] = new Complex();
			fd[i] = new Complex();
		}
		
		//计算加权系数
		for(i = 0; i < count/2; i++)
		{
			angle = -i*Math.PI*2/count;
			w[i].re = Math.cos(angle);
			w[i].im = Math.sin(angle);
		}
		
		//将实域点写入x1
		for(i = 0; i < count; i++)
		{
			x1[i] = td[i];
		}
	}
	
	public Complex[] getData() {
	
		//蝶形运算
		for(k = 0; k < r; k++)
		{			
			for(j = 0; j < 1<<k; j++)
			{
				bfsize = 1<<(r-k);
				for(i = 0; i < bfsize/2; i++)
				{
					Complex temp1 = new Complex();
					Complex temp2 = new Complex();			
					
					p = j*bfsize;
					x2[i+p]=temp1.Add(x1[i+p],x1[i+p+bfsize/2]);
					temp2=temp1.Sub(x1[i+p],x1[i+p+bfsize/2]);
					x2[i+p+bfsize/2]=temp1.Mul(temp2,w[i*(1<<k)]);
				}
			}
						
			for(i = 0; i < count; i++)
			{
				x1[i].re = x2[i].re;
				x1[i].im = x2[i].im;
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
			
			fd[j].re = x1[p].re;
			fd[j].im = x1[p].im;
		}
		return fd;
		
	}

	//测试
	/*
	public static void main(String[] args) {
		
		Complex[] td;
		Complex[] fd;
		int i;
		int k = 4;
		td = new Complex[k];
		fd = new Complex[k];
		
		for(i = 0; i < k; i++)
		{
			td[i] = new Complex();
			fd[i] = new Complex();
		}
		
		DFT tempDft = new DFT();
			
		for(i = 0; i < k; i++)
		{
			td[i].re = i+1;
			td[i].im = 0;
		}
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(td[i].re+" ");
		}
		
		tempDft.setData(td,2);
		fd = tempDft.getData();
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(fd[i].re+"+"+fd[i].im+"i"+" ");
		}	
	}
	*/
}