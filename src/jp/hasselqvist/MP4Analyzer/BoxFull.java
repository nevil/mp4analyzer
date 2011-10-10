package jp.hasselqvist.MP4Analyzer;

public abstract class BoxFull extends Box {
	protected int mVersion;
	protected int mFlags;

	public BoxFull(String aType, long aSize, long aStartOffset) {
		super(aType, aSize, aStartOffset);
		mVersion = mProvider.readInt8();
		mFlags = mProvider.readInt24();
	}

	public int getVersion() {
		return mVersion;
	}

	public int getFlags() {
		return mFlags;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("\n\t Version: %d, Flags: %x", mVersion, mFlags);
	}

}
