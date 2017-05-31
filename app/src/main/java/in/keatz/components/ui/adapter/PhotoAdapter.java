/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package in.keatz.components.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;



import java.util.List;
import java.util.Objects;

import in.keatz.components.R;
import in.keatz.components.data.remote.model.Album;
import in.keatz.components.databinding.AdapterPhotoItemBinding;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    List<Album> photos;

    /*@Nullable
    private final ProductClickCallback mProductClickCallback;
*/
    public PhotoAdapter() {

    }

    public void setProductList(final List<Album> photoList) {
        if (photos == null) {
            this.photos = photoList;
            notifyItemRangeInserted(0, photos.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return photos.size();
                }

                @Override
                public int getNewListSize() {
                    return photoList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return photos.get(oldItemPosition).getId() ==
                            photoList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Album photo = photos.get(newItemPosition);
                    Album old = photos.get(oldItemPosition);
                    return photo.getId() == old.getId()
                            && Objects.equals(photo.getAlbumId(), old.getAlbumId())
                            && Objects.equals(photo.getUrl(), old.getUrl())
                            && photo.getThumbnailUrl() == old.getThumbnailUrl();
                }
            });
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterPhotoItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_photo_item,
                        parent, false);
        //binding.setCallback(mProductClickCallback);
        return new PhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.binding.setPhoto(photos.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return photos == null ? 0 : photos.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {

        final AdapterPhotoItemBinding binding;

        public PhotoViewHolder(AdapterPhotoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
