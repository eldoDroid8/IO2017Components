package in.keatz.components.data.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import in.keatz.components.BuildConfig;
import in.keatz.components.data.remote.model.Album;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Inspiron on 5/25/2017.
 */

public class NetworkDataManager {
    private NetworkInteractor interactor;

    private final Retrofit retrofit;

    public NetworkDataManager(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public NetworkDataManager() {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor).build();
        }
        httpClient.build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void syncPhotos() {
        PhotoApiEndPoint photoApiEndPoint = retrofit.create(PhotoApiEndPoint.class);
        Call photos = photoApiEndPoint.getPhotos();
        photos.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(@NonNull Call<List<Album>> call, @NonNull Response<List<Album>> response) {
                Log.d("Syncing succcessfull", "----yeeee-----");
                if (response.isSuccessful()) {

                    interactor.onSyncData(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Album>> call, @NonNull Throwable t) {
                t.printStackTrace();
                interactor.onFailed(null);
            }
        });


    }

    public void registerInteractot(NetworkInteractor networkInteractor) {
        this.interactor = networkInteractor;
    }
}