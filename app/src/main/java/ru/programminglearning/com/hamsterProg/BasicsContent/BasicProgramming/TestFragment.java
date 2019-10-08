package ru.programminglearning.com.hamsterProg.BasicsContent.BasicProgramming;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.programminglearning.com.hamsterProg.Basics.Adapter;
import ru.programminglearning.com.hamsterProg.BasicsContent.TypeVariables.TypeActivity;
import ru.programminglearning.com.project123456.R;

public class TestFragment extends Fragment  {
    private View view;
    private Button textAlgo, textProg, textLan;
    private Button textAlgoChild,textProgChild, textLanChild;
    private ImageView imageView;
    private Button button;
    private boolean isPressAlgo = false;
    private boolean isPressProg = false;
    private boolean isPressLan = false;
    private boolean isPressAlgoChild= false;
    private boolean isPressProgChild = false;
    private boolean isPressLanChild = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_basic_test,container,false);

        imageView = view.findViewById(R.id.imageSucccessful);
        button = view.findViewById(R.id.onAnswer);
        setSuccesefulDatabase(imageView,button);

        initViewsParent();
        initViewChild();
        onNext();
        return view;
    }


    private void initViewsParent() {
        textAlgo = view.findViewById(R.id.textAlgo);
        textProg = view.findViewById(R.id.textProg);
        textLan = view.findViewById(R.id.textLan);

        onClickAlgo();
        onClickProg();
        onClickLan();
    }

    private void initViewChild(){
        textAlgoChild = view.findViewById(R.id.button2);
        textProgChild = view.findViewById(R.id.button4);
        textLanChild = view.findViewById(R.id.button3);

        onClickAlgoChild();
        onClickProgChild();
        onClickLanChild();
    }

    private void onClickAlgo(){
        final Drawable buttonBackground = textAlgo.getBackground();
        textAlgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (isPressAlgo == false){
                  isPressAlgo = true;
                  textAlgo.setBackgroundColor(getResources().getColor(R.color.colorComponent));
              }else{
                  textAlgo.setBackground(buttonBackground);
                  isPressAlgo = false;
              }
            }
        });
    }

    private void onClickProg(){
        final Drawable buttonBackground = textProg.getBackground();
        textProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPressProg == false){
                    isPressProg = true;
                    textProg.setBackgroundColor(getResources().getColor(R.color.colorComponent));
                }else{
                    textProg.setBackground(buttonBackground);
                    isPressProg = false;
                }
            }
        });
    }

    private void onClickLan(){
        final Drawable buttonBackground = textLan.getBackground();
        textLan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPressLan == false){
                    isPressLan = true;
                    textLan.setBackgroundColor(getResources().getColor(R.color.colorComponent));
                }else{
                    textLan.setBackground(buttonBackground);
                    isPressLan = false;
                }
            }
        });
    }

    private void setBackroundWrongAnswer(final View vieww, final Drawable drawable){
        vieww.setBackgroundColor(getResources().getColor(R.color.wrongAnswer));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                vieww.setBackground(drawable);
            }
        },1500);
    }

    private void onClickAlgoChild(){
        final Drawable buttonBackground = textAlgoChild.getBackground();
        textAlgoChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPressAlgo == false){
                    setBackroundWrongAnswer(textAlgoChild,buttonBackground);
                    isPressAlgoChild = false;
                }else{
                    textAlgoChild.setBackgroundColor(getResources().getColor(R.color.colorComponent));
                    isPressAlgoChild = true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textAlgo.setEnabled(false);
                            textAlgoChild.setEnabled(false);
                        }
                    },1000);

                }
            }
        });
    }

    private void onClickProgChild(){
        final Drawable buttonBackground = textProgChild.getBackground();
        textProgChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPressProg == false){
                    setBackroundWrongAnswer(textProgChild,buttonBackground);
                    isPressProgChild = false;
                }else{
                    textProgChild.setBackgroundColor(getResources().getColor(R.color.colorComponent));
                    isPressProgChild = true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textProg.setEnabled(false);
                            textProgChild.setEnabled(false);
                        }
                    },1000);
                }
            }
        });
    }

    private void onClickLanChild(){
        final Drawable buttonBackground = textLanChild.getBackground();
        textLanChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPressLan == false){
                    setBackroundWrongAnswer(textLanChild,buttonBackground);
                    isPressLanChild = false;
                }else{
                    textLanChild.setBackgroundColor(getResources().getColor(R.color.colorComponent));
                    isPressLanChild = true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textLan.setEnabled(false);
                            textLanChild.setEnabled(false);
                        }
                    },1000);
                }
            }
        });
    }

    private void onNext(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllAnswerTrue()){
                    setSuccesefulDatabase(imageView,button);
                    Intent intent = new Intent(getActivity(), TypeActivity.class);
                    intent.putExtra(Adapter.KEY,Adapter.list[1]);
                    startActivity(intent);
                    getActivity().finish();

                }else{
                    Snackbar.make(view,"Выберите все правильные ответы",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isAllAnswerTrue(){
        return isPressAlgo && isPressAlgoChild && isPressProg
                && isPressProgChild && isPressLan && isPressLanChild;
    }

    private void setSuccesefulDatabase(final ImageView view1,final Button view2) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("Users").child(user.getUid()).child("Number0");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("0/1")){
                    if (isAllAnswerTrue())
                        databaseReference.setValue("1/1");
                }else {
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.GONE);
                    Glide.with(view).load("https://ya-webdesign.com/images/up-vector-successful-3.png")
                            .into(view1);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
