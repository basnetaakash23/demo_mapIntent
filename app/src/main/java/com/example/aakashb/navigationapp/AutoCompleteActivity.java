package com.example.aakashb.navigationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;


public class AutoCompleteActivity extends AppCompatActivity {
    private TextView txtSelectedPlaceName;
    private static final String TAG = AutoCompleteActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);
        txtSelectedPlaceName = (TextView) this.findViewById(R.id.txtSelectedPlaceName);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.fragment_autocomplete);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.d(TAG,"Place: " + place.getName().toString());
                txtSelectedPlaceName.setText(String.format("Selected places : %s  - %s" , place.getName(), place.getAddress()));
            }

            @Override
            public void onError(Status status) {
                Log.d(TAG, "An error occurred: " + status);
                Toast.makeText(AutoCompleteActivity.this, "Place cannot be selected!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
