package chat;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login login = new Login();
		login.setVisible(true);
		login.setDefaultCloseOperation(login.EXIT_ON_CLOSE);
		login.setLocationRelativeTo(null);
		login.setResizable(false);
		login.setSize(220, 110);
	}

}
