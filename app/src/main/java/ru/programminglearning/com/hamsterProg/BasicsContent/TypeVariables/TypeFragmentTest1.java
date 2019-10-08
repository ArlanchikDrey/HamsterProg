package ru.programminglearning.com.hamsterProg.BasicsContent.TypeVariables;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.programminglearning.com.hamsterProg.BasicsContent.Successfully.BottomNavigationDrawerFragment;
import ru.programminglearning.com.project123456.R;

public class TypeFragmentTest1 extends Fragment implements View.OnClickListener{

    private View view;
    private Button button1, button2, button3, button4;
    private FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_type_test1,container,false);

        init();
        manager = getFragmentManager();


        return view;
    }

    private void init(){

        button1 = view.findViewById(R.id.btn1);
        button2 = view.findViewById(R.id.btn2);
        button3 = view.findViewById(R.id.btn3);
        button4 = view.findViewById(R.id.btn4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                listenWrongAnswer(button1);
                break;
             case R.id.btn2:
                setValueDatabase();
                final BottomNavigationDrawerFragment bottomNavigationView = new BottomNavigationDrawerFragment();
                bottomNavigationView.show(manager,"Tag");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bottomNavigationView.dismiss();
                        TypeActivity.pager.setCurrentItem(2);
                    }
                },2000);
                break;

            case R.id.btn3:
                listenWrongAnswer(button3);
                break;

            case R.id.btn4:
                listenWrongAnswer(button4);
                break;
        }
    }

    private void listenWrongAnswer(final Button button){
        final Drawable draw = button.getBackground();
        button.setBackgroundColor(getResources().getColor(R.color.wrongAnswer));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                button.setBackground(draw);
            }
        },1000);
    }

    private void setValueDatabase(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("Users").child(user.getUid()).child("Number1");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("0/2")){
                    databaseReference.setValue("1/2");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
