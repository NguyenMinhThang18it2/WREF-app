package thang.com.wref.Main.More;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import thang.com.wref.Adapter.CropYieldAdapter;
import thang.com.wref.Adapter.PPAdapter;
import thang.com.wref.Models.CropYieldModel;
import thang.com.wref.Models.PPModel;
import thang.com.wref.R;

public class CropYieldActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CropYieldActivity";
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private String title = "";
    private RecyclerView rcvProcess, rcvProductivityPrediction;
    private BarChart myBarChart;
    private ImageView imgTitle;

    private ArrayList<CropYieldModel> cropYieldModelsArr;
    private CropYieldAdapter cropYieldAdapter;

    private ArrayList<PPModel> ppModelsArr;
    private PPAdapter ppAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_yield);
        mappingView();
        setUpToolBar();
        addDataProcess();
        addDataPP();
        addBarChart();
    }

    private void setUpToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = getIntent().getStringExtra("plant");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(title.equals("Dưa leo")) imgTitle.setImageResource(R.drawable.img_dua_leo);
        else if(title.equals("Cà chua")) imgTitle.setImageResource(R.drawable.img_ca_chua);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.CollapsingToolbarLayout);

        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
                {
                    collapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);
                    toolbar.getNavigationIcon().setTint(Color.BLACK);
                }
                else
                {
                    collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
                    toolbar.getNavigationIcon().setTint(Color.WHITE);
                }
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mappingView() {
        rcvProcess = (RecyclerView) findViewById(R.id.rcvProcess);
        rcvProductivityPrediction = (RecyclerView) findViewById(R.id.rcvProductivityPrediction);
        myBarChart = (BarChart) findViewById(R.id.myBarChart);
        imgTitle = (ImageView) findViewById(R.id.imgTitle);
    }

    @Override
    public void onClick(View v) {

    }
    private void addDataProcess() {
        rcvProcess.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvProcess.setLayoutManager(linearLayoutManager);

        cropYieldModelsArr = new ArrayList<>();
        cropYieldModelsArr.add(
                new CropYieldModel(
                        "Chọn giống - Gieo hạt", R.drawable.ic_process_1, "Các loại giống tối ưu", "12 loại giống", R.drawable.ic_baseline_check, "#22E079"
                ));
        cropYieldModelsArr.add(
                new CropYieldModel(
                        "Làm đất - Trồng cây", R.drawable.ic_process_2, "Chuẩn bị tốt cho cây trồng", "5 Kĩ thuật", R.drawable.ic_baseline_check, "#22E079"
                ));
        cropYieldModelsArr.add(
                new CropYieldModel(
                        "Bón phân - Phát triển", R.drawable.ic_process_3, "Tăng năng suất cây trồng", "6 Phân bón", R.drawable.ic_baseline_check, "#22E079"
                ));
        cropYieldModelsArr.add(
                new CropYieldModel(
                        "Phòng bệnh - Thuốc trừ sâu", R.drawable.ic_process_4, "Phòng các nguy cơ sâu bệnh", "8 Thuốc trừ sâu", R.drawable.ic_outline_circle, "#2285E0"
                ));
        cropYieldModelsArr.add(
                new CropYieldModel(
                        "Thu hoạch - Bảo quản", R.drawable.ic_process_5, "Phòng các nguy cơ sâu bệnh", "3 Kĩ thuật", R.drawable.ic_baseline_access_time, "#62AEFB"
                ));
        cropYieldAdapter = new CropYieldAdapter(cropYieldModelsArr, this);
        rcvProcess.setAdapter(cropYieldAdapter);
    }

    private void addDataPP(){
        rcvProductivityPrediction.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvProductivityPrediction.setLayoutManager(linearLayoutManager);

        ppModelsArr = new ArrayList<>();
        ppModelsArr.add(
                new PPModel(
                        "55.5 Tạ/ha", "Tháng 4 - 7", R.drawable.ic_mdi_arrow_top_right, "130%", "#22E079"
                ));
        ppModelsArr.add(
                new PPModel(
                        "40.5 Tạ/ha", "Tháng 8 - 10", R.drawable.ic_mdi_arrow_top_right, "105%", "#2285E0"
                ));
        ppModelsArr.add(
                new PPModel(
                        "40.5 Tạ/ha", "Tháng 11 - 2(năm sau)", R.drawable.ic_mdi_arrow_bottom_right, "85%", "#E04422"
                ));

        ppAdapter = new PPAdapter(ppModelsArr, this);
        rcvProductivityPrediction.setAdapter(ppAdapter);
    }

    private void addBarChart(){
        myBarChart.setDrawBarShadow(false);
        myBarChart.setDrawValueAboveBar(true);
        myBarChart.setPinchZoom(false);
        myBarChart.setDrawGridBackground(false);
        myBarChart.setScaleEnabled(false);
        myBarChart.getDescription().setEnabled(false);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(0, 40f));
        barEntries.add(new BarEntry(1, 30f));
        barEntries.add(new BarEntry(2, 44f));
        barEntries.add(new BarEntry(3, 105f));
        barEntries.add(new BarEntry(4, 120f));
        barEntries.add(new BarEntry(5, 118f));
        barEntries.add(new BarEntry(6, 109f));
        barEntries.add(new BarEntry(7, 90f));
        barEntries.add(new BarEntry(8, 102f));
        barEntries.add(new BarEntry(9, 95f));
        barEntries.add(new BarEntry(10, 28f));
        barEntries.add(new BarEntry(11, 44f));

        Legend legend = myBarChart.getLegend();
        legend.setEnabled(false);

        String[] months = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12"};
        XAxis xAxis = myBarChart.getXAxis();
        xAxis.setAxisMinimum(-0.5f);
        xAxis.setGranularity(0f);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(12);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxisLeft = myBarChart.getAxisLeft();
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setAxisMaximum(150f);

        YAxis yAxisRight = myBarChart.getAxisRight();
        yAxisRight.setEnabled(false);

        BarDataSet barDataSet = new BarDataSet(barEntries, "Data set 1");
        barDataSet.setColors(new int[] {Color.parseColor("#FFE8E3"),
                Color.parseColor("#FFE8E3"),
                Color.parseColor("#FFE8E3"),
                Color.parseColor("#22E079"),
                Color.parseColor("#22E079"),
                Color.parseColor("#22E079"),
                Color.parseColor("#22E079"),
                Color.parseColor("#B2E2FE"),
                Color.parseColor("#B2E2FE"),
                Color.parseColor("#B2E2FE"),
                Color.parseColor("#FFE8E3"),
                Color.parseColor("#FFE8E3")
        });

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);

        myBarChart.setData(barData);
    }
    private class MyBarChartRenderer extends BarChartRenderer{

        public MyBarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
            super(chart, animator, viewPortHandler);
        }

    }
}