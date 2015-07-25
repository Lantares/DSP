import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class slider extends Frame {

	
	
	private float lowThreshold;
	private float highThreshold;
	private float gaussianKernelRadius;
	private int gaussianKernelWidth;
	private boolean contrastNormalized;
	
	private void initCED()
	{
		lowThreshold = 2.5f;
		highThreshold = 7.5f;
		gaussianKernelRadius = 2f;
		gaussianKernelWidth = 16;
		contrastNormalized = false;
	}
	
	public slider(Image im)
	{

		
		JLabel lowThresholdLabel, highThresholdLabel, gaussianKernelRadiusLabel, gaussianKernelWidthLabel, contrastNormalizedLabel;
		JSlider lowThresholdSlider, highThresholdSlider, gaussianKernelRadiusSlider, gaussianKernelWidthSlider;
		JCheckBox contrastNormalizedCheckBox;
		
		JButton defaultButton, confirmButton;
		JPanel buttonPanel; 
		
		String lowThresholdString = "lowThreshold";
		String highThresholdString = "highThreshold";
		String gaussianKernelRadiusString = "gaussianKernelRadius";
		String gaussianKernelWidthString = "gaussianKernelWidth";
		String contrastNormalizedString = "contrastNormalized";
		
		initCED();
		
		setSize(450,300);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				Quit();
			}
		});

		
        //lowThreshold
        lowThresholdLabel = new JLabel(lowThresholdString + " : " + lowThreshold);	
		lowThresholdSlider = new JSlider(0, 200, 25);
		lowThresholdSlider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider s = (JSlider)arg0.getSource();
				lowThreshold =  ((float)s.getValue())/10;
				lowThresholdLabel.setText(lowThresholdString + " : " + lowThreshold);
			}
		});
		lowThresholdSlider.setMajorTickSpacing(50);
		lowThresholdSlider.setMinorTickSpacing(10);
		lowThresholdSlider.setPaintTicks(true);
		
		
		//highThreshold
		highThresholdLabel = new JLabel(highThresholdString + " : " + highThreshold);	
		highThresholdSlider = new JSlider(0, 200, 75);
		highThresholdSlider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider s = (JSlider)arg0.getSource();
				highThreshold =  ((float)s.getValue())/10;
				highThresholdLabel.setText(highThresholdString + " : " + highThreshold);
			}
		});
		highThresholdSlider.setMajorTickSpacing(50);
		highThresholdSlider.setMinorTickSpacing(10);
		highThresholdSlider.setPaintTicks(true);
		
		
		//gaussianKernelRadius
		gaussianKernelRadiusLabel = new JLabel(gaussianKernelRadiusString + " : " + gaussianKernelRadius);	
		gaussianKernelRadiusSlider = new JSlider(1, 200, 20);
		gaussianKernelRadiusSlider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider s = (JSlider)arg0.getSource();
				gaussianKernelRadius =  ((float)s.getValue())/10;
				gaussianKernelRadiusLabel.setText(gaussianKernelRadiusString + " : " + gaussianKernelRadius);
			}
		});
		gaussianKernelRadiusSlider.setMajorTickSpacing(50);
		gaussianKernelRadiusSlider.setMinorTickSpacing(10);
		gaussianKernelRadiusSlider.setPaintTicks(true);
		
		
		//gaussianKernelWidth
		gaussianKernelWidthLabel = new JLabel(gaussianKernelWidthString + " : 16");	
		gaussianKernelWidthSlider = new JSlider(2, 50, 16);
		gaussianKernelWidthSlider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider s = (JSlider)arg0.getSource();
				gaussianKernelWidth =  s.getValue();
				gaussianKernelWidthLabel.setText(gaussianKernelWidthString + " : " + gaussianKernelWidth);
			}
		});
		gaussianKernelWidthSlider.setMajorTickSpacing(5);
		gaussianKernelWidthSlider.setMinorTickSpacing(20);
		gaussianKernelWidthSlider.setPaintTicks(true);
		
		
		//contrastNormalized
		contrastNormalizedLabel = new JLabel(contrastNormalizedString + " : " + contrastNormalized);
		contrastNormalizedCheckBox = new JCheckBox("contrastNormalizedCheckBox");
		contrastNormalizedCheckBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(contrastNormalizedCheckBox.isSelected())
					contrastNormalized = true;
				else
					contrastNormalized = false;
				contrastNormalizedLabel.setText(contrastNormalizedString + " : " + contrastNormalized);
			}
			
		});
		
		
		//button
		
		defaultButton = new JButton("default");
		defaultButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				initCED();
				
				lowThresholdSlider.setValue(25);
				lowThresholdLabel.setText(lowThresholdString + " : " + lowThreshold);
				highThresholdSlider.setValue(75);
				highThresholdLabel.setText(highThresholdString + " : " + highThreshold);
				gaussianKernelRadiusSlider.setValue(20);
				gaussianKernelRadiusLabel.setText(gaussianKernelRadiusString + " : " + gaussianKernelRadius);
				gaussianKernelWidthSlider.setValue(16);
				gaussianKernelWidthLabel.setText(gaussianKernelWidthString + " : " + gaussianKernelWidth);
				contrastNormalizedCheckBox.setSelected(false);
				contrastNormalizedLabel.setText(contrastNormalizedString + " : " + contrastNormalized);
			}
			
		});
		confirmButton = new JButton("confirm");
		confirmButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ImageTransform imagetrans = new ImageTransform();
				imagetrans.CEDTransform(im, lowThreshold, highThreshold, gaussianKernelRadius, gaussianKernelWidth, contrastNormalized);
				imagetrans.setTitle("Frequency Domain Image of Canny Transform");
				imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
				imagetrans.show();	
			}
			
		});
		buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(defaultButton);
		buttonPanel.add(confirmButton);
				
		
		JPanel contentPane=new JPanel();
        add(contentPane, BorderLayout.CENTER);
        GridLayout gird=new GridLayout(5,2); 
        contentPane.setLayout(gird);
        
        
		contentPane.add(lowThresholdLabel);
		contentPane.add(lowThresholdSlider);
		contentPane.add(highThresholdLabel);
		contentPane.add(highThresholdSlider);
		contentPane.add(gaussianKernelRadiusLabel);
		contentPane.add(gaussianKernelRadiusSlider);
		contentPane.add(gaussianKernelWidthLabel);
		contentPane.add(gaussianKernelWidthSlider);
		contentPane.add(contrastNormalizedLabel);
		contentPane.add(contrastNormalizedCheckBox);
	}
	
	
	
	public void Quit(){
		this.hide();
	}
	
}
