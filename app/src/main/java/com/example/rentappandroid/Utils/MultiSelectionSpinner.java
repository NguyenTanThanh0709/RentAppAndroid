package com.example.rentappandroid.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.widget.AppCompatSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MultiSelectionSpinner extends AppCompatSpinner {

    @Override
    public boolean performClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select Items");

        builder.setMultiChoiceItems(items.toArray(new CharSequence[items.size()]), selected,
                (dialog, which, isChecked) -> selected[which] = isChecked);

        builder.setPositiveButton("OK", (dialog, which) -> {
            if (listener != null) {
                listener.onItemsSelected(selected);
            }
        });

        builder.setNegativeButton("Cancel", null);

        builder.show();
        return true;
    }

    private List<String> items;
    private boolean[] selected;
    private MultiSpinnerListener listener;

    public MultiSelectionSpinner(Context context) {
        super(context);
    }

    public MultiSelectionSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiSelectionSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setItems(List<String> items) {
        this.items = items;
        selected = new boolean[items.size()];
        Arrays.fill(selected, false);
        MultiSelectionAdapter adapter = new MultiSelectionAdapter(getContext(), items);
        setAdapter(adapter);
    }

    public void setListener(MultiSpinnerListener listener) {
        this.listener = listener;
    }

    public List<String> getSelectedItems() {
        List<String> selectedItems = new LinkedList<>();
        for (int i = 0; i < items.size(); i++) {
            if (selected[i]) {
                selectedItems.add(items.get(i));
            }
        }
        return selectedItems;
    }

    private class MultiSelectionAdapter extends ArrayAdapter<String> {

        private MultiSelectionAdapter(Context context, List<String> items) {
            super(context, android.R.layout.simple_spinner_item, items);
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            if (position == getCount()) {
                // Set the last item as hidden
                view.setVisibility(View.GONE);
                view.setLayoutParams(new android.view.ViewGroup.LayoutParams(0, 0));
            } else {
                view.setVisibility(View.VISIBLE);
            }
            return view;
        }
    }

    public interface MultiSpinnerListener {
        void onItemsSelected(boolean[] selected);
    }
}
