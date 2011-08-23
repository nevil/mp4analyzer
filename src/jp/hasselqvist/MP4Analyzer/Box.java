package jp.hasselqvist.MP4Analyzer;

public abstract class Box {
	protected String mType;
	protected long mSize;
	protected MP4FileProvider mProvider = MP4FileProvider.getProvider();
	
	public Box(String aType, long size) {
		mType = aType;
		mSize = size;
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
