package jp.hasselqvist.MP4Analyzer.boxes;

import jp.hasselqvist.MP4Analyzer.Box;
import jp.hasselqvist.MP4Analyzer.BoxTree;

public class BoxFtyp extends Box {
	String mMajorBrand;
	long mMinorVersion;
	String[] mCompatibleBrands;

	public BoxFtyp(String aType, long aSize, long aStartOffset) {
		super(aType, aSize, aStartOffset);
	}

	@Override
	public BoxTree parse() {
		mMajorBrand = mProvider.readBoxType();
		mMinorVersion = mProvider.readInt32();

		int left = (int)(mSize - (mProvider.getPosition() - mStartOffset)) / 4;
		mCompatibleBrands = new String[left];

		for (int i = 0; i < left; ++i)
			mCompatibleBrands[i] = mProvider.readBoxType();

		return null;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("\n\t%s, %d, %s", mType, mSize, mMajorBrand, mMinorVersion, mCompatibleBrands[0]);
	}
}
