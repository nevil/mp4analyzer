package jp.hasselqvist.MP4Analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MP4Analyzer {
	BoxTree mTree;
	MP4FileProvider mFile;

	MP4Analyzer(String aFile) throws IOException {
		mTree = new BoxTree();
		mFile = new MP4FileProvider(aFile);
	}

	public void analyze() {
		
	}
	
	public void printTree() {
		BoxTree.Leaf tree = mTree.getRoot();

		printLeaf(tree);
	}

	private void printLeaf(BoxTree.Leaf aLeaf) {
		if (aLeaf == null)
			return;

		if (!aLeaf.getIsRoot())
		{
			System.out.println(aLeaf.getBox().toString());
		}

		ArrayList<BoxTree.Leaf> leafs = aLeaf.getLeafs();
		if (leafs != null) {
			Iterator<BoxTree.Leaf> it = leafs.iterator();
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
