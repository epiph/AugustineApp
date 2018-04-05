package coderoly.augustine.auth.email_password;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import coderoly.augustine.MainActivity;
import coderoly.augustine.errors.ConnectionErrors;
import coderoly.augustine.messages.DialogMessages;

public class LoginWithFirebase {

    private DialogMessages dialogMessages = new DialogMessages();


    public void Log_In_With_Firebase(final String email, final String password, final Context context) {

        dialogMessages.Show_Logging_In_Progress_Dialog(context);

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           /* Toast.makeText(getBaseContext, "Logged In", Toast.LENGTH_SHORT).show(); */
                            dialogMessages.Hide_Progress_Dialog();

                            context.startActivity(new Intent(context, MainActivity.class));

                        } else if (!task.isSuccessful()) {
                            /* Toast.makeText(context, "Password Error", Toast.LENGTH_SHORT).show(); */
                            dialogMessages.Hide_Progress_Dialog();
                            new ConnectionErrors.AuthenticationErrors().Password_Validation_Error(context);
                        } else {
                            dialogMessages.Hide_Progress_Dialog();
                            new ConnectionErrors().An_Error_Occurred_Try_Again_Later(context);
                        }
                    }
                });
    }

}


