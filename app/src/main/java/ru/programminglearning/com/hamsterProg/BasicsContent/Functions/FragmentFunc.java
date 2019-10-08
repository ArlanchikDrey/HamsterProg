package ru.programminglearning.com.hamsterProg.BasicsContent.Functions;

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

public class FragmentFunc extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_func,container,false);

        initTextAboutFun();
        initTextAnnounceFun();
        initAboutFunTypeReturn();
        initFunVoidExample();
        onClick();

        return view;
    }

    private void initTextAboutFun(){
        TextView textView = view.findViewById(R.id.textAboutFun);
        textView.setText(Html.fromHtml(getString(R.string.aboutFun)));
    }

    private void initTextAnnounceFun(){
        TextView textView = view.findViewById(R.id.funAnnounceExample);
        textView.setText(Html.fromHtml(getString(R.string.funAnnounceExample)));
    }

    private void initAboutFunTypeReturn(){
        TextView textView = view.findViewById(R.id.aboutFunTypeReturn);
        textView.setText(Html.fromHtml(getString(R.string.aboutFunTypeReturn)));
    }

    private void initFunVoidExample(){
        TextView textView = view.findViewById(R.id.funVoidExample);
        textView.setText(Html.fromHtml(getString(R.string.funVoidExample)));
    }

    private void onClick(){
        Button button = view.findViewById(R.id.onNextTestFun);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionsActivity.viewPager.setCurrentItem(1);
            }
        });
    }
}
