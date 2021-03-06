/*
 * Copyright 2012 Kevin Sawicki <kevinsawicki@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.play.core;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Task to add starred songs to the queue
 */
public class QueueStarsTask extends AsyncTask<Void, Void, SongResult> {

	private static final String TAG = "QueueStarsTask";

	private final AtomicReference<PlayService> service;

	/**
	 * Create task to add starred songs to the queue
	 *
	 * @param service
	 */
	public QueueStarsTask(final AtomicReference<PlayService> service) {
		this.service = service;
	}

	@Override
	protected SongResult doInBackground(Void... params) {
		try {
			return new SongResult(service.get().queueStars());
		} catch (IOException e) {
			return new SongResult(e);
		}
	}

	@Override
	protected void onPostExecute(final SongResult result) {
		super.onPostExecute(result);

		if (result.exception != null)
			Log.d(TAG, "Queueing starred songs failed", result.exception);
	}
}
