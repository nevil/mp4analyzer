package jp.hasselqvist.MP4Analyzer.boxes;

import jp.hasselqvist.MP4Analyzer.Box;
import jp.hasselqvist.MP4Analyzer.BoxTree;

public class BoxFree extends Box {
	public BoxFree(String aType, long aSize, long aStartOffset) {
		super(aType, aSize, aStartOffset);
	}

	@Override
	public BoxTree parse() {
		return null;
	}
}
