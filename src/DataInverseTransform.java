/*****************************************************************
	����˵��������ɢ���ݽ��и���Ҷ���������ҡ��ֶ�ʲ��Uϵͳ���任��

	����޸�ʱ�䣺2005-09-16
	
	���ߣ������� ���ſƼ���ѧ
*****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.Window;

public class DataInverseTransform extends Frame {
	
	double[] intranspdata;

	int l,w,h;
	int lp,wp,hp;
	
	//��ʱ����
	int i,j,k;
	
	IDFT tempIDFT;
	IDCT tempIDCT;
	IWHT tempIWHT;
	ISLT tempISLT;
	IUST tempIUST;
	IVST tempIVST;
	IDWT tempIDWT;
	
	//��������(ά��)���
	int flagDimension = 0;
	
	public DataInverseTransform() {	
		
		Panel pdown;
		Button quit;
		
		pdown = new Panel();
		quit = new Button("Close Window");
		
		this.add(pdown,BorderLayout.SOUTH);
		this.setSize(700,430);
		
		pdown.add(quit);
		
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quit();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Quit();
			}
		});
	
	}
	
	public void Quit(){
		this.hide();
	}
	
	public void DFTInverseTransform(Complex[] transpdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//����IDFT
			// ����ֵ
			l = 1;
			lp = 0;
			
			//�������IDFT�Ŀ�Ⱥ͸߶ȣ�2�������η���
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//�����ڴ�
			Complex[] fd = new Complex[l];
			Complex[] td = new Complex[l];	
			intranspdata = new double[l];
			
			//��ʼ��
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = new Complex();		
			}
			
			//����DFT
			tempIDFT = new IDFT();
			tempIDFT.setData(fd,lp);
			td = tempIDFT.getData();
	
			//����Ƶ��
			for(i = 0; i < l; i++)
			{
				intranspdata[i] = td[i].re;
			}
			
		}
		
		else {
		}
	
	}
	
	public void DCTInverseTransform(double[] transpdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//����IDCT
			// ����ֵ
			l = 1;
			lp = 0;
			
			//�������IDCT�Ŀ�Ⱥ͸߶ȣ�2�������η���
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//�����ڴ�
			double[] fd = new double[l];
			double[] td = new double[l];	
			intranspdata = new double[l];
			
			//��ʼ��
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = 0;
			}
			
			//����IDCT
			tempIDCT = new IDCT();
			tempIDCT.setData(fd,lp);
			td = tempIDCT.getData();
	
			//����Ƶ��
			for(i = 0; i < l; i++)
			{	
				intranspdata[i] = td[i];
			}

		}
		else {
		}
	
	}
	
	public void WHTInverseTransform(double[] transpdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//����IWHT
			// ����ֵ
			l = 1;
			lp = 0;
			
			//�������IWHT�Ŀ�Ⱥ͸߶ȣ�2�������η���
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//�����ڴ�
			double[] fd = new double[l];
			double[] td = new double[l];	
			intranspdata = new double[l];
			
			//��ʼ��
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = 0;
			}
			
			//����IWHT
			tempIWHT = new IWHT();
			tempIWHT.setData(fd,lp);
			td = tempIWHT.getData();
	
			//����Ƶ��
			for(i = 0; i < l; i++)
			{	
				intranspdata[i] = td[i];
			}

		}
		else {
		}
	
	}
	
	public void SLTInverseTransform(double[] transpdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//����ISLT
			// ����ֵ
			l = 1;
			lp = 0;
			
			//�������ISLT�Ŀ�Ⱥ͸߶ȣ�2�������η���
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//�����ڴ�
			double[] fd = new double[l];
			double[] td = new double[l];	
			intranspdata = new double[l];
			
			//��ʼ��
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = 0;
			}
			
			//����ISLT
			tempISLT = new ISLT();
			tempISLT.setData(fd,lp);
			td = tempISLT.getData();
	
			//����Ƶ��
			for(i = 0; i < l; i++)
			{	
				intranspdata[i] = td[i];
			}

		}
		else {
		}
	
	}
		
		
	public void USTInverseTransform(double[] transpdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//����IUST
			// ����ֵ
			l = 1;
			lp = 0;
			
			//�������IUST�Ŀ�Ⱥ͸߶ȣ�2�������η���
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//�����ڴ�
			double[] fd = new double[l];
			double[] td = new double[l];	
			intranspdata = new double[l];
			
			//��ʼ��
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = 0;
			}
			
			//����IUST
			tempIUST = new IUST();
			tempIUST.setData(fd,lp);
			td = tempIUST.getData();
	
			//����Ƶ��
			for(i = 0; i < l; i++)
			{	
				intranspdata[i] = td[i];
			}

		}
		else {
		}
	
	}

	public void VSTInverseTransform(double[] transpdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//����IVST
			// ����ֵ
			l = 1;
			lp = 0;
			
			//�������IVST�Ŀ�Ⱥ͸߶ȣ�2�������η���
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//�����ڴ�
			double[] fd = new double[l];
			double[] td = new double[l];	
			intranspdata = new double[l];
			
			//��ʼ��
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = 0;
			}
			
			//����IVST
			tempIVST = new IVST();
			tempIVST.setData(fd,lp);
			td = tempIVST.getData();
	
			//����Ƶ��
			for(i = 0; i < l; i++)
			{	
				intranspdata[i] = td[i];
			}

		}
		else {
		}
	
	}
	
	public void DWTInverseTransform(double[] transpdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//����IDWT
			// ����ֵ
			l = 1;
			lp = 0;
			
			//�������IDWT�Ŀ�Ⱥ͸߶ȣ�2�������η���
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//�����ڴ�
			double[] fd = new double[l];
			double[] td = new double[l];	
			intranspdata = new double[l];
			
			//��ʼ��
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = 0;
			}
			
			//����IDWT
			tempIDWT = new IDWT();
			tempIDWT.setData(fd,lp);
			td = tempIDWT.getData();
	
			//����Ƶ��
			for(i = 0; i < l; i++)
			{	
				intranspdata[i] = td[i];
			}

		}
		else {
		}
	
	}
		
	public void paint(Graphics g) {
		
		setBackground(Color.lightGray);

		//��һά����ͼ
		if(flagDimension == 1) {
			
			int[] ltemp = new int[l];
			int[] temppdata = new int[l];
			double temp1,temp2,temp3;
			int xline;
				
			temp1 = 0;
			temp2 = 0;
			temp3 = 0;
			
			for(int i = 0; i < l; i++)
			{
				temp1 = intranspdata[i];
				if(temp1 > temp2){ 
					temp2 = temp1; 
				}
				
				if(temp1 < temp3){ 
					temp3 = temp1; 
				}	
			}
				
			//xline = 70+(int)((temp2*280)/(temp2-temp3));				
			xline = 360;
			
			for(int i = 0; i < l; i++)
			{
				//temppdata[i] = (int)((intranspdata[i]*280)/(temp2-temp3));
				temppdata[i] = (int)(((intranspdata[i]-temp3)*280)/(temp2-temp3));
				ltemp[i] = i*600/l;
			}
			
			//����ˮƽ�ʹ�ֱ����
			g.drawLine(45,xline,645,xline);
			g.drawLine(45,70,45,360);
			
			//������������
			g.drawString("0",45,xline+15);
			
			String x1 = l/4+"";
			g.drawString(x1,193,xline+15);
			
			String x2 = l*2/4+"";
			g.drawString(x2,343,xline+15);
			
			String x3 = l*3/4+"";
			g.drawString(x3,493,xline+15);
			
			String x4 = l+"";
			g.drawString(x4,643,xline+15);
			
			/*
			//������������
			int s1 = (int)temp2/4;
			String y1 = s1+"";
			g.drawString(y1,25,xline+6-(int)((s1*280)/(temp2-temp3)));
			
			int s2 = (int)temp2*2/4;
			String y2 = s2+"";
			g.drawString(y2,25,xline+6-(int)((s2*280)/(temp2-temp3)));
			
			int s3 = (int)temp2*3/4;
			String y3 = s3+"";
			g.drawString(y3,25,xline+6-(int)((s3*280)/(temp2-temp3)));
			
			int s4 = (int)temp2;
			String y4 = s4+"";
			g.drawString(y4,25,xline+6-(int)((s4*280)/(temp2-temp3))); 
			
			//֧�ָ���
			int t1 = (int)temp3/4;
			String z1 = t1+"";
			g.drawString(z1,25,xline+6-(int)((t1*280)/(temp2-temp3)));
			
			int t2 = (int)temp3*2/4;
			String z2 = t2+"";
			g.drawString(z2,25,xline+6-(int)((t2*280)/(temp2-temp3)));
			
			int t3 = (int)temp3*3/4;
			String z3 = t3+"";
			g.drawString(z3,25,xline+6-(int)((t3*280)/(temp2-temp3)));
			
			int t4 = (int)temp3;
			String z4 = t4+"";
			g.drawString(z4,25,xline+6-(int)((t4*280)/(temp2-temp3))); 
			*/
			
			//������������
			double s1 = 0.25;
			String y1 = s1+"";
			g.drawString(y1,20,xline+6-70);
			
			double s2 = 0.50;
			String y2 = s2+"";
			g.drawString(y2,20,xline+6-140);
			
			double s3 = 0.75;
			String y3 = s3+"";
			g.drawString(y3,20,xline+6-210);
			
			double s4 = 1.00;
			String y4 = s4+"";
			g.drawString(y4,20,xline+6-280); 			
						
			for(int i = 1; i < l; i++)
			{
				g.drawLine(45+ltemp[i-1],xline-temppdata[i-1],45+ltemp[i],xline-temppdata[i]);
			}
			
			if(temppdata[0] > 0) {
				g.drawLine(42,xline-temppdata[0],45,xline+3-temppdata[0]);
				g.drawLine(45,xline+3-temppdata[0],48,xline-temppdata[0]);
				g.drawLine(48,xline-temppdata[0],45,xline-3-temppdata[0]);
				g.drawLine(45,xline-3-temppdata[0],42,xline-temppdata[0]);
			}
			
			for(int i = 0; i < l; i++)
			{
				g.drawLine(45+ltemp[i],xline,45+ltemp[i],xline-temppdata[i]);
			}
							
		}
		else {
		}
		
	}
	
}