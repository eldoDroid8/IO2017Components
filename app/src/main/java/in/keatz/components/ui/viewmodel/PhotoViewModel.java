package in.keatz.components.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import in.keatz.components.data.remote.NetworkDataManager;
import in.keatz.components.data.remote.NetworkInteractor;
import in.keatz.components.data.remote.model.Album;


public class PhotoViewModel extends ViewModel implements NetworkInteractor<Album> {
    NetworkDataManager networkDataManager;
    MutableLiveData<List<Album>> albums;

    PhotoViewModel() {
        albums = new MutableLiveData<>();
        networkDataManager = new NetworkDataManager();
        networkDataManager.registerInteractot(this);
        networkDataManager.syncPhotos();
    }


    @Override
    public void onSyncData(List<Album> data) {
        albums.setValue(data);
    }

    public MutableLiveData<List<Album>> getPhotos() {
        return albums;
    }

    @Override
    public void onFailed(Album error) {

    }
}
