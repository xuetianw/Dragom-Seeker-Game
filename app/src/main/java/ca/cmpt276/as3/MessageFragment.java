package ca.cmpt276.as3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import ca.cmpt276.as3.model.R;

/**
 * This class is responsible for showing the congratulations
 * message at the end of the game when the user has found all
 * the number of dragons.
 */
public class MessageFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create the view to show
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.message_layout, null);

        // Create a button Listener
        DialogInterface.OnClickListener listener = (dialogInterface, i) -> {
            Intent intent = MenuActivity.makeIntent(getActivity());
            getActivity().finish();
            startActivity(intent);
        };

        // Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                // message is associated with the team of the game
                .setTitle("Congratulations! You won! Happy Chinese New Year!")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }
}
