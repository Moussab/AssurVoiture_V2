package dz.tdm.esi.myapplication.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import dz.tdm.esi.myapplication.SignUp;

/**
 * Created by Amine on 20/05/2017.
 */

public class Util {

    public static AlertDialog.Builder alert(Context context, String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(s)
                .setTitle("Alerte")
                .setPositiveButton("D\'accord", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        return builder;
    }

}
