import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JTextField textPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setBackground(Color.WHITE);
		setFont(new Font("Arial", Font.BOLD, 12));
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 271);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 250, 210));
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textUsername = new JTextField();
		textUsername.setBounds(232, 45, 320, 27);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		textPass = new JTextField();
		textPass.setBounds(232, 95, 320, 27);
		contentPane.add(textPass);
		textPass.setColumns(10);
		
		JLabel lblUser = new JLabel("Username :");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUser.setBounds(148, 48, 74, 21);
		contentPane.add(lblUser);
		
		JLabel lblPass = new JLabel("Password :");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPass.setBounds(148, 101, 74, 21);
		contentPane.add(lblPass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//set the mysql jdbc driver
					Class.forName("com.mysql.cj.jdbc.Driver");
					//set the mysql connection string
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/copl_db","root","");
					Statement stmt = (Statement) con.createStatement();
					//sql query for the login 
					String sql = "Select * from users_tbl where username='"+textUsername.getText() + "'and password='"+textPass.getText()+"'";
					
					//execute query
					ResultSet rs = ((java.sql.Statement)stmt).executeQuery(sql);
										

					// conditions for uname & pword
					if(rs.next()) {
						String userName = textUsername.getText();
						Dashboard frmtwo = new Dashboard();
						frmtwo.lbluserT.setText("USER : "+userName);
						frmtwo.setVisible(true);
						dispose();
						//JOptionPane.showMessageDialog(null, "Login successful...","Login Alert",2);
					}else if (textUsername.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Username is required...","Login Warning",2);
					}else if (textPass.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Password is required...","Login Warning",2);
					}else {
						JOptionPane.showMessageDialog(null, "Username or password incorrect...","Login Warning",2);
					}
				}catch(Exception ex) {
					System.out.print(ex);
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogin.setBounds(393, 133, 159, 23);
		contentPane.add(btnLogin);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(232, 133, 159, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\ACAD\\OOP\\Eclipse\\OE8\\OE8\\src\\image\\user1.png"));
		lblNewLabel.setBounds(10, 22, 128, 134);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(220, 24, 347, 144);
		contentPane.add(panel);
	}
}
