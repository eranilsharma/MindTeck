package com.sharma.mindteck.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.examp.project2.model.Model
import com.sharma.mindteck.R
import com.sharma.mindteck.adapter.RecyclerViewAdapter
import com.sharma.mindteck.adapter.ViewPagerAdapter
import com.sharma.mindteck.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var dotscount = 0
    lateinit var dots: Array<ImageView?>
    var labelList:ArrayList<Model> = ArrayList()
    lateinit var adapter: RecyclerViewAdapter
    lateinit var mainBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        createCustomData("Dummy Data")
        insiliazeAdapter();
        val viewPagerAdapter = ViewPagerAdapter(this)
        mainBinding.viewPager!!.adapter = viewPagerAdapter
        dotscount = viewPagerAdapter.count
        dots = arrayOfNulls(dotscount)
        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.non_active_dot
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            mainBinding.SliderDots!!.addView(dots[i], params)
        }
        dots[0]!!.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.active_dot
            )
        )
        mainBinding.viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]!!
                        .setImageDrawable(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.non_active_dot
                            )
                        )
                }
                dots[position]!!
                    .setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.active_dot
                        )
                    )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        mainBinding.edtSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(editable: Editable?) {
                filter(editable.toString());
            }

        })
    }
    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filterdLabel: ArrayList<Model> = ArrayList()

        //looping through existing elements
        for (s in labelList) {
            //if the existing elements contains the search input
            if (s.lable.contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdLabel.add(s)
            }
        }

        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filterdLabel)
    }
    private fun insiliazeAdapter() {
        adapter= RecyclerViewAdapter(this,labelList)
        mainBinding.childLayout.dummyList.layoutManager= LinearLayoutManager(this)
        mainBinding.childLayout.dummyList.adapter=adapter
    }

    private fun createCustomData(label: String) {
        for(i in 0..15){
            labelList.add(Model(label+" "+i,R.drawable.active_dot))
        }
    }
}