package jp.hasselqvist.MP4Analyzer.boxes;

import jp.hasselqvist.MP4Analyzer.Box;

public class BoxFtyp extends Box {
	String mMajorBrand;
	long mMinorVersion;
	String[] mCompatibleBrands;

	public BoxFtyp(String aType, long aSize, long aStartOffset) {
		super(aType, aSize, aStartOffset);
	}

	public boolean parse() {
		mMajorBrand = mProvider.readBoxType();
		mMinorVersion = mProvider.readInt32();

		int left = (int)(mSize - (mProvider.getPosition() - mStartOffset)) / 4;
		mCompatibleBrands = new String[left];

		for (int i = 0; i < left; ++i)
			mCompatibleBrands[i] = mProvider.readBoxType();

		return true;
	}

	public String toString() {
		return String.format("%s (%d)\n\t%s, %d, %s", mType, mSize, mMajorBrand, mMinorVersion, mCompatibleBrands[0]);
	}
}
