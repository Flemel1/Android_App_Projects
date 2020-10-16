package Network;

import java.util.List;

import Response.ResponseRecipe;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiHelper {
    @GET("getRecipe.php")
    Call<List<ResponseRecipe>> getAllRecipe();
}
