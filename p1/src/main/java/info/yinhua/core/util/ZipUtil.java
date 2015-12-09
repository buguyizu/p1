package info.yinhua.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * zip工具类
 */
public class ZipUtil {

	private static String TEMP_NAME = null;
	
	static {
		URL url = ZipUtil.class.getClassLoader().getResource("//");
		if (url != null) {
			TEMP_NAME = url.getPath() + ".temp";
			System.out.println(TEMP_NAME);
		} else {
			TEMP_NAME = ".temp";
		}
	}
	
	//压缩
	public static boolean pack(String[] files, String zipName) {
		
		ZipOutputStream zos = null;
		
		try {
			
			zos = new ZipOutputStream(new FileOutputStream(zipName));
			InputStream is = null;
			
			for (String file : files) {
				
				ZipEntry entry = new ZipEntry(file);
				zos.setComment(file);
				zos.putNextEntry(entry);
				
				try {
					is = new FileInputStream(file);
					int len = 0;
					while ((len = is.read()) != -1) {
						zos.write(len);
					}
				} finally {
					try {
						if (is != null) {
							is.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
						System.out
								.println("======ERROR======File Close Error.");
						return false;
					}
				}
			}

			return true;
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("======ERROR======Zip File Not Exist.");
			return false;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println("======ERROR======Zip File Write Error.");
			return false;
			
		} finally {
			
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("======ERROR======Zip File Close Error.");
				return false;
			}
		}
	}

    //解压第一个文件，返回属性集，供授权文件时使用
    public static Properties unPack(String zipName) {
    	ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(new File(ZipUtil.class
					.getClassLoader().getResource(zipName).toURI()));

			@SuppressWarnings("unchecked")
			Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile
					.entries();
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();
				InputStream is = zipFile.getInputStream(zipEntry);

				Properties p = new Properties();
				p.load(is);
				return p;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("授权出错(读取授权文件失败)，请与软件供应商联系。");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (zipFile != null) {
					zipFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
    }
    

    //解压第一个文件，返回属性集，供授权文件时使用
    public static Properties unPack(File file) {
    	ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(file);

			@SuppressWarnings("unchecked")
			Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile
					.entries();
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();
				InputStream is = zipFile.getInputStream(zipEntry);

				Properties p = new Properties();
				p.load(is);
				return p;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("授权出错(读取授权文件失败)，请与软件供应商联系。");
		} finally {
			try {
				if (zipFile != null) {
					zipFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
    }

    //解压第一个文件，返回属性集，供授权文件时使用
	public static Properties unPack(InputStream in) {
		//先写入临时文件
		OutputStream os = null;
		try {
			os = new FileOutputStream(TEMP_NAME);
			int len = 0;
			while ((len = in.read()) != -1) {
				os.write(len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File file = new File(TEMP_NAME);
		Properties p = unPack(file);
		//删除临时文件
		file.delete();
		return p;
	}
	


    //解压第一个文件，返回属性集，供授权文件时使用
	public static Properties unPack(byte[] b) {
		//先写入临时文件
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(TEMP_NAME);
			os.write(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File file = new File(TEMP_NAME);
		Properties p = unPack(file);
		//删除临时文件
		file.delete();
		return p;
	}
}