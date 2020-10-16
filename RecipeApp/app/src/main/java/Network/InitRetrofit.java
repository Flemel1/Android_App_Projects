package Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {
    public final static String URL = "http://gkekasongan.000webhostapp.com/RecipeApi/";

    public static Retrofit setInitRetrofit(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static ApiHelper getRetrofit(){
        return setInitRetrofit().create(ApiHelper.class);
    }
}
