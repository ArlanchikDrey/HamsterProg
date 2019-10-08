package ru.programminglearning.com.hamsterProg.Registration.Welcome;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import ru.programminglearning.com.hamsterProg.Basics.BasicActivity;
import ru.programminglearning.com.project123456.R;


public class WelcomeFragment extends Fragment {

    private LinearLayout dotLayout;
    private View view;
    private GoogleSignInClient mGoogleClient;
    private FirebaseAuth mAuth;

    public static WelcomeFragment newInstance() {
        WelcomeFragment fragment = new WelcomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //настройки входа в гугл
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleClient= GoogleSignIn.getClient(getActivity(),gso);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_welcome, container, false);

        init();
        onClick();

        return view;
    }

    private void init(){
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        dotLayout = view.findViewById(R.id.dotsLayout);
        SliderAdapter sliderAdapter = new SliderAdapter(getActivity());

        viewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0); // ставим индикатор позиции на 0
        viewPager.addOnPageChangeListener(viewListener);
    }

    //показ позиции
    private void addDotsIndicator(int position) {
        TextView[] mDots = new TextView[2];
        dotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(getActivity());
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextColor(getResources().getColor(R.color.colorIndicatorFalse));
            mDots[i].setTextSize(30);
            dotLayout.addView(mDots[i]);
        }
        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorIndicatorTrue));
        }
    }

    /**
     * Слушатель об изменениях позициию*/
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * После интеграции Google вход в систему, вход в деятельность
     */
    private void signInIntent (){
        Intent signInIntent = mGoogleClient.getSignInIntent();
        startActivityForResult(signInIntent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Результат, возвращенный при запуске Intent из GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Вход в Google был успешным, аутентификация с Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Ошибка входа в Google
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            getConnectDatabase();
                            startActivity(BasicActivity.class);

                        }
                        else{
                            String error = "Ошибка входа:(" + "\n" + "Проверьте наличие интернета";
                            View parentLayout = view.findViewById(android.R.id.content);
                            Snackbar.make(parentLayout, error, Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void startActivity(Class<?> classes) {
        Intent intent = new Intent
                (getActivity(), classes);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    private void getConnectDatabase() {
        final FirebaseUser user = mAuth.getCurrentUser();
        final String userId = user.getUid();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("Users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.hasChild(userId))){ //если юзера нет в базе

                    String name = user.getDisplayName();
                    Uri photoAvatar = user.getPhotoUrl();
                    int bill = 0;

                    Map<String,Object> map = new HashMap<>();
                    map.put("Name",name);
                    map.put("Photo",photoAvatar.toString());
                    map.put("Score",bill);

                    for (int i = 0; i < 9; i++ )
                        map.put("Number"+String.valueOf(i),getNumbersList()[i]);

                    databaseReference.child(userId).setValue(map);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private String[] getNumbersList(){
        String[] list = {"0/1","0/2","0/2","0/1","0/2","0/1","0/2"};
        return list;
    }
    private void onClick(){
        Button button = view.findViewById(R.id.btnNextToLevel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInIntent();
            }
        });
    }



}
