package jp.hasselqvist.MP4Analyzer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MP4FileProvider {
	private InputStream mStream;
	private long mFileSize;

	public MP4FileProvider(String aFilePath) throws IOException {
		mStream = null;

		try {
			File file = new File(aFilePath);
			mFileSize = file.length();
			mStream = new BufferedInputStream(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public long getFileSize() {
		return mFileSize;
	}

	public long readInt32() {
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

	public String readAtomType() {
		byte[] type = new byte[4];
		try {
			mStream.read(type);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return new String(type);
	}
}
