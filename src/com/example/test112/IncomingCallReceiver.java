/*
 * Copyright (C) 2010 The Android Open Source Project
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

package com.example.test112;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.MailTo;
import android.net.sip.*;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Listens for incoming SIP calls, intercepts and hands them off to
 * WalkieTalkieActivity.
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class IncomingCallReceiver extends BroadcastReceiver {
	/**
	 * Processes the incoming call, answers it, and hands it over to the
	 * WalkieTalkieActivity.
	 * 
	 * @param context
	 *            The context under which the receiver is running.
	 * @param intent
	 *            The intent being received.
	 */
	private Dialog dialog;
	private ImageView img_acCall;
	private ImageView img_rejectCall;
	private SipAudioCall incomingCall = null;
	private TextView tv_nameUser;
	private int flagtocheckbusy;
	String Username;
	private MediaPlayer playerCallIn;

	MainActivity wtActivity;
	SipAudioCall.Listener listener;
	SipSession.Listener sessListener;

	@Override
	public void onReceive(final Context context, final Intent intent) {
		flagtocheckbusy = 0;
		wtActivity = (MainActivity) context;
		dialog = new Dialog(context);
		dialog.setContentView(R.layout.receivecalllayout);
		dialog.show();
		playerCallIn = MediaPlayer.create(context, R.raw.nhaccho1);
		playerCallIn.setLooping(true);
		playerCallIn.start();
		img_rejectCall = (ImageView) dialog.findViewById(R.id.img_rejectcall);
		tv_nameUser = (TextView) dialog.findViewById(R.id.tv_nameUseCalling);
		tv_nameUser.setText(getUsername(context, intent));
		sessListener = new SipSession.Listener() {

			@Override
			public void onCallEnded(SipSession session) {
				Log.i("SipAudioCall", "end call Hung");
				if (dialog.isShowing() == true) {
					dialog.dismiss();
				}
				super.onCallEnded(session);
			}

		};
		try {
			SipSession sipSess = wtActivity.manager.getSessionFor(intent);
			sipSess.setListener(sessListener);
			// sipSess.setListener()
		} catch (SipException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		listener = new SipAudioCall.Listener() {
			@Override
			public void onRinging(SipAudioCall call, SipProfile caller) {
				try {
					call.answerCall(30);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onCallEnded(SipAudioCall call) {
				// TODO Auto-generated method stub
				if (dialog.isShowing() == true) {
					dialog.dismiss();
				}
				super.onCallEnded(call);
			}

			@Override
			public void onCallEstablished(SipAudioCall call) {
				// TODO Auto-generated method stub
				playerCallIn.stop();
				try {
					playerCallIn.prepare();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.onCallEstablished(call);
				flagtocheckbusy = 1;
			}
		};

		img_acCall = (ImageView) dialog.findViewById(R.id.img_acceptcall);
		img_acCall.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Click to accept call
				takeACall(context, intent);
				img_acCall.setImageResource(R.drawable.called);
				img_acCall.setClickable(false);
				playerCallIn.stop();
				try {
					playerCallIn.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		img_rejectCall.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Click to reject call
				try {
					incomingCall.endCall();
					// wtActivity.ketthuccuocgoi();
				} catch (SipException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				playerCallIn.stop();
				try {
					playerCallIn.prepare();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dialog.dismiss();
			}
		});

	}

	/**
	 * TODO take a call
	 * 
	 * @param context
	 * @param intent
	 */

	public void takeACall(Context context, Intent intent) {
		try {

			incomingCall = wtActivity.manager.takeAudioCall(intent, listener);
			incomingCall.answerCall(30);
			incomingCall.startAudio();
			incomingCall.setSpeakerMode(true);
			if (incomingCall.isMuted()) {
				Log.i("Sip Out Call", "has Muted");
				incomingCall.toggleMute();
			}
			wtActivity.call = incomingCall;
			Log.i("Nhan dien thoai", incomingCall.getPeerProfile()
					.getUriString());

		} catch (Exception e) {
			if (incomingCall != null) {
				incomingCall.close();
			}
		}
	}

	public String getUsername(Context context, Intent intent) {
		SipAudioCall.Listener listener1 = new SipAudioCall.Listener() {

		};
		try {
			incomingCall = wtActivity.manager.takeAudioCall(intent, listener1);
		} catch (SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return incomingCall.getPeerProfile().getUriString();
	}

}
