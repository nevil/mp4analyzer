package jp.hasselqvist.MP4Analyzer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MP4FileProvider {
	private static MP4FileProvider mInstance;
	private RandomAccessFile mFile;
	private long mFileSize;

	private MP4FileProvider(String aFilePath) throws IOException {
		mFile = null;

		try {
			File file = new File(aFilePath);
			mFileSize = file.length();
			mFile = new RandomAccessFile(file, "r");
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static MP4FileProvider createProvider(String aFilePath) throws IOException {
		if (mInstance == null)
			mInstance = new MP4FileProvider(aFilePath);

		return mInstance;
	}

	public static MP4FileProvider getProvider() {
		return mInstance;
	}

	public long getFileSize() {
		return mFileSize;
	}

	public long getPosition() {
		try {
			return mFile.getFilePointer();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int skipBytes(int aOffset) {
		try {
			return mFile.skipBytes(aOffset);
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int readInt8() {
		try {
			return mFile.read();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int readInt16() {
		byte[] b = new byte[2];

		try {
			int read;
			read = mFile.read(b);
			if (read != b.length)
				return -1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}

		return ((b[0] & 0xFF) << 8) | (b[1] & 0xFF);
	}

	public int readInt24() {
		byte[] b = new byte[3];

		try {
			int read;
			read = mFile.read(b);
			if (read != b.length)
				return -1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}

		return ((b[0] & 0xFF) << 16) | ((b[1] & 0xFF) << 8) | (b[2] & 0xFF);
	}

	public long readInt32() {
		byte[] b = new byte[4];

		try {
			int read;
			read = mFile.read(b);
			if (read != b.length)
				return -1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}

		return ((b[0] & 0xFF) << 24) | ((b[1] & 0xFF) << 16) | ((b[2] & 0xFF) << 8) | (b[3] & 0xFF);
	}

	public long readInt64() {
		long ls, ms;
		// TODO: Correct read order
		ls = readInt32();
		ms = readInt32();

		if (ls == -1 || ms == -1)
			return -1;

		return (ls << 32) | ms;
	}

	public String readBoxType() {
		byte[] type = new byte[4];
		try {
			mFile.read(type);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return new String(type);
	}
}
