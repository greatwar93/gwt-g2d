/*
 * Copyright 2009 Hao Nguyen
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
package gwt.g2d.client.media;

import com.google.gwt.user.client.ui.FocusWidget;

/**
 * Represents a widget for manipulating a media element.
 * 
 * @author hao1300@gmail.com
 */
public abstract class Media extends FocusWidget { 
	
	protected Media() {
		
	}
	
	protected Media(MediaElement element) {
		setElement(element);
	}
	
	/**
	 * Gets how confident the user agent is that it can play media resources of 
	 * the given type.
	 * 
	 * @param type
	 */
	public Playability canPlayType(String type) {
		return getMediaElement().canPlayType(type);
	}
	
	/**
	 * Returns a TimeRanges object that represents the ranges of the media 
	 * resource that the user agent has buffered.
	 */
	public TimeRanges getBuffered() {
		return getMediaElement().getBuffered();
	}
	
	/**
	 * Gets the address of the current media resource.
	 * 
	 * @return the empty string when there is no media resource.
	 */
	public String getCurrentSrc() {
		return getMediaElement().getCurrentSrc();
	}

	/**
	 * Gets the current playback position, expressed in seconds.
	 */
	public float getCurrentTime() {
		return getMediaElement().getCurrentTime();
	}
	
	/**
	 * Gets the desired speed at which the media resource is to play, as a 
	 * multiple of its intrinsic speed.
	 */
	public float getDefaultPlaybackRate() {
		return getMediaElement().getDefaultPlaybackRate();
	}
	
	/**
	 * Gets the length of the media resource, in seconds.
	 */
	public float getDuration() {
		return getMediaElement().getDuration();
	}
	
	/**
	 * Gets the code for the error.
	 */
	public MediaError getError() {
		return getMediaElement().getError();
	}
	
	/**
	 * Gets the current network activity.
	 */
	public NetworkState getNetworkState() {
		return getMediaElement().getNetworkState();
	}
	
	/**
	 * Gets the speed at which the media resource plays, as a multiple of its 
	 * intrinsic speed.
	 */
	public float getPlaybackRate() {
		return getMediaElement().getPlaybackRate();
	}
	
	/**
	 * Gets a new static normalized TimeRanges object that represents the ranges 
	 * of the media resource, if any, that the user agent has so far rendered, 
	 * at the time the attribute is evaluated.
	 */
	public TimeRanges getPlayed() {
		return getMediaElement().getPlayed();
	}
	
	/**
	 * Gets the current ready state of the media element.
	 */
	public ReadyState getReadyState() {
		return getMediaElement().getReadyState();
	}
	
	/**
	 * Gets a new static normalized TimeRanges object that represents the ranges 
	 * of the media resource, if any, that the user agent is able to seek to, at 
	 * the time the attribute is evaluated.
	 */
	public TimeRanges getSeekable() {
		return getMediaElement().getSeekable();
	}
	
	/**
	 * Gets the address of the media resource (video, audio) to show.
	 */
	public String getSrc() {
		return getMediaElement().getSrc();
	}
	
	/**
	 * Gets the earliest possible position, expressed in seconds.
	 */
	public float getStartTime() {
		return getMediaElement().getStartTime();
	}
	
	/**
	 * Gets the playback volume of any audio portions of the media element, in 
	 * the range 0.0 (silent) to 1.0 (loudest).
	 */
	public float getVolume() {
		return getMediaElement().getVolume();
	}
	
	/**
	 * Gets whether autobuffer is used.
	 */
	public boolean isAutobuffer() {
		return getMediaElement().isAutobuffer();
	}
	
	/**
	 * Gets whether the user agent will automatically begin playback of the media 
	 * resource as soon as it can do so without stopping. 
	 */
	public boolean isAutoplay() {
		return getMediaElement().isAutobuffer();
	}
	
	/**
	 * Gets whether the user agent to provide its own set of controls.
	 */
  public boolean isControls() {
		return getMediaElement().isControls();
	}
	
	/**
	 * Returns true if the media element has ended playback and the direction of 
	 * playback is forwards, and false otherwise.
	 */
	public boolean isEnded() {
		return getMediaElement().isEnded();
	}
	
	/**
	 * Gets whether the media element is to seek back to the start of the media 
	 * resource upon reaching the end.
	 */
	public boolean isLoop() {
		return getMediaElement().isLoop();
	}
	
	/**
	 * Returns true if the audio channels are muted and false otherwise.
	 */
	public boolean isMuted() {
		return getMediaElement().isMuted();
	}
	
	/**
	 * Gets whether the media element is paused or not. The attribute is 
	 * initially true.
	 */
	public boolean isPaused() {
		return getMediaElement().isPaused();
	}
	
	/**
	 * Returns true if the user agent is currently seeking.
	 */
	public boolean isSeeking() {
		return getMediaElement().isSeeking();
	}
	
	/**
	 * Causes the element to reset and start selecting and loading a new media 
	 * resource from scratch.
	 */
	public void load() {
		getMediaElement().load();
	}
	
	/**
	 * Sets the paused attribute to true, loading the media resource if necessary.
	 */
	public void pause() {
		getMediaElement().pause();
	}
	
	/**
	 * Sets the paused attribute to false, loading the media resource and 
	 * beginning playback if necessary. If the playback had ended, will restart 
	 * it from the start.
	 */
	public void play() {
		getMediaElement().play();
	}
	
	/**
	 * 
	 * Sets whether autobuffer is used.
	 * If true, hints to the user agent that the author believes that the media 
	 * element will likely be used, even though the element does not have an 
	 * autoplay attribute. getMediaElement() attribute may be ignored altogether. The 
	 * attribute must be ignored if the autoplay attribute is present.
	 * 
	 * @param autobuffer 
	 */
	public void setAutobuffer(boolean autobuffer) {
		getMediaElement().setAutobuffer(autobuffer);
	}
	
	/**
	 * If true, the user agent will automatically begin playback of the media 
	 * resource as soon as it can do so without stopping.
	 */
	public void setAutoplay(boolean autoplay) {
		getMediaElement().setAutoplay(autoplay);
	}
	
	/**
	 * If true, it indicates that the author has not provided a scripted 
	 * controller and would like the user agent to provide its own set of 
	 * controls.
	 */
  public void setControls(boolean controls) {
		getMediaElement().setControls(controls);
	}
	
	/**
	 * Sets the current playback position, expressed in seconds.
	 */
	public void setCurrentTime(float currentTime) {
		getMediaElement().setCurrentTime(currentTime);
	}
	
	/**
	 * Sets the desired speed at which the media resource is to play, as a 
	 * multiple of its intrinsic speed.
	 * 
	 * Default: 1
	 */
	public void setDefaultPlaybackRate(float defaultPlaybackRate) {
		getMediaElement().setDefaultPlaybackRate(defaultPlaybackRate);
	}
	
	/**
	 * If true, indicates that the media element is to seek back to the start of 
	 * the media resource upon reaching the end.
	 */
	public void setLoop(boolean loop) {
		getMediaElement().setLoop(loop);
	}
	
	/**
	 * If true, the audio channels are muted.
	 */
	public void setMuted(boolean muted) {
		getMediaElement().setMuted(muted);
	}
	
	/**
	 * Sets the speed at which the media resource plays, as a multiple of its 
	 * intrinsic speed. If it is not equal to the defaultPlaybackRate, then the 
	 * implication is that the user is using a feature such as fast forward or 
	 * slow motion playback.
	 * 
	 * Default: 1
	 */
	public void setPlaybackRate(float playbackRate) {
		getMediaElement().setPlaybackRate(playbackRate);
	}
	
	/**
	 * Sets the address of the media resource (video, audio) to show.
	 * 
	 * @param src
	 */
	public void setSrc(String src) {
		getMediaElement().setSrc(src);
	}
	
	/**
	 * Sets the playback volume of any audio portions of the media element, in 
	 * the range 0.0 (silent) to 1.0 (loudest).
	 * 
	 * @param volume
	 */
	public void setVolume(float volume) {
		getMediaElement().setVolume(volume);
	}
	
	/**
	 * Gets the media element.
	 */
	protected MediaElement getMediaElement() {
		return getElement().cast();
	}
}
