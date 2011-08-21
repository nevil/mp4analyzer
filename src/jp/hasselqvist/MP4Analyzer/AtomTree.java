package jp.hasselqvist.MP4Analyzer;

import java.util.ArrayList;

public class AtomTree {
	private Leaf mRoot;

	public AtomTree() {
		mRoot = new Leaf(null, true);
	}

	public AtomTree(Atom aAtom) {
		mRoot = new Leaf(null, true);
		addLeaf(mRoot, aAtom);
	}

	public void addLeaf(Atom aAtom) {
		addLeaf(mRoot, aAtom);
	}

	public void addLeaf(Leaf aNode, Atom aAtom) {
		Leaf leaf = new Leaf(aAtom);
		aNode.addLeaf(leaf);
	}

	public Leaf getRoot() {
		return mRoot;
	}

	public class Leaf {
		private final Atom mAtom;
		private Leaf mParent;
		private ArrayList<Leaf> mLeafs;
		private boolean mIsRoot;

		public Leaf(Atom aAtom) {
			mAtom = aAtom;
			mParent = null;
			mIsRoot = false;
		}

		private Leaf(Atom aAtom, boolean aIsRoot) {
			mAtom = aAtom;
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

		public Atom getAtom() {
			return mAtom;
		}

		public boolean getIsRoot() {
			return mIsRoot;
		}
	}
}
