package bestpractice;

interface Authority {
    public String resource();

    public default String write() {
        return String.format("%s:write", resource());
    }

    public static Authority documents() {
        return () -> "documents";
    }

}
public class BuildAuthorityWithLambda {
    public static void main(String[] args) {
        Authority authority = Authority.documents();
        System.out.println(authority.resource());
        System.out.println(Authority.documents().write());
    }
}
