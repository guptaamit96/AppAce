package inmortal.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;

public class WebServiceRequest {

    APIInterface apiInterface;
    public static WebServiceRequest webServiceRequest;

    public WebServiceRequest(){
        apiInterface=RESTClient.getClient().create(APIInterface.class);
    }

        public static WebServiceRequest getInstance()
        {
        if (webServiceRequest==null){
            webServiceRequest=new WebServiceRequest();
        }
        return webServiceRequest;

        }
        public void hitLOGIN(String artistName, Callback<ImageResponse> callback)
        {


        Call <ImageResponse> call=null;


        call = apiInterface.loginAPI(
                "artist.getinfo",
                artistName,
                "bcedb57fdd94aa7d02406e23e7041ce1",
                "json");


        call.enqueue(callback);


        }

}
