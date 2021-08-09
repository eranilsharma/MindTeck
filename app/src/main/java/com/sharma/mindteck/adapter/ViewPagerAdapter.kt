package com.sharma.mindteck.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.sharma.mindteck.R


class ViewPagerAdapter(context: Context) : PagerAdapter() {
    private val context: Context
    private val images = arrayOf<Int>(R.drawable.slide_1, R.drawable.slide_2, R.drawable.slide_3)
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View =LayoutInflater.from(context).inflate(R.layout.custom_layout, null);
        val imageView: ImageView = view.findViewById(R.id.imageView)
        imageView.setImageResource(images[position])
        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view: View = `object` as View
        vp.removeView(view)
    }

    init {
        this.context = context
    }
}