/******************************
	程序说明：傅立叶反变换

	最后修改时间：2005-09-16
	
	作者：梁延研
******************************/

import java.awt.*;

public class IDFT {
	
	// Dft变换点数
	int count;
	
	//循环变量
	int  i;
	
	//中间变量	
	Complex[] x,td;
	DFT tempDft;
	
	public void setData(Complex[] fd,int r) {
		
		//计算Dft变换的点数
		count = 1<<r;
		
		//分配空间
		x = new Complex[count];
		td = new Complex[count];
		
		//初始化
		for(i = 0; i < count; i++)
		{
			x[i] = new Complex();
			td[i] = new Complex();
		}
			
		for(i = 0; i < count; i++)
		{
			//将频域点写入x
			x[i] = fd[i];
			//求共轭
			x[i].im = -x[i].im;
		}
		
		//调用DFT计算
		tempDft = new DFT();
		tempDft.setData(x,r);
		x = tempDft.getData();		
				
	}	
	
	public Complex[] getData() {
		
		for(i = 0; i < count; i++)
		{
			td[i].re = x[i].re/count;
		}	
		return td;
		
	}
	
	//测试
	/*
	public static void main(String[] args) {
	
		Complex[] fd;
		Complex[] td;
		int i;
		int k = 4;
		fd = new Complex[k];
		td = new Complex[k];
		
		for(i = 0; i < k; i++)
		{
			fd[i] = new Complex();
			td[i] = new Complex();
		}
		
		IDFT tempIDft = new IDFT();
		
		fd[0].re = 10;
		fd[0].im = 0;
		fd[1].re = -2;
		fd[1].im = 2;		
		fd[2].re = -2;
		fd[2].im = 0;
		fd[3].re = -2;
		fd[3].im = -2;
						
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(fd[i].re+"+"+fd[i].im+"i ");
		}
		
		tempIDft.setData(fd,2);
		td = tempIDft.getData();
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(td[i].re+" ");
		}	
	}
	*/
}