package com.tpsmedia.mysoejasch;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tpsmedia.materialx.ui.design.data.DataGenerator;
import com.tpsmedia.materialx.ui.design.intro.IntroPagerAdapter;
import com.tpsmedia.materialx.ui.design.intro.Transformer;
import com.tpsmedia.materialx.ui.design.model.Image;
import com.tpsmedia.materialx.ui.design.utils.Tools;
import com.tpsmedia.appmanager.Prefs;
import com.tpsmedia.mysoejasch.databinding.ActivityIntroBinding;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ActivityIntroBinding binding;

    private IntroPagerAdapter.MyViewPagerAdapter myViewPagerAdapter;

    private List<Image> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        items = DataGenerator.getListImage(this, R.array.intro_image, R.array.intro_title, R.array.intro_content);

        initComponent();
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {

        IntroPagerAdapter.onChangeListener viewPagerPageChangeListener = new IntroPagerAdapter.onChangeListener(this, items.size(), binding.btnNext, binding.layoutDots, R.color.orange_700);

        myViewPagerAdapter = new IntroPagerAdapter.MyViewPagerAdapter(this, items, R.layout.item_intro);
        binding.viewPager.setAdapter(myViewPagerAdapter);
        binding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        binding.viewPager.setPageTransformer(false, new Transformer.Depth());
        binding.btnNext.setOnClickListener(v -> {
            int current = binding.viewPager.getCurrentItem() + 1;
            if (current < items.size()) {
                // move to next screen
                binding.viewPager.setCurrentItem(current);
            } else {
                new Prefs(this).setVersion_code(BuildConfig.VERSION_CODE);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        findViewById(R.id.bt_close).setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
    }
}