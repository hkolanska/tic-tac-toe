package App;

public enum Symbol { O,X,P;
    public Symbol returnOposite(Symbol s){
        if (s==X)
            return O;
        else if (s==O)
            return X;
        else
            return P;
    }

    @Override
    public String toString() {
        if (this==O)
            return "O";
        else if(this==X){
            return "X";
        }
        else return "P";
    }
}
