package jp.hasselqvist.MP4Analyzer.boxes;

import jp.hasselqvist.MP4Analyzer.BoxFull;
import jp.hasselqvist.MP4Analyzer.BoxTree;

public class BoxMvhd extends BoxFull {
	long mCreationTime;
	long mModificationTime;
	long mTimescale;
	long mDuration;
	long mRate;
	int mVolume;
	long mNextTrackId;

	public BoxMvhd(String aType, long aSize, long aStartOffset) {
		super(aType, aSize, aStartOffset);
		if (this.mVersion == 1) {
			mCreationTime = mProvider.readInt64();
			mModificationTime = mProvider.readInt64();
			mTimescale = mProvider.readInt64();
			mDuration = mProvider.readInt64();
		} else {
			mCreationTime = mProvider.readInt32();
			mModificationTime = mProvider.readInt32();
			mTimescale = mProvider.readInt32();
			mDuration = mProvider.readInt32();
		}

		mRate = mProvider.readInt32();
		mVolume = mProvider.readInt16();

		mProvider.readInt16(); // const bit(16) reserved = 0;
		mProvider.readInt32(); // const unsigned int(32)[2] reserved = 0;
		mProvider.readInt32();

		//	template int(32)[9] matrix =
		//		{ 0x00010000,0,0,0,0x00010000,0,0,0,0x40000000 };
		//	// Unity matrix
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();

		//	bit(32)[6]  pre_defined = 0;
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();

		//	unsigned int(32)  next_track_ID;
		mNextTrackId = mProvider.readInt32();

	}

	@Override
	public BoxTree parse() {
		return null;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("\n\t Creation time: %d, Modification time %d, time scale %d, duration %d, rate %d, volume %d, next track id %d",
				mCreationTime, mModificationTime, mTimescale, mDuration, mRate, mVolume, mNextTrackId);
	}
}
