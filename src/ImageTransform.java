/****************************************************************
	����˵����������ͼ����и���Ҷ���������ҡ��ֶ�ʲ��Uϵͳ�任��

	����޸�ʱ�䣺2005-09-16
	
	���ߣ������� ���ſƼ���ѧ
****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageTransform extends Frame {
	
	Image transim;
	int[] transpixels;
	
	RawDXF dfx;
	DXFExport Xprt;

	int w,h;
	int wp,hp;
	
	//��ʱ����
	double temp0;
	int temp1,temp2,temp3;
	int i,j,k;
	int ttt;
	
	//Ƶ��ͼ�Ҷȱ��ֲ��
	double graytemp1 = 0.001;
	int graytemp2 = 200000;
	
	DFT tempDFT;
	DCT tempDCT;
	WHT tempWHT;
	SLT tempSLT;
	UST tempUST;
	VST tempVST;
	DWT tempDWT;
	
	//covert Image object to BufferedImage object
	private BufferedImage convertImageToBuffer(Image im){
		BufferedImage bufferedImage = new BufferedImage(im
				.getWidth(null), im.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferedImage.createGraphics();
		g.drawImage(im, 0, 0, null);
		g.dispose();
		return bufferedImage;
	}
	
	public ImageTransform() {
		
		Panel pdown;
		Button quit;
		
		pdown = new Panel();
		quit = new Button("Close Window");
		
		this.add(pdown,BorderLayout.SOUTH);
		
		pdown.add(quit);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				Quit();
			}
		});

		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Quit();
			}
		});
	
	}
	
	public void Quit(){
		this.hide();
	}
	
	public void DFTTransform(int[] pixels, int iw, int ih) {
				
		//��ͼ�����DFT
		ColorModel cm = ColorModel.getRGBdefault();
		
		// ����ֵ
		w = 1;
		h = 1;
		wp = 0;
		hp = 0;
		
		//�������DFT�Ŀ�Ⱥ͸߶ȣ�2�������η���
		while(w*2 <= iw)
		{
			w *= 2;
			wp++;
		}
		
		while(h*2 <= ih)
		{
			h *= 2;
			hp++;
		}
		
		//�����ڴ�
		Complex[] td = new Complex[h*w];
		Complex[] fd = new Complex[h*w];	
		transpixels = new int[h*w];
		
		//��ʼ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[i*w+j] = new Complex(pixels[i*iw+j]&0xff,0);
				fd[i*w+j] = new Complex();
			}
		}
		
		//��ʼ���м����
		Complex[] tempW1 = new Complex[w];
		Complex[] tempW2 = new Complex[w];
		
		for(j = 0; j < w; j++)
		{
			tempW1[j] = new Complex(0,0);
			tempW2[j] = new Complex(0,0);
		}
		
		//��y�����Ͻ���DFT
		for(i = 0; i < h; i++)
		{
			//ÿһ����DFT
			for(j = 0; j < w; j++)
			{
				tempW1[j] = td[i*w+j];
			}
			
			//һάDFT
			tempDFT = new DFT();
			tempDFT.setData(tempW1,wp);
			tempW2 = tempDFT.getData();
			
			for(j = 0; j < w; j++)
			{
				fd[i*w+j] = tempW2[j];
			}
		}
		
		//����任���
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[j*h+i] = fd[i*w+j];
			}
		}
		
		//��ʼ���м����
		tempW1 = new Complex[h];
		tempW2 = new Complex[h];
		for(j = 0; j < h; j++)
		{
			tempW1[j] = new Complex(0,0);					
			tempW2[j] = new Complex(0,0);
		}
	
		//��x�������DFT
		for(j = 0; j < w; j++)
		{
			//ÿһ����DFT
			for(i = 0; i < h; i++)
			{
				tempW1[i] = td[j*h+i];
			}
			
			//һάDFT
			tempDFT = new DFT();
			tempDFT.setData(tempW1,hp);
			tempW2 = tempDFT.getData();
			
			for(i = 0; i < h; i++)
			{
				fd[j*h+i] = tempW2[i];
			}
		}
		
		temp2 = 0;
		temp3 = 0;
		ttt = 0;
		
		//����Ƶ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				double re = fd[j*h+i].re;
				double im = fd[j*h+i].im;
				
				
				temp0 = Math.sqrt(re*re+im*im);
				if(temp0 > 1){
					ttt++;
				}
				
				temp1 = (int)(Math.sqrt(re*re+im*im)*graytemp1);
				transpixels[i*w+j] = temp1;
				
				if(temp1 > temp2){ 
					temp2 = temp1; 
				}
				
				if(temp1 < temp3){ 
					temp3 = temp1; 
				}					
				
				/*
				//ʹƵ��ƽ�Ƶ��м�
				int ii = 0,jj = 0;
				if(i < h/2) { 
					ii = i+h/2; 
				} 
				else { 
					ii = i-h/2; 
				}
				
				if(j < w/2){ 
					jj = j+w/2; 
				} 
				else {
					jj=j-w/2; 
				}
				transpixels[ii*w+jj] = temp;
				*/
			}
		}
		System.out.println("DFT no zero pixels number:"+ttt);
		
		for(i = 0; i < w*h; i++)
		{
			transpixels[i] = (transpixels[i]*graytemp2)/(temp2-temp3);
			int x = transpixels[i];
			transpixels[i] = (255<<24)|(x<<16)|(x<<8)|(x);
		}
	
		//�������е����ز���һ��ͼ��
		ImageProducer ip = new MemoryImageSource(w,h,transpixels,0,w);
		transim = createImage(ip);

	}
	
	public void CEDTransform(Image im, 
			float lowThreshold, 
			float highThreshold, 
			float gaussianKernelRadius, 
			int gaussianKernelWidth, 
			boolean contrastNormalized) {
		CannyEdgeDetector detector = new CannyEdgeDetector();
		detector.setLowThreshold(lowThreshold);
		detector.setHighThreshold(highThreshold);
		detector.setGaussianKernelRadius(gaussianKernelRadius);
		detector.setGaussianKernelWidth(gaussianKernelWidth);
		detector.setContrastNormalized(contrastNormalized);
		//apply it to an image
		detector.setSourceImage(convertImageToBuffer(im));
		detector.process();
		BufferedImage edges = detector.getEdgesImage();
		transim =(Image)edges;
		
		int iw = transim.getWidth(this);
		int ih = transim.getHeight(this);
		
		//��ȡͼ�������pixels
		int pixels[] = new int[iw*ih];
		try	{
			PixelGrabber pg = new PixelGrabber(transim,0,0,iw,ih,pixels,0,iw);
		pg.grabPixels();
		}
		catch (InterruptedException e3) {
			e3.printStackTrace();
		}
		
		System.out.println(iw);
		System.out.println(ih);
		
		Xprt = new DXFExport();		
		
		
		for(i = 0; i < ih; i++)
		{
			for(j = 0; j < iw; j++)
			{
				if(pixels[i*iw+j] == -1)
				{
					sgDrawPixel(j,i);
					System.out.print("*");
				}
				else
					System.out.print(" ");
			}
			System.out.println();
		}
			
		try {
			Xprt.saveToFile("DXFFile.dxf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	  /**
	   * Draw a pixel.
	  */
	  public void sgDrawPixel(int x, int y) {
	    Color cl = Color.BLACK;
	    String strPixel = "Pixel";
	    if (Xprt!= null) {
	      DXFLayer Layer = new DXFLayer(strPixel);
	      Xprt.setCurrentLayer(Layer);
	      DXFData Dt = new DXFData();
	      Dt.LayerName = Layer.getName();
	      Dt.Color = Constants.convertColorRGBToDXF(cl);
	      Dt.Point = new DXFPoint(x, -y, 0);
	      Xprt.addPixel(Dt);
	      Dt.Point.setTo(x - 10, -(y - 3), 0);
	      Dt.Point1 = new DXFPoint(Dt.Point.X, Dt.Point.Y, 0);
	      Dt.FHeight = 10;
	      Dt.HAlign = 1;
	      //Dt.Text = new String(strPixel);
	      Xprt.addText(Dt);
	      Dt = null;
	    }
	  }

	public void DCTTransform(int[] pixels, int iw, int ih) {
				
		//��ͼ�����DCT
		ColorModel cm = ColorModel.getRGBdefault();
		
		// ����ֵ
		w = 1;
		h = 1;
		wp = 0;
		hp = 0;
		
		//�������DCT�Ŀ�Ⱥ͸߶ȣ�2�������η���
		while(w*2 <= iw)
		{
			w *= 2;
			wp++;
		}
		
		while(h*2 <= ih)
		{
			h *= 2;
			hp++;
		}
		
		//�����ڴ�
		double[] td = new double[h*w];
		double[] fd = new double[h*w];	
		transpixels = new int[h*w];
		
		//��ʼ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[i*w+j] = pixels[i*iw+j]&0xff;
				fd[i*w+j] = 0;
			}
		}
		
		/*	
		tempDCT = new DCT();
		tempDCT.setData(td,wp+hp);
		fd = tempDCT.getData();
		*/

		//��ʼ���м����
		double[] tempW1 = new double[w];
		double[] tempW2 = new double[w];
		
		for(j = 0; j < w; j++)
		{
			tempW1[j] = 0;
			tempW2[j] = 0;
		}
		
		//��y�����Ͻ���DCT
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				tempW1[j] = td[i*w+j];
			}
			
			tempDCT = new DCT();
			tempDCT.setData(tempW1,wp);
			tempW2 = tempDCT.getData();
			
			for(j = 0; j < w; j++)
			{
				fd[i*w+j] = tempW2[j]*Math.sqrt(w);
			}
		}
		
		//����任���
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[j*h+i] = fd[i*w+j];
			}
		}
		
		//��ʼ���м����
		tempW1 = new double[h];
		tempW2 = new double[h];
		for(j = 0; j < h; j++)
		{
			tempW1[j] = 0;					
			tempW2[j] = 0;
		}

		//��x�������DCT
		for(j = 0; j < w; j++)
		{
			for(i = 0; i < h; i++)
			{
				tempW1[i] = td[j*h+i];
			}
			
			tempDCT = new DCT();
			tempDCT.setData(tempW1,hp);
			tempW2 = tempDCT.getData();
			
			for(i = 0; i < h; i++)
			{
				fd[j*h+i] = tempW2[i]*Math.sqrt(h);
			}
		}
		
		temp2 = 0;
		temp3 = 0;
		ttt = 0;
		
		//����Ƶ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{					
			
				temp0 = Math.abs(fd[j*h+i]);
				if(temp0 > 1){
					ttt++;
				}
			
				temp1 = (int)(Math.abs(fd[j*h+i])*graytemp1);
				transpixels[i*w+j] = temp1;
				
				if(temp1 > temp2){ 
					temp2 = temp1; 
				}
				
				if(temp1 < temp3){ 
					temp3 = temp1; 
				}					

			}
		}
		System.out.println("DCT no zero pixels number:"+ttt);
		
		for(i = 0; i < w*h; i++)
		{
			transpixels[i] = (transpixels[i]*graytemp2)/(temp2-temp3);
			int x = transpixels[i];
			transpixels[i] = (255<<24)|(x<<16)|(x<<8)|(x);
		}
		
		//�������е����ز���һ��ͼ��
		ImageProducer ip = new MemoryImageSource(w,h,transpixels,0,w);
		transim = createImage(ip);
		
	}
	
	public void WHTTransform(int[] pixels, int iw, int ih) {
				
		//��ͼ�����WHT
		ColorModel cm = ColorModel.getRGBdefault();
		
		// ����ֵ
		w = 1;
		h = 1;
		wp = 0;
		hp = 0;
		
		//�������WHT�Ŀ�Ⱥ͸߶ȣ�2�������η���
		while(w*2 <= iw)
		{
			w *= 2;
			wp++;
		}
		
		while(h*2 <= ih)
		{
			h *= 2;
			hp++;
		}
		
		//�����ڴ�
		double[] td = new double[h*w];
		double[] fd = new double[h*w];	
		transpixels = new int[h*w];
		
		//��ʼ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[i*w+j] = pixels[i*iw+j]&0xff;
				fd[i*w+j] = 0;
			}
		}
		
		/*	
		tempWHT = new WHT();
		tempWHT.setData(td,wp+hp);
		fd = tempWHT.getData();
		*/

		//��ʼ���м����
		double[] tempW1 = new double[w];
		double[] tempW2 = new double[w];
		
		for(j = 0; j < w; j++)
		{
			tempW1[j] = 0;
			tempW2[j] = 0;
		}
		
		//��y�����Ͻ���WHT
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				tempW1[j] = td[i*w+j];
			}
			
			tempWHT = new WHT();
			tempWHT.setData(tempW1,wp);
			tempW2 = tempWHT.getData();
			
			for(j = 0; j < w; j++)
			{
				fd[i*w+j] = tempW2[j];
			}
		}
		
		//����任���
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[j*h+i] = fd[i*w+j];
			}
		}
		
		//��ʼ���м����
		tempW1 = new double[h];
		tempW2 = new double[h];
		for(j = 0; j < h; j++)
		{
			tempW1[j] = 0;					
			tempW2[j] = 0;
		}

		//��x�������WHT
		for(j = 0; j < w; j++)
		{
			for(i = 0; i < h; i++)
			{
				tempW1[i] = td[j*h+i];
			}
			
			tempWHT = new WHT();
			tempWHT.setData(tempW1,hp);
			tempW2 = tempWHT.getData();
			
			for(i = 0; i < h; i++)
			{
				fd[j*h+i] = tempW2[i];
			}
		}
		
		temp2 = 0;
		temp3 = 0;
		ttt = 0;
		
		//����Ƶ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{					
			
				temp0 = Math.abs(fd[j*h+i]);
				if(temp0 > 1){
					ttt++;
				}
			
				temp1 = (int)(Math.abs(fd[j*h+i])*graytemp1);
				transpixels[i*w+j] = temp1;
				
				if(temp1 > temp2){ 
					temp2 = temp1; 
				}
				
				if(temp1 < temp3){ 
					temp3 = temp1; 
				}									

			}
		}
		System.out.println("WHT no zero pixels number:"+ttt);
		
		for(i = 0; i < w*h; i++)
		{
			transpixels[i] = (transpixels[i]*graytemp2)/(temp2-temp3);
			int x = transpixels[i];
			transpixels[i] = (255<<24)|(x<<16)|(x<<8)|(x);
		}
		
		//�������е����ز���һ��ͼ��
		ImageProducer ip = new MemoryImageSource(w,h,transpixels,0,w);
		transim = createImage(ip);
		
	}
	
	public void SLTTransform(int[] pixels, int iw, int ih) {
				
		//��ͼ�����SLT
		ColorModel cm = ColorModel.getRGBdefault();
		
		// ����ֵ
		w = 1;
		h = 1;
		wp = 0;
		hp = 0;
		
		//�������SLT�Ŀ�Ⱥ͸߶ȣ�2�������η���
		while(w*2 <= iw)
		{
			w *= 2;
			wp++;
		}
		
		while(h*2 <= ih)
		{
			h *= 2;
			hp++;
		}
		
		//�����ڴ�
		double[] td = new double[h*w];
		double[] fd = new double[h*w];	
		transpixels = new int[h*w];
		
		//��ʼ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[i*w+j] = pixels[i*iw+j]&0xff;
				fd[i*w+j] = 0;
			}
		}
		
		//��ʼ���м����
		double[] tempW1 = new double[w];
		double[] tempW2 = new double[w];
		
		for(j = 0; j < w; j++)
		{
			tempW1[j] = 0;
			tempW2[j] = 0;
		}
		
		//��y�����Ͻ���SLT
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				tempW1[j] = td[i*w+j];
			}
			
			tempSLT = new SLT();
			tempSLT.setData(tempW1,wp);
			tempW2 = tempSLT.getData();
			
			for(j = 0; j < w; j++)
			{
				fd[i*w+j] = tempW2[j];
			}
		}
		
		//����任���
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[j*h+i] = fd[i*w+j];
			}
		}
		
		//��ʼ���м����
		tempW1 = new double[h];
		tempW2 = new double[h];
		for(j = 0; j < h; j++)
		{
			tempW1[j] = 0;					
			tempW2[j] = 0;
		}

		//��x�������SLT
		for(j = 0; j < w; j++)
		{
			for(i = 0; i < h; i++)
			{
				tempW1[i] = td[j*h+i];
			}
			
			tempSLT = new SLT();
			tempSLT.setData(tempW1,hp);
			tempW2 = tempSLT.getData();
			
			for(i = 0; i < h; i++)
			{
				fd[j*h+i] = tempW2[i];
			}
		}
		
		temp2 = 0;
		temp3 = 0;
		ttt = 0;
		
		//����Ƶ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{					
			
				temp0 = Math.abs(fd[j*h+i]);
				if(temp0 > 1){
					ttt++;
				}
			
				temp1 = (int)(Math.abs(fd[j*h+i])*graytemp1);
				transpixels[i*w+j] = temp1;
				
				if(temp1 > temp2){ 
					temp2 = temp1; 
				}
				
				if(temp1 < temp3){ 
					temp3 = temp1; 
				}					

			}
		}
		System.out.println("SLT no zero pixels number:"+ttt);
		
		for(i = 0; i < w*h; i++)
		{
			transpixels[i] = (transpixels[i]*graytemp2)/(temp2-temp3);
			int x = transpixels[i];
			transpixels[i] = (255<<24)|(x<<16)|(x<<8)|(x);
		}
		
		//�������е����ز���һ��ͼ��
		ImageProducer ip = new MemoryImageSource(w,h,transpixels,0,w);
		transim = createImage(ip);
		
	}
	
	public void USTTransform(int[] pixels, int iw, int ih) {
				
		//��ͼ�����UST
		ColorModel cm = ColorModel.getRGBdefault();
		
		// ����ֵ
		w = 1;
		h = 1;
		wp = 0;
		hp = 0;
		
		//�������UST�Ŀ�Ⱥ͸߶ȣ�2�������η���
		while(w*2 <= iw)
		{
			w *= 2;
			wp++;
		}
		
		while(h*2 <= ih)
		{
			h *= 2;
			hp++;
		}
		
		//�����ڴ�
		double[] td = new double[h*w];
		double[] fd = new double[h*w];	
		transpixels = new int[h*w];
		
		//��ʼ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[i*w+j] = pixels[i*iw+j]&0xff;
				fd[i*w+j] = 0;
			}
		}
		
		//��ʼ���м����
		double[] tempW1 = new double[w];
		double[] tempW2 = new double[w];
		
		for(j = 0; j < w; j++)
		{
			tempW1[j] = 0;
			tempW2[j] = 0;
		}
		
		//��y�����Ͻ���UST
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				tempW1[j] = td[i*w+j];
			}
			
			tempUST = new UST();
			tempUST.setData(tempW1,wp);
			tempW2 = tempUST.getData();
			
			for(j = 0; j < w; j++)
			{
				fd[i*w+j] = tempW2[j];
			}
		}
		
		//����任���
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[j*h+i] = fd[i*w+j];
			}
		}
		
		//��ʼ���м����
		tempW1 = new double[h];
		tempW2 = new double[h];
		for(j = 0; j < h; j++)
		{
			tempW1[j] = 0;					
			tempW2[j] = 0;
		}

		//��x�������UST
		for(j = 0; j < w; j++)
		{
			for(i = 0; i < h; i++)
			{
				tempW1[i] = td[j*h+i];
			}
			
			tempUST = new UST();
			tempUST.setData(tempW1,hp);
			tempW2 = tempUST.getData();
			
			for(i = 0; i < h; i++)
			{
				fd[j*h+i] = tempW2[i];
			}
		}
		
		temp2 = 0;
		temp3 = 0;
		ttt = 0;
		
		//����Ƶ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{			
			
				temp0 = Math.abs(fd[j*h+i]);
				if(temp0 > 1){
					ttt++;
				}
					
				temp1 = (int)(Math.abs(fd[j*h+i])*graytemp1);
				transpixels[i*w+j] = temp1;
				
				if(temp1 > temp2){ 
					temp2 = temp1; 
				}
				
				if(temp1 < temp3){ 
					temp3 = temp1; 
				}					

			}
		}
		System.out.println("UST no zero pixels number:"+ttt);
		
		for(i = 0; i < w*h; i++)
		{
			transpixels[i] = (transpixels[i]*graytemp2)/(temp2-temp3);
			int x = transpixels[i];
			transpixels[i] = (255<<24)|(x<<16)|(x<<8)|(x);
		}
		
		//�������е����ز���һ��ͼ��
		ImageProducer ip = new MemoryImageSource(w,h,transpixels,0,w);
		transim = createImage(ip);
		
	}
	
	public void VSTTransform(int[] pixels, int iw, int ih) {
				
		//��ͼ�����VST
		ColorModel cm = ColorModel.getRGBdefault();
		
		// ����ֵ
		w = 1;
		h = 1;
		wp = 0;
		hp = 0;
		
		//�������VST�Ŀ�Ⱥ͸߶ȣ�2�������η���
		while(w*2 <= iw)
		{
			w *= 2;
			wp++;
		}
		
		while(h*2 <= ih)
		{
			h *= 2;
			hp++;
		}
		
		//�����ڴ�
		double[] td = new double[h*w];
		double[] fd = new double[h*w];	
		transpixels = new int[h*w];
		
		//��ʼ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[i*w+j] = pixels[i*iw+j]&0xff;
				fd[i*w+j] = 0;
			}
		}
		
		//��ʼ���м����
		double[] tempW1 = new double[w];
		double[] tempW2 = new double[w];
		
		for(j = 0; j < w; j++)
		{
			tempW1[j] = 0;
			tempW2[j] = 0;
		}
		
		//��y�����Ͻ���VST
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				tempW1[j] = td[i*w+j];
			}
			
			tempVST = new VST();
			tempVST.setData(tempW1,wp);
			tempW2 = tempVST.getData();
			
			for(j = 0; j < w; j++)
			{
				fd[i*w+j] = tempW2[j];
			}
		}
		
		//����任���
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[j*h+i] = fd[i*w+j];
			}
		}
		
		//��ʼ���м����
		tempW1 = new double[h];
		tempW2 = new double[h];
		for(j = 0; j < h; j++)
		{
			tempW1[j] = 0;					
			tempW2[j] = 0;
		}

		//��x�������VST
		for(j = 0; j < w; j++)
		{
			for(i = 0; i < h; i++)
			{
				tempW1[i] = td[j*h+i];
			}
			
			tempVST = new VST();
			tempVST.setData(tempW1,hp);
			tempW2 = tempVST.getData();
			
			for(i = 0; i < h; i++)
			{
				fd[j*h+i] = tempW2[i];
			}
		}
		
		temp2 = 0;
		temp3 = 0;
		ttt = 0;
		
		//����Ƶ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{	
			
				temp0 = Math.abs(fd[j*h+i]);
				if(temp0 > 1){
					ttt++;
				}
							
				temp1 = (int)(Math.abs(fd[j*h+i])*graytemp1);
				transpixels[i*w+j] = temp1;
				
				if(temp1 > temp2){ 
					temp2 = temp1; 
				}
				
				if(temp1 < temp3){ 
					temp3 = temp1; 
				}					
				
			}
		}
		System.out.println("VST no zero pixels number:"+ttt);
		
		for(i = 0; i < w*h; i++)
		{
			transpixels[i] = (transpixels[i]*graytemp2)/(temp2-temp3);
			int x = transpixels[i];
			transpixels[i] = (255<<24)|(x<<16)|(x<<8)|(x);
		}
		
		//�������е����ز���һ��ͼ��
		ImageProducer ip = new MemoryImageSource(w,h,transpixels,0,w);
		transim = createImage(ip);
		
	}
	
	public void DWTTransform(int[] pixels, int iw, int ih) {
				
		//��ͼ�����DWT
		ColorModel cm = ColorModel.getRGBdefault();
		
		// ����ֵ
		w = 1;
		h = 1;
		wp = 0;
		hp = 0;
		
		//�������DWT�Ŀ�Ⱥ͸߶ȣ�2�������η���
		while(w*2 <= iw)
		{
			w *= 2;
			wp++;
		}
		
		while(h*2 <= ih)
		{
			h *= 2;
			hp++;
		}
		
		//�����ڴ�
		double[] td = new double[h*w];
		double[] fd = new double[h*w];	
		transpixels = new int[h*w];
		
		//��ʼ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[i*w+j] = pixels[i*iw+j]&0xff;
				fd[i*w+j] = 0;
			}
		}
		
		//��ʼ���м����
		double[] tempW1 = new double[w];
		double[] tempW2 = new double[w];
		
		for(j = 0; j < w; j++)
		{
			tempW1[j] = 0;
			tempW2[j] = 0;
		}
		
		//��y�����Ͻ���DWT
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				tempW1[j] = td[i*w+j];
			}
			
			tempDWT = new DWT();
			tempDWT.setData(tempW1,wp);
			tempW2 = tempDWT.getData();
			
			for(j = 0; j < w; j++)
			{
				fd[i*w+j] = tempW2[j];
			}
		}
		
		//����任���
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				td[j*h+i] = fd[i*w+j];
			}
		}
		
		//��ʼ���м����
		tempW1 = new double[h];
		tempW2 = new double[h];
		for(j = 0; j < h; j++)
		{
			tempW1[j] = 0;					
			tempW2[j] = 0;
		}

		//��x�������VST
		for(j = 0; j < w; j++)
		{
			for(i = 0; i < h; i++)
			{
				tempW1[i] = td[j*h+i];
			}
			
			tempDWT = new DWT();
			tempDWT.setData(tempW1,hp);
			tempW2 = tempDWT.getData();
			
			for(i = 0; i < h; i++)
			{
				fd[j*h+i] = tempW2[i];
			}
		}
		
		temp2 = 0;
		temp3 = 0;
		ttt = 0;
		
		//����Ƶ��
		for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{					
			
				temp0 = Math.abs(fd[j*h+i]);
				if(temp0 > 1){
					ttt++;
				}
			
				temp1 = (int)(Math.abs(fd[j*h+i])*graytemp1);
				transpixels[i*w+j] = temp1;
				
				if(temp1 > temp2){ 
					temp2 = temp1; 
				}
				
				if(temp1 < temp3){ 
					temp3 = temp1; 
				}					

			}
		}
		System.out.println("DWT no zero pixels number:"+ttt);
		
		for(i = 0; i < w*h; i++)
		{
			transpixels[i] = (transpixels[i]*graytemp2)/(temp2-temp3);
			int x = transpixels[i];
			transpixels[i] = (255<<24)|(x<<16)|(x<<8)|(x);
		}
		
		//�������е����ز���һ��ͼ��
		ImageProducer ip = new MemoryImageSource(w,h,transpixels,0,w);
		transim = createImage(ip);
		
	}
	
	public void paint(Graphics g) {
		
		setBackground(Color.lightGray);
		g.drawImage(transim,25,50,this);	

	}
	
}