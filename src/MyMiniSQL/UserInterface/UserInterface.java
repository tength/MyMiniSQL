package MyMiniSQL.UserInterface;

class OutputMessage{
    //unfinished definition, you could extends this class to make suitable functions
    private String easyMsg = "";

    OutputMessage(){}

    public void setEasyMsg(String easyMsg) {
        this.easyMsg = easyMsg;
    }

    //get the final output message for UI, could be reload in the subclass
    public String getOutputMessage(){
        return easyMsg;
    }
}

public interface UserInterface {
    public void getUserInput(String msg);
    public void ShowProgramOutput(OutputMessage outMsg);
}
