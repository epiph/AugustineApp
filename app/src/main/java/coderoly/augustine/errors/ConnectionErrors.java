package coderoly.augustine.errors;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import coderoly.augustine.R;

public class ConnectionErrors {


    public void Internet_Connection_Failed_Error(Context context) {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(R.string.CheckInternetConnectionAndTryAgain))
                .setTitle(context.getString(R.string.Oops))
                .setPositiveButton(context.getString(R.string.OK), null);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();

    }

    public void An_Error_Occurred_Try_Again_Later(Context context) {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(R.string.AnErrorOccurred_TryAgainLater))
                .setTitle(context.getString(R.string.Oops))
                .setPositiveButton(context.getString(R.string.OK), null);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();

    }

    public void A_Fatal_Error(Context context ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Fatal Error")
                .setTitle("Fatal Error")
                .setPositiveButton(context.getString(R.string.OK), null);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
    }

    public static class AuthenticationErrors {

        public void Password_Validation_Error(Context context) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.EnterCorrectPasswordAndTryAgain)
                    .setTitle(context.getString(R.string.IncorrectPassword))
                    .setPositiveButton(context.getString(R.string.OK), null)
                    .setNegativeButton(R.string.ForgotPassword, null);

            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        public void Email_Does_Not_Exist(Context context) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.Email_IsNotRegistered)
                    .setTitle(context.getString(R.string.Oops))
                    .setPositiveButton(context.getString(R.string.OK), null)
                    .setNegativeButton(context.getString(R.string.ForgotPassword), null);

            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }
}
