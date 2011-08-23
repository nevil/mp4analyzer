package jp.hasselqvist.MP4Analyzer;

public abstract class Box {
	protected long mStartOffset;
	protected String mType;
	protected long mSize;
	protected MP4FileProvider mProvider = MP4FileProvider.getProvider();

	public Box(String aType, long aSize, long aStartOffset) {
		mType = aType;
		mSize = aSize;
		mStartOffset = aStartOffset;
	}

	public String getType() {
		return mType;
	}

	public long getSize() {
		return mSize;
	}

	abstract public boolean parse();

	public String toString() {
		return String.format("%s (%d)", mType, mSize);
	}
}
