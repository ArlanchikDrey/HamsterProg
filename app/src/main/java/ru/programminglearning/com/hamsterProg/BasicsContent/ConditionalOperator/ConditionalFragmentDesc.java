package ru.programminglearning.com.hamsterProg.BasicsContent.ConditionalOperator;

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

public class ConditionalFragmentDesc extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_conditional_desc, container , false);

        initTextFirst();
        initTextAboutIf();
        initTextExampleIf();
        initTextExampleIfElse();
        onClick();

        return view;
    }

    private void initTextFirst(){
        TextView textView = view.findViewById(R.id.condTextSravn);
        String s = "Условие формируется при помощи следующих операторов сравнения:" +
                "\n"+
                "\n" + "< меньше чем"  +
                "\n" + "> больше чем" +
                "\n" + "!= не равно" +
                "\n" + "== равно" +
                "\n" + "<= меньше, либо равно"+
                "\n" + ">= больше, либо равно";
        textView.setText(s);
    }

    private void initTextAboutIf(){
        TextView textView = view.findViewById(R.id.condTextAboutIf);
        textView.setText(Html.fromHtml(getString(R.string.condDescAboutIf)));
    }

    private void initTextExampleIf(){
        TextView textView = view.findViewById(R.id.textCondIf);
        textView.setText(Html.fromHtml(getString(R.string.condExampleIf)));
    }

    private void initTextExampleIfElse(){
        TextView textView = view.findViewById(R.id.textCondIfelse);
        textView.setText(Html.fromHtml(getString(R.string.condExampleIfElse)));
    }

    private void onClick(){
        Button button = view.findViewById(R.id.onNextCond);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConditionalOperatorActivity.pager.setCurrentItem(1);
            }
        });

    }

}
