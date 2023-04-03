import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

public class GitCommits {
    public static void main(String[] args) throws IOException, GitAPIException {
        // Imposta il percorso della cartella del repository
        String repoPath = "C:\\Users\\vlrbr\\OneDrive\\Desktop\\bookkeeper";

        // Apri il repository
        try (Repository repository = Git.open(new File(repoPath)).getRepository()) {
            // Crea un oggetto Git
            try (Git git = new Git(repository)) {
                // Crea un oggetto RevWalk per scorrere la storia dei commit
                try (RevWalk walk = new RevWalk(repository)) {
                    // Prendi l'ultimo commit
                    RevCommit commit = walk.parseCommit(repository.resolve("HEAD"));
                    // Imposta il percorso del file
                    String filePath = "C:\\Users\\vlrbr\\Downloads\\12\\12\\GetReleaseInfo\\GetReleaseInfo2\\src\\CommitLog.txt";
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                    // Apri il file e crea un BufferedWriter

                    // Scorro i commit a ritroso
                    while (commit != null) {
                        //System.out.println("Commit: " + commit.getName() + " - " + commit.getFullMessage());

                        writer.write("Commit: " + commit.getName() + " - " + commit.getFullMessage() + " - " + commit.getAuthorIdent());
                        writer.newLine();

                        // Prendi il commit precedente
                        commit = commit.getParentCount() > 0 ? walk.parseCommit(commit.getParent(0)) : null;


                    }
                }
            }
        }


    }

}