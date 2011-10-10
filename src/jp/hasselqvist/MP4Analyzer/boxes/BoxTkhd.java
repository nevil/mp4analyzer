package jp.hasselqvist.MP4Analyzer.boxes;

import jp.hasselqvist.MP4Analyzer.BoxFull;
import jp.hasselqvist.MP4Analyzer.BoxTree;

public class BoxTkhd extends BoxFull {
	long mCreationTime;
	long mModificationTime;
	long mTrackId;
	long mDuration;
	long mWidth;
	long mHeight;

	public BoxTkhd(String aType, long aSize, long aStartOffset) {
		super(aType, aSize, aStartOffset);

		if (this.mVersion == 1) {
			mCreationTime = mProvider.readInt64();
			mModificationTime = mProvider.readInt64();
			mTrackId = mProvider.readInt32();
			mProvider.readInt32(); // reserved 32
			mDuration = mProvider.readInt64();
		} else {
			mCreationTime = mProvider.readInt32();
			mModificationTime = mProvider.readInt32();
			mTrackId = mProvider.readInt32();
			mProvider.readInt32(); // reserved 32
			mDuration = mProvider.readInt32();
		}

		mProvider.readInt32(); // const unsigned int(32)[2] reserved = 0;
		mProvider.readInt32();

		mProvider.readInt16(); // template int(16) layer = 0;
		mProvider.readInt16(); // template int(16) alternate_group = 0;
		mProvider.readInt16(); // template int(16) volume = {if track_is_audio 0x0100 else 0};

		mProvider.readInt16(); //const unsigned int(16) reserved = 0;

		mProvider.readInt32(); // template int(32)[9] matrix=unity matrix
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();
		mProvider.readInt32();

		mWidth = mProvider.readInt32();
		mHeight = mProvider.readInt32();

	}

	@Override
	public BoxTree parse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("\n\t Creation time: %d, Modification time %d, track id %d, duration %d" +
				" width %d, height %d",
				mCreationTime, mModificationTime, mTrackId, mDuration,
				mWidth, mHeight);
	}

}
