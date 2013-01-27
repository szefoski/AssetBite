package com.gamelion.assetbite.model.rootdirectory;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryDataModel {
	private TreeElement rootElement;
	Path rootPath;
	
	
	public DirectoryDataModel(Path root) {
		rootPath = root;
	}
	
	
	public void refresh() {
		rootElement = new TreeElement(rootPath, null);
		DirectoryVisitor t = new DirectoryVisitor();
		
		try {
			Files.walkFileTree(rootPath, t);
			rootElement.sort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void add(Path path) {
		rootElement.AddChild(path);
    }
	
	private class DirectoryVisitor extends SimpleFileVisitor<Path> {
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
			add(file);
			return FileVisitResult.CONTINUE;
		}


		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
			//System.out.format("Directory: %s%n", dir);
			add(dir);
			return FileVisitResult.CONTINUE;
		}

		
		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) {
			System.err.println(exc);
			return FileVisitResult.CONTINUE;
		}
	}

	public TreeElement GetRootElement() {
		// TODO Auto-generated method stub
		return rootElement;
	}
}
