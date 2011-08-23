package jp.hasselqvist.MP4Analyzer.boxes;

import jp.hasselqvist.MP4Analyzer.Box;

public class BoxFree extends Box {
	public BoxFree(String aType, long aSize, long aStartOffset) {
		super(aType, aSize, aStartOffset);
	}

	public boolean parse() {
		return true;
	}
}
