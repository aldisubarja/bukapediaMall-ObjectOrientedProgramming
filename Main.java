import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	Vector<Account> vecAccount = new Vector<Account>();
	Account a;
	Receipt r;
	Item i;
	int success;
	int saveIndex;
	
	public Main() {
		int choose;
		do {
			cls();
			System.out.println("Bukapedia Mall");
			System.out.println("==============");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			System.out.print(">> ");
			choose = scan.nextInt();scan.nextLine();
			switch(choose) {
			case 1:
				success=-1;
				saveIndex=-1;
				login();
				if(success==1) {
					System.out.println("Login success!");
					pause();
					menuUtama();
				}else {
					System.out.println("Login failed!");
					pause();
				}
				break;
			case 2:
				register();
				break;
			case 3:
				System.out.println("Thankyou for using Bukapedia Mall!~");
				break;
			default:
				break;
			}
		}while(choose!=3);
	}
	
	private void cls() {
		for(int i=0;i<50;i++) System.out.println();
	}

	private void pause() {
		System.out.println();
		System.out.println("Press enter to continue");
		System.out.println();
		scan.nextLine();	
	}
	
	private void login() {
		String username;
		String password;
		int index = -1;
		System.out.print("Masukkan username anda: ");
		username = scan.nextLine();
		for(int i=0;i<vecAccount.size();i++) {
			if(vecAccount.get(i).getUsername().equals(username)) {
				index = i;
				break;
			}
		}
		if(index==-1) {
			System.out.println("Username tidak ditemukan");
			return;
		}else {
			do {
				System.out.print("Masukkan password anda[ketik 'batal!' untuk membatalkan login]: ");
				password = scan.nextLine();
				if(password.equalsIgnoreCase("batal!")) break;
				if(!password.equals(vecAccount.get(index).getPassword())) System.out.println("Passwordmu salah!");
			}while(!password.equals(vecAccount.get(index).getPassword()));
			if(password.equalsIgnoreCase("batal!")) return;
			if(password.equals(vecAccount.get(index).getPassword())) {
				saveIndex=index;
				success=1;
			}
		}
	}
	
	private void register() {
		String name,username,password;
		System.out.print("Masukan nama anda: ");
		name=scan.nextLine();
		a=new Account();
		a.setName(name);
		int error;
		do {
			error=0;
			System.out.print("Masukan username anda[6-20][huruf atau angka]:");
			username=scan.nextLine();
			a.setUsername(username);
			a.usernameErrorCheck(vecAccount);
			error+=a.usernameErrorCheckNumber(vecAccount);
		}while(error!=0);
		do {
			error=0;
			System.out.print("Masukan password anda[6-10][huruf atau angka]:");
			password=scan.nextLine();
			a.setPassword(password);
			a.passwordErrorCheck();
			error+=a.passwordErrorCheckNumber();
		}while(error!=0);
		vecAccount.add(a);
		System.out.println("Register telah berhasil!");
		pause();
	}
	
	private void menuUtama() {
		int choose;
		do {
			cls();
			System.out.println("Bukapedia Mall");
			System.out.println("==============");
			System.out.printf("Halo, %s\n\n",vecAccount.get(saveIndex).getName());
			System.out.println("1. Beli pulsa");
			System.out.println("2. Beli barang");
			System.out.println("3. History belanja");
			System.out.println("4. Log out");
			System.out.print(">> ");
			choose=scan.nextInt();scan.nextLine();
			switch(choose) {
			case 1:
				menuPulsa();
				break;
			case 2:
				menuBarang();
				break;
			case 3:
				historyBelanja();
				pause();
				break;
			case 4:
				System.out.println("Anda akan dikembalikan ke halaman login.");
				pause();
				break;
			}
		}while(choose!=4);
	}
	
	private void menuBarang() {
		int choose;
		r=new Receipt();
		r.getVecItem().removeAllElements();
		r.setCode(randomWord());
		r.setDate(dateGenerator());
		do {
			cls();
			System.out.println("Beli Barang");
			System.out.println("==========");
			System.out.println("1. Add Item");
			System.out.println("2. Lihat keranjang (sort by harga)");
			System.out.println("3. Lihat keranjang (sort by nama item)");
			System.out.println("4. Check out");
			System.out.println("5. Batalkan pesanan");
			System.out.print(">> ");
			choose = scan.nextInt();scan.nextLine();
			switch(choose) {
			case 1:
				addBarang();
				pause();
				break;
			case 2:
				System.out.println("List Barang");
				System.out.println("===========");
				r.printKeranjang(1);
				pause();
				break;
			case 3:
				System.out.println("List Barang");
				System.out.println("===========");
				r.printKeranjang(2);
				pause();
				break;
			case 4:
				checkOut();
				pause();
				break;
			case 5:
				r.batal();
				pause();
				break;
			default:
				break;
			}
		}while(choose!=4);
	}
	
	private void addBarang() {
		String name;
		int qty;
		Integer price=0;
		String ans;
		do {
			System.out.print("Silahkan masukan nama barang[tidak boleh kosong]: ");
			name=scan.nextLine();
		}while(name.equals(""));
		do {
			System.out.print("Masukan jumlah barang yang ingin dibeli[1-100]: ");
			qty=scan.nextInt();scan.nextLine();
		}while(qty<1||qty>100);
		price=qty*rand.nextInt(100001);
		do {
			System.out.print("Apakah anda yakin?[Y/N]: ");
			ans=scan.nextLine();
			if(ans.equals("Y")) {
				System.out.println("Pesanan telah masuk keranjang");
				i=new Barang(name, qty, price);
				r.getVecItem().add(i);
				break;
			}
			else if(ans.equals("N")) System.out.println("Pesanan telah dibatalkan");
		}while(!ans.equals("N"));
	}

	private void checkOut() {
		if(r.getVecItem().size()==0) {
			System.out.println("Anda tidak melakukan transaksi apapun");
		}else {
			System.out.println("Kode transaksi: "+r.getCode());
			System.out.println("Tanggal dan Waktu transaksi: "+r.getDate());
			System.out.println("Detail Transaksi");
			System.out.println("================");
			System.out.printf("%-4s%-20s%-20s%-20s\n","No","Nama Barang","Jumlah/Nominal","Harga");
			int index=1;
			for (Item i : r.getVecItem()) {
				System.out.printf("%-4d%-20s%-20d%-20d\n",index,i.getName(),i.getAmount(),i.getPrice());
				index++;
			}
			System.out.println("Transaksi disimpan, terimakasih!");
			vecAccount.get(saveIndex).getVecReceipt().add(r);
		}
	}
	
	private String dateGenerator() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();    
		return dtf.format(now);
	}
	
	private String randomWord() {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String code=""+str.charAt(rand.nextInt(26))+str.charAt(rand.nextInt(26))+str.charAt(rand.nextInt(26))+str.charAt(rand.nextInt(26))+str.charAt(rand.nextInt(26));
	    return code;
	}
	
	private void menuPulsa() {
		int choose;
		r=new Receipt();
		r.getVecItem().removeAllElements();
		r.setCode(randomWord());
		r.setDate(dateGenerator());
		do {
			cls();
			System.out.println("Beli Pulsa");
			System.out.println("==========");
			System.out.println("1. Add Item");
			System.out.println("2. Lihat keranjang (sort by harga)");
			System.out.println("3. Lihat keranjang (sort by nama item)");
			System.out.println("4. Check out");
			System.out.println("5. Batalkan pesanan");
			System.out.print(">> ");
			choose = scan.nextInt();scan.nextLine();
			switch(choose) {
			case 1:
				addPulsa();
				pause();
				break;
			case 2:
				System.out.println("List Pulsa");
				System.out.println("==========");
				r.printKeranjang(1);
				pause();
				break;
			case 3:
				System.out.println("List Pulsa");
				System.out.println("==========");
				r.printKeranjang(2);
				pause();
				break;
			case 4:
				checkOut();
				pause();
				break;
			case 5:
				r.batal();
				pause();
				break;
			default:
				break;
			}
		}while(choose!=4);
	}
	
	private void addPulsa() {
		String nomor;
		int nominal=0;
		Integer price=0;
		String ans;
		int flag;
		do {
			flag=0;
			System.out.print("Silahkan masukan nomor HP[angka saja]: ");
			nomor=scan.nextLine();
			for(int i=0;i<nomor.length();i++) {
				if(nomor.charAt(i)>='0'&&nomor.charAt(i)<='9') {
					flag+=1;
				}
			}
		}while(nomor.length()!=flag);
		int choose;
		do {
			System.out.println("Pilih nominal pulsa yang ingin dibeli: ");
			System.out.println("1. 10.000");
			System.out.println("2. 25.000");
			System.out.println("3. 50.000");
			System.out.println("4. 100.000");
			System.out.println("5. 150.000");
			System.out.println("6. 200.000");
			System.out.println("7. 300.000");
			System.out.println("8. 500.000");
			System.out.println("9. 1.000.000");
			System.out.print(">> ");
			choose=scan.nextInt();scan.nextLine();
			switch(choose){
			case 1:
				nominal = 10000;
				break;
			case 2:
				nominal = 25000;
				break;
			case 3:
				nominal = 50000;
				break;
			case 4:
				nominal = 100000;
				break;
			case 5:
				nominal = 150000;
				break;
			case 6:
				nominal = 200000;
				break;
			case 7:
				nominal = 300000;
				break;
			case 8:
				nominal = 500000;
				break;
			case 9:
				nominal = 1000000;
				break;
			default:
				break;
			}
		}while(choose<1||choose>9);
		price=nominal;
		price+=nominal*10/100;
		do {
			System.out.print("Apakah anda yakin?[Y/N]: ");
			ans=scan.nextLine();
			if(ans.equals("Y")) {
				System.out.println("Pesanan telah masuk keranjang");
				i=new Pulsa(nomor, nominal, price);
				r.getVecItem().add(i);
				break;
			}
			else if(ans.equals("N")) System.out.println("Pesanan telah dibatalkan");
		}while(!ans.equals("N"));
	}
	
	private void historyBelanja() {
		int idx1=1;
		for (Receipt r : vecAccount.get(saveIndex).getVecReceipt()) {
			System.out.println("Nomor "+idx1);
			System.out.println("Kode Pembelian : "+r.getCode());
			System.out.println("Tanggal dan Waktu Pembelian : "+r.getDate());
			System.out.println("Detail pembelian :");
			System.out.printf("%-4s%-20s%-20s%-20s\n","No","Nama Barang","Jumlah/Nominal","Harga");
			int idx2=1;
			for (Item i : r.getVecItem()) {
				System.out.printf("%-4d%-20s%-20d%-20d\n",idx2,i.getName(),i.getAmount(),i.getPrice());
				idx2++;
			}
			idx1++;
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
