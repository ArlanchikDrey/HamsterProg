package ru.programminglearning.com.hamsterProg.BasicsContent.TypeVariables;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.programminglearning.com.project123456.R;

public class TypeFragmentDescription1 extends Fragment {

    private View view;
    private TextView textView1,textView2,textView3;
    private Animation animation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_type_desc1,container,false);

        animation = AnimationUtils.loadAnimation(getActivity(),R.anim.from);


        initTextViewAnim();
        initImageView();
        setOnClick();
        return view;
    }

    private void initImageView(){
        ImageView imageView = view.findViewById(R.id.imageVariable);
        imageView.setAnimation(animation);
        Glide.with(getActivity()).
                load("https://pp.userapi.com/c845218/v845218631/1c1438/nOG9eWSa4yk.jpg").
                into(imageView);
    }

    private void initTextViewAnim(){
        textView1 = view.findViewById(R.id.whatIsVariable);
        textView2 = view.findViewById(R.id.varible1);
        textView3 = view.findViewById(R.id.varible2);


        textView1.setAnimation(animation);
        textView2.setAnimation(animation);
        textView3.setAnimation(animation);
    }

    private void setOnClick(){
        Button button = view.findViewById(R.id.onNextType);
        button.setAnimation(animation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TypeActivity.pager.setCurrentItem(1);
            }
        });
    }
}
