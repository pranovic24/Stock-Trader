package src.main.model.account;

public class TFSA extends Account {
    
    public TFSA(double funds) {
        super(funds);
    }

    public TFSA(TFSA source) {
        super(source);
    }
}
