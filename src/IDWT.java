/***********************************
	程序说明：小波反变换

	最后修改时间：2005-09-16
	
	作者：梁延研
***********************************/

import java.awt.*;

public class IDWT {

	// Ust变换点数
	int count;
	
	//循环变量
	int  i,j,k;
	
	//中间变量
	int bfsize,p;	
	int r;
	double temp;
	double[] temp1,x,td;
	IWHT tempIWHT;
	
	public void setData(double[] fd,int r) {
	
		this.r = r;
		
		//计算IDWT变换的点数
		count = 1<<r;
		x = new double[count];
		td = new double[count];
					
		//将频域点写入x
		for(i = 0; i < count; i++)
		{
			x[i] = fd[i];
		}	
		
	}
	
	public double[] getData() {
		
		//蝶形运算
		for(int a = 0; a < r; a++)
		{

			bfsize = 1<<(r-a);
			temp1 = new double[bfsize/2];
			for(i = 0; i < bfsize/2; i++)
			{		
				temp1[i] = x[i+bfsize/2];
			}
			int s = r-a-1;
			tempIWHT = new IWHT();
			tempIWHT.setData(temp1,s);
			temp1 = tempIWHT.getData();
			for(i = 0; i < bfsize/2; i++)
			{			
				x[i+bfsize/2] = temp1[i]*Math.sqrt(bfsize/2);
			}

		}	
			
		tempIWHT = new IWHT();
		tempIWHT.setData(x,r);
		td = tempIWHT.getData();
		
		return td;
		
	}

	//测试
	/*
	public static void main(String[] args) {
		
		double[] fd;
		double[] td;
		int i;
		int k = 8;
		fd = new double[k];
		td = new double[k];
		
		IDWT tempIDWT = new IDWT();
		
		fd[0] = 36;
		fd[1] = -16;
		fd[2] = -5.656854249492381;
		fd[3] = -5.656854249492381;
		fd[4] = -2;
		fd[5] = -2;
		fd[6] = -2;
		fd[7] = -2;
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(fd[i]+" ");
		}
		
		tempIDWT.setData(fd,3);
		td = tempIDWT.getData();
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(td[i]+" ");
		}	
	}
	*/
}