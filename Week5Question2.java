class AccessCheckerV2 {

    private AccessCheckerV2() { }

    private static final String OWN = "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE";
    private static final String PARENT = "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE";

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            throw new IllegalArgumentException("modifier and context must not be null");
        }
        boolean knownContext = accessorContext.equals("SAME_CLASS")
                || accessorContext.equals("SAME_PACKAGE")
                || accessorContext.equals("DIFFERENT_PACKAGE")
                || accessorContext.equals(OWN)
                || accessorContext.equals(PARENT);
        if (!knownContext) {
            throw new IllegalArgumentException("Unknown accessor context: " + accessorContext);
        }

        boolean allowed;
        switch (fieldModifier) {
            case "private":
                allowed = accessorContext.equals("SAME_CLASS");
                break;
            case "default":
                allowed = accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE");
                break;
            case "protected":
                allowed = accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        || accessorContext.equals(OWN);
                break;
            case "public":
                allowed = true;
                break;
            default:
                throw new IllegalArgumentException("Unknown modifier: " + fieldModifier);
        }
        return allowed ? "ALLOWED" : "DENIED";
    }
}

public class Week5Question2 {
    public static void main(String[] args) {
        System.out.println(AccessCheckerV2.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(AccessCheckerV2.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));

        String[] modifiers = {"private", "default", "protected", "public"};
        String[] contexts = {
            "SAME_CLASS", "SAME_PACKAGE", "DIFFERENT_PACKAGE",
            "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
        };
        System.out.println();
        for (String m : modifiers) {
            for (String c : contexts) {
                System.out.println(m + " / " + c + " -> " + AccessCheckerV2.classifyAccess(m, c));
            }
            System.out.println();
        }
    }
}
