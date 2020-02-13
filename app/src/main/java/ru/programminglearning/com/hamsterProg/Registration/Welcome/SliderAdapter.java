package ru.programminglearning.com.hamsterProg.Registration.Welcome;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ru.programminglearning.com.project123456.R;

/**
 * При реализации PagerAdapter необходимо переопределить как минимум следующие методы
 * instantiateItem(ViewGroup, int)
   destroyItem(ViewGroup, int, Object)
   getCount()
   isViewFromObject(View, Object)
 */

public class SliderAdapter extends PagerAdapter {
    private Context context;

    SliderAdapter(Context context) {
        this.context = context;
    }

    private int[] getSlideImages(){
        int[] listImage = {R.drawable.test1, R.drawable.test2};
        return  listImage;
    }

    private String[] getSlideHeadings(){
        String[] slide_headings = {
                "Добро пожаловать в \n HamsterProg",
                "Изучите основы программирования"
        };
        return slide_headings;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        init(position, view);
        container.addView(view);

        return view;
    }

    private void init(int position, View view) {
        ImageView slideImageView = view.findViewById(R.id.slide_image);
        TextView slideHeading =  view.findViewById(R.id.slide_heading);

        slideImageView.setImageResource(getSlideImages()[position]);
        slideHeading.setText(getSlideHeadings()[position]);
    }

    @Override
    public int getCount() {
        return getSlideHeadings().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout)o;
    }

    /**
     * Переопределяем этот метод. Потому что без него при скролле PagerAdapter
     * может выброситься искллючение*/
    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {
        container.removeView((RelativeLayout)object);
    }
}
