package com.example.widget2;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    Button btn_show, btn_hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // atleast every 30 minute widget will update

        btn_show = findViewById(R.id.btn_show);
        btn_hide = findViewById(R.id.btn_hide);


        btn_show.setOnClickListener(v -> {

            RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.example_widget);
            remoteView.setViewVisibility(R.id.widget_root,View.VISIBLE);

            // This time we dont have widgetId. Reaching our widget with that way.
            ComponentName appWidget = new ComponentName(MainActivity.this, ExampleAppwidgetProvider.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(MainActivity.this);
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidget, remoteView);

        });



        btn_hide.setOnClickListener(v -> {
            RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.example_widget);
            remoteView.setViewVisibility(R.id.widget_root,View.GONE);

            // This time we dont have widgetId. Reaching our widget with that way.
            ComponentName appWidget = new ComponentName(MainActivity.this, ExampleAppwidgetProvider.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(MainActivity.this);
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidget, remoteView);

        });
    }
}