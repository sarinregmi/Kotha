package com.dumma.kotha.newlisting;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dumma.kotha.R;
import com.dumma.kotha.newlisting.models.PricingDetailsModel;
import com.hornet.dateconverter.DatePicker.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;


public class PricingDetailsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = PricingDetailsFragment.class.getSimpleName();

    @BindView(R.id.description)
    EditText descriptionET;

    @BindView(R.id.datePicker)
    EditText datePickerET;

    @BindView(R.id.rentAmount)
    EditText rentAmountET;

    private long date;
    private PricingDetailsSetListener psl;

    public PricingDetailsFragment() {}

    public interface PricingDetailsSetListener {
        void submitPricingDetails(PricingDetailsModel model);
    }

    public static PricingDetailsFragment newInstance() {
        return new PricingDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pricing_details, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PricingDetailsSetListener) {
            psl = (PricingDetailsSetListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement pricing details listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        psl = null;
    }

    @OnClick(R.id.nextButton)
    public void nextButtonPressed() {
        String rent = rentAmountET.getText().toString();
        String description = descriptionET.getText().toString();

        String message = "";
         if (TextUtils.isEmpty(rent)) {
             rentAmountET.requestFocus();
            message = "Rent price is missing";
        } else if (date < 0) {
             datePickerET.requestFocus();
            message = "Available date is missing";
        } else if (TextUtils.isEmpty(description)) {
             descriptionET.requestFocus();
            message = "Description is missing";
        } else {
             PricingDetailsModel model = new PricingDetailsModel(description, date, Double.parseDouble(rent));
             psl.submitPricingDetails(model);
             return;
        }
        showSnackbar(message);
    }

    @OnTouch(R.id.datePicker)
    public boolean chooseDatePressed() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        if (getFragmentManager().findFragmentByTag("Datepickerdialog") == null) {
            dpd.show(getFragmentManager(), "Datepickerdialog");
        }
        return true;
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        date =  calendar.getTimeInMillis();
        datePickerET.setText(day + "/" + month + "/" + year);
    }

    private void showSnackbar(String message)
    {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}
