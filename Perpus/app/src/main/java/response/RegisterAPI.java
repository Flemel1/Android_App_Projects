package response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("insert.php")
    Call<Response> register(@Field("id_buku") String id,
                            @Field("nama_buku") String judul,
                            @Field("penulis") String penulis,
                            @Field("penerbit") String penerbit,
                            @Field("tahun") String tahun);
}
