package com.project.agroworld.articles;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.project.agroworld.R;
import com.project.agroworld.articles.model.CropsResponse;
import com.project.agroworld.articles.model.FlowersResponse;
import com.project.agroworld.articles.model.FruitsResponse;
import com.project.agroworld.databinding.ActivityArticleDetailsBinding;
import com.project.agroworld.utils.Constants;

public class ArticleDetailsActivity extends AppCompatActivity {
    private ActivityArticleDetailsBinding binding;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_details);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        CropsResponse cropsResponse = ((CropsResponse) intent.getSerializableExtra("itemResponse"));
        boolean isCropResponse = intent.getBooleanExtra("isCropResponse", false);
        FruitsResponse fruitsResponse = ((FruitsResponse) intent.getSerializableExtra("fruitItemResponse"));
        boolean isFruitResponse = intent.getBooleanExtra("isFruitResponse", false);
        FlowersResponse flowersResponse = ((FlowersResponse) intent.getSerializableExtra("flowerItemResponse"));
        boolean isFlowersResponse = intent.getBooleanExtra("isFlowersResponse", false);

        if (isCropResponse) {
            updateUIForCropsData(cropsResponse);
        } else if (isFruitResponse) {
            updateUIForFruitsData(fruitsResponse);
        } else if (isFlowersResponse) {
            updateUIForFlowersData(flowersResponse);
        }

    }

    private void updateUIForFlowersData(FlowersResponse response) {
        actionBar.setTitle(response.getTitle());
        binding.llSeason.setVisibility(View.GONE);
        binding.llTimeFertilize.setVisibility(View.GONE);
        Constants.bindImage(binding.ivItemPicture, response.getImageLink(), binding.ivItemPicture);
        binding.tvTitleArtDetailTxt.setText(response.getTitle());
        binding.tvStateArtDetailTxt.setText(response.getSeason());
        binding.tvArticleInfoTxt.setText(response.getFlowerInfo());
        binding.tvIrrigationInfoTxt.setText(response.getIrrigation());
        binding.tvLandPrepInfoTxt.setText(response.getLandPreparation());
        binding.tvHarvestingInfoTxt.setText(response.getHarvesting());
        binding.tvPostHarvestingInfoTxt.setText(response.getPostHarvest());
    }

    private void updateUIForFruitsData(FruitsResponse response) {
        actionBar.setTitle(response.getTitle());
        binding.llTimeFertilize.setVisibility(View.GONE);
        Constants.bindImage(binding.ivItemPicture, response.getImageLink(), binding.ivItemPicture);
        binding.tvTitleArtDetailTxt.setText(response.getTitle());
        binding.tvStateArtDetailTxt.setText(response.getState());
        binding.tvSeasonTxt.setText(response.getSeason());
        binding.tvReqTempTxt.setText(response.getRequiredTemperature());
        binding.tvArticleInfoTxt.setText(response.getFruitInfo());
        binding.tvIrrigationInfoTxt.setText(response.getIrrigation());
        binding.tvLandPrepInfoTxt.setText(response.getLandPreparation());
        binding.tvHarvestingInfoTxt.setText(response.getHarvesting());
        binding.tvPostHarvestingInfoTxt.setText(response.getPostHarvest());
    }

    private void updateUIForCropsData(CropsResponse response) {
        actionBar.setTitle(response.getTitle());
        Constants.bindImage(binding.ivItemPicture, response.getImageLink(), binding.ivItemPicture);
        binding.tvTitleArtDetailTxt.setText(response.getTitle());
        binding.tvStateArtDetailTxt.setText(response.getState());
        binding.tvSeasonTxt.setText(response.getSeason());
        binding.tvReqTempTxt.setText(response.getRequiredTemperature());
        binding.tvTimeTxt.setText(response.getTimeOfSowing());
        binding.tvFertilizeTxt.setText(response.getFertilizer());
        binding.tvArticleInfoTxt.setText(response.getCropInfo());
        binding.tvIrrigationInfoTxt.setText(response.getIrrigation());
        binding.tvLandPrepInfoTxt.setText(response.getLandPreparation());
        binding.tvHarvestingInfoTxt.setText(response.getHarvesting());
        binding.tvPostHarvestingInfoTxt.setText(response.getPostHarvest());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}