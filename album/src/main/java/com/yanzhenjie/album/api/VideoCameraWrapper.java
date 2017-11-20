/*
 * Copyright © Yan Zhenjie. All Rights Reserved
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
package com.yanzhenjie.album.api;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.ui.CameraActivity;

/**
 * <p>Camera wrapper.</p>
 * Created by Yan Zhenjie on 2017/4/18.
 */
public class VideoCameraWrapper extends BasicCameraWrapper<VideoCameraWrapper> {

    @IntRange(from = 0, to = 1)
    private int mQuality = 1;
    @IntRange(from = 1, to = Long.MAX_VALUE)
    private long mLimitDuration = Long.MAX_VALUE;
    private long mLimitBytes = Long.MAX_VALUE;

    public VideoCameraWrapper(@NonNull Context context) {
        super(context);
    }

    /**
     * Currently value 0 means low quality, suitable for MMS messages, and  value 1 means high quality.
     */
    public VideoCameraWrapper quality(@IntRange(from = 0, to = 1) int quality) {
        this.mQuality = quality;
        return this;
    }

    /**
     * Specify the maximum allowed recording duration in seconds.
     */
    public VideoCameraWrapper limitDuration(@IntRange(from = 1, to = Long.MAX_VALUE) long duration) {
        this.mLimitDuration = duration;
        return this;
    }

    /**
     * Specify the maximum allowed size.
     */
    public VideoCameraWrapper limitBytes(@IntRange(from = 1, to = Long.MAX_VALUE) long bytes) {
        this.mLimitBytes = bytes;
        return this;
    }

    public void start() {
        CameraActivity.sResult = mResult;
        CameraActivity.sCancel = mCancel;
        Intent intent = new Intent(mContext, CameraActivity.class);
        intent.putExtra(Album.KEY_INPUT_REQUEST_CODE, mRequestCode);

        intent.putExtra(Album.KEY_INPUT_FUNCTION, Album.FUNCTION_CAMERA_VIDEO);
        intent.putExtra(Album.KEY_INPUT_FILE_PATH, mFilePath);
        intent.putExtra(Album.KEY_INPUT_CAMERA_QUALITY, mQuality);
        intent.putExtra(Album.KEY_INPUT_CAMERA_DURATION, mLimitDuration);
        intent.putExtra(Album.KEY_INPUT_CAMERA_BYTES, mLimitBytes);
        mContext.startActivity(intent);
    }
}