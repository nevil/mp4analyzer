package jp.hasselqvist.MP4Analyzer;

import jp.hasselqvist.MP4Analyzer.atoms.AtomFtyp;

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

	final public static Atom createAtom(String aType, long size) {
		if ("ftyp".equals(aType))
			return new AtomFtyp(aType, size);

		return null;
	}
}
