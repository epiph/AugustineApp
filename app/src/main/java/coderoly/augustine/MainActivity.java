package coderoly.augustine;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.ProviderQueryResult;

import coderoly.augustine.auth.email_password.LoginWithFirebase;
import coderoly.augustine.auth.email_password.SignupWithFirebase;
import coderoly.augustine.errors.ConnectionErrors;
import coderoly.augustine.homepage.HomeActivity;
import coderoly.augustine.homepage.chats.Chats;
import coderoly.augustine.messages.DialogMessages;

@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity {

    ConnectionErrors connectionErrors = new ConnectionErrors();
    DialogMessages dialogMessages = new DialogMessages();

    public String doesEmailExist;
    public AutoCompleteTextView mEmailView;
    public EditText mPasswordView;

    int RC_SIGN_IN = 9001;


    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//            getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


//            Locale.setDefault(new Locale("sw"));
//            Configuration config = new Configuration();
//            Resources res = getResources();
//            res.updateConfiguration(config, res.getDisplayMetrics());
//            recreate();


        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            startActivity(new Intent(this, HomeActivity.class));


        findViewById(R.id.Btn_AuthWithEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hide_Keyboard_and_Lose_Focus();
                if (!Is_Network_Connected()) {
                    connectionErrors.Internet_Connection_Failed_Error(MainActivity.this);
                    return;
                }
                Email_Password_Auth();
            }
        });

        findViewById(R.id.buttonSignWithGoogle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hide_Keyboard_and_Lose_Focus();
                if (!Is_Network_Connected()) {
                    connectionErrors.Internet_Connection_Failed_Error(MainActivity.this);
                } else {
                    dialogMessages.Show_Please_Wait_Dialog(MainActivity.this);

                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(getString(R.string.default_web_client_id) /*"AIzaSyCTa0jrvljwxA_GyQXDdNd9T9buUb7ZeJM"*/)
                            .requestEmail()
                            .build();


                    GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                            .enableAutoManage(MainActivity.this, null)
                            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                            .build();

                    dialogMessages.Hide_Progress_Dialog();
                    dialogMessages.Show_Authenticating_Progress_Dialog(MainActivity.this);

                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(signInIntent, RC_SIGN_IN);

                    dialogMessages.Hide_Progress_Dialog();
                    dialogMessages.Show_Authenticating_Progress_Dialog(MainActivity.this);


                }
            }
        });


        findViewById(R.id.buttonSignWithFacebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hide_Keyboard_and_Lose_Focus();
                if (!Is_Network_Connected()) {
                    connectionErrors.Internet_Connection_Failed_Error(MainActivity.this);
                    // return;
                }
               /* new GoogleAuthFragment().Google_Sign_in_Options(AuthenticationActivity.this); */
            }
        });

        findViewById(R.id.buttonSignWithTwitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hide_Keyboard_and_Lose_Focus();
                if (!Is_Network_Connected()) {
                    connectionErrors.Internet_Connection_Failed_Error(MainActivity.this);
                    // return;
                }
               /* startActivity(new Intent(AuthenticationActivity.this, GoogleAuthActivity.class)); */
            }
        });
    }

    public void Hide_Keyboard_and_Lose_Focus() {

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @SuppressWarnings("deprecation")
    public boolean Is_Network_Connected() {
        boolean wifiNetworkState = false;
        boolean mobileNetworkState = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected()) wifiNetworkState = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected()) mobileNetworkState = true;
        }
        return wifiNetworkState || mobileNetworkState;
    }

    public void Email_Password_Auth() {

        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        final String typedEmail = mEmailView.getText().toString();
        final String typedPassword = mPasswordView.getText().toString();


        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        /* Validity Checks */
        switch (EmailValidityCheck(typedEmail)) {
            case "empty":
                mEmailView.setError(getString(R.string.error_field_required));
                mEmailView.requestFocus();
                return;
            case "space":
                mEmailView.setError(getString(R.string.EmailShouldNotContainSpaces));
                mEmailView.requestFocus();
                return;
            case "period":
                mEmailView.setError(getString(R.string.error_invalid_email));
                mEmailView.requestFocus();
                return;
            case "@":
                mEmailView.setError(getString(R.string.error_invalid_email));
                mEmailView.requestFocus();
                return;

            default:
                switch (PasswordValidityCheck(typedPassword)) {
                    case "empty":
                        mPasswordView.setError(getString(R.string.error_field_required));
                        mPasswordView.requestFocus();
                        return;
                    case "short":
                        mPasswordView.setError(getString(R.string.error_short_password));
                        mPasswordView.requestFocus();
                        return;
                    default:
                        break;
                }
                break;
        }

        Hide_Keyboard_and_Lose_Focus();


        dialogMessages.Show_Please_Wait_Dialog(MainActivity.this);

        FirebaseAuth.getInstance().fetchProvidersForEmail(typedEmail)
                .addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                    @SuppressWarnings("ConstantConditions")
                    @Override
                    public void onComplete(@NonNull Task<ProviderQueryResult> task) {

                        /* getProviders() will return size 1. if email ID is available.*/
                        if (task.isSuccessful()) {

                            dialogMessages.Hide_Progress_Dialog();

                            doesEmailExist = (task.getResult().getProviders().size() == 1) ? "yes" : "no";

                          /* Toast.makeText(getBaseContext(), "y or n = "+doesEmailExist, Toast.LENGTH_SHORT).show(); */

                            switch (doesEmailExist) {
                                case "no":
                                    new SignupWithFirebase().Sign_Up_With_Firebase(typedEmail, typedPassword, MainActivity.this, getBaseContext());
                                    return;
                                case "yes":
                                    new LoginWithFirebase().Log_In_With_Firebase(typedEmail, typedPassword, MainActivity.this);
                                    return;
                                default:
                                    connectionErrors.An_Error_Occurred_Try_Again_Later(MainActivity.this);
                                    break;
                            }

                        } else {
                            dialogMessages.Hide_Progress_Dialog();
                            connectionErrors.An_Error_Occurred_Try_Again_Later(MainActivity.this);
                        }

                    }
                });
    }

    public String PasswordValidityCheck(String typedPassword) {
        String error = "none";

        if (TextUtils.isEmpty(typedPassword)) error = "empty";
        if (typedPassword.length() < 6) error = "short";

        return error;
    }

    private String EmailValidityCheck(String typedEmail) {
        String error = "none";

        if (TextUtils.isEmpty(typedEmail)) error = "empty";
        if (!typedEmail.contains(getString(R.string.at_symbol))) error = "@";
        if (!typedEmail.contains(getString(R.string.fullstop_symbol))) error = "period";
        if (typedEmail.contains(getString(R.string.space))) error = "space";

        return error;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        dialogMessages.Show_Authenticating_Progress_Dialog(MainActivity.this);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {

                GoogleSignInAccount account = result.getSignInAccount();
                Log.e("Google Authenticating", "" + account.getEmail());
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    dialogMessages.Hide_Progress_Dialog();
                                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                                } else {
                                    dialogMessages.Hide_Progress_Dialog();
                                    new ConnectionErrors().An_Error_Occurred_Try_Again_Later(MainActivity.this);
                                }
                            }
                        });
            } else {
                dialogMessages.Hide_Progress_Dialog();
                new ConnectionErrors().An_Error_Occurred_Try_Again_Later(MainActivity.this);
            }
        }
    }

}
