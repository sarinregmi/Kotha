package com.dumma.kotha.newlisting;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dumma.kotha.R;
import com.dumma.kotha.newlisting.models.BasicDetailsModel;
import com.dumma.kotha.newlisting.models.TYPE;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicDetailsFragment extends Fragment {

    public static String TAG = BasicDetailsFragment.class.getSimpleName();

    @BindView(R.id.noOfRooms)
    EditText numberOfRooms;

    @BindView(R.id.noOfBathrooms)
    EditText numberOfBath;

    @BindView(R.id.floorCount)
    EditText floorNumber;

    @BindView(R.id.optionsRecylerView)
    RecyclerView optionsRecyclerView;

    private ButtonsAdapter adapter;
    private BasicDetailsSetListener bdl;

    public BasicDetailsFragment() {}

    public interface BasicDetailsSetListener {
        void submitBasicDetails(BasicDetailsModel model);
    }

    public static BasicDetailsFragment newInstance() {
        return new BasicDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_details, container, false);
        ButterKnife.bind(this, v);
        setTypeRecycler();
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BasicDetailsSetListener) {
            bdl = (BasicDetailsSetListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement basic details listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        bdl = null;
    }

    private void setTypeRecycler() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        optionsRecyclerView.setLayoutManager(gridLayoutManager);
        adapter = new ButtonsAdapter(getActivity(), Arrays.asList(TYPE.class.getEnumConstants()));
        optionsRecyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.nextButton)
    public void nextButtonPressed() {
        String floor = floorNumber.getText().toString();
        String rooms = numberOfRooms.getText().toString();
        String baths = numberOfBath.getText().toString();
        TYPE type = adapter.getSelectedType();

        String message = "";
        if (type == null) {
            message = "Select property type";
        } else if (TextUtils.isEmpty(rooms)) {
            numberOfRooms.requestFocus();
            message = "Rooms count missing";
        } else if (TextUtils.isEmpty(baths)) {
            message = "Number of baths missing";
            numberOfBath.requestFocus();
        } else if (TextUtils.isEmpty(floor)) {
            floorNumber.requestFocus();
            message = "Floor missing";
        } else {
            BasicDetailsModel model = new BasicDetailsModel(type, Integer.parseInt(rooms), Integer.parseInt(baths), Integer.parseInt(floor));
            bdl.submitBasicDetails(model);
            return;
        }
        showSnackbar(message);
    }

    private void showSnackbar(String message)
    {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

}
