package com.example.newera;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {

    public SignUpFragment() {
        // Required empty public constructor
    }

    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText fullName;
    private EditText password;
    private EditText confirmPassword;

    private ImageButton closeBtn;
    private Button signupBtn;
    private ProgressBar progressBar;

//    private FirebaseAuth firebaseAuth;
//    private FirebaseFirestore firebaseFirestore;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        parentFrameLayout = getActivity().findViewById(R.id.register_framelayout);
        alreadyHaveAnAccount = view.findViewById(R.id.tv_alreay_have_an_account);
        email = view.findViewById(R.id.sign_up_email);
        fullName = view.findViewById(R.id.sign_up_full_mane);
        password = view.findViewById(R.id.sing_up_password);
        confirmPassword = view.findViewById(R.id.sign_up_confirm_password);

        closeBtn = view.findViewById(R.id.sign_in_close_button);
        signupBtn = view.findViewById(R.id.sign_up_btn_register);
        progressBar = view.findViewById(R.id.sign_in_progressbar);

//        firebase
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseFirestore = FirebaseFirestore.getInstance();
//

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });

//        close button

//        closeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mainIntent();
//            }
//        });

// chech user

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


//        signupBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkEmailAndPassword();
//            }
//        });
    }

    //ritorna alla signin fragment
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(fullName.getText())) {
                if (!TextUtils.isEmpty(password.getText()) && password.length() >= 8) {
                    if (!TextUtils.isEmpty(confirmPassword.getText())) {
                        signupBtn.setEnabled(true);
                        signupBtn.setTextColor(Color.rgb(255, 255, 255));

                    } else {
                        signupBtn.setEnabled(false);
                        signupBtn.setTextColor(Color.argb(50, 255, 255, 255));
                    }
                } else {
                    signupBtn.setEnabled(false);
                    signupBtn.setTextColor(Color.argb(50, 255, 255, 255));
                }
            } else {

            }
        } else {
            signupBtn.setEnabled(false);
            signupBtn.setTextColor(Color.argb(50, 255, 255, 255));
        }
    }

//    private void checkEmailAndPassword() {

//        custom error icon

//        Drawable customErrorIcon = getResources().getDrawable(R.mipmap.custom_error_icon);
//        customErrorIcon.setBounds(0, 0, customErrorIcon.getIntrinsicWidth(), customErrorIcon.getIntrinsicHeight());
//
//
//        if (email.getText().toString().matches(emailPattern)) {
//            if (password.getText().toString().equals(confirmPassword.getText().toString())) {
//
//                progressBar.setVisibility(View.VISIBLE);
//                signupBtn.setEnabled(false);
//                signupBtn.setTextColor(Color.argb(50, 255, 255, 255));
//
//                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//
////                                   firestore check error icon
//
//                                    Map<Object, String> userdata = new HashMap<>();
//                                    userdata.put("fullname", fullName.getText().toString());
//
//                                    firebaseFirestore.collection("USERS")
//                                            .add(userdata)
//                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<DocumentReference> task) {
//                                                    if (task.isSuccessful()) {
////                                                        for check email and passwrod
//                                                        mainIntent();
//
//                                                    } else {
//                                                        //                progressbar
//
//                                                        progressBar.setVisibility(View.INVISIBLE);
//                                                        signupBtn.setEnabled(true);
//                                                        signupBtn.setTextColor(Color.rgb(255, 255, 255));
//
//                                                        //                        chech email anmd password
//
//                                                        String error = task.getException().getMessage();
//                                                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
//                                                    }
//                                                }
//                                            });
//
////                           intent
//
//
//                                } else {
////                                    check confirm password
//                                    progressBar.setVisibility(View.INVISIBLE);
//                                    signupBtn.setEnabled(true);
//                                    signupBtn.setTextColor(Color.rgb(255, 255, 255));
//                                    String error = task.getException().getMessage();
//                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//
//
//            } else {
//                confirmPassword.setError("Password doesn't matched!", customErrorIcon);
//
//            }
//        } else {
//            email.setError("Invalid Email!", customErrorIcon);
//
//        }
//    }

//    private void mainIntent() {
//        Intent mainIntent = new Intent(getActivity(), MainActivity.class);
//        startActivity(mainIntent);
//        getActivity().finish();
//    }
}
