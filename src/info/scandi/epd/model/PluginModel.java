package info.scandi.epd.model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import info.scandi.epd.service.FileService;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class PluginModel {

	private String name;
	private String path;
	private FileService fileService = new FileService();

	public PluginModel(String name, String path) {
		super();
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Observable<String> download(String pluginDir, String eclipsePath, int verbose) {
		return Observable.create(s -> {
			s.onNext("Downloading plugin " + this.name);
			Integer fileCount;
			String dirPath = pluginDir + File.separator + this.name.replace(" ", "_");
			this.fileService.createDir(dirPath);
			do {
				fileCount = this.fileService.getFileCountInDir(dirPath);
				StringBuilder command = new StringBuilder(eclipsePath + File.separator
						+ "eclipse -nosplash -verbose -application org.eclipse.equinox.p2.metadata.repository.mirrorApplication");
				command.append(" -source " + this.path);
				command.append(" -destination " + pluginDir + File.separator + this.name);
				this.executeCommand(command.toString(), verbose, s);
				this.executeCommand(command.toString().replace("metadata", "artifact"), verbose, s);
				s.onNext(this.fileService.getFileCountInDir(dirPath) == fileCount
						? "Téléchargement de " + this.name + " terminé"
						: "");
			} while (this.fileService.getFileCountInDir(dirPath) != fileCount);

			s.onComplete();
		});
	}

	private void executeCommand(String command, int verbose, ObservableEmitter<String> s) {
		Scanner sc = null;
		try {
			Process p = Runtime.getRuntime().exec(command);
			if (verbose == 1) {
				sc = new Scanner(p.getInputStream());
				while (sc.hasNext()) {
					s.onNext(sc.nextLine());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
}
