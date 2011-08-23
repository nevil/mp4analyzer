package jp.hasselqvist.MP4Analyzer.boxes;

import jp.hasselqvist.MP4Analyzer.Box;

public class BoxFtyp extends Box {
	String mMajorBrand;
	long mMinorVersion;
	long[] mCompatibleBrands;

	public BoxFtyp(String aType, long aSize) {
		super(aType, aSize);
	}

	public boolean parse() {
		mMajorBrand = mProvider.readBoxType();
		
		return false;
	}
}
