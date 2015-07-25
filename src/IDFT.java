/******************************
	����˵��������Ҷ���任

	����޸�ʱ�䣺2005-09-16
	
	���ߣ�������
******************************/

import java.awt.*;

public class IDFT {
	
	// Dft�任����
	int count;
	
	//ѭ������
	int  i;
	
	//�м����	
	Complex[] x,td;
	DFT tempDft;
	
	public void setData(Complex[] fd,int r) {
		
		//����Dft�任�ĵ���
		count = 1<<r;
		
		//����ռ�
		x = new Complex[count];
		td = new Complex[count];
		
		//��ʼ��
		for(i = 0; i < count; i++)
		{
			x[i] = new Complex();
			td[i] = new Complex();
		}
			
		for(i = 0; i < count; i++)
		{
			//��Ƶ���д��x
			x[i] = fd[i];
			//����
			x[i].im = -x[i].im;
		}
		
		//����DFT����
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
	
	//����
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