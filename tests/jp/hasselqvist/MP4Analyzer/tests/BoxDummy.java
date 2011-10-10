package jp.hasselqvist.MP4Analyzer.tests;

import jp.hasselqvist.MP4Analyzer.Box;
import jp.hasselqvist.MP4Analyzer.BoxTree;

public class BoxDummy extends Box {
	String mId;

	public BoxDummy(String aType, long aSize, long aStartOffset) {
		super(aType, aSize, aStartOffset);
	}

	public BoxDummy(String aId) {
		this("Dummy", 0, 0);

		mId = aId;
	}

	@Override
	public BoxTree parse() {
		return null;
	}

	public String getId() {
		return mId;
	}
}
