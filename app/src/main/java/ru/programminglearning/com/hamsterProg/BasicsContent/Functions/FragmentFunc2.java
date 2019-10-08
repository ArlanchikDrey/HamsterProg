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

public class FragmentFunc2 extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_func_2,container,false);

        TextView textView = view.findViewById(R.id.funArgsExample);
        textView.setText(Html.fromHtml(getString(R.string.funArgsExample)));

        Button button = view.findViewById(R.id.onNextTestFun22);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionsActivity.viewPager.setCurrentItem(3);
            }
        });
        return view;
    }


}
