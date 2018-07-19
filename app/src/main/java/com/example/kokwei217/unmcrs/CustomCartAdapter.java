package com.example.kokwei217.unmcrs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomCartAdapter extends ArrayAdapter<CartComponent> {
    public CustomCartAdapter(Context context, ArrayList<CartComponent> cartComponents) {
        super(context, 0, cartComponents);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        final CartComponent cartComponent = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cart_component, parent, false);
        }
        // Lookup view for data population
        TextView tvName = convertView.findViewById(R.id.itemName_cart);
        TextView tvQuantity = convertView.findViewById(R.id.itemQuantity_cart);
        // Populate the data into the template view using the data object
        tvName.setText(cartComponent.name);
        tvQuantity.setText(String.valueOf(cartComponent.quantity));

        Button removeButton = convertView.findViewById(R.id.removeButton_cart);
        // Cache row position inside the button using `setTag`
        removeButton.setTag(position);
        // Attach the click event handler
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                // Access the row position here to get the correct data item
                CartComponent cartComponentTagged = getItem(position);
                // Do what you want here...
                remove(cartComponentTagged);
            }
        });
        //return the completed view
        return convertView;
    }
}

