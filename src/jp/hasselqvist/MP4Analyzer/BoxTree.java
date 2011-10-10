package jp.hasselqvist.MP4Analyzer;

import java.util.ArrayList;
import java.util.Iterator;

public class BoxTree {
	private final Box mBox;
	private BoxTreeArray mLeafs;
	private boolean mIsRoot = false;

	public class BoxTreeArray extends ArrayList<BoxTree> {
		private static final long serialVersionUID = 3859507086587808884L;

	}

	public BoxTree() {
		this(null);
		mIsRoot = true;
	}

	private BoxTree(Box aBox) {
		mBox = aBox;
		mIsRoot = false;
		mLeafs = new BoxTreeArray();
	}

	public BoxTree addLeaf(Box aBox) {
		BoxTree leaf = new BoxTree(aBox);
		mLeafs.add(leaf);
		return leaf;
	}

	public void appendTree(BoxTree aTree) {
		if (aTree.mIsRoot) {
			Iterator<BoxTree>it = aTree.mLeafs.iterator();
			while(it.hasNext()) {
				mLeafs.add(it.next());
			}
		}
		else {
			mLeafs.add(aTree);
		}
	}

	public Box getBox() {
		return mBox;
	}

	public BoxTreeArray getLeafs() {
		return mLeafs;
	}

	public boolean isRoot() {
		return mIsRoot;
	}
}
