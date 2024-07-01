package com.example.newera;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class RegisterActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    public static Boolean onResetPasswordFragment = false; //    pulsante goback in reset password
    public static Boolean setSignUpFragment = false; //tut46

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        frameLayout = findViewById(R.id.register_framelayout);

        //tut46
        if(setSignUpFragment){
            setSignUpFragment = false;
            setDefaultFragment(new SignUpFragment());
        }else{
            setDefaultFragment(new SignInFragment());
        }

            //tut46 end
        setFragment( new SignInFragment());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (onResetPasswordFragment) {


                onResetPasswordFragment = false;//se false se schiaccio indietro nella pagina di registrazione esce dall'applicazione

                setFragment(new SignInFragment());
                return false;
            }


        }
        return super.onKeyDown(keyCode, event);
    }



    private void setDefaultFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    //ritorna alla signin fragment
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_left);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}