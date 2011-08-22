package jp.hasselqvist.MP4Analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MP4Analyzer {
	AtomTree mTree;
	MP4FileProvider mFile;

	MP4Analyzer(String aFile) throws IOException {
		mTree = new AtomTree();
		mFile = new MP4FileProvider(aFile);
	}

	public void analyze() {
		
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
			analyzer.analyze();
			analyzer.printTree();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
