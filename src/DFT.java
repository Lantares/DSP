/******************************
	����˵��������Ҷ�任

	����޸�ʱ�䣺2005-09-16
	
	���ߣ�������
******************************/

import java.awt.*;

public class DFT {

	// Dft�任����
	int count;
	
	//ѭ������
	int  i,j,k;
	
	//�м����
	int bfsize,p;	
	int r;
	
	Complex[] w,x1,x2,fd;
	
	public void setData(Complex[] td,int r) {
	
		this.r = r;
		
		//�Ƕ�
		double angle;
		
		//����Dft�任�ĵ���
		count = 1<<r;
		
		//����ռ�
		w = new Complex[count/2];
		x1 = new Complex[count];
		x2 = new Complex[count];
		fd = new Complex[count];
		
		//��ʼ��
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
		
		//�����Ȩϵ��
		for(i = 0; i < count/2; i++)
		{
			angle = -i*Math.PI*2/count;
			w[i].re = Math.cos(angle);
			w[i].im = Math.sin(angle);
		}
		
		//��ʵ���д��x1
		for(i = 0; i < count; i++)
		{
			x1[i] = td[i];
		}
	}
	
	public Complex[] getData() {
	
		//��������
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
	
	
		//��������
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

	//����
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