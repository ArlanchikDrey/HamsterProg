package ru.programminglearning.com.hamsterProg.BasicsContent.TypeVariables;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.programminglearning.com.hamsterProg.BasicsContent.MathOperator.MathOperatorActivity;
import ru.programminglearning.com.hamsterProg.BasicsContent.Successfully.BottomNavigationDrawerFragment;
import ru.programminglearning.com.project123456.R;

public class TypeFragmentTest2  extends Fragment{

    private View view;
    private TextView textDouble, textBool, textBoolState, textDoubleState;
    private boolean isTrueClickRight1 = false;
    private boolean isTrueClickRight2 = false;
    private FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_type_test2,container,false);
        manager = getFragmentManager();

        textBool = view.findViewById(R.id.textBool);
        textDouble = view.findViewById(R.id.textDouble);
        textBoolState = view.findViewById(R.id.textBoolState);
        textDoubleState = view.findViewById(R.id.textDoubleState);

        onClickTextDouble();
        onClickTextBool();
        initSecondTextViews();
        onClickButton();
        return view;
    }

    private void onClickTextDouble(){
        textDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textBoolState.getText().toString().equals("_____")){
                    textBoolState.setText(textDouble.getText().toString());
                    textDouble.setVisibility(View.GONE);
                }else{
                    textDoubleState.setText(textDouble.getText().toString());
                    textDouble.setVisibility(View.GONE);
                    isTrueClickRight1 = true;
                }
            }
        });
    }

    private void onClickTextBool(){
        textBool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textBoolState.getText().toString().equals("_____")){
                    textBoolState.setText(textBool.getText().toString());
                    textBool.setVisibility(View.GONE);
                    isTrueClickRight2 = true;
                }else{
                    textDoubleState.setText(textBool.getText().toString());
                    textBool.setVisibility(View.GONE);

                }
            }
        });
    }

    private void onClickButton(){
        Button button = view.findViewById(R.id.onNextType3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTrueClickRight1 && isTrueClickRight2){
                    setValueDatabase();
                    final BottomNavigationDrawerFragment bottomNavigationView = new BottomNavigationDrawerFragment();
                    bottomNavigationView.show(manager,"Tag");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bottomNavigationView.dismiss();
                            Intent intent = new Intent(getActivity(), MathOperatorActivity.class);
                            intent.putExtra("Keys","Арифметические операторы");
                            startActivity(intent);
                            getActivity().finish();
                        }
                    },2000);
                }else{
                    Snackbar.make(v,"Неверно, попробуйте еще раз:)",Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textBool.setVisibility(View.VISIBLE);
                            textDouble.setVisibility(View.VISIBLE);
                            textBoolState.setText("_____");
                            textDoubleState.setText("_____");
                        }
                    },2000);
                }
            }
        });
    }

    private void setValueDatabase(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("Users").child(user.getUid()).child("Number1");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("1/2")){
                    databaseReference.setValue("2/2");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initSecondTextViews(){
        TextView text1,text2,text3;

        text1 = view.findViewById(R.id.idText123);
        text2 = view.findViewById(R.id.textCharState);
        text3 = view.findViewById(R.id.idText12334);

        text1.setText(Html.fromHtml(getString(R.string.idText123)));
        text2.setText(Html.fromHtml(getString(R.string.textCharState)));
        text3.setText(Html.fromHtml(getString(R.string.idText12334)));
    }
}
