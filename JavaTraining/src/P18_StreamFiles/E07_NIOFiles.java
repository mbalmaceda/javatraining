package P18_StreamFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/*
 *  With Windows 7 or newer, you can add a user to the list of who may create symbolic links
 *  (without disabling UAC) using security policies.
 *
 *  1. Run secpol.msc
 *  2. Go to Security Settings|Local Policies|User Rights Assignment|Create symbolic links
 *  3. Add your user name.
 *  4. Restart your session.
 */

public class E07_NIOFiles {

	public static void main(String[] args) {

		Path DIR = Paths.get("src", "P18_StreamFiles", "files");
		
		Path newDIR = DIR.resolve("testfolder");
		
		if(!Files.exists(newDIR)) {
			try {
				Files.createDirectory(newDIR);
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		Path file = DIR.resolve("myFile.txt");
		
		if(!Files.exists(file, LinkOption.NOFOLLOW_LINKS)) {
			try {
				Files.createFile(file);
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		Path link = newDIR.resolve("myLink");
		Path book = DIR.resolve("book.pdf");
		
		try {
			Files.createSymbolicLink(link, book.toAbsolutePath());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		
		Path newBook = newDIR.resolve("bookcopy.pdf");
		
		try {
			Files.copy(book, newBook, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		
		Path renameBook = newDIR.resolve("book.pdf");
		
		try {
			Files.move(newBook, renameBook, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		
		try {
			System.out.println(Files.getOwner(renameBook));
			System.out.println(Files.getLastModifiedTime(renameBook));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		
		System.out.println();
		
		try {
			Files.list(newDIR).map(p -> p.getFileName()).forEach(System.out::println);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		
		System.out.println();
		
		try {
			Files.walk(DIR).map(p -> p.toAbsolutePath()).forEach(System.out::println);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		
		try {
			Files.delete(renameBook);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		
		try {
			Files.deleteIfExists(link);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		
		try {
			Files.deleteIfExists(newDIR);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

}
