package jp.hasselqvist.MP4Analyzer.boxes;

import jp.hasselqvist.MP4Analyzer.Box;
import jp.hasselqvist.MP4Analyzer.BoxRecognizer;
import jp.hasselqvist.MP4Analyzer.BoxTree;

public class BoxMoov extends Box {
	public BoxMoov(String aType, long aSize, long aStartOffset) {
		super(aType, aSize, aStartOffset);
	}

	@Override
	public BoxTree parse() {
		// Todo Needs to check size bounds
		BoxRecognizer recognizer = BoxRecognizer.getRecognizer();
		Box box = null;
		BoxTree tree = null;
		BoxTree subTree = null;

		do {
			box = recognizer.identifyBox();
			if (box == null)
				break;

			if (tree == null)
				tree = new BoxTree();

			subTree = box.parse();
			BoxTree leaf = tree.addLeaf(box);
			if (subTree != null) {
				leaf.appendTree(subTree);
			}
		} while (box != null);

		return tree;
	}

}
