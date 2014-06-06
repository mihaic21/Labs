package ESDomain;

import java.util.Map;

/**
 * Created by mihai on 05.06.2014.
 */
public class Rule {

    private String id;
    private String[] ifStatement;
    private String[] thenStatement;
    private boolean state;

    public Rule(String id, String[] ifStatement, String[] thenStatement) {
        this.id = id;
        this.ifStatement = ifStatement;
        this.thenStatement = thenStatement;
        this.state = false;
    }

    public void checkInferenceForward(Map<String, Fact> facts){

        this.state = facts.get(ifStatement[0]).isState();
        for (int i = 0; i < ifStatement.length; i++){
            if (ifStatement[i] != null){
                if (ifStatement[i].equals("AND")){
                    this.state = this.state && facts.get(ifStatement[i+1]).isState();
                }
                if (ifStatement[i].equals("OR")){
                    this.state = facts.get(ifStatement[i+1]).isState();
                }
            }
        }

        if (!this.state){
            return;
        }
        //if the causes are evaluated to true, then the effects are true
        for (int i = 0; i < thenStatement.length; i += 2){
            facts.get(thenStatement[i]).setState(true);
        }
    }

    public void checkInferenceBackward(Map<String, Fact> facts){
        //System.out.println("The size is " + facts.size());
        this.state = facts.get(thenStatement[0]).isState();
        for (int i = 2; i < thenStatement.length; i += 2){
            if (thenStatement[i] != null){
                this.state = this.state && facts.get(thenStatement[i]).isState();
            }
        }

        if (!this.state){
            return;
        }

        for (int i = 0; i < ifStatement.length; i += 2){
            facts.get(ifStatement[i]).setState(true);
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getIfStatement() {
        return ifStatement;
    }

    public void setIfStatement(String[] ifStatement) {
        this.ifStatement = ifStatement;
    }

    public String[] getThenStatement() {
        return thenStatement;
    }

    public void setThenStatement(String[] thenStatement) {
        this.thenStatement = thenStatement;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        String rule = this.id;
        rule += " { IF";
        for (int i = 0; i < this.ifStatement.length; i++)
        {
            rule += " " + ifStatement[i];
        }

        rule += " THEN";

        for (int i = 0; i < this.thenStatement.length; i++)
        {
            rule += " " + thenStatement[i];
        }

        rule += " } " + this.state;
        return rule;
    }
}
