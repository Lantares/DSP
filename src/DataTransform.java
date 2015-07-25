/*****************************************************************
	程序说明：对离散数据进行傅立叶、正交余弦、沃尔什和U系统变换。

	最后修改时间：2005-09-16
	
	作者：梁延研 澳门科技大学
*****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.Window;

public class DataTransform extends Frame {
	
	MenuBar menu_bar;
	Menu intransMenu;
	MenuItem menu_intrans_DFT,menu_intrans_DCT,menu_intrans_WHT,menu_intrans_SLT,menu_intrans_UST,menu_intrans_VST,menu_intrans_DWT;
	
	double[] transpdata,temptranspdata;
	Complex[] DFTTranspdata;

	int l,w,h;
	int lp,wp,hp;
	
	//临时变量
	int i,j,k;
	int ttt;
	
	DFT tempDFT;
	DCT tempDCT;
	WHT tempWHT;
	SLT tempSLT;
	UST tempUST;
	VST tempVST;
	DWT tempDWT;
	
	//数据装载完成标记	
	boolean flagLoad = false;
	//数据类型(维数)标记
	int flagDimension = 0;
	
	public DataTransform() {
		
		menu_bar = new MenuBar();
		intransMenu = new Menu("InverseTransform");
		menu_intrans_DFT = new MenuItem("Fourier InverseTransform");
		menu_intrans_DCT = new MenuItem("Cosine InverseTransform");
		menu_intrans_WHT = new MenuItem("Walsh InverseTransform");
		menu_intrans_SLT = new MenuItem("Slant InverseTransform");
		menu_intrans_UST = new MenuItem("U-system InverseTransform");
		menu_intrans_VST = new MenuItem("V-system InverseTransform");
		menu_intrans_DWT = new MenuItem("Wavelet InverseTransform");
		intransMenu.add(menu_intrans_DFT);
		intransMenu.add(menu_intrans_DCT);
		intransMenu.add(menu_intrans_WHT);
		intransMenu.add(menu_intrans_SLT);
		intransMenu.add(menu_intrans_UST);
		intransMenu.add(menu_intrans_VST);
		intransMenu.add(menu_intrans_DWT);	
		
		menu_bar.add(intransMenu);	
		this.setMenuBar(menu_bar);		
		
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
		
		menu_intrans_DFT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InverseTransform(1);
			}
		});
		
		menu_intrans_DCT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InverseTransform(2);
			}
		});
		
		menu_intrans_WHT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InverseTransform(3);
			}
		});
		
		menu_intrans_SLT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InverseTransform(4);
			}
		});
		
		menu_intrans_UST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InverseTransform(5);
			}
		});	
			
		menu_intrans_VST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InverseTransform(6);
			}
		});	

		menu_intrans_DWT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InverseTransform(7);
			}
		});	
			
	}
	
	public void Quit(){
		this.hide();
	}
	
	public void DFTTransform(double[] pdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//进行DFT
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行DFT的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			Complex[] td = new Complex[l];
			Complex[] fd = new Complex[l];
			DFTTranspdata = new Complex[l];	
			temptranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				td[i] = new Complex(pdata[i],0);
				fd[i] = new Complex();		
			}
			
			//进行DFT
			tempDFT = new DFT();
			tempDFT.setData(td,lp);
			fd = tempDFT.getData();
			
			ttt = 0;
			//计算频谱
			for(i = 0; i < l; i++)
			{
				DFTTranspdata[i] = fd[i];
				temptranspdata[i] = Math.sqrt(fd[i].re*fd[i].re+fd[i].im*fd[i].im);
				if(temptranspdata[i] > 0.000001){
					ttt++;
				}
			}
			System.out.println("DFT no zero pixels number:"+ttt);
			
			flagLoad = true;
		}
		
		else {
		}
	
	}
	
	public void DCTTransform(double[] pdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//进行DCT
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行DCT的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			double[] td = new double[l];
			double[] fd = new double[l];	
			transpdata = new double[l];
			temptranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				td[i] = pdata[i];
				fd[i] = 0;
			}
			
			//进行DCT
			tempDCT = new DCT();
			tempDCT.setData(td,lp);
			fd = tempDCT.getData();
			
			ttt = 0;
			//计算频谱
			for(i = 0; i < l; i++)
			{	
				transpdata[i] = fd[i];
				temptranspdata[i] = Math.abs(fd[i]*Math.sqrt(l));
				if(temptranspdata[i] > 0.000001){
					ttt++;
				}
			}
			System.out.println("DCT no zero pixels number:"+ttt);
			
			flagLoad = true;
		}
		else {
		}
	
	}
	
	public void WHTTransform(double[] pdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
			
			//进行WHT
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行WHT的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			double[] td = new double[l];
			double[] fd = new double[l];	
			transpdata = new double[l];
			temptranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				td[i] = pdata[i];
				fd[i] = 0;
			}
			
			//进行WHT
			tempWHT = new WHT();
			tempWHT.setData(td,lp);
			fd = tempWHT.getData();
			
			ttt = 0;
			//计算频谱
			for(i = 0; i < l; i++)
			{	
				transpdata[i] = fd[i];
				temptranspdata[i] = Math.abs(fd[i]);
				if(temptranspdata[i] > 0.000001){
					ttt++;
				}
			}
			System.out.println("WHT no zero pixels number:"+ttt);
			
			flagLoad = true;
		}
		else{
		}
	
	}
	
	public void SLTTransform(double[] pdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//进行SLT
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行SLT的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			double[] td = new double[l];
			double[] fd = new double[l];	
			transpdata = new double[l];
			temptranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				td[i] = pdata[i];
				fd[i] = 0;
			}
			
			//进行SLT
			tempSLT = new SLT();
			tempSLT.setData(td,lp);
			fd = tempSLT.getData();
			
			ttt = 0;
			//计算频谱
			for(i = 0; i < l; i++)
			{	
				transpdata[i] = fd[i];
				temptranspdata[i] = Math.abs(fd[i]);
				if(temptranspdata[i] > 0.000001){
					ttt++;
				}
			}
			System.out.println("SLT no zero pixels number:"+ttt);
			
			flagLoad = true;
		}
		else{
		}
	}
	
	public void USTTransform(double[] pdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//进行UST
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行UST的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			double[] td = new double[l];
			double[] fd = new double[l];	
			transpdata = new double[l];
			temptranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				td[i] = pdata[i];
				fd[i] = 0;
			}
			
			//进行UST
			tempUST = new UST();
			tempUST.setData(td,lp);
			fd = tempUST.getData();
	
			ttt = 0;
			//计算频谱
			for(i = 0; i < l; i++)
			{	
				transpdata[i] = fd[i];
				temptranspdata[i] = Math.abs(fd[i]);
				if(temptranspdata[i] > 0.000001){
					ttt++;
				}
			}
			System.out.println("UST no zero pixels number:"+ttt);
			
			flagLoad = true;
		}
		else{
		}
	}
	
	public void VSTTransform(double[] pdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//进行VST
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行VST的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			double[] td = new double[l];
			double[] fd = new double[l];	
			transpdata = new double[l];
			temptranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				td[i] = pdata[i];
				fd[i] = 0;
			}
			
			//进行VST
			tempVST = new VST();
			tempVST.setData(td,lp);
			fd = tempVST.getData();
	
			ttt = 0;
			//计算频谱
			for(i = 0; i < l; i++)
			{	
				transpdata[i] = fd[i];
				temptranspdata[i] = Math.abs(fd[i]);
				if(temptranspdata[i] > 0.000001){
					ttt++;
				}
			}
			System.out.println("VST no zero pixels number:"+ttt);
			
			flagLoad = true;
		}
		else{
		}
	}

	public void DWTTransform(double[] pdata, int il, int dimension) {
		
		flagDimension = dimension;
		if(flagDimension == 1) {
		
			//进行DWT
			// 赋初值
			l = 1;
			lp = 0;
			
			//计算进行DWT的宽度和高度（2的整数次方）
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
			
			//分配内存
			double[] td = new double[l];
			double[] fd = new double[l];	
			transpdata = new double[l];
			temptranspdata = new double[l];
			
			//初始化
			for(i = 0; i < l; i++)
			{
				td[i] = pdata[i];
				fd[i] = 0;
			}
			
			//进行DWT
			tempDWT = new DWT();
			tempDWT.setData(td,lp);
			fd = tempDWT.getData();
	
			ttt = 0;
			//计算频谱
			for(i = 0; i < l; i++)
			{	
				transpdata[i] = fd[i];
				temptranspdata[i] = Math.abs(fd[i]);
				if(temptranspdata[i] > 0.000001){
					ttt++;
				}
			}
			System.out.println("DWT no zero pixels number:"+ttt);
			
			flagLoad = true;
		}
		else{
		}
	}

	//反变换
	public void InverseTransform(int task) {
		
		if(flagLoad){
			
			//进行一维数据反变换
			if(flagDimension == 1) {
					
				if (task == 1) {
					DataInverseTransform dataintrans = new DataInverseTransform();
					dataintrans.DFTInverseTransform(DFTTranspdata,l,flagDimension);
					dataintrans.setTitle("Time Domain Image of Fourier Inverse Transform");
					dataintrans.show();
				}
					
				else if (task == 2) {
					DataInverseTransform dataintrans = new DataInverseTransform();
					dataintrans.DCTInverseTransform(transpdata,l,flagDimension);
					dataintrans.setTitle("Time Domain Image of Cosine Inverse Transform");
					dataintrans.show();
				}
				else if (task == 3) {
					DataInverseTransform dataintrans = new DataInverseTransform();
					dataintrans.WHTInverseTransform(transpdata,l,flagDimension);
					dataintrans.setTitle("Time Domain Image of Walsh Inverse Transform");
					dataintrans.show();
				}
				else if (task == 4) {
					DataInverseTransform dataintrans = new DataInverseTransform();
					dataintrans.SLTInverseTransform(transpdata,l,flagDimension);
					dataintrans.setTitle("Time Domain Image of Linear U-system Inverse Transform");
					dataintrans.show();
				}
				else if (task == 5) {
					DataInverseTransform dataintrans = new DataInverseTransform();
					dataintrans.USTInverseTransform(transpdata,l,flagDimension);
					dataintrans.setTitle("Time Domain Image of High Degree U-system Inverse Transform");
					dataintrans.show();
				}
				else if (task == 6) {
					DataInverseTransform dataintrans = new DataInverseTransform();
					dataintrans.VSTInverseTransform(transpdata,l,flagDimension);
					dataintrans.setTitle("Time Domain Image of High Degree V-system Inverse Transform");
					dataintrans.show();
				}
				else if (task == 7) {
					DataInverseTransform dataintrans = new DataInverseTransform();
					dataintrans.DWTInverseTransform(transpdata,l,flagDimension);
					dataintrans.setTitle("Time Domain Image of High Degree V-system Inverse Transform");
					dataintrans.show();
				}	
		
			}
			
			/*
			//进行二维数据反变换
			//进行图像数据反变换
			else if(flagDimension == 2) {
					
				if (task == 1) {
					ImageTransform imagetrans = new ImageTransform();
					imagetrans.DFTTransform(pixels,iw,ih);
					imagetrans.setTitle("Time Domain Image of Fourier Inverse Transform");
					imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
					imagetrans.show();
				}	
				else if (task == 2) {
					ImageTransform imagetrans = new ImageTransform();
					imagetrans.DCTTransform(pixels,iw,ih);
					imagetrans.setTitle("Time Domain Image of Cosine Inverse Transform");
					imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
					imagetrans.show();
				}
				else if (task == 3) {
					ImageTransform imagetrans = new Domain ImageTransform();
					imagetrans.WHTTransform(pixels,iw,ih);
					imagetrans.setTitle("Time Image of Walsh Inverse Transform");
					imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
					imagetrans.show();
				}
				else if (task == 4) {
					ImageTransform imagetrans = new Domain ImageTransform();
					imagetrans.UstTransform(pixels,iw,ih);
					imagetrans.setTitle("Time Image of Linear U-system Inverse Transform");
					imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
					imagetrans.show();
				}
					
			}	
			*/
			

		}
		else {
			JOptionPane.showMessageDialog(null,"Please Import Data!",
		                 "Alert",JOptionPane.WARNING_MESSAGE);
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
			
			if(flagLoad) {
				
				temp1 = 0;
				temp2 = 0;
				temp3 = 0;
				
				for(int i = 0; i < l; i++)
				{
					temp1 = temptranspdata[i];
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
					//temppdata[i] = (int)((temptranspdata[i]*280)/(temp2-temp3));
					temppdata[i] = (int)(((temptranspdata[i]-temp3)*280)/(temp2-temp3));
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
				
				/*				
				for(int i = 1; i < l; i++)
				{
					g.drawLine(45+ltemp[i-1],xline-temppdata[i-1],45+ltemp[i],xline-temppdata[i]);
				}
				*/
				
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
			
		}
		else {
		}
		
	}
	
}