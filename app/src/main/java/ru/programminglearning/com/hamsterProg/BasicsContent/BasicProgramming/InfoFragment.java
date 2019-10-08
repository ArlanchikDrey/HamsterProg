package ru.programminglearning.com.hamsterProg.BasicsContent.BasicProgramming;

import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;

import java.util.Locale;

import ru.programminglearning.com.project123456.R;

public class InfoFragment extends Fragment implements TextToSpeech.OnInitListener {
    private View mView;
    protected static TextToSpeech mTTS;
    private RelativeLayout relativeLayoutLanguage, relativeLayoutProgram;
    private int countClick = 0;
    private ScrollView scrollView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_basic_info,
                container, false);

        scrollView = mView.findViewById(R.id.scroll);

        initTextImageFirst();
        onClick();
        return mView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTTS = new TextToSpeech(getActivity(), this);

    }

    /**
     * Этот метод запускает озвучку понятия алгоритма при первом запуске
     */
    private void initTextImageFirst() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String textAlghoritm = "Алгоритм" + "\n\n" +
                        "Алгоритм - это последовательность действий для достижения поставленной цели.";
                speak(textAlghoritm);
            }
        }, 1000);

        initImage(R.id.imageАлгоритм,getArrayUrl()[0]);

    }

    private void initImage(int id,String url) {
        ImageView imageView = mView.findViewById(id);
        Glide.with(mView).
                load(url)
                .into(imageView);
    }

    private void speak(String text) {
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    private String[] getArrayUrl(){
       String[] url = {"https://school3fryaz.edumsko.ru/uploads/2600/2513/section/151758/DYZh8cAW0AE4wwy.jpg?1546170032846",
       "https://www.cleveroad.com/images/article-previews/TOP-20-Programming-Languages-2015-all.png",
       "https://pp.userapi.com/c846120/v846120286/1b77ed/WNaV7ZVQLHU.jpg"};
       return url;
    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub
        if (status == TextToSpeech.SUCCESS) {

            Locale locale = new Locale("ru");

            int result = mTTS.setLanguage(locale);
            //int result = mTTS.setLanguage(Locale.getDefault());

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Извините, этот язык не поддерживается");
            }

        } else {
            Log.e("TTS", "Ошибка!");
        }
    }

    private void onClick() {
        Button button = mView.findViewById(R.id.onClickNext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countClick++;

                relativeLayoutLanguage = mView.findViewById(R.id.relativeLayoutLanguage);
                relativeLayoutProgram = mView.findViewById(R.id.relativeProgram);

                if (countClick == 1) {
                    relativeLayoutLanguage.setVisibility(View.VISIBLE);
                    onHandler();
                    speak(getResources().getText(R.string.языки_программирования).toString());
                    initImage(R.id.imageЯзыки,getArrayUrl()[1]);

                } else if (countClick == 2) {
                    relativeLayoutProgram.setVisibility(View.VISIBLE);
                    onHandler();
                    speak(getResources().getText(R.string.программа).toString());
                    initImage(R.id.imageПрограмма,getArrayUrl()[2]);
                } else if(countClick > 2){
                    BasicProgramming.viewPager.setCurrentItem(1);

                }


            }
        });
    }

    private void onHandler() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown mTTS!
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onStop() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onStop();
    }

}
