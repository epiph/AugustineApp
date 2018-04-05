package coderoly.augustine.auth;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import coderoly.augustine.MainActivity;
import coderoly.augustine.R;


public class AddUserToDatabase extends Fragment {

    FirebaseUser signedUser = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users");

    String userEmail = signedUser.getEmail();

    public AddUserToDatabase() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.frag_add_user_to_database, container, false);



        EditText email = (EditText) view.findViewById(R.id.email_register);
        email.setText(userEmail);

        final EditText username = (EditText) view.findViewById(R.id.username_register);
        final EditText phone = (EditText) view.findViewById(R.id.phone_register);
        final Spinner diocese = (Spinner) view.findViewById(R.id.diocese_register);

        view.findViewById(R.id.Btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new MainActivity().Hide_Keyboard_and_Lose_Focus();

                if (TextUtils.isEmpty(username.getText().toString())) {
                    username.setError(getString(R.string.error_field_required));
                    return;

                } else if (username.length() < 3) {
                    username.setError("Username should be 3 characters or more");
                    return;
                }


                if (TextUtils.isEmpty(phone.getText().toString())) {
                    phone.setError(getString(R.string.error_field_required));
                    return;
                } else if (phone.length() != 10) {
                    phone.setError("Phone should be 10 numbers");
                    return;
                }


                userEmail = userEmail.replace("@", "%at%");
                userEmail = userEmail.replace(".", "%fullStop%");



        dbRef.child(userEmail).child("Email_Address").setValue(signedUser.getEmail());
        dbRef.child(userEmail).child("Username").setValue(username.getText().toString());
        dbRef.child(userEmail).child("Phone").setValue(phone.getText().toString());
        dbRef.child(userEmail).child("Diocese").setValue(String.valueOf(diocese.getSelectedItem()));

                getActivity().getFragmentManager().beginTransaction().remove(AddUserToDatabase.this).commit();

            }
        });




        return view;
    }
}
