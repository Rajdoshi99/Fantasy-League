//NEW COLUMN IN USER.SQL
//UPDATING TOTAL OF USER  AT TIME OF UPDATING

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class user {
	private static Connection Con;
	private static Connection Con2;
	private static Statement st;
	public static JFrame frame1=new JFrame("Frame1");
   public static JFrame frame = new JFrame("LOG-IN");
   public static JPanel p;
   public static JButton b1,b3,quitgame;
   public static JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
   public static JTextField t1,t2,t3,t4,t5,t6;

   static int as, ycard, sav, rcard, goals, pid;
	private static void build_con()
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		 Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/league ","root", "");
		 st =(Statement) Con.createStatement();
		}
		catch(Exception e)
		{
		System.out.print("Do not connect to DB - Error:"+e);
		}
	}
	
	private static void build_con2()
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		 Con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/league ","root", "");
		 st=(Statement) Con2.createStatement();
		}
		catch(Exception e)
		{
		System.out.print("Do not connect to DB - Error:"+e);
		}
	}
	

   public static void main(String[] args) {

       frame.setSize(300, 150);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       JPanel panel = new JPanel();
       frame.add(panel);
       placeComponents(panel);
       frame.setVisible(true);

   }

   public static void placeComponents(JPanel panel) {
       panel.setLayout(null);

       JLabel userLabel = new JLabel("User");
       userLabel.setBounds(10, 10, 80, 25);
       panel.add(userLabel);

       final JTextField userText = new JTextField(20);
       userText.setBounds(100, 10, 160, 25);
       panel.add(userText);

       JLabel passwordLabel = new JLabel("Password");
       passwordLabel.setBounds(10, 40, 80, 25);
       panel.add(passwordLabel);

       final JPasswordField passwordText = new JPasswordField(20);
       passwordText.setBounds(100, 40, 160, 25);
       panel.add(passwordText);

       JButton loginButton = new JButton("login");
       loginButton.setBounds(100, 80, 80, 25);
       panel.add(loginButton);

       loginButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent arg0){
               String uname=userText.getText();
               String psd=passwordText.getText();
               if(uname.equals("name") && psd.equals("password"))
               {
                   frame.setVisible(false);
                   creategui();
               }
           }
       });
   }
   public static void creategui() {
       frame1 = new JFrame("Main Menu");
       frame1.setVisible(true);
       frame1.setSize(800, 800);
       frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       p = new JPanel(new BorderLayout(8, 8));
       //p.setBackground(Color.YELLOW);
       p.setBackground(Color.LIGHT_GRAY);

       l2=new JLabel("Player ID:");
       l2.setBounds(150,70,200,70);
       p.add(l2);
       t1 = new JTextField(20);
       t1.setBounds(250,100,150,20);
       t1.setText(null);
       p.add(t1);


       l4=new JLabel("Goals:");
       l4.setBounds(150,170,200,70);
       p.add(l4);
       t2 = new JTextField(20);
       t2.setBounds(250,200,150,20);
       t2.setText(null);
       p.add(t2);


       l5=new JLabel("Assist:");
       l5.setBounds(150,270,200,70);
       p.add(l5);
       t3 = new JTextField(20);
       t3.setBounds(250,300,150,20);
       t3.setText(null);
       p.add(t3);


       l6=new JLabel("Yellow Card:");
       l6.setBounds(150,370,200,70);
       p.add(l6);
       t4 = new JTextField(20);
       t4.setBounds(250,400,150,20);
       t4.setText(null);
       p.add(t4);


       l7=new JLabel("Red Card:");
       l7.setBounds(150,470,200,70);
       p.add(l7);
       t5 = new JTextField(20);
       t5.setBounds(250,500,150,20);
       t5.setText(null);
       p.add(t5);


       l8=new JLabel("Saves:");
       l8.setBounds(150,570,200,70);
       p.add(l8);
       t6 = new JTextField(20);
       t6.setBounds(250,600,150,20);
       t6.setText(null);
       p.add(t6);


       b3 = new JButton("Log Out");
       b3.setBounds(50, 700, 120, 40);
       l3 = new JLabel("");
       p.add(b3, BorderLayout.CENTER);
       p.add(l3);

       b1 = new JButton("Update");
       b1.setBounds(320, 700, 120, 40);
       l1 = new JLabel("");
       p.add(b1, BorderLayout.CENTER);
       p.add(l1);

       quitgame = new JButton("Quit");
       quitgame.setBounds(600, 700, 120, 40);
       l9 = new JLabel("");
       p.add(quitgame, BorderLayout.CENTER);
       p.add(l9);
       frame1.getContentPane().add(p);
       frame1.setLocationRelativeTo(null);

       b1.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               pid = Integer.parseInt(t1.getText());
               goals = Integer.parseInt(t2.getText());
               as = Integer.parseInt(t3.getText());
               ycard = Integer.parseInt(t4.getText());
               rcard = Integer.parseInt(t5.getText());
               sav = Integer.parseInt(t6.getText());
               if (pid > 0 && pid <= 20 && (ycard == 1 || ycard == 0) && (rcard == 1 || rcard == 0)) {
                   JOptionPane.showMessageDialog(p,"Updated Successfully","UPDATE",JOptionPane.PLAIN_MESSAGE);
/* Insert Your Code from here. Utilise the variables above */
               	
               //for league table to calculate total and updating
                  
            	    build_con();  
            		try {
            			int a = 4*goals - ycard - 2*rcard + 3*as + (int)sav/3;
            			String query = "SELECT * FROM league WHERE player_id = ?";
            		    PreparedStatement preparedStmt;
            		    int id  = pid;
            		    ResultSet res = null;
            			preparedStmt = Con.prepareStatement(query);
            			preparedStmt.setInt   (1, id);
            			res = preparedStmt.executeQuery();
            			res.next();
            			goals += res.getInt("Goal");
            			as += res.getInt("Assist");
            			ycard += res.getInt("Yellow_Card");
            			rcard += res.getInt("Red_Card");
            			sav += res.getInt("Saves");
            			int total = 4*goals - ycard - 2*rcard + 3*as + (int)sav/3;
            	
            			total += res.getInt("Total");
            			query = "UPDATE league SET Goal = ? , Assist = ?, Yellow_Card = ?, Red_Card = ?,Saves = ?, Total = ?  WHERE player_id = ?";
            			preparedStmt = Con.prepareStatement(query);
            			preparedStmt.setInt   (1, goals);
            			preparedStmt.setInt   (2, as);
            			preparedStmt.setInt   (3, ycard);
            			preparedStmt.setInt   (4, rcard);
            			preparedStmt.setInt   (5, sav);
            			preparedStmt.setInt   (6, total);
            			preparedStmt.setInt   (7, pid);
            			preparedStmt.executeUpdate();
            			if(check(pid))
            			{
            				up_user(a);
            			}
            			Con.close();
            		} catch (SQLException ex) {
            			ex.printStackTrace();
            		}
            		
            	}
                   
                   
               else{
                   JOptionPane.showMessageDialog(p,"WRONG INPUTS!!","ERROR",JOptionPane.ERROR_MESSAGE);
               }
           }

		private void up_user(int a) {
			// TODO Auto-generated method stub
			build_con2();
			
			try {
				ResultSet r = null; 
					String query = "SELECT * FROM user";
					PreparedStatement preparedStmt;
					preparedStmt = Con.prepareStatement(query);
					r = preparedStmt.executeQuery();
					r.next();
					int t = r.getInt("Total");
					t += a;
					query = "UPDATE user Set Total = " +t;
					preparedStmt = Con.prepareStatement(query);
					preparedStmt.executeUpdate();	
				Con2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public boolean check(int id)//TO CHECK IF PLAYER IS IN USERS SQUAD
		{							//TRUE IF FOUND
			ResultSet res = null;
			String query = "SELECT * FROM user";
			try {
				
				res = ((java.sql.Statement) st).executeQuery(query);
				res.next();
			
			int i = 1;
			while(i < 6)
			{
				String a = "P_"+i;
				int x = res.getInt(a);
				
				if(x == id)
				{
					res.close();
					return true;	
				}
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
       }
       
    		   
    		   );

       b3.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e)
           {
               close();
           }
           private void close() {
               frame1.setVisible(false);
               frame.setVisible(true);
           }
       });

       quitgame.addActionListener(new ActionListener() {
           public void actionPerformed(final ActionEvent e) {
               tclose();
           }

           private void tclose() {
               frame1.setVisible(false);
               System.exit(0);
           }
       });

   }
}
