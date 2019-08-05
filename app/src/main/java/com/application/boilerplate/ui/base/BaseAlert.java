package com.application.boilerplate.ui.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boilerplate.R;

import java.util.Objects;


public class BaseAlert {

    private AlertDialog alertDialog;

    public BaseAlert(){

    }

    public void showAlert(Activity context, String title, String body, ButtonType type,
                          String positiveButtonText, String negativeButtonText,
                          DialogType dialogType,
                          OnBaseAlertListener baseAlertListener) {

        if (context != null) {
            View inflatedView = context.getLayoutInflater().inflate(R.layout.dialog_base_alert,
                    null, false);
            final Button positiveButton = inflatedView.findViewById(R.id.btn_positive);
            final Button negativeButton = inflatedView.findViewById(R.id.btn_negative);
            final ImageView typeImageView = inflatedView.findViewById(R.id.iv_type);
            TextView titleTextView = inflatedView.findViewById(R.id.tv_title);
            TextView contentTextView = inflatedView.findViewById(R.id.tv_content);
            RelativeLayout negativeRelativeLayout = inflatedView.findViewById(R.id.rl_negative);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            titleTextView.setText(title);
            contentTextView.setText(body);
            positiveButton.setText(positiveButtonText);
            negativeButton.setText(negativeButtonText);


            //Setting Button type
            if (type.equals(ButtonType.SINGLE)) {
                negativeRelativeLayout.setVisibility(View.GONE);
            } else {
                negativeRelativeLayout.setVisibility(View.VISIBLE);
            }

            //Setting Alert type
            switch (dialogType) {
                case NORMAL:
                    typeImageView.setVisibility(View.GONE);
                    break;
                case SUCCESS:
                    typeImageView.setImageResource(R.drawable.ic_success);
                    break;
                case WARNING:
                    typeImageView.setImageResource(R.drawable.ic_warning);
                    break;
                case ERROR:
                    typeImageView.setImageResource(R.drawable.ic_error);
                    break;
                default:
                    break;
            }

            builder.setView(inflatedView);
            alertDialog = builder.create();
            Objects.requireNonNull(alertDialog.getWindow())
                    .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    baseAlertListener.onCancelClicked(alertDialog);
                }
            });
            alertDialog.show();

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseAlertListener.onSuccessClicked(alertDialog);
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseAlertListener.onCancelClicked(alertDialog);
                }
            });
        }
    }

    public void showCustomProgressAlert(Activity context) {
        if (context != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View inflate = context.getLayoutInflater().inflate(R.layout.sweet_alert_progress,
                    null, false);
            builder.setView(inflate);
            alertDialog = builder.create();
            alertDialog.setCancelable(false);
            Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            if (!alertDialog.isShowing())
                alertDialog.show();
        }
    }

    public void hideCustomProgressAlert() {
        if (alertDialog != null) {
            if (alertDialog.isShowing())
                alertDialog.dismiss();
        }
    }

    public void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public enum Type {
        SINGLE, DOUBLE
    }

    public interface onAlertDialogClick {

        public void onPositiveClick(DialogInterface dialog, int which);

        public void onNegativeClick(DialogInterface dialog, int which);
    }

    public enum ButtonType {
        SINGLE, DOUBLE
    }

    public enum DialogType {
        WARNING, NORMAL, ERROR, SUCCESS
    }

    public interface OnBaseAlertListener{
        void onCancelClicked(DialogInterface dialog);
        void onSuccessClicked(DialogInterface dialog);
    }
}
