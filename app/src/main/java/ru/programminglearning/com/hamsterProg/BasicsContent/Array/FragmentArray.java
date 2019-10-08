package ru.programminglearning.com.hamsterProg.BasicsContent.Array;

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

public class FragmentArray extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_array,container,false);

        initTextAboutArray();
        initTextArrayExample();
        initTextArrayExampleJava();
        onNextArray();

        return view;
    }

    private void initTextArrayExampleJava() {
        TextView textView = view.findViewById(R.id.textArrayExampleJava);
        textView.setText(Html.fromHtml(getString(R.string.exampleArrayJava)));

    }

    private void initTextAboutArray(){
        TextView textView = view.findViewById(R.id.textAboutArray);
        textView.setText(Html.fromHtml(getString(R.string.aboutArray)));
    }

    private void initTextArrayExample(){
        TextView textView = view.findViewById(R.id.textArrayExample);
        textView.setText(Html.fromHtml(getString(R.string.exampleArray)));
    }

    private void onNextArray(){
        Button button = view.findViewById(R.id.onNextArray);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayActivity.viewPager.setCurrentItem(1);
            }
        });
    }
}
