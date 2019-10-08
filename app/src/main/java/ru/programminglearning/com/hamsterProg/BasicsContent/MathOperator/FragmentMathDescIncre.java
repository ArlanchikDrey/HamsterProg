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
import android.widget.TextView;

import ru.programminglearning.com.project123456.R;

public class FragmentMathDescIncre extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_math_des_incre,container,false);

        initTextAbout();
        initTextExample();
        onClick();

        return view ;
    }

    private void initTextAbout(){
        TextView textView = view.findViewById(R.id.textAboutIncre);
        textView.setText(Html.fromHtml(getString(R.string.mathIncre)));
    }

    private void initTextExample(){
        TextView textView = view.findViewById(R.id.textExaplesMath);
        textView.setText(Html.fromHtml(getString(R.string.mathIncre2)));

        TextView textView2 = view.findViewById(R.id.textExaplesMath2);
        textView2.setText(Html.fromHtml(getString(R.string.mathIncre3)));

        TextView textView3 = view.findViewById(R.id.textExaplesMathPre);
        textView3.setText(Html.fromHtml(getString(R.string.mathPrefics)));

        TextView textView4 = view.findViewById(R.id.textExaplesMathPost);
        textView4.setText(Html.fromHtml(getString(R.string.mathPostfics)));
    }

    private void onClick(){
        Button button = view.findViewById(R.id.onNextMathIncre);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MathOperatorActivity.pager.setCurrentItem(3);
            }
        });
    }
}
