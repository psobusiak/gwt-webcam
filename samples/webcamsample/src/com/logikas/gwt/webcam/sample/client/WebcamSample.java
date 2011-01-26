/*
 * Copyright 2011 Logikas.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.logikas.gwt.webcam.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.logikas.gwt.webcam.client.Webcam;
import com.logikas.gwt.webcam.client.Webcam.ErrorEvent;
import com.logikas.gwt.webcam.client.Webcam.LoadEvent;

public class WebcamSample implements EntryPoint {

  @Override
  public void onModuleLoad() {

    final Webcam webcam = Webcam.get();

    webcam.setShutterSoundEnabled(true);
    webcam.setApiUrl("resources");
    webcam.setQuality(90);
    webcam.setShutterSoundEnabled(false);
    webcam.setStealthEnabled(true);

    webcam.addWebcamErrorHandler(new Webcam.ErrorHandler() {

      @Override
      public void onError(ErrorEvent event) {
        Window.alert("There is an error: " + event.getResult());
      }
    });

    webcam.addWebcamLoadHandler(new Webcam.LoadHandler() {

      @Override
      public void onLoad(LoadEvent event) {
        webcam.configure(Webcam.ConfigurationType.PRIVACY);
      }
    });

    final Widget widget = webcam.createCurrentWidget(320, 240);

    RootPanel.get("webcam").add(widget);

  }
}
