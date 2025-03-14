package in.keatz.components.ui.lifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.List;
import in.keatz.components.data.remote.model.Album;
import in.keatz.components.databinding.ActivityComponentsBinding;
import in.keatz.components.ui.adapter.PhotoAdapter;
import in.keatz.components.ui.base.BaseActivity;
import in.keatz.components.ui.viewmodel.PhotoViewModel;


public class ComponentsActivity extends BaseActivity {
    PhotoAdapter mPhotoAdapter;
    ActivityComponentsBinding binding;

    private String TAG = getClass().getName();
    private PhotoViewModel photoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComponentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setIsLoading(true);
        photoViewModel = new ViewModelProvider(this).get(PhotoViewModel.class);
        mPhotoAdapter = new PhotoAdapter();
        binding.componentsRvAlbumList.setLayoutManager(new LinearLayoutManager(this));
        binding.componentsRvAlbumList.setAdapter(mPhotoAdapter);


        photoViewModel.getPhotos().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(@Nullable List<Album> alba) {
                Log.d(TAG, "----onChanged-----");
                if (alba != null) {
                    binding.setIsLoading(false);
                    mPhotoAdapter.setProductList(alba);
                } else {
                    binding.setIsLoading(true);
                }
            }
        });
        //componentPresenter.initializeApplicationData();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "----onResume-----");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "----onPause-----");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "----onStop-----");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "----onDestroy-----");
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }
}
