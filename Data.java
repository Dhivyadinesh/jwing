package Model;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class Data extends JFrame implements ActionListener {
   JPanel panel;
   JLabel our_site, gsm_site, message1,message2;
   JTextField oursiteurl;
   JTextField gsmsiteurl;
   String gsmBatterydata;
   String ourBatterydata;
   JButton submit, cancel;
   Data() {
      // Username Label
      our_site = new JLabel();
      our_site.setText("91Mobiles Url :");
      oursiteurl = new JTextField();
      // Password Label
      gsm_site = new JLabel();
      gsm_site.setText("Gsm Url :");
      gsmsiteurl = new JTextField();
      // Submit
      submit = new JButton("SUBMIT");
      panel = new JPanel(new GridLayout(5, 5));
      panel.add(our_site);
      panel.add(oursiteurl);
      panel.add(gsm_site);
      panel.add(gsmsiteurl);
      message1 = new JLabel();
      panel.add(message1);
      message2 = new JLabel();
      panel.add(message2);
      panel.add(submit);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Adding the listeners to components..
      submit.addActionListener(this);
      add(panel, BorderLayout.CENTER);
      setTitle("Data to Compare !");
      setSize(1080,1080);
      setVisible(true);
   }
   public static void main(String[] args) {
      new Data();
   }
  
	@Override
	public void actionPerformed(ActionEvent e) {
		 String oursite = oursiteurl.getText();
         String gsmsite_url = gsmsiteurl.getText();
         Document doc;
		try {
			doc = Jsoup.connect(gsmsite_url).get();
			Thread.sleep(1000);
			gsmBatterydata = doc.select(
					"#body > div > div.review-header > div > div.center-stage.light.nobg.specs-accent > ul > li.help.accented.help-battery > strong")
					.text();
         if(gsmsite_url.contains("gsm")) {
             message1.setText("Data of gsmsite " + gsmBatterydata);
         }else {
             message1.setText(" Invalid Url.. ");
         }
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			doc = Jsoup.connect(oursite).get();
			Thread.sleep(1000);
			ourBatterydata = doc.select(
					"#overview > div.content_inner_wrap.ovrviw_spcl.border > div.overview_rgt > div:nth-child(6) > div.key_specs_box > table > tbody > tr > td:nth-child(4) > ul > li > label:nth-child(3)")
					.text();
         if(oursite.contains("91mobiles")) {
             message2.setText("Data of oursite " + ourBatterydata);
         }else {
             message2.setText(" Invalid Url.. ");
         }
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
