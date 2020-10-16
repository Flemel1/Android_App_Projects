package Response;

import com.google.gson.annotations.SerializedName;

public class ResponseRecipe{

	@SerializedName("create_by")
	private String createBy;

	@SerializedName("image")
	private String image;

	@SerializedName("recipe_name")
	private String recipeName;

	@SerializedName("ingredients")
	private String ingredients;

	@SerializedName("id")
	private String id;

	@SerializedName("steps")
	private String steps;

	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}

	public String getCreateBy(){
		return createBy;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setRecipeName(String recipeName){
		this.recipeName = recipeName;
	}

	public String getRecipeName(){
		return recipeName;
	}

	public void setIngredients(String ingredients){
		this.ingredients = ingredients;
	}

	public String getIngredients(){
		return ingredients;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setSteps(String steps){
		this.steps = steps;
	}

	public String getSteps(){
		return steps;
	}

	@Override
 	public String toString(){
		return 
			"ResponseRecipe{" + 
			"create_by = '" + createBy + '\'' + 
			",image = '" + image + '\'' + 
			",recipe_name = '" + recipeName + '\'' + 
			",ingredients = '" + ingredients + '\'' + 
			",id = '" + id + '\'' + 
			",steps = '" + steps + '\'' + 
			"}";
		}
}