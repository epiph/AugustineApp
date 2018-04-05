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


public class SignupWithFirebase {

    private DialogMessages dialogMessages = new DialogMessages();


    public void Sign_Up_With_Firebase(final String email, final String password, final Context context, final Context getBaseContext) {

        dialogMessages.Show_Registering_Progress_Dialog(context);

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            /* Toast.makeText(getBaseContext, "Registered", Toast.LENGTH_SHORT).show(); */
                            dialogMessages.Hide_Progress_Dialog();
                            context.startActivity(new Intent(context, MainActivity.class));
                        } else {
                            dialogMessages.Hide_Progress_Dialog();
                            new ConnectionErrors().An_Error_Occurred_Try_Again_Later(context);
                        }
                    }
                });
    }
}