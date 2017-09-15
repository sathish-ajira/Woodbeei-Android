package tech.ajira.woodbeei.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;
import tech.ajira.woodbeei.R;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        initializeImageSlider();
    }

    private void initializeImageSlider() {
        BannerSlider bannerSlider = (BannerSlider) findViewById(R.id.banner_slider);
        List<Banner> banners=new ArrayList<>();
        //add banner using image url
        banners.add(new RemoteBanner("http://woodbeei.com/sofas/x-cross/1.jpg"));
        banners.add(new RemoteBanner("http://woodbeei.com/sofas/x-cross/2.jpg"));
        banners.add(new RemoteBanner("http://woodbeei.com/sofas/x-cross/3.jpg"));
        banners.add(new RemoteBanner("http://woodbeei.com/sofas/x-cross/4.jpg"));
        banners.add(new RemoteBanner("http://woodbeei.com/sofas/x-cross/5.jpg"));
        //add banner using resource drawable
      //  banners.add(new DrawableBanner(R.drawable.yourDrawable));
        bannerSlider.setBanners(banners);
    }
}
