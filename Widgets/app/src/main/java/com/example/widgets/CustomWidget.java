package com.example.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.widget.RemoteViews;

public class CustomWidget extends AppWidgetProvider {

    private static boolean flashlightOn = false;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_view);
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Intent flashlightIntent = new Intent(context, CustomWidget.class);
            flashlightIntent.setAction("TOGGLE_FLASHLIGHT");
            PendingIntent flashlightPendingIntent = PendingIntent.getBroadcast(context, 0, flashlightIntent, 0);

            views.setOnClickPendingIntent(R.id.flashlight_button, flashlightPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals("TOGGLE_FLASHLIGHT")) {
            toggleFlashlight(context);
        }
    }

    private void toggleFlashlight(Context context) {
        // Получите доступ к менеджеру камеры (фонарику)
        CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            if (flashlightOn) {
                // Выключить фонарик
                cameraManager.setTorchMode(cameraId, false);
                flashlightOn = false;
            } else {
                // Включить фонарик
                cameraManager.setTorchMode(cameraId, true);
                flashlightOn = true;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}