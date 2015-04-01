import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class filesearch {

	private String filename;
	private List<String> result = new ArrayList<String>();

	public String getFileName() {
		return filename;
	}

	public void setFileName(String fileNameToSearch) {
		this.filename = fileNameToSearch;
	}

	public List<String> getResult() {
		return result;
	}

	public static void main(String[] args) {

		filesearch fileSearch = new filesearch();
		File nameOfFile;
		File directory = new File("E:/workspace");
		File[] files = directory.listFiles();

		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};
		files = directory.listFiles(fileFilter);
		// System.out.println(files.length);
		if (files.length == 0) {
			System.out.println("Either directory does not exist or is not a directory");
		} 
		else {
			for (int i = 0; i < files.length; i++) {
				nameOfFile = files[i];
				System.out.println(nameOfFile.toString());
			
				fileSearch.searchDirectory(new File(nameOfFile.toString()),"sample.txt");

				int numOfFile = fileSearch.getResult().size();
				if (numOfFile == 0) {
					System.out.println("\nNo result found!");
				} 
				else {
					System.out.println("\nFound " + numOfFile + " result!\n");
					for (String matched : fileSearch.getResult()) {
						System.out.println("Found : " + matched);
					}
				}
			}
		}
	}

	public void searchDirectory(File directory, String fileNameToSearch) {
		setFileName(fileNameToSearch);
		if (directory.isDirectory()) {
			search(directory);
		} 
		else {
			System.out.println(directory.getAbsoluteFile() + " is not a directory!!");
		}

	}

	private void search(File file) {
		if (file.isDirectory()) {
			System.out.println("Searching directory:    "+ file.getAbsoluteFile());

			if (file.canRead()) {
				try {
					for (File current : file.listFiles()) {
						if (current.isDirectory()) {
							search(current);
						} else {
							if (getFileName().equals(current.getName().toLowerCase())) {
								result.add(current.getAbsoluteFile().toString());
							}

						}
					}
				} catch (NullPointerException n) {
				}
			} else {
				System.out.println(file.getAbsoluteFile() + "Permission Denied!!!");
			}
		}

	}

}