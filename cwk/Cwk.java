package cwk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * CWK - czasowy wylacznik komputera dla systemu Windows
 * Java Swing
 * Main Class, GUI
 */
public class Cwk extends JFrame {
	
	public Cwk() {
		setSize(400, 200);
		setResizable(false);
	    setLayout(new BorderLayout());
		setTitle("CWK");
       
		JPanel center = new JPanel();
        JPanel bottom = new JPanel();
        add(BorderLayout.CENTER, center);
        add(BorderLayout.SOUTH, bottom);
		center.setBackground(new Color(188,183,183));
        bottom.setBackground(Color.gray);
   
        JButton start = new JButton("START");
        JButton stop = new JButton("STOP");
        JLabel info = new JLabel("Shutdown in:");
        JTextField time = new JTextField();
        time.setColumns(3);
        JLabel info2 = new JLabel("[min]");
        JLabel timeleft = new JLabel("0:00:00");

        bottom.add(info);
        bottom.add(time);
        bottom.add(info2);
        bottom.add(start);
        bottom.add(stop);
        center.add(timeleft);
          
        ActionListener actionListener = new CwkService(this, start, stop, time, timeleft);
        start.addActionListener(actionListener);
        stop.addActionListener(actionListener); 
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);   
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		    @Override
		    public void run() {
		        Cwk cwk = new Cwk();
		    }
		});

	}

}
