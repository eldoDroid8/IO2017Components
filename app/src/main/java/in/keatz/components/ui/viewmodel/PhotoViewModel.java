package in.keatz.components.ui.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.coreutils.BuildConfig;
import android.util.Log;

import java.util.List;



import in.keatz.components.data.remote.NetworkDataManager;
import in.keatz.components.data.remote.NetworkInteractor;
import in.keatz.components.data.remote.model.Album;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Inspiron on 5/26/2017.
 */

public class PhotoViewModel extends ViewModel implements NetworkInteractor<Album> {
    NetworkDataManager networkDataManager;
    MutableLiveData<List<Album>> albums;

    PhotoViewModel(){
        albums = new MutableLiveData<>();
        networkDataManager = new NetworkDataManager();
        networkDataManager.registerInteractot(this);
        networkDataManager.syncPhotos();
    }


    @Override
    public void onSyncData(List<Album> data) {
        Log.d("onSyncData","----------");
        albums.setValue(data);
    }

    public  MutableLiveData<List<Album>> getPhotos(){
        Log.d("return mutable","----------");
        return albums;
    }
    @Override
    public void onFailed(Album error) {

    }
}
