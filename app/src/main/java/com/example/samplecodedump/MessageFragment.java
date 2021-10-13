package com.example.samplecodedump;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class MessageFragment extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // create the view to show
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_sample_twenty_one, null);

        // create a button listener -> can use lambda (but shown for learning purposes)
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch(which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        TextView tv = getActivity().findViewById(R.id.tvSampleTwentyOne);
                        tv.setText(R.string.sampleTwentyOneSecretMessage);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        // build the alert dialogue
        return new AlertDialog.Builder(getActivity())
                .setTitle("changing message")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .setNegativeButton(android.R.string.cancel, listener)
                .create();
    }

}
