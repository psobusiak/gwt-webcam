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
package com.logikas.gwt.webcam.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.logikas.gwt.webcam.client.resources.WebcamResources;

class WebcamImpl extends Webcam {

  protected WebcamImpl() {

    final JsWebcam jsWebcam = JsWebcam.get();

    jsWebcam.set_hook(JsWebcam.ON_LOAD_HOOK, createOnLoadHook());

    jsWebcam.set_hook(JsWebcam.ON_ERROR_HOOK, createOnErrorHook());

    jsWebcam.set_hook(JsWebcam.ON_COMPLETE_HOOK, createOnCompleteHook());

    jsWebcam.set_swf_url(WebcamResources.INSTANCE.webcamMovie().getUrl());

    jsWebcam.set_shutter_sound(true, WebcamResources.INSTANCE.shutterSound().getUrl());
  }

  private native JavaScriptObject createOnErrorHook() /*-{
    return function(result) {
    	this.@com.logikas.gwt.webcam.client.WebcamImpl::fireErrorEvent(Ljava/lang/String;)(result);
    };
  }-*/;

  private native JavaScriptObject createOnLoadHook() /*-{
    return function() {
    	this.@com.logikas.gwt.webcam.client.WebcamImpl::fireLoadEvent()();
    };
  }-*/;

  private native JavaScriptObject createOnCompleteHook() /*-{
    return function(result) {
    	this.@com.logikas.gwt.webcam.client.WebcamImpl::fireCompleteEvent(Ljava/lang/String;)(result);
    };
  }-*/;

  @Override
  public void setShutterSoundEnabled(boolean shutterSoundEnabled) {
    JsWebcam.get().set_shutter_sound(shutterSoundEnabled);
  }

  @Override
  public void setStealthEnabled(boolean stealthEnabled) {
    JsWebcam.get().set_stealth(stealthEnabled);
  }

  @Override
  public void configure(ConfigurationType configuration) {
    JsWebcam.get().configure(configuration.getTypeName());
  }

  @Override
  public void configure() {
    JsWebcam.get().configure();
  }

  @Override
  public void snap() {
    JsWebcam.get().snap();
  }

  @Override
  public void freeze() {
    JsWebcam.get().freeze();
  }

  @Override
  public void upload() {
    JsWebcam.get().upload();
  }

  @Override
  public void reset() {
    JsWebcam.get().reset();
  }

  @Override
  public void setQuality(int quality) {
    JsWebcam.get().set_quality(quality);
  }

  @Override
  public void setApiUrl(String apiUrl) {
    JsWebcam.get().set_api_url(apiUrl);
  }

  @Override
  protected Widget createCanvas(int width, int height, int serverWidth, int serverHeight) {
    final String html = JsWebcam.get().get_html(width, height, serverWidth, serverHeight);
    final HTMLPanel panel = new HTMLPanel(html);
    panel.setPixelSize(width, height);
    return panel;
  }
}
