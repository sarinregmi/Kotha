package com.dumma.kotha.newlisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dumma.kotha.R;
import com.dumma.kotha.newlisting.models.TYPE;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sregmi on 8/19/16.
 */
public class ButtonsAdapter extends RecyclerView.Adapter<ButtonsAdapter.ViewHolder> {

    private ArrayList<TYPE> buttonItems;
    private Context context;
    private int selectedIndex;

    public ButtonsAdapter(Context context, List<TYPE> items) {
        this.context = context;
        this.buttonItems = new ArrayList<>();
        buttonItems.addAll(items);
        selectedIndex = -1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_button_item, parent, false);
        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(view -> {
            selectedIndex = vh.getAdapterPosition();
            notifyDataSetChanged();
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String item = buttonItems.get(position).getValue();
        Button button = ((Button) holder.itemView);
        button.setText(item);
        button.setSelected(position == selectedIndex);

    }

    @Override
    public int getItemCount() {
        return buttonItems.size();
    }

    public TYPE getSelectedType() {
        if (selectedIndex != -1) {
            return buttonItems.get(selectedIndex);
        }
        return null;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View v) {
            super(v);
        }
    }

}
