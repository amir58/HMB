package com.gp.hmb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gp.hmb.databinding.ActivityWarningBinding;

public class WarningActivity extends AppCompatActivity {
    ActivityWarningBinding binding;

    String[] data = {
            "• الطفل لا يستطيع النوم على بطنه. \n" +
                    "• يدا الطفل تميلان إلى الجنب والخلف، مثل وضعية الطائرة بشكل دائم عند نومه على بطنه. \n" +
                    "• جسم الطفل صلب جدا أو رخو جدا، مما قد يشير إلى جهد العضلات غير المتوازن. \n" +
                    "• الطفل غير هادئ ويبكي لساعات طويلة حتى عندما لا يكون هناك سبب أو مبرر لذلك. \n" +
                    "• الطفل لا يبدأ برفع رأسه، ولا يحركه من جانب إلى آخر.\n" +
                    " • الطفل يتعب بسرعة من نشاط بسيط، مثل الرضاعة. \n" +
                    "• الطفل لا يغمض عينيه عند التعرض للضوء الساطع. \n" +
                    "• رأس الطفل مائل أو جسمه كله يميل إلى جانب واحد. \n" +
                    "• لا يضع الطفل رأسه على الأرض عندما يكون مستلقيا على بطنه. \n" +
                    "• وزن الطفل لا يرتفع بشكل سليم.\n"
            ,
            "• . الطفل في هذه المرحلة لا ينبغي له الانقلاب من جانب إلى آخر.\n " +
                    "إذا كان يقوم بالانقلاب فهذا غير طبيعي ويدل على ضغط زائد في العضلات.\n" +
                    "• لا يحافظ الطفل على التواصل البصري مع والديه ولا يبتسم.\n" +
                    " • لدى الطفل تشوهات خفيفة أو شديدة في الجمجمة. \n" +
                    "• يواصل الطفل النوم لساعات طويلة، كما في الأسابيع الأولى من حياته.\n" +
                    " • تتواجد كدمات بلون أزرق في أجزاء مختلفة من جسم الطفل. \n" +
                    "• لا يصدر الطفل أي ضجيج ولا يتفاعل مع الضوضاء المختلفة التي تدور حوله. \n" +
                    "• يبدو الطفل عصبيا ويرفض تناول الطعام. \n"
            ,
            "• الطفل لا يزال غير قادر على رفع رأسه. \n" +
                    "• لا يحاول الطفل الامساك بالأشياء أو يفشل في الامساك بها. \n" +
                    "• لا يتواصل الطفل مع البيئة المحيطة. \n" +
                    "• لا يصدر الطفل أصواتا ولا يلفظ المقاطع السهلة. \n" +
                    "• لا يحاول الطفل التدحرج.\n"
            ,
            "• لا يستطيع الطفل الجلوس بشكل مريح حتى عند دعمه.\n" +
                    " • لا يصدر الطفل أصواتا أو لا يحاول التواصل مع البيئة.\n" +
                    " • لا يركز الطفل على الأشياء أو الصور.\n" +
                    " • لا يزال الطفل غير قادر على التدحرج من البطن الى الظهر وبالعكس.\n"
            ,
            "• عندما يفقد الطفل التوازن بسهولة وبشكل متكرر. على سبيل المثال: عندما ينحني لالتقاط شيء ما. \n" +
                    "• لا يزال الطفل غير قادر على لفظ المقاطع بالمرة.\n" +
                    " • لا يحافظ الطفل على التواصل البصري مع المحيطين به. \n" +
                    "• لا يزحف الطفل أو لا يحاول الوقوف.\n" +
                    " • لا يهتم باللعب.\n"
            ,
            "• الطفل غير قادر على السير خطوة واحدة حتى سن سنة ونصف. \n" +
                    "• الطفل لا يهتم بالألعاب واللعب المختلفة.\n" +
                    " • الطفل لا يستطيع ان يشغل نفسه ويكثر من البكاء.\n" +
                    " • لا يركز أثناء قراءة كتاب أو تصفح الصور.\n" +
                    " • لا يستجيب لاسمه حتى بعد سن ثمانية عشر شهرا. \n" +
                    "• حركات الطفل ليست متناسقة. على سبيل المثال، يفضل استخدام قدمه ويده اليسرى فقط.\n"
            ,
            "• الطفل لا يهتم بالألعاب الجديدة ولا يستخدمها بشكل صحيح. \n" +
                    "• لا ينجح الطفل في ربط كلمتين لتكوين جملة أو يكون قليل الكلام. \n" +
                    "• لا يتواصل الطفل من خلال اللغة، لا يسأل ولا يطلب أي شيء. \n" +
                    "• لا يستجيب الطفل عند التحدث معه، ولا يحاول إجراء محادثة\n"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_warning);

        int position = 0;
        int days = getIntent().getIntExtra("days", 0);

        if (days <= 30) {
            position = 0;
        } else if (days <= 90) {
            position = 1;
        } else if (days <= 180) {
            position = 2;
        } else if (days <= 270) {
            position = 3;
        } else if (days <= 360) {
            position = 4;
        } else if (days <= 540) {
            position = 5;
        } else if (days <= 720) {
            position = 6;
        }

        binding.setMessage(data[position]);
    }

}