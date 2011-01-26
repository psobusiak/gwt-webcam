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

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Widget;

public abstract class Webcam {

  public static enum ConfigurationType {

    CAMERA("camera"), 
    PRIVACY("privacy"), 
    DEFAULT("default"), 
    LOCAL_STORAGE("localStorage"), 
    MICROPHONE("microphone"), 
    SETTINGS_MANAGER("settingsManager");

    private final String typeName;

    private ConfigurationType(String typeName) {
      this.typeName = typeName;
    }

    public String getTypeName() {
      return typeName;
    }
  }

  private static Webcam instance = null;

  public static Webcam get() {
    if (instance == null) {
      instance = new WebcamImpl();
    }
    return instance;
  }

  protected final HandlerManager handlerManager = new HandlerManager(this);

  protected Widget currentWidget;

  public static interface CompleteHandler extends EventHandler {

    void onComplete(CompleteEvent event);
  }

  public static class CompleteEvent extends GwtEvent<CompleteHandler> {

    private static final Type<CompleteHandler> TYPE = new Type<CompleteHandler>();

    static Type<CompleteHandler> getType() {
      return TYPE;
    }

    private final String result;

    protected CompleteEvent(String result) {
      this.result = result;
    }

    @Override
    public final Type<CompleteHandler> getAssociatedType() {
      return TYPE;
    }

    public String getResult() {
      return result;
    }

    @Override
    protected void dispatch(CompleteHandler handler) {
      handler.onComplete(this);
    }
  }

  protected void fireCompleteEvent(String result) {
    handlerManager.fireEvent(new CompleteEvent(result));
  }

  public void addWebcamCompleteHandler(CompleteHandler handler) {
    handlerManager.addHandler(CompleteEvent.getType(), handler);
  }

  public static interface LoadHandler extends EventHandler {

    void onLoad(LoadEvent event);
  }

  public static class LoadEvent extends GwtEvent<LoadHandler> {

    private static final Type<LoadHandler> TYPE = new Type<LoadHandler>();

    static Type<LoadHandler> getType() {
      return TYPE;
    }

    protected LoadEvent() {
    }

    @Override
    public final Type<LoadHandler> getAssociatedType() {
      return TYPE;
    }

    @Override
    protected void dispatch(LoadHandler handler) {
      handler.onLoad(this);
    }
  }

  protected void fireLoadEvent() {
    handlerManager.fireEvent(new LoadEvent());
  }

  public void addWebcamLoadHandler(LoadHandler handler) {
    handlerManager.addHandler(LoadEvent.getType(), handler);
  }

  public static interface ErrorHandler extends EventHandler {

    void onError(ErrorEvent event);
  }

  public static class ErrorEvent extends GwtEvent<ErrorHandler> {

    private static final Type<ErrorHandler> TYPE = new Type<ErrorHandler>();

    static Type<ErrorHandler> getType() {
      return TYPE;
    }

    private final String result;

    protected ErrorEvent(String result) {
      this.result = result;
    }

    @Override
    public final Type<ErrorHandler> getAssociatedType() {
      return TYPE;
    }

    public String getResult() {
      return result;
    }

    @Override
    protected void dispatch(ErrorHandler handler) {
      handler.onError(this);
    }
  }

  protected void fireErrorEvent(String result) {
    handlerManager.fireEvent(new ErrorEvent(result));
  }

  public void addWebcamErrorHandler(ErrorHandler handler) {
    handlerManager.addHandler(ErrorEvent.getType(), handler);
  }

  public abstract void setShutterSoundEnabled(boolean shutterSoundEnabled);

  public abstract void snap();

  public abstract void freeze();

  public abstract void upload();

  public abstract void reset();

  public abstract void setQuality(int quality);

  public abstract void setApiUrl(String apiUrl);

  public abstract void configure(ConfigurationType configurationType);

  public abstract void configure();

  public abstract void setStealthEnabled(boolean stealthEnabled);

  protected abstract Widget createWidget(int width, int height, int serverWidth, int serverHeight);

  public Widget createCurrentWidget(int width, int height, int serverWidth, int serverHeight) {
    if (currentWidget != null) {
      currentWidget.removeFromParent();
    }
    currentWidget = createWidget(width, height, serverWidth, serverHeight);
    return currentWidget;
  }

  public Widget createCurrentWidget(int width, int height) {
    return createCurrentWidget(width, height, width, height);
  }
}
