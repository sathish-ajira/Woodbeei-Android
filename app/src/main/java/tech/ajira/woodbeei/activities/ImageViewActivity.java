package tech.ajira.woodbeei.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;
import tech.ajira.woodbeei.R;

public class ImageViewActivity extends AppCompatActivity {

    private String[] imageArray = {"http://woodbeei.com/sofas/x-cross/1.jpg",
            "http://woodbeei.com/sofas/x-cross/2.jpg",
            "http://woodbeei.com/sofas/x-cross/3.jpg",
            "http://woodbeei.com/sofas/x-cross/4.jpg",
            "http://woodbeei.com/sofas/x-cross/5.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        initializeImageSlider();
    }

    private void initializeImageSlider() {
        final BannerSlider bannerSlider = (BannerSlider) findViewById(R.id.banner_slider);
        List<Banner> banners=new ArrayList<>();
        for (int i = 0 ; i < imageArray.length; i++ ) {
            banners.add(new RemoteBanner(imageArray[i]));
        }
        bannerSlider.setBanners(banners);

        bannerSlider.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onClick(int position) {

            }
        });
    }
}
