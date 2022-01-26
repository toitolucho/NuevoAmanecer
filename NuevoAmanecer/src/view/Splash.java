package view;

import java.awt.Color;
import java.awt.Font;

import Splash.SplashWindow;

public class Splash extends SplashWindow {

	public Splash(String image, boolean transparent) {
		super(image, transparent);
		// TODO Auto-generated constructor stub	
		
	}
	public void inicializar()
	{
            this.setDelay(1000);
	    this.setProgressBarMaximum(7);
	    this.setProgressBarValue(0);
	    this.setProgressBarBounds(10,120,315,5);
	    this.setProgressBarColor(Color.WHITE);
	    this.setProgressBarVisible(true);
	    this.setProgressTextBounds(10,135,200,15);
	    this.setProgressTextFont(new Font("SYSTEM",Font.BOLD,15));
	    this.setProgressTextColor(Color.WHITE);
	    this.setProgressTextVisible(true);
	    this.setForeground(new Color(10,250,30));
	    
	    this.setVisible(true);
	    //Wait
	    try{
	    Thread.sleep(100);
	    }catch(InterruptedException e){}
	    
	    
	    String punto = ".....";
	    for(int i =0; i<7; i++)
	    {
	    	this.setProgressText("Cargando"+punto);
	    	this.incrementProgressBarValue(1);
	    	punto = punto+"...";
	    }

	    this.close();
	}

	
	private static final long serialVersionUID = 1L;



}
