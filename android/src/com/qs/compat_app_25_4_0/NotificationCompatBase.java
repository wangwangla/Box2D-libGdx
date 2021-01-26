package com.qs.compat_app_25_4_0;/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @hide
 */
@TargetApi(9)
public class NotificationCompatBase {
    private static Method sSetLatestEventInfo;

    public static Notification add(Notification notification, Context context,
                                   CharSequence contentTitle, CharSequence contentText, PendingIntent contentIntent) {
        if (sSetLatestEventInfo == null) {
            try {
                sSetLatestEventInfo = Notification.class.getMethod("setLatestEventInfo",
                        Context.class, CharSequence.class, CharSequence.class, PendingIntent.class);
            } catch (NoSuchMethodException e) {
                // This method was @removed, so it must exist on later
                // versions even if it's not in public API.
                throw new RuntimeException(e);
            }
        }

        try {
            sSetLatestEventInfo.invoke(notification, context,
                    contentTitle, contentText, contentIntent);
        } catch (IllegalAccessException e) {
            // This method was @removed, so it must be invokable on later
            // versions even if it's not in public API.
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            // This method was @removed, so it must be invokable on later
            // versions even if it's not in public API.
            throw new RuntimeException(e);
        }
        return notification;
    }
}
