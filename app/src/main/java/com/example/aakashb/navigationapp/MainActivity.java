package com.example.aakashb.navigationapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private EditText mEditText;

    private String address;
    final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private TextView txtSelectedPlaceName;
    private static final String TAG = AutoCompleteActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);
        txtSelectedPlaceName = (TextView) findViewById(R.id.txtSelectedPlaceName);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.fragment_autocomplete);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.d(TAG, "Place: " + place.getName().toString());
                txtSelectedPlaceName.setText(String.format("Selected places : %s  - %s", place.getName(), place.getAddress()));
                address = place.getName().toString();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));

                //creating an intent
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }

            @Override
            public void onError(Status status) {
                Log.d(TAG, "An error occurred: " + status);
                //Toast.makeText(AutoCompleteActivity.this, "Place cannot be selected!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

       // mEditText.setOnClickListener(new View.OnClickListener() {
                                     //    @Override
                                       // public void onClick(View view) {

                                             /*try {
                                                 Intent intent =
                                                         new IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                                                 .build(this);
                                                 startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                                             }catch(GooglePlayServicesRepairableException e){
                                                 //

                                             }catch (GooglePlayServicesNotAvailableException e){

                                             }
                                         }

                                        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                                            if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
                                                if (resultCode == RESULT_OK) {
                                                    Place place = PlaceAutocomplete.getPlace(this, data);

                                                } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                                                    WifiConfiguration.Status status = PlaceAutocomplete.getStatus(this, data);
                                                    // TODO: Handle the error.
                                                    Log.i(TAG, status.getStatusMessage());

                                                } else if (resultCode == RESULT_CANCELED) {
                                                    // The user canceled the operation.
                                                }
                                            }
                                        }*/
                                //         }});








    /*private class IntentBuilder {
        public IntentBuilder(int modeFullscreen) {
        }
    }*/

