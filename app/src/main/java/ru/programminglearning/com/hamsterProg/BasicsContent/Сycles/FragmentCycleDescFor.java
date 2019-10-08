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

public class FragmentCycleDescFor extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loop_desc_for,container,false);

        initText();
        onClick();
        return view;
    }

    private void initText(){
        TextView textView = view.findViewById(R.id.textForExample);
        textView.setText(Html.fromHtml(getString(R.string.ForExample)));
    }

    private void onClick(){
        Button button = view.findViewById(R.id.onNextTestFor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CyclesActivity.viewPager.setCurrentItem(3);
            }
        });
    }

}
