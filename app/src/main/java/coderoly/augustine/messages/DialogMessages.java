package coderoly.augustine.messages;

import android.app.ProgressDialog;
import android.content.Context;

import coderoly.augustine.R;


public class DialogMessages {

    private ProgressDialog mProgressDialog;

    public void Show_Authenticating_Progress_Dialog(Context context) {
        if (mProgressDialog != null) Hide_Progress_Dialog();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(context.getString(R.string.Authenticating_dialog));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public void Show_Logging_In_Progress_Dialog(Context context) {
        if (mProgressDialog != null) Hide_Progress_Dialog();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(context.getString(R.string.Logging_you_in_dialog));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public void Show_Registering_Progress_Dialog(Context context) {
        if (mProgressDialog != null) Hide_Progress_Dialog();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(context.getString(R.string.Registering_you_dialog));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public void Show_Please_Wait_Dialog(Context context) {
        if (mProgressDialog != null) Hide_Progress_Dialog();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(context.getString(R.string.Please_wait_dialog));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public void Hide_Progress_Dialog() {
        mProgressDialog.dismiss();
    }


}
