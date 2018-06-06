package com.dumma.kotha.newlisting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dumma.kotha.R;
import com.dumma.kotha.views.ImagePagerAdapter;
import com.dumma.kotha.views.TitleSubtitleView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListingFragment extends Fragment {

    public static final String TAG = ListingFragment.class.getSimpleName();

    @BindView(R.id.description)
    TitleSubtitleView description;

    @BindView(R.id.posted)
    TitleSubtitleView posted;

    @BindView(R.id.available)
    TitleSubtitleView available;

    @BindView(R.id.viewPager)
    ViewPager imagePager;

    private ImagePagerAdapter imagePagerAdapter;

    public ListingFragment() {}

    public static ListingFragment newInstance() {
        ListingFragment fragment = new ListingFragment();
        return fragment;
    }


    String[] imageUrls = {"https://images.pexels.com/photos/259600/pexels-photo-259600.jpeg?auto=compress&cs=tinysrgb&h=300",
            "https://images.pexels.com/photos/186077/pexels-photo-186077.jpeg?auto=compress&cs=tinysrgb&h=300"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listing2, container, false);
        ButterKnife.bind(this, v);
        posted.setSubtitleText("March 25 1990");
        available.setSubtitleText("May 25, 2018");
        description.setSubtitleText("\n" +
                "Federal Democratic Republic of Nepal\n" +
                "सङ्घीय लोकतान्त्रिक गणतन्त्र नेपाल (Nepali)\n" +
                "Sanghiya Loktāntrik Ganatantra Nepāl\n" +
                "Flag of Nepal\n" +
                "Flag\n" +
                "Emblem of Nepal\n" +
                "Emblem\n" +
                "Motto: जननी जन्मभूमिश्च स्वर्गादपि गरीयसी (Sanskrit)\n" +
                "Mother and Motherland are Greater than Heaven (English)\n" +
                "Anthem: सयौं थुँगा फूलका (Nepali)\n" +
                "Made of Hundreds of Flowers (English)\n" +
                "MENU0:00\n" +
                "Location of Nepal\n" +
                "Location of Nepal\n" +
                "Capital\n" +
                "and largest city\tKathmandu\n" +
                "28°10′N 84°15′ECoordinates: 28°10′N 84°15′E\n" +
                "Official languages\tNepali\n" +
                "Recognised regional languages\t\n" +
                "Awadhi Bhojpuri Bajjika Doteli Gurung Kiranti Limbu Magar Maithili Newar Rai Sherpa Tamang Tharu Urdu[1]\n" +
                "Ethnic groups (2011[2] [needs update])\t\n" +
                "Ethnic demography[show]\n" +
                "Religion\t81.3% Hinduism\n" +
                "9% Buddhism\n" +
                "4.4% Islam\n" +
                "3% Kirant\n" +
                "1.4% Christianity\n" +
                "0.4% Animism\n" +
                "0.5% Irreligion[3][4]\n" +
                "Demonym\tNepalese\n" +
                "Government\tFederal parliamentary republic\n" +
                "• President\n" +
                ".नेपाल\n");
        imagePagerAdapter = new ImagePagerAdapter(getFragmentManager());
        imagePager.setAdapter(imagePagerAdapter);
        imagePagerAdapter.setImageUrls(Arrays.asList(imageUrls));
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
