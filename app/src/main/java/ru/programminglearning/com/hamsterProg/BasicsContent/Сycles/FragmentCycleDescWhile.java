package ru.programminglearning.com.hamsterProg.BasicsContent.Сycles;

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

public class FragmentCycleDescWhile extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loop_desc_while,container,false);

        initTextAboutWhile();
        setCodeExampleWhile();
        onClick();

        return view;
    }

    private void initTextAboutWhile(){
        TextView textView = view.findViewById(R.id.textAboutLoop);
        textView.setText(Html.fromHtml(getString(R.string.aboutLoop)));

        TextView textView2 = view.findViewById(R.id.textAboutWhile);
        textView2.setText(Html.fromHtml(getString(R.string.aboutWhile)));
    }

    private void setCodeExampleWhile(){
        TextView textView = view.findViewById(R.id.textWhileExample);
        textView.setText(Html.fromHtml(getString(R.string.whileExample)));

        TextView textView2 = view.findViewById(R.id.textDoWhileExample);
        textView2.setText(Html.fromHtml(getString(R.string.doWhileExample)));
    }

    private void onClick(){
        Button button = view.findViewById(R.id.onNextTestWhile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CyclesActivity.viewPager.setCurrentItem(1);
            }
        });
    }
}
