/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.jearomr.weatherapp;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

/**
 * A SingleLiveEvent used for messages. Like a {@link SingleLiveEvent} but also prevents
 * null messages and uses a custom observer.
 * <p>
 * Note that only one observer is going to be notified of changes.
 */
public class StringMessageEvent extends SingleLiveEvent<String> {

    public void observe(LifecycleOwner owner, final MessageObserver observer){
        super.observe(owner, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if(s == null){
                    return;
                }
                observer.onNewMessage(s);
            }
        });
    }

    public interface MessageObserver {
        /**
         * Called when there is a new message to be shown.
         * @param message The new message, non-null.
         */
        void onNewMessage(String message);
    }

}
