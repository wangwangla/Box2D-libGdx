package com.qs.compat_app_25_4_0;/*
 * Copyright (C) 2012 The Android Open Source Project
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

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Helper for accessing features in {@link Notification}
 * introduced after API level 4 in a backwards compatible fashion.
 */
public class NotificationCompat {

    static final NotificationCompatImplBase IMPL;

    static class NotificationCompatImplBase {
        public Notification build(Builder b) {
            Notification result = b.mNotification;
            result = NotificationCompatBase.add(result, b.mContext,
                    b.mContentTitle, b.mContentText, b.mContentIntent);
            // translate high priority requests into legacy flag
            return result;
        }

    }

    static {
        IMPL = new NotificationCompatImplBase();
    }

    /**
     * Builder class for {@link NotificationCompat} objects.  Allows easier control over
     * all the flags, as well as help constructing the typical notification layouts.
     * <p>
     * On platform versions that don't offer expanded notifications, methods that depend on
     * expanded notifications have no effect.
     * </p>
     * <p>
     * For example, action buttons won't appear on platforms prior to Android 4.1. Action
     * buttons depend on expanded notifications, which are only available in Android 4.1
     * and later.
     * <p>
     * For this reason, you should always ensure that UI controls in a notification are also
     * available in an {@link Activity} in your app, and you should always start that
     * {@link Activity} when users click the notification. To do this, use the
     * {@link Builder#setContentIntent setContentIntent()}
     * method.
     * </p>
     *
     */
    public static class Builder {
        /**
         * Maximum length of CharSequences accepted by Builder and friends.
         *
         * <p>
         * Avoids spamming the system with overly large strings such as full e-mails.
         */
        private static final int MAX_CHARSEQUENCE_LENGTH = 5 * 1024;

        // All these variables are declared public/hidden so they can be accessed by a builder
        // extender.

        public Context mContext;

        public CharSequence mContentTitle;
        public CharSequence mContentText;
        PendingIntent mContentIntent;

        public Notification mNotification = new Notification();

        /**
         * Constructor.
         *
         * Automatically sets the when field to {@link System#currentTimeMillis()
         * System.currentTimeMillis()} and the audio stream to the
         * {@link Notification#STREAM_DEFAULT}.
         *
         * @param context A {@link Context} that will be used to construct the
         *      RemoteViews. The Context will not be held past the lifetime of this
         *      Builder object.
         */
        public Builder(Context context) {
            mContext = context;

            // Set defaults to match the defaults of a Notification
            mNotification.when = System.currentTimeMillis();
            mNotification.audioStreamType = Notification.STREAM_DEFAULT;

        }

        /**
         * Set the small icon to use in the notification layouts.  Different classes of devices
         * may return different sizes.  See the UX guidelines for more information on how to
         * design these icons.
         *
         * @param icon A resource ID in the application's package of the drawble to use.
         */
        public Builder setSmallIcon(int icon) {
            mNotification.icon = icon;
            return this;
        }

        /**
         * Set the title (first row) of the notification, in a standard notification.
         */
        public Builder setContentTitle(CharSequence title) {
            mContentTitle = limitCharSequenceLength(title);
            return this;
        }

        /**
         * Set the text (second row) of the notification, in a standard notification.
         */
        public Builder setContentText(CharSequence text) {
            mContentText = limitCharSequenceLength(text);
            return this;
        }

        /**
         * Supply a custom RemoteViews to use instead of the standard one.
         */
        public Builder setContent(RemoteViews views) {
            mNotification.contentView = views;
            return this;
        }

        /**
         * Supply a {@link PendingIntent} to send when the notification is clicked.
         * If you do not supply an intent, you can now add PendingIntents to individual
         * views to be launched when clicked by calling {@link RemoteViews#setOnClickPendingIntent
         * RemoteViews.setOnClickPendingIntent(int,PendingIntent)}.  Be sure to
         * read {@link Notification#contentIntent Notification.contentIntent} for
         * how to correctly use this.
         */
        public Builder setContentIntent(PendingIntent intent) {
            mContentIntent = intent;
            return this;
        }

        /**
         * Combine all of the options that have been set and return a new {@link Notification}
         * object.
         */
        public Notification build() {
            return IMPL.build(this);
        }

        protected static CharSequence limitCharSequenceLength(CharSequence cs) {
            if (cs == null) return cs;
            if (cs.length() > MAX_CHARSEQUENCE_LENGTH) {
                cs = cs.subSequence(0, MAX_CHARSEQUENCE_LENGTH);
            }
            return cs;
        }
    }
}
