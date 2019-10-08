package ru.programminglearning.com.hamsterProg.BasicsContent.MathOperator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.programminglearning.com.project123456.R;

public class FragmentMathDesc extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_math_desc,container,false);

        initImage();
        initTextExamples();
        onClick();
        return view ;
    }

    private void initImage(){
        ImageView imageView = view.findViewById(R.id.mathMainOperator);
        Glide.with(view)
                .load("https://pp.userapi.com/c849332/v849332477/14a330/tMxg5H-5XlM.jpg")
                .into(imageView);
    }

    private void initTextExamples(){
        TextView textView = view.findViewById(R.id.textExaplesMath);
        textView.setText(Html.fromHtml(getString(R.string.mathExamles)));
    }

    private void onClick(){
        Button button = view.findViewById(R.id.onNextMath);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MathOperatorActivity.pager.setCurrentItem(1);
            }
        });
    }
}
