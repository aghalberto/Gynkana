package com.pmdm.gynkana;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.SupportMapFragment;


public class PasswordFragment extends DialogFragment {

    private String tagMarker;
    public String getTagMarker() {
        return tagMarker;
    }
    public void setTagMarker(String tagMarker) {
        this.tagMarker = tagMarker;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction.

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        //Constructor
        builder
            .setView(inflater.inflate(R.layout.dialog_activity, null));

        //Dependiendo del tag, creamos títulos y subtítulos
        if (getTagMarker().equals("AQUALAND")){
            builder
                .setIcon(R.drawable.aqualand)
                .setTitle(R.string.aqualand)
                .setMessage(R.string.aqualand_snippet);
        } else if (getTagMarker().equals("COCODRILE")) {

        }

        // Create the AlertDialog object and return it.
        return builder.create();
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
    }


}


