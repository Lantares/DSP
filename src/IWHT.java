/******************************
	����˵�����ֶ�ʲ���任

	����޸�ʱ�䣺2005-09-16
	
	���ߣ�������
******************************/

import java.awt.*;

public class IWHT {

	// Wht�任����
	int count;
	
	//ѭ������
	int  i,j,k;
	
	//�м����
	int bfsize,p;	
	int r;
	
	double[] w,x1,x2,td;	
	
	public void setData(double[] fd,int r){
	
		this.r = r;
		
		//����Wht�任�ĵ���
		count = 1<<r;
		
		//����ռ�
		x1 = new double[count];
		x2 = new double[count];
		td = new double[count];
		
		//��ʵ���д��x1
		for(i = 0; i < count; i++)
		{
			x1[i] = fd[i];
		}
	}
	
	public double[] getData(){
	
		//��������
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
	
		//��������
		for(j = 0; j < count; j++)
		{
			
			p = 0;
			for(i = 0; i < r; i++)
			{
				if((j&(1<<i)) != 0)
					p += 1<<(r-i-1);
			}
			
			td[j] = x1[p]/count;
		}
		return td;
		
	}

	//����
	/*
	public static void main(String[] args){
		
		double[] fd;
		double[] td;
		int i;
		int k = 4;
		fd = new double[k];
		td = new double[k];
		
		IWHT tempIWht = new IWHT();
		
		fd[0] = 10;
		fd[1] = -4;
		fd[2] = -2;
		fd[3] = 0;					
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(fd[i]+" ");
		}
		
		tempIWht.setData(fd,2);
		td = tempIWht.getData();
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(td[i]+" ");
		}	
	}
	*/
}