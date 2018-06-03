package com.dumma.kotha.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dumma.kotha.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    List<String> imageUrls = new ArrayList<>();

    public ImagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(imageUrls.get(position), position, getCount());
    }


    public static class ImageFragment extends Fragment {
        private static final String ARG_IMAGE_URL = "ARG_IMAGE_URL";
        private static final String ARG_IMAGE_POSITION = "ARG_IMAGE_POSITION";
        private static final String ARG_IMAGE_COUNT = "ARG_IMAGE_COUNT";


        private String imageUrl;
        private int position;
        private int count;

        public static ImageFragment newInstance(final String imageUrl, int position, int count) {
            Bundle b = new Bundle();
            b.putString(ARG_IMAGE_URL, imageUrl);
            b.putInt(ARG_IMAGE_POSITION, position);
            b.putInt(ARG_IMAGE_COUNT, count);
            ImageFragment fImage = new ImageFragment();
            fImage.setArguments(b);
            return fImage;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            imageUrl = getArguments().getString(ARG_IMAGE_URL);
            position = getArguments().getInt(ARG_IMAGE_POSITION);
            count = getArguments().getInt(ARG_IMAGE_COUNT);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.item_image, container, false);
            ImageView image = v.findViewById(R.id.image);
            TextView positionTv = v.findViewById(R.id.pager_position);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (count > 1) {
                positionTv.setText(String.format(getString(R.string.image_position_format), (position + 1), count));
            }
            Picasso.get()
                    .load(imageUrl)
                    .into(image);
            return v;
        }
    }
}

