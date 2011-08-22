package jp.hasselqvist.MP4Analyzer;

public abstract class Atom {
	private String mType;
	private long mSize;

	public Atom(String aType, long size) {
		mType = aType;
		mSize = size;
	}

	public String getType() {
		return mType;
	}

	public long getSize() {
		return mSize;
	}

	public String toString() {
		return String.format("%s (%d)", mType, mSize);
	}
}
