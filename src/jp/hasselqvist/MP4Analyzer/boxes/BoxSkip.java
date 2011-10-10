package jp.hasselqvist.MP4Analyzer.boxes;

import jp.hasselqvist.MP4Analyzer.Box;
import jp.hasselqvist.MP4Analyzer.BoxTree;

public class BoxSkip extends Box {
	public BoxSkip(String aType, long aSize, long aStartOffset) {
		super(aType, aSize, aStartOffset);
	}

	public BoxTree parse() {
		return null;
	}
}
