import java.util.ArrayList;

public class Note {
    private ArrayList<String> notes;

    public Note(){
        this.notes = new ArrayList<>();
    }

    public void addNots(String note){notes.add(note);}

    public ArrayList<String> getNotes(){return notes;}

    public void printNotes(){
        for(String note : notes){
            System.out.println("- " + note);
        }
    }
}
