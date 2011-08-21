package jp.hasselqvist.MP4Analyzer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class MP4Analyzer {
	AtomTree mTree;
	InputStream mStream;
	long mFileSize;

	MP4Analyzer(String aFile) throws IOException {
		mStream = null;

		try {
			File file = new File(aFile);
			mFileSize = file.length();
			mStream = new BufferedInputStream(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

		mTree = new AtomTree();
	}

	private long readInt32() {
		byte[] b = new byte[4];

		try {
			int read;
			read = mStream.read(b);
			if (read != b.length)
				return -1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}

		return ((b[0] & 0xFF) << 24) | ((b[1] & 0xFF) << 16) | ((b[2] & 0xFF) << 8) | (b[3] & 0x000000FF);
	}

	private String readAtomType() {
		byte[] type = new byte[4];
		try {
			mStream.read(type);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return new String(type);
	}

	public void parse() {
		long size = -1;
		String atom;
		do {
			if (-1 == (size = readInt32())) {
				break;
			}
			atom = readAtomType();
			try {
				long more = (size - 4 - 4);
				long skipped;
				while (more != 0) {
					skipped = mStream.skip(more);
					more -= skipped;
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			mTree.addLeaf(Atom.createAtom(atom, size));
		} while(true);
	}

	public void printTree() {
		AtomTree.Leaf tree = mTree.getRoot();

		printLeaf(tree);
	}

	private void printLeaf(AtomTree.Leaf aLeaf) {
		if (aLeaf == null)
			return;
		if (!aLeaf.getIsRoot())
		{
			System.out.println(aLeaf.getAtom().toString());
		}

		ArrayList<AtomTree.Leaf> leafs = aLeaf.getLeafs();
		if (leafs != null) {
			Iterator<AtomTree.Leaf> it = leafs.iterator();
			while(it.hasNext()) {
				printLeaf(it.next());
			}
		}
	}


	public static void main(String[] args) {
		MP4Analyzer analyzer;
		try {
			analyzer = new MP4Analyzer(args[0]);
			analyzer.parse();
			analyzer.printTree();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
