/***********************************
	����˵����С���任

	����޸�ʱ�䣺2005-09-16
	
	���ߣ�������
***********************************/

import java.awt.*;

public class DWT {

	//DWT�任����
	int count;
	
	//ѭ������
	int  i,j,k;
	
	//�м����
	int bfsize,p;	
	int r;
	double temp;
	double[] x,fd;
		
	public void setData(double[] td,int r) {
	
		this.r = r;
		
		//����DWT�任�ĵ���
		count = 1<<r;		
		x = new double[count];
		fd = new double[count];
				
		//��ʵ���д��x
		for(i = 0; i < count; i++)
		{
			x[i] = td[i];
		}
	}
	
	public double[] getData() {
				
		int t = 0;
		
	    temp = 0;
		for(i = 0; i < count; i++)
	    {
	        temp = temp + x[i];
	    }    
    	fd[t] = temp;
    	t++;
	    
		//��������
		for(int a = 0; a < r; a++)
		{
			for(j = 0; j < 1<<a; j++)
			{
				bfsize = 1<<(r-a);
				temp = 0;
				for(i = 0; i < bfsize/2; i++)
				{			
					p = j*bfsize;
					temp = temp + x[i+p] - x[i+p+bfsize/2];
				}
				fd[t] = temp/Math.sqrt(bfsize)*Math.sqrt(count);
				t++;
			}
		}
										
		return fd;
		
	}

	//����
	/*
	public static void main(String[] args) {
		
		double[] td;
		double[] fd;
		int i;
		int k = 8;
		td = new double[k];
		fd = new double[k];
		
		DWT tempnDWT = new DWT();
		
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
		
		tempnDWT.setData(td,3);
		fd = tempnDWT.getData();
		
		for(i = 0; i < k; i++)
		{
			if (0 == Math.IEEEremainder(i,k))
				System.out.print("\n");
			System.out.print(fd[i]+" ");
		}	
	}
	*/
}