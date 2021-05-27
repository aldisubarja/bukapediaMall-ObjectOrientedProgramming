import java.util.Vector;

public class Account {

	private String name;
	private String username;
	private String password;
	private Vector<Receipt> vecReceipt =  new Vector<Receipt>();
	
	public Account(String name, String username, String password, Vector<Receipt> vecReceipt) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.vecReceipt = vecReceipt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Vector<Receipt> getVecReceipt() {
		return vecReceipt;
	}

	public void setVecReceipt(Vector<Receipt> vecReceipt) {
		this.vecReceipt = vecReceipt;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	public void usernameErrorCheck(Vector<Account> vecAccount) {
		int len=alphanumericCheck(username);
		for(int i=0;i<vecAccount.size();i++) {
			if(vecAccount.get(i).getUsername().equals(username)) {
				System.out.println("Username telah terdaftar");
				break;
			}
		}
		if(len!=username.length()) {
			System.out.println("Username hanya boleh terdiri dari huruf atau angka");
		}
		if(username.length()<6) System.out.println("Username harus 6 karakter atau lebih");
		if(username.length()>20)System.out.println("Username tidak boleh lebih dari 20 karakter");
	}
	
	public void passwordErrorCheck() {
		int len=alphanumericCheck(password);
		if(len!=password.length()) {
			System.out.println("Password hanya boleh terdiri dari huruf atau angka");
		}
		if(password.length()<6) System.out.println("Password harus 6 karakter atau lebih");
		if(password.length()>10)System.out.println("Password tidak boleh lebih dari 10 karakter");
	}
	
	public int usernameErrorCheckNumber(Vector<Account> vecAccount) {
		int flag=0;
		int len=alphanumericCheck(username);
		if(username.length()<6||username.length()>20) {
			flag+=1;
		}
		if(len!=username.length()) {
			flag+=1;
		}
		for(int i=0;i<vecAccount.size();i++) {
			if(vecAccount.get(i).getUsername().equals(username)) flag+=1;
		}
		return flag;
	}
	
	public int passwordErrorCheckNumber() {
		int flag=0;
		int len=alphanumericCheck(password);
		if(password.length()<6||password.length()>10) {
			flag+=1;
		}
		if(len!=password.length()) {
			flag+=1;
		}
		return flag;
	}
	
	public int alphanumericCheck(String test) {
		int len=0;
		for(int i=0;i<test.length();i++) {
			if(test.charAt(i)>='A'&&test.charAt(i)<='Z') len+=1;
			if(test.charAt(i)>='a'&&test.charAt(i)<='z') len+=1;
			if(test.charAt(i)>='0'&&test.charAt(i)<='9') len+=1;
		}
		return len;
	}

	
}
