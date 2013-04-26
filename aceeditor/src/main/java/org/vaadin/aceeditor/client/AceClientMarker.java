package org.vaadin.aceeditor.client;

import java.io.Serializable;


@SuppressWarnings("serial")
public class AceClientMarker implements Serializable, Comparable<AceClientMarker> {
	
	public enum Type {
		line,
		text
	}
	
	/**
	 * What to do with the marker when the text changes.
	 * 
	 * By default, Ace just keeps the marker in its place (DEFAULT).
	 * Alternatively, you can set the marker to ADJUST to text insertion/deletion.
	 * Or, you can set the marker to be REMOVE'd on text change.
	 *
	 */
	public enum OnTextChange {
		/**
		 * Keep the marker in its place.
		 */
		DEFAULT,
		/**
		 * Adjust the marker based on text insertion/deletion.
		 */
		ADJUST,
		/**
		 * Remove the marker when text changes.
		 */
		REMOVE
	}
	
	public long serverId = -1;
	public AceClientRange range;
	public OnTextChange onChange;
	public String cssClass;
	public Type type;
	public boolean inFront = false;
	
	public AceClientMarker() {
		
	}
	
	
	
	public AceClientMarker(AceClientRange range, String cssClass, Type type, boolean inFront, OnTextChange onChange) {
		this.range = range.isBackwards() ? range.reversed() : range;
		this.cssClass = cssClass;
		this.type = type;
		this.inFront = inFront;
		this.onChange = onChange;
	}



	@Override
	public String toString() {
		return "("+range+";"+cssClass+";"+type+";"+inFront+")";
	}



	@Override
	public int compareTo(AceClientMarker other) {
		if (range.row1 < other.range.row1) {
			return -1;
		}
		if (range.row1 > other.range.row1) {
			return 1;
		}
		if (range.col1 < other.range.col1) {
			return -1;
		}
		if (range.col1 > other.range.col1) {
			return 1;
		}
		return 0;
	}
	
}
