public class Response{

	@SerializedName("body")
	private List<BodyItem> body;

	public List<BodyItem> getBody(){
		return body;
	}
}
