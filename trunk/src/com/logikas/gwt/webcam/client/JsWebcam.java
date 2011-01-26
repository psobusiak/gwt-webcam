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

class JsWebcam extends JavaScriptObject {

	public static final String ON_LOAD_HOOK = "onLoad";
	public static final String ON_ERROR_HOOK = "onError";
	public static final String ON_COMPLETE_HOOK = "onComplete";

	public native static JsWebcam get() /*-{
		return $wnd.webcam;
	}-*/;

	protected JsWebcam() {
	}
	
	public native final void set_hook(String hookName, JavaScriptObject hook) /*-{
		this.set_hook(hookName, hook);
	}-*/;

	public native final String get_html(int width, int height,
			int server_width, int server_height) /*-{
		return this.get_html(width, height, server_width, server_height);
	}-*/;

	public native final String get_html(int width, int height) /*-{
		return this.get_html(width, height);
	}-*/;

	public native final void set_shutter_sound(boolean enabled, String url) /*-{
		this.set_shutter_sound(enabled, url);
	}-*/;

	public native final void set_api_url(String api_url) /*-{
		this.set_api_url(api_url);
	}-*/;

	public native final void set_swf_url(String swf_url) /*-{
		this.set_swf_url(swf_url);
	}-*/;

	public native final void set_quality(int quality) /*-{
		this.set_quality(quality);
	}-*/;

	public native final void set_stealth(boolean enabled) /*-{
		this.set_stealth(enabled);
	}-*/;

	public native final void reset() /*-{
		this.reset();
	}-*/;

	public native final void upload() /*-{
		this.upload();
	}-*/;

	public native final void freeze() /*-{
		this.freeze();
	}-*/;

	public native final void snap() /*-{
		this.snap();
	}-*/;

	public native final void set_shutter_sound(boolean enabled) /*-{
		this.set_shutter_sound(enabled);
	}-*/;

	public native final void configure(String panel) /*-{
		this.configure(panel);
	}-*/;

	public native final void configure() /*-{
		this.configure();
	}-*/;
}
