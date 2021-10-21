package org.rick.serial;

import java.io.*;
import java.util.zip.*;

//gzip、zip压缩、解压缩文件
public class FileZip {

	public static void main(String[] args) throws IOException {
		String in="pom.xml";
		String out="file"+File.separator+"pom.xml";
		gzip(in);
		gunzip("pom.xml.gz",out);
		
		File f=new File("file/");
		zip(f);
		unzip(new File("file.zip"),"file"+File.separator+"dir");
	}
	
		//gzip压缩文件
		public static void gzip(String fileName) throws IOException {
		    InputStream in = null;
		    String gzipFileName = fileName + ".gz";
		    OutputStream out = null;
		    try {
		        in = new BufferedInputStream(new FileInputStream(fileName));
		        out = new GZIPOutputStream(new BufferedOutputStream(
		                new FileOutputStream(gzipFileName)));
		        copy(in, out);
		    } finally {
		        if (out != null) {
		            out.close();
		        }
		        if (in != null) {
		            in.close();
		        }
		    }
		}
		public static void copy(InputStream input, OutputStream output)
				throws IOException {
			byte[] buf = new byte[4096];
			int bytesRead = 0;
			while ((bytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, bytesRead);
			}
		}
		
		//gzip解压缩文件
		public static void gunzip(String gzipFileName, String unzipFileName)
		        throws IOException {
		    InputStream in = null;
		    OutputStream out = null;
		    try {
		        in = new GZIPInputStream(new BufferedInputStream(
		                new FileInputStream(gzipFileName)));
		        out = new BufferedOutputStream(new FileOutputStream(
		                unzipFileName));
		        copy(in, out);
		    } finally {
		        if (out != null) {
		            out.close();
		        }
		        if (in != null) {
		            in.close();
		        }
		    }
		}
		
		//zip压缩文件
		public static void zip(File inFile) throws IOException {
			File zipFile = new File(inFile.getName()+".zip");
		    ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
		            new FileOutputStream(zipFile)));
		    try {
		        if (!inFile.exists()) {
		            throw new FileNotFoundException(inFile.getAbsolutePath());
		        }
		        inFile = inFile.getCanonicalFile();
		        String rootPath = inFile.getParent();
		        if (!rootPath.endsWith(File.separator)) {
		            rootPath += File.separator;
		        }
		        addFileToZipOut(inFile, out, rootPath);
		    } finally {
		        out.close();
		    }
		}
		private static void addFileToZipOut(File file, ZipOutputStream out,
		        String rootPath) throws IOException {
		    String relativePath = file.getCanonicalPath().substring(
		            rootPath.length());
		    if (file.isFile()) {
		        out.putNextEntry(new ZipEntry(relativePath));
		        InputStream in = new BufferedInputStream(new FileInputStream(file));
		        try {
		            copy(in, out);
		        } finally {
		            in.close();
		        }
		    } else {
		        out.putNextEntry(new ZipEntry(relativePath + File.separator));
		        for (File f : file.listFiles()) {
		            addFileToZipOut(f, out, rootPath);
		        }
		    }
		}
		//zip解压文件
		public static void unzip(File zipFile, String destDir) throws IOException {
		    ZipInputStream zin = new ZipInputStream(new BufferedInputStream(
		            new FileInputStream(zipFile)));
		    if (!destDir.endsWith(File.separator)) {
		        destDir += File.separator;
		    }
		    try {
		        ZipEntry entry = zin.getNextEntry();
		        while (entry != null) {
		            extractZipEntry(entry, zin, destDir);
		            entry = zin.getNextEntry();
		        }
		    } finally {
		        zin.close();
		    }
		}
		private static void extractZipEntry(ZipEntry entry, ZipInputStream zin,
		        String destDir) throws IOException {
		    if (!entry.isDirectory()) {
		        File parent = new File(destDir + entry.getName()).getParentFile();
		        if (!parent.exists()) {
		            parent.mkdirs();
		        }
		        OutputStream entryOut = new BufferedOutputStream(
		                new FileOutputStream(destDir + entry.getName()));
		        try {
		            copy(zin, entryOut);
		        } finally {
		            entryOut.close();
		        }
		    } else {
		        new File(destDir + entry.getName()).mkdirs();
		    }
		}
}
