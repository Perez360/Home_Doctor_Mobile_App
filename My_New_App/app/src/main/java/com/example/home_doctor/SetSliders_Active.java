package com.example.home_doctor;

import android.content.Context;
import android.view.animation.AnimationUtils;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetSliders_Active {
    List<SlideModel> imageList;

    public SetSliders_Active(Context context, ImageSlider imageSlider1) {
        imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.slide_img1, "Call us now for help on medications", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img2, "Call us for test", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img3, "Do you want to check your BP", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img4, "Check your Temperature", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img5, "Get intouch with our Doctors", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img6, "How to take pills", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img7, "Our Services", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img8, "Are you felling sick?\nCall us now on 0249065507", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img9, "Chat your Doctors", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img11, "Our Services", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img12, "Call us now for help", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img13, "Call us on 0249065507", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img14, "Do you like our Service?", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img15, "Don't know how to take you pills", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img16, "We can help you", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img17, "You can chat your doctors on this platform", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img19, "Call a Doctor", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img18, "We are here for you", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img20, "This platform is mobile frirendly", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img21, "Are you sick?", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slide_img22, "Make video conference with your Doctor", ScaleTypes.CENTER_CROP));

        Collections.shuffle(imageList);

        imageSlider1.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));

        imageSlider1.setImageList(imageList);
    }
}
