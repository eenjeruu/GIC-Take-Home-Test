public enum Command {
    L, // rotate left
    R, // rotate right
    F; // move forward

    public static Command fromChar(char commandChar) {
        switch (commandChar) {
            case 'L': return L;
            case 'R': return R;
            case 'F': return F;
            default: throw new IllegalArgumentException("Invalid command character: " + commandChar);
        }
    }
}
