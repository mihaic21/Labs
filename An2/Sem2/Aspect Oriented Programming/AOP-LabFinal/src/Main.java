import Repository.*;
import UI.HomeForm;

public class Main {

    public static void main(String[] args) {

        String CANDIDATES_FILE_NAME = "candidates.txt";
        String SECTIONS_FILE_NAME = "sections.txt";
        String ADMITTED_FILE_NAME = "admitted.txt";
        String FAILED_FILE_NAME = "failed.txt";

        RepoInterface repository = new Repository(CANDIDATES_FILE_NAME, SECTIONS_FILE_NAME, ADMITTED_FILE_NAME, FAILED_FILE_NAME);

        HomeForm homeForm = new HomeForm(repository);

    }
}
