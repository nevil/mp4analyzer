package jp.hasselqvist.MP4Analyzer.boxes;

import jp.hasselqvist.MP4Analyzer.Box;

public class BoxMdat extends Box {

	public BoxMdat(String aType, long aSize, long aStartOffset) {
		super(aType, aSize, aStartOffset);
	}

	public boolean parse() {
		mProvider.skipBytes((int)(mSize - 8)); //temporary
		return true;
	}

}
