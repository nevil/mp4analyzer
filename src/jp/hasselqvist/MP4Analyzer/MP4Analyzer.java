package jp.hasselqvist.MP4Analyzer;

import java.io.IOException;
import java.util.Iterator;

import jp.hasselqvist.MP4Analyzer.BoxTree.BoxTreeArray;

public class MP4Analyzer {
	BoxTree mTree;

	MP4Analyzer(String aFile) throws IOException {
		mTree = new BoxTree();
		MP4FileProvider.createProvider(aFile);
		BoxRecognizer.createRecognizer(MP4FileProvider.getProvider());
	}

	public void analyze() {
		BoxRecognizer recognizer = BoxRecognizer.getRecognizer();
		Box box = null;
		BoxTree subTree = null;

		do {
			box = recognizer.identifyBox();
			if (box == null)
				break;

			subTree = box.parse();
			BoxTree leaf = mTree.addLeaf(box);
			if (subTree != null) {
				leaf.appendTree(subTree);
			}
		} while (box != null);
	}

	public void printTree() {
		printLeaf(mTree, 0);
	}

	private void printLeaf(BoxTree aLeaf, int indent) {
		if (aLeaf == null)
			return;

		if (!aLeaf.isRoot())
		{
			String s = "";
			for(int i = 0; i < indent; ++i)
				s = s + "-";

			System.out.println(s + aLeaf.getBox().toString());
		}

		++indent;
		BoxTreeArray leafs = aLeaf.getLeafs();
		if (leafs.size() > 0) {
			Iterator<BoxTree> it = leafs.iterator();
			while(it.hasNext()) {
				printLeaf(it.next(), indent);
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
