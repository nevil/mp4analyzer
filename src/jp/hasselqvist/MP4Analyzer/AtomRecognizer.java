package jp.hasselqvist.MP4Analyzer;

import java.io.IOException;

import jp.hasselqvist.MP4Analyzer.atoms.AtomFtyp;

// Singleton recognizer
public class AtomRecognizer {
	private static AtomRecognizer mAtomRecognizer;
	private MP4FileProvider mFile;

	private AtomRecognizer() {
		
	}

	public static AtomRecognizer createRecognizer(MP4FileProvider aFile) {
		if (mAtomRecognizer == null) {
			mAtomRecognizer = new AtomRecognizer();
		}

		mAtomRecognizer.mFile = aFile;
		
		return mAtomRecognizer;
	}
	
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

	final public static Atom createAtom(String aType, long size) {
		if ("ftyp".equals(aType))
			return new AtomFtyp(aType, size);

		return null;
	}
}
