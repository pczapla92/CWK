package cwk;

import java.awt.event.*;
import javax.swing.*;
import java.util.TimerTask;
import java.io.IOException;

/**
 * 
 * Obsluga GUI
 *
 */
public class CwkService implements ActionListener {
		JButton buttonStop;
		JButton buttonStart;
		JTextField time;
		JLabel timeDisplay;
		JFrame mainWindow;



		int hours;
		int minutes;
		int seconds;
		//asd
    //asd2
		java.util.Timer timer;
		java.util.Timer displayTime;

	   	CwkService(JFrame mainFrame, JButton start, JButton stop, JTextField timeSet, JLabel timeLeft) {
	   			buttonStop = stop;
	   			buttonStart = start;
	   			time = timeSet;
	   			timeDisplay = timeLeft;
	   			mainWindow = mainFrame;
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
						
			if(e.getSource()==buttonStart) {
				String t=this.time.getText();
				int minutes;
				try {
					minutes = Integer.parseInt(t); }
				catch (Exception exn) {
					return;		
				}
				setIntegers(minutes);
				buttonStart.setEnabled(false);
				time.setText(null);
				timer(minutes);
				}
			
			if(e.getSource()==buttonStop) {
				buttonStart.setEnabled(true);
				timer.cancel();
				displayTime.cancel();
				mainWindow.setTitle("CWK");
				timeDisplay.setText("00:00:00");
			}		
		}
		
		public void timer(int minutes) {	
			displayTime = new java.util.Timer();			
			displayTime.schedule(new TimerTask() {
				  @Override
				  public void run() {
					  mainWindow.setTitle(titleDisplay());
					  timeDisplay.setText(titleDisplay());
					  runtimeIntegers();
				  }
				}, 0,1000);
			timer = new java.util.Timer();			
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
					  try {
						shutdown();
					} catch (IOException e) {
						return;
					}
				  }
				}, minutes*60*1000+1000);
			}
		
		public String titleDisplay() {
			String min;
			String sec;
			if (minutes<10)
				min="0"+Integer.toString(minutes);
			else
				min=Integer.toString(minutes);
			if (seconds<10)
				sec="0"+Integer.toString(seconds);
			else
				sec=Integer.toString(seconds);			
			return Integer.toString(hours)+":"+min+":"+sec;
		}
		
		public void setIntegers(int minutes) {
			hours=minutes/60;
			this.minutes=minutes-hours*60;
			seconds=0;
		}
		public void runtimeIntegers() {
			if(seconds==0 && minutes!=0) {
				minutes-=1;
				seconds=60;
				}
			if(minutes==0 && seconds==0 && hours>0) {
				hours-=1;
				minutes=59;
				seconds=60;
				}
			if(minutes==0 && seconds==0 && hours==0) {
				displayTime.cancel();
				return;
			}
			seconds-=1;
		}
		
		public static void shutdown() throws IOException {
				Runtime runtime = Runtime.getRuntime();
				Process proc = runtime.exec("shutdown -s -t 0");
				System.exit(0);
			}
		

}


