package jp.hasselqvist.MP4Analyzer;

import java.util.ArrayList;

public class BoxTree {
	private Leaf mRoot;

	public BoxTree() {
		mRoot = new Leaf(null, true);
	}

	public BoxTree(Box aBox) {
		mRoot = new Leaf(null, true);
		addLeaf(mRoot, aBox);
	}

	public void addLeaf(Box aBox) {
		addLeaf(mRoot, aBox);
	}

	public void addLeaf(Leaf aNode, Box aBox) {
		Leaf leaf = new Leaf(aBox);
		aNode.addLeaf(leaf);
	}

	public Leaf getRoot() {
		return mRoot;
	}

	public class Leaf {
		private final Box mBox;
		private Leaf mParent;
		private ArrayList<Leaf> mLeafs;
		private boolean mIsRoot;

		public Leaf(Box aBox) {
			mBox = aBox;
			mParent = null;
			mIsRoot = false;
		}

		private Leaf(Box aBox, boolean aIsRoot) {
			mBox = aBox;
			mParent = null;
			mIsRoot = aIsRoot;
		}

		public void addLeaf(Leaf aLeaf) {
			aLeaf.mParent = this;

			if (mLeafs == null)
				mLeafs = new ArrayList<Leaf>();

			mLeafs.add(aLeaf);
		}

		public Leaf getParent() {
			return mParent;
		}

		public ArrayList<Leaf> getLeafs() {
			return mLeafs;
		}

		public Box getBox() {
			return mBox;
		}

		public boolean getIsRoot() {
			return mIsRoot;
		}
	}
}
