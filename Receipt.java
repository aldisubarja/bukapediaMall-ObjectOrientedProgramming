import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class Receipt {

	private String code;
	private String date;
	private Vector<Item> vecItem = new Vector<Item>();
	
	public Receipt(String code, String date, Vector<Item> vecItem) {
		super();
		this.code = code;
		this.date = date;
		this.vecItem = vecItem;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Vector<Item> getVecItem() {
		return vecItem;
	}

	public void setVecItem(Vector<Item> vecItem) {
		this.vecItem = vecItem;
	}
	
	public void sortingHarga() {
		Collections.sort(vecItem,new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				return o1.getPrice().compareTo(o2.getPrice());
			}
		});
	}
	
	public void sortingNama() {
		Collections.sort(vecItem,new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	public void printData() {
		int idx=1;
		for (Item i : vecItem) {
			System.out.printf("%-4d%-20s%-20d%-20d\n",idx,i.getName(),i.getAmount(),i.getPrice());
			idx++;
		}
	}
	
	public void printKeranjang(int num) {
		if(vecItem.size()==0) {
			System.out.println("Keranjang kosong");
		}else {
			System.out.printf("%-4s%-20s%-20s%-20s\n","No","Nama Barang","Jumlah/Nominal","Harga");
			switch(num) {
			case 1:
				sortingHarga();
				printData();
				break;
			case 2:
				sortingNama();
				printData();
				break;
			}			
		}
	}
	
	public void batal() {
		if(vecItem.size()==0) {
			System.out.println("Keranjang kosong");
		}else {
			System.out.println("Pesanan dibatalkan");
			vecItem.removeAllElements();			
		}
	}
	
	public Receipt() {
		// TODO Auto-generated constructor stub
	}

}
