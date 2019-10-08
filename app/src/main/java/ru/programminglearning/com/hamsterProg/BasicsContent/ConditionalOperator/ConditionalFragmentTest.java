package ru.programminglearning.com.hamsterProg.BasicsContent.ConditionalOperator;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.programminglearning.com.hamsterProg.BasicsContent.Successfully.BottomNavigationDrawerFragment;
import ru.programminglearning.com.hamsterProg.BasicsContent.Сycles.CyclesActivity;
import ru.programminglearning.com.project123456.R;

public class ConditionalFragmentTest extends Fragment {

    private View view;
    private FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_conditional_test, container , false);

        manager = getFragmentManager();

        initText();
        initTextIfElse();
        onClickWight();
        onClickHeight();
        return view;
    }

    private void initText(){
        TextView textView = view.findViewById(R.id.condVariables);
        textView.setText(Html.fromHtml(getString(R.string.condVariables)));
    }

    private void initTextIfElse(){
        TextView textView = view.findViewById(R.id.textCondIfelseTest);
        textView.setText(Html.fromHtml(getString(R.string.condTestIf)));
    }

    private void onClickHeight(){
        Button button = view.findViewById(R.id.btn_height);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValueDatabase();
                final BottomNavigationDrawerFragment bottomNavigationView =
                        new BottomNavigationDrawerFragment();
                bottomNavigationView.show(manager,"Tag");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bottomNavigationView.dismiss();
                        Intent intent = new Intent(getActivity(), CyclesActivity.class);
                        intent.putExtra("Keys","Циклы");
                        startActivity(intent);
                        getActivity().finish();
                    }
                },2000);
            }
        });
    }

    private void setValueDatabase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference = FirebaseDatabase.
                getInstance().getReference("Users").child(user.getUid()).child("Number3");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("0/1")){
                    reference.setValue("1/1");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void onClickWight(){
        final Button button = view.findViewById(R.id.btn_width);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Drawable draw = button.getBackground();
                button.setBackgroundColor(getResources().getColor(R.color.wrongAnswer));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        button.setBackground(draw);
                    }
                },1000);
            }
        });
    }


}
