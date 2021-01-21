package com.example.widget2;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class ExampleAppwidgetProvider extends AppWidgetProvider {
    private static int mCounter = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {

            RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.example_widget);


            // getActivity
            Intent intent1 = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 0, intent1, 0);
            remoteView.setOnClickPendingIntent(R.id.btn1, pendingIntent1);

          // getBroadcast
            Intent intent2 = new Intent(context, ExampleAppwidgetProvider.class);
            intent2.setAction("btn2");
            PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context, 0, intent2, 0);
            remoteView.setOnClickPendingIntent(R.id.btn2, pendingIntent2);

            remoteView.setTextViewText(R.id.txt_count,"0");

            appWidgetManager.updateAppWidget(appWidgetId, remoteView);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if(intent.getAction().equals("btn2")){

            mCounter++;
            RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.example_widget);

            remoteView.setTextViewText(R.id.txt_count,String.valueOf(mCounter));
//            Toast.makeText(context, "btn2 clicked !", Toast.LENGTH_SHORT).show();

            // This time we dont have widgetId. Reaching our widget with that way.
            ComponentName appWidget = new ComponentName(context, ExampleAppwidgetProvider.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidget, remoteView);
        }
    }
}
