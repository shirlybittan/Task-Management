package system;

// Using Strategy Pattern
public interface Status {

    public int status();


}

class NotDone implements Status{

    @Override
    public int status() {
        return 1;
    }

}

class InProgress implements Status{

    @Override
    public int status() {
        return 2;
    }
}

class Done implements Status{

    @Override
    public int status() {
        return 3;
    }
}
