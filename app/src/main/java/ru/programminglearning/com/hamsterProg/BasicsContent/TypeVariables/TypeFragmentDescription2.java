package ru.programminglearning.com.hamsterProg.BasicsContent.TypeVariables;

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

public class TypeFragmentDescription2 extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_type_desc2,container,false);


        initText();
        onClick();
        return view;
    }

    private void initText(){
        TextView textView = view.findViewById(R.id.textInteger2);
        textView.setText(Html.fromHtml(getString(R.string.integer)));

        TextView textView1 = view.findViewById(R.id.aboutTYpes);
        textView1.setText(Html.fromHtml(getString(R.string.aboutTepys)));

        TextView textView2 = view.findViewById(R.id.textManyTypes);
        textView2.setText(Html.fromHtml(getString(R.string.aboutTepys2)));
    }

    private void onClick(){
        Button button = view.findViewById(R.id.onNextType2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TypeActivity.pager.setCurrentItem(3);
            }
        });
    }
}
