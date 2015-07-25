/******************************
	程序说明：生成一组一维数据。

	最后修改时间：2005-09-16
	
	作者：梁延研
******************************/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
import java.awt.Window;

public class Create1dData extends Frame {
	
	GridLayout gl;	
	Panel top,bottom;
	Label ltype,lnum,lmag,blank1,blank2;
	Choice ctype;
	TextField tnum,tmag;
	Button confirm,quit;
	
	double[] pdata,tempdata;
	Complex[] cpdata,ctempdata;
	int il,l,lp;
	int tl = 64;
	int mag = 1;
	int transtype;
		
	//临时变量
	int i;
	IDFT tempIDFT;
	IDCT tempIDCT;
	IWHT tempIWHT;
	ISLT tempISLT;
	IUST tempIUST;
	IVST tempIVST;
	IDWT tempIDWT;
	
	public Create1dData() {
				
		top = new Panel();
		bottom = new Panel();
		
		ltype = new Label("Please choose the base of transform:");
		ctype = new Choice();
		ctype.addItem("Fourier Transform");
		ctype.addItem("Cosine Transform");
		ctype.addItem("Walsh Transform");
		ctype.addItem("Slant Transform");
		ctype.addItem("U-system Transform");
		ctype.addItem("V-system Transform");
		ctype.addItem("Wavelet Transform");
		ctype.addItem("Create a Random Signal");
		ctype.addItem("Create a Gaussian-distributing Signal");
		
		lnum = new Label("Please input the number of signal point:");
		tnum = new TextField();		
		
		lmag = new Label("Please input the magnitude of signal point:");
		tmag = new TextField();	
		
		blank1 = new Label();
		blank2 = new Label();
		
		confirm = new Button("Confirm");
		quit = new Button("Close Window");
		
		gl = new GridLayout(8,1);
		
		top.add(ltype);
		top.add(ctype);
		top.add(blank1);
		top.add(lnum);
		top.add(tnum);
		top.add(blank2);
		//top.add(lmag);
		//top.add(tmag);
		
		top.setLayout(gl);
		
		bottom.add(confirm);
		bottom.add(quit);
		
		this.add(top,BorderLayout.NORTH);
		this.add(bottom,BorderLayout.SOUTH);
		this.setSize(250,300);
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Confirm();
			}
		});		
		
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
	
	public void Confirm() {
		
		il = Integer.parseInt(tnum.getText());
		//mag = Integer.parseInt(tmag.getText());
		if (ctype.getSelectedItem() == "Fourier Transform") {
			transtype = 1;
		}
		else if (ctype.getSelectedItem() == "Cosine Transform") {
			transtype = 2;
		}
		else if (ctype.getSelectedItem() == "Walsh Transform") {
			transtype = 3;
		}
		else if (ctype.getSelectedItem() == "Slant Transform") {
			transtype = 4;
		}
		else if (ctype.getSelectedItem() == "U-system Transform") {
			transtype = 5;
		}
		else if (ctype.getSelectedItem() == "V-system Transform") {
			transtype = 6;
		}
		else if (ctype.getSelectedItem() == "Wavelet Transform") {
			transtype = 7;
		}
		else if (ctype.getSelectedItem() == "Create a Random Signal") {
			transtype = 8;
		}
		else if (ctype.getSelectedItem() == "Create a Gaussian-distributing Signal") {
			transtype = 9;
		}
		this.hide();
		
	}
	
	public double[] CreateData() {
		
		Random rd = new Random();
		pdata = new double[il];
		
		if (transtype == 1) {
			
			cpdata = new Complex[il];
			ctempdata = new Complex[il];
			
			for(i = 0; i < il; i++)
			{
				cpdata[i] = new Complex();
				ctempdata[i] = new Complex();
			}			
			
			for(i = 0; i < tl/2; i++)
			{
				ctempdata[i].re = Math.random();
				ctempdata[i].im = Math.random();
			}	

			for(i = il-1; i > (il-1-tl/2); i--)
			{
				ctempdata[i].re = Math.random();
				ctempdata[i].im = Math.random();
			}	
					
			l = 1;
			lp = 0;
			
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
				
			tempIDFT = new IDFT();
			tempIDFT.setData(ctempdata,lp);
			cpdata = tempIDFT.getData();
			
			for(i = 0; i < il; i++)
			{
				pdata[i] = cpdata[i].re;
			}			
			
		}
		
		else if (transtype == 2) {
			
			tempdata = new double[il];
			
			for(i = 0; i < tl/2; i++)
			{
				tempdata[i] = Math.random();
			}
				
			for(i = il-1; i > (il-1-tl/2); i--)
			{
				tempdata[i] = Math.random();
			}
								
			l = 1;
			lp = 0;
			
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
				
			tempIDCT = new IDCT();
			tempIDCT.setData(tempdata,lp);
			pdata = tempIDCT.getData();
			
		}
		
		else if (transtype == 3) {
			
			tempdata = new double[il];
			
			for(i = 0; i < tl/2; i++)
			{
				tempdata[i] = Math.random();
			}
				
			for(i = il-1; i > (il-1-tl/2); i--)
			{
				tempdata[i] = Math.random();
			}
					
			l = 1;
			lp = 0;
			
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
				
			tempIWHT = new IWHT();
			tempIWHT.setData(tempdata,lp);
			pdata = tempIWHT.getData();
	
		}
		
		else if (transtype == 4) {
			
			tempdata = new double[il];
			
			for(i = 0; i < tl/2; i++)
			{
				tempdata[i] = Math.random();
			}
				
			for(i = il-1; i > (il-1-tl/2); i--)
			{
				tempdata[i] = Math.random();
			}
					
			l = 1;
			lp = 0;
			
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
				
			tempISLT = new ISLT();
			tempISLT.setData(tempdata,lp);
			pdata = tempISLT.getData();
			
		}
		
		else if (transtype == 5) {
			
			tempdata = new double[il];
			
			for(i = 0; i < tl/2; i++)
			{
				tempdata[i] = Math.random();
			}
				
			for(i = il-1; i > (il-1-tl/2); i--)
			{
				tempdata[i] = Math.random();
			}
					
			l = 1;
			lp = 0;
			
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
				
			tempIUST = new IUST();
			tempIUST.setData(tempdata,lp);
			pdata = tempIUST.getData();
			
		}
		
		else if (transtype == 6) {
			
			tempdata = new double[il];
			
			for(i = 0; i < tl/2; i++)
			{
				tempdata[i] = Math.random();
			}
				
			for(i = il-1; i > (il-1-tl/2); i--)
			{
				tempdata[i] = Math.random();
			}
					
			l = 1;
			lp = 0;
			
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
				
			tempIVST = new IVST();
			tempIVST.setData(tempdata,lp);
			pdata = tempIVST.getData();
			
		}
		
		else if (transtype == 7) {
			
			tempdata = new double[il];
			
			for(i = 0; i < tl/2; i++)
			{
				tempdata[i] = Math.random();
			}
				
			for(i = il-1; i > (il-1-tl/2); i--)
			{
				tempdata[i] = Math.random();
			}
					
			l = 1;
			lp = 0;
			
			while(l*2 <= il)
			{
				l *= 2;
				lp++;
			}
				
			tempIDWT = new IDWT();
			tempIDWT.setData(tempdata,lp);
			pdata = tempIDWT.getData();
			
		}
		
		else if (transtype == 8) {
			
			for(i = 0; i < il; i++)
			{
				pdata[i] = Math.random()*mag;
			}
			
		}
		
		else if (transtype == 9) {
			
			for(i = 0; i < il; i++)
			{
				pdata[i] = rd.nextGaussian()*mag;
			}
			
		}
		return pdata;
		
	}
	
	public void Quit() {
		this.hide();
	}
	
}