package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
	
	public static void createFile(String filePath, String fileName, Boolean overwriteFlag) {
		try {
			if (!filePath.endsWith("\\")) {
				filePath = filePath+"\\";
			}
			File fileObj = new File(filePath+fileName);
			if (fileObj.exists()) {
				Log.logInfo(fileName+" exists at "+filePath);
				if (overwriteFlag) {
					fileObj.delete();
					Log.logInfo(fileName+" at "+filePath+" was deleted as overwriteFlag is true");
				}
			}
			if (fileObj.createNewFile()) {
				Log.logInfo(fileName+" was successfully created at "+filePath);
			}
			else {
				if (!overwriteFlag) {
					Log.logWarning(fileName + "already exists at "+filePath+" and overwriteFlag is false");
				}	
			}
			fileObj = null;
		} catch (IOException e) {
			Log.logException("Exception in createFile for path - "+filePath+" file name - "+fileName, e);
		}
	}
	
	public static void deleteFile (String filePath, String fileName) {
		try {
			if (!filePath.endsWith("\\")) {
				filePath = filePath+"\\";
			}
			File fileObj = new File(filePath+fileName);
			if (fileObj.exists()) {
				fileObj.delete();
				Log.logInfo(fileName+" at "+filePath+" was deleted");
			}
			else {
				Log.logWarning(fileName+" doesnt exist at "+filePath);
			}
			fileObj = null;
		} catch (Exception e) {
			Log.logException("Exception in deleteFile for path - "+filePath+" file name - "+fileName, e);
		}
	}
	
	public static void writeTextToFile(String filePath, String fileName, String stringToWrite, Boolean shouldAppend) {
		try {
			if (!filePath.endsWith("\\")) {
				filePath = filePath+"\\";
			}
			FileWriter fileWriterObj = new FileWriter(filePath+fileName);
			if(shouldAppend) {
				fileWriterObj.append(stringToWrite);
			}
			else {
				fileWriterObj.write(stringToWrite);
			}
			fileWriterObj.close();
			fileWriterObj = null;
		} catch (IOException e) {
			Log.logException("Exception in writeTextToFile on "+filePath+fileName,e);
		}
	}
	
	public static void renameFile(String filePath, String currentFileName, String newFileName) {
		try {
			if (!filePath.endsWith("\\")) {
				filePath = filePath+"\\";
			}
			File newFileObj = new File(filePath+newFileName);
			if (newFileObj.exists()) {
				throw new java.io.IOException("Cannot rename file as there is an existing file with name - "+newFileName+" at path - "+filePath);
			}
			else
			{
				File currentFileObj = new File(filePath+currentFileName);
				if (currentFileObj.renameTo(newFileObj)) {
					Log.logInfo(currentFileName+" at "+filePath+" was renamed to "+newFileName);
				}
			}
			newFileObj = null;
		} catch (IOException e) {
			Log.logException("Exception in renameFile from - "+currentFileName+" to - "+newFileName+" at "+filePath,e);
		}
	}
	
	public static void moveFile(String currentFilePath, String newFilePath, String fileName) {
		try {
			Files.move(Paths.get(currentFilePath+fileName), Paths.get(newFilePath+fileName));
			Log.logInfo(fileName+" moved from - "+currentFilePath+" to - "+newFilePath);
		} catch (Exception e) {
			Log.logException("Exception in moveFile "+fileName+" from - "+currentFilePath+" to - "+newFilePath,e);
		}
	}

}
