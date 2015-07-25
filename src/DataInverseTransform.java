/*****************************************************************
	程序说明：对离散数据进行傅立叶、正交余弦、沃尔什和U系统反变换。

	最后修改时间：2005-09-16
	
	作者：梁延研 澳门科技大学
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
	
	//临时变量
	int i,j,k;
	
	IDFT tempIDFT;
	IDCT tempIDCT;
	IWHT tempIWHT;
	ISLT tempISLT;
	IUST tempIUST;
	IVST tempIVST;
	IDWT tempIDWT;
	
	//数据类型(维数)标记
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
		
			//进行IDFT
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行IDFT的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			Complex[] fd = new Complex[l];
			Complex[] td = new Complex[l];	
			intranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = new Complex();		
			}
			
			//进行DFT
			tempIDFT = new IDFT();
			tempIDFT.setData(fd,lp);
			td = tempIDFT.getData();
	
			//计算频谱
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
		
			//进行IDCT
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行IDCT的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			double[] fd = new double[l];
			double[] td = new double[l];	
			intranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = 0;
			}
			
			//进行IDCT
			tempIDCT = new IDCT();
			tempIDCT.setData(fd,lp);
			td = tempIDCT.getData();
	
			//计算频谱
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
		
			//进行IWHT
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行IWHT的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			double[] fd = new double[l];
			double[] td = new double[l];	
			intranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = 0;
			}
			
			//进行IWHT
			tempIWHT = new IWHT();
			tempIWHT.setData(fd,lp);
			td = tempIWHT.getData();
	
			//计算频谱
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
		
			//进行ISLT
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行ISLT的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			double[] fd = new double[l];
			double[] td = new double[l];	
			intranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = 0;
			}
			
			//进行ISLT
			tempISLT = new ISLT();
			tempISLT.setData(fd,lp);
			td = tempISLT.getData();
	
			//计算频谱
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
		
			//进行IUST
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行IUST的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			double[] fd = new double[l];
			double[] td = new double[l];	
			intranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = 0;
			}
			
			//进行IUST
			tempIUST = new IUST();
			tempIUST.setData(fd,lp);
			td = tempIUST.getData();
	
			//计算频谱
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
		
			//进行IVST
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行IVST的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			double[] fd = new double[l];
			double[] td = new double[l];	
			intranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = 0;
			}
			
			//进行IVST
			tempIVST = new IVST();
			tempIVST.setData(fd,lp);
			td = tempIVST.getData();
	
			//计算频谱
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
		
			//进行IDWT
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行IDWT的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			double[] fd = new double[l];
			double[] td = new double[l];	
			intranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				fd[i] = transpdata[i];
				td[i] = 0;
			}
			
			//进行IDWT
			tempIDWT = new IDWT();
			tempIDWT.setData(fd,lp);
			td = tempIDWT.getData();
	
			//计算频谱
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

		//画一维数据图
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
			
			//画出水平和垂直的轴
			g.drawLine(45,xline,645,xline);
			g.drawLine(45,70,45,360);
			
			//画出横轴坐标
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
			//画出纵轴坐标
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
			
			//支持负数
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
			
			//画出纵轴坐标
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