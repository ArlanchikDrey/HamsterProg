package ru.programminglearning.com.hamsterProg.Registration.Welcome

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import ru.programminglearning.com.project123456.R

/**
 * При реализации PagerAdapter необходимо переопределить как минимум следующие методы
 * instantiateItem(ViewGroup, int)
 * destroyItem(ViewGroup, int, Object)
 * getCount()
 * isViewFromObject(View, Object)
 */
class SliderAdapter internal constructor(private val context: Context) : PagerAdapter() {
    private val slideImages: IntArray
        private get() = intArrayOf(R.drawable.test1, R.drawable.test2)

    private val slideHeadings: Array<String>
        private get() = arrayOf(
                "Добро пожаловать в \n HamsterProg",
                "Изучите основы программирования"
        )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.slide_layout, container, false)
        init(position, view)
        container.addView(view)
        return view
    }

    private fun init(position: Int, view: View) {
        val slideImageView = view.findViewById<ImageView>(R.id.slide_image)
        val slideHeading = view.findViewById<TextView>(R.id.slide_heading)
        slideImageView.setImageResource(slideImages[position])
        slideHeading.text = slideHeadings[position]
    }

    override fun getCount(): Int {
        return slideHeadings.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o as RelativeLayout
    }

    /**
     * Переопределяем этот метод. Потому что без него при скролле PagerAdapter
     * может выброситься искллючение */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

}