package jp.hasselqvist.MP4Analyzer;

import jp.hasselqvist.MP4Analyzer.boxes.BoxFree;
import jp.hasselqvist.MP4Analyzer.boxes.BoxFtyp;
import jp.hasselqvist.MP4Analyzer.boxes.BoxMdat;
import jp.hasselqvist.MP4Analyzer.boxes.BoxMoov;
import jp.hasselqvist.MP4Analyzer.boxes.BoxMvhd;
import jp.hasselqvist.MP4Analyzer.boxes.BoxSkip;
import jp.hasselqvist.MP4Analyzer.boxes.BoxTkhd;
import jp.hasselqvist.MP4Analyzer.boxes.BoxTrak;

// Singleton recognizer
public class BoxRecognizer {
	private static BoxRecognizer mBoxRecognizer;
	private MP4FileProvider mFile;

	private BoxRecognizer(MP4FileProvider aProvider) {
		mFile = aProvider;
	}

	public static BoxRecognizer getRecognizer() {
		return mBoxRecognizer;
	}

	public static BoxRecognizer createRecognizer(MP4FileProvider aProvider) {
		if (mBoxRecognizer == null) {
			mBoxRecognizer = new BoxRecognizer(aProvider);
		}

		return mBoxRecognizer;
	}

	public Box identifyBox() {
		long position = mFile.getPosition();
		long size = mFile.readInt32();
		String type = mFile.readBoxType();

		// TODO support size 1 - 64 bit large size, and 0 - to the end of file
		// TODO support extended type, uuid + 16 byte user type

		if ("ftyp".equals(type))
			return new BoxFtyp(type, size, position);
		if ("free".equals(type))
			return new BoxFree(type, size, position);
		if ("skip".equals(type))
			return new BoxSkip(type, size, position);
		else if ("moov".equals(type))
			return new BoxMoov(type, size, position);
		else if ("mvhd".equals(type))
			return new BoxMvhd(type, size, position);
		else if ("mdat".equals(type))
			return new BoxMdat(type, size, position);
		else if ("trak".equals(type))
			return new BoxTrak(type, size, position);
		else if ("tkhd".equals(type))
			return new BoxTkhd(type, size, position);
		else
			System.out.println("------ " + type + " -----");

		return null;
	}
}
