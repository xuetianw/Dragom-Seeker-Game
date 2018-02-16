package ca.cmpt276.as3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import ca.cmpt276.as3.model.R;

/**
 * Created by wu on 2/16/2018.
 */

public class MessageFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create the view to show
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.message_layout, null);
        //Create a button Listener

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = MainActivity.makeIntent(getActivity());
                getActivity().finish();
                startActivity(intent);
            }
        };

        // Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("Congratulations! You won! Happy Chinese New Year!")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }
}
