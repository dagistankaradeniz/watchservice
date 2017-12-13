package io.dagistan.watchservice;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchServiceDemo {

	public static void main(String[] args) {
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			Path dir = Paths.get("D:/Test");// give your full directory path to listen
			dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);

			System.out.println("WatchService listens: " + dir.getFileName());
			System.out.println("===============================================");
			WatchKey key;
			while ((key = watchService.take()) != null) {
				for (WatchEvent<?> event : key.pollEvents()) {
					System.out.println("Event: " + event.kind() + "\t File: " + event.context());
				}
				key.reset();
			}

		} catch (IOException e) {
			System.err.println("### IOException : " + e);
		} catch (InterruptedException e) {
			System.err.println("### InterruptedException : " + e);
		}
	}
}
