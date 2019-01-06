package inmortal.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("2.0")
    Call<ImageResponse>loginAPI(
            @Query("method")String methodName,
            @Query("artist")String artistname,
            @Query("api_key")String apiKey,
            @Query("format")String format);

}
