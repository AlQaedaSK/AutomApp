package com.example.helder.automapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class SuperviseActivity extends AppCompatActivity {

    private TextView tvTitulo;
    private TextView tvLegenda;
    private TextView tvSair;
    private LinearLayout rlChart;

    //// TODO: 29/09/2016 criar variáveis de acordo com a necessidade
    private ArrayAdapter<String> myAdapter;
    private LineChart lineChart; // exemplo
    private ListView lvDados;
    private String[] dados = {
            "Temperatura:               21°C",
            "Estado do motor ESTEIRA:   ON",
            "Estado do motor BOMBA:     OFF"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervise);

        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        Bundle bundle = getIntent().getExtras();
        String sistema="INDEF";
        if(bundle.containsKey("SISTEMA")){
            sistema = bundle.getString("SISTEMA");
            tvTitulo.setText(sistema);

        }
        tvLegenda = (TextView) findViewById(R.id.tvLegenda);
        tvLegenda.setText("Informações do sistema controlado pelo "+sistema);
        tvSair = (TextView) findViewById(R.id.tvSair);
        tvSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvDados = (ListView) findViewById(R.id.lvDados);

        //// TODO: 29/09/2016 Verificar as informações a serem exibidas (repetição)
        myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dados);
        lvDados.setAdapter(myAdapter);

        rlChart = (LinearLayout) findViewById(R.id.rlChart);
        lineChart = new LineChart(this);
        rlChart.addView(lineChart);

        //Configuração do gráfico

        lineChart.setDescription("Variação da temperatura no tempo");
        lineChart.setNoDataTextDescription("Não existem dados para exibição no momento");
        lineChart.setHighlightPerTapEnabled(true);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.setPinchZoom(true);
        lineChart.setBackgroundColor(Color.LTGRAY);
        lineChart.setMinimumHeight(600);

        LineData lineData = new LineData();
        lineData.setValueTextColor(Color.WHITE);
        lineChart.setData(lineData);

        Legend leg = lineChart.getLegend();
        leg.setForm(Legend.LegendForm.LINE);
        leg.setTextColor(Color.BLUE);

        XAxis x1 = lineChart.getXAxis();
        x1.setTextColor(Color.BLUE);
        x1.setDrawGridLines(false);
        x1.setAvoidFirstLastClipping(true);

        YAxis y1 = lineChart.getAxisLeft();
        y1.setTextColor(Color.BLUE);
        y1.setDrawGridLines(true);
        y1.setAxisMaxValue(100f);

        YAxis y2 = lineChart.getAxisRight();
        y2.setEnabled(false);

    }

    @Override
    protected void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<24;i++){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();
                        }
                    });
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void addEntry(){
        LineData lineData = lineChart.getData();
        if(lineData != null) {
            LineDataSet set = (LineDataSet) lineData.getDataSetByIndex(0);
            if (set == null) {
                set = createSet();
                lineData.addDataSet(set);
            }
            //// TODO: 28/09/2016 leitura de valores para o gráfico
            lineData.addXValue("");
            Entry e = new Entry((float)Math.random()*100f, set.getEntryCount(), 0);
            lineData.addEntry(e,0);

            lineChart.notifyDataSetChanged();
            lineChart.setVisibleXRange(0f,12f);
            lineChart.moveViewToX(lineData.getXValCount()-13);



        }
    }

    private LineDataSet createSet(){
        LineDataSet set = new LineDataSet(null, "Leituras");
        set.setDrawCubic(true);
        set.setCubicIntensity(0.2f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setLineWidth(2f);
        set.setCircleColor(ColorTemplate.getHoloBlue());
        set.setLineWidth(2f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244,177,177));
        set.setValueTextColor(Color.BLUE);
        set.setValueTextSize(10f);

        return set;
    }
}