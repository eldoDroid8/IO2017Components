package in.keatz.components.data.remote;

import java.util.List;

import in.keatz.components.data.remote.model.Album;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Inspiron on 5/25/2017.
 */

public interface PhotoApiEndPoint<ResultType> {
    @GET("/photos")
    Call<List<Album>> getPhotos();


}
