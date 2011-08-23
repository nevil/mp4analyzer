package jp.hasselqvist.MP4Analyzer;

import java.io.IOException;

import jp.hasselqvist.MP4Analyzer.boxes.BoxFtyp;

// Singleton recognizer
public class BoxRecognizer {
	private static BoxRecognizer mBoxRecognizer;
	private MP4FileProvider mFile;

	private BoxRecognizer() {
		
	}

	public static BoxRecognizer createRecognizer(MP4FileProvider aFile) {
		if (mBoxRecognizer == null) {
			mBoxRecognizer = new BoxRecognizer();
		}

		mBoxRecognizer.mFile = aFile;
		
		return mBoxRecognizer;
	}
/*	
	public void parse() {
		long size = -1;
		String atom;
		do {
			if (-1 == (size = mFile.readInt32())) {
				break;
			}
			atom = mFile.readAtomType();
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
*/
	public Box identifyBox() {
		long size = mFile.readInt32();
		String type = mFile.readBoxType();

		if ("ftyp".equals(type))
			return new BoxFtyp(type, size);
		if ("free".equals(type))
			return null;
		else if ("moov".equals(type))
			return null;
		else if ("mdat".equals(type))
			return null;

		return null;
	}
}
