package in.keatz.components.ui.lifecycle;

import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.List;


import in.keatz.components.R;
import in.keatz.components.data.remote.model.Album;
import in.keatz.components.databinding.ActivityComponentsBinding;

import in.keatz.components.ui.adapter.PhotoAdapter;
import in.keatz.components.ui.base.BaseActivity;
import in.keatz.components.ui.viewmodel.PhotoViewModel;


public class ComponentsActivity extends BaseActivity{
    PhotoAdapter mPhotoAdapter;
    ActivityComponentsBinding binding;

    private String TAG = getClass().getName();
    private PhotoViewModel photoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_components);
        Log.d(TAG,"----oncreate-----");
        binding.setIsLoading(true);
        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);
        mPhotoAdapter = new PhotoAdapter();
        binding.componentsRvAlbumList.setLayoutManager(new LinearLayoutManager(this));
        binding.componentsRvAlbumList.setAdapter(mPhotoAdapter);


        photoViewModel.getPhotos().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(@Nullable List<Album> alba) {
                Log.d(TAG,"----onChanged-----");
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
        Log.d(TAG,"----onResume-----");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG,"----onPause-----");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"----onStop-----");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"----onDestroy-----");
        super.onDestroy();
    }



    /*public void showLoading() {
        progressDialog = ProgressDialog.show(this, "PLease wait", "Loading..");
        progressDialog.show();
    }

    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }*/

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }
}
