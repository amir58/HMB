package com.gp.hmb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gp.hmb.databinding.ActivityVaccinateBinding;

public class VaccinateActivity extends AppCompatActivity {

    ActivityVaccinateBinding binding;

    String[] data = {
            "عند الولادة" +
                    "\n\n" +
                    "تطعيم الدرن\n" +
                    "تطعيم الكبد الفيروس ب (جرعه صفريه)\n" +
                    "تطعيم شلل الأطفال (جرعه صفريه)\n"
            ,
            "الشهر الثاني" +
                    "\n\n" +
                    "• تطعيم خماسي\n" +
                    "(الجرعة الأولى)\n" +
                    "• تطعيم شلل الأطفال  \n" +
                    "• (الجرعة الأولى)\n"
            ,
            "• اربعه شهور" +
                    "\n\n" +
                    "• تطعيم شلل الأطفال (بالحقن)￼￼\n" +
                    "￼ • تطعيم خماسي (الجرعة الثانية)\n" +
                    " • تطعيم شلل الأطفال (الجرعة الثانية)\n"
            ,
            "• سته شهور" +
                    "\n\n" +
                    "• تطعيم خماسي (الجرعة الثالثة)￼￼ \n" +
                    "• تطعيم شلل الأطفال (الجرعة الثالثة)\n"
            ,
            "• تسعه شهور" +
                    "\n\n" +
                    "• كبسولة فيتامين أ￼￼￼ \n" +
                    "• تطعيم شلل الأطفال (الجرعة الرابعة)\n"
            ,
            "• الثانية عشر￼￼￼ شهرا" +
                    "\n\n" +
                    "• تطعيم ضد (الحاصبة – النكاف – الحصبة الالماني)￼￼ \n" +
                    "• تطعيم شلل الأطفال (الجرعة الخامسة)\n"
            ,
            "• ثمانية عشر￼￼￼￼ شهرا" +
                    "\n\n" +
                    "• تطعيم ضد (الحاصبة – النكاف – الحصبة الالماني) (جرعة منشطة)￼￼￼ \n" +
                    "• 2 كبسولة فيتامين أ \n" +
                    "• تطعيم ثلاثي (جرعة منشطة) \n" +
                    "• تطعيم شلل الأطفال (الجرعة المنشطة)\n"
            ,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vaccinate);

        int position = 0;
        int days = getIntent().getIntExtra("days", 0);

        if (days <= 30) {
            position = 0;
        } else if (days <= 60) {
            position = 1;
        } else if (days <= 120) {
            position = 2;
        } else if (days <= 270) {
            position = 3;
        } else if (days <= 360) {
            position = 4;
        } else if (days <= 540) {
            position = 5;
        }

        binding.setMessage(data[position]);



    }

}
