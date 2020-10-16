public class BodyItem{

	@SerializedName("datetime")
	private String datetime;

	@SerializedName("receiver")
	private String receiver;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("stock")
	private String stock;

	public String getDatetime(){
		return datetime;
	}

	public String getReceiver(){
		return receiver;
	}

	public String getNamaBarang(){
		return namaBarang;
	}

	public String getStock(){
		return stock;
	}
}
