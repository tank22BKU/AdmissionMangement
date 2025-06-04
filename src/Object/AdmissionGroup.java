package Object;

public enum AdmissionGroup{
    A00,
    A01,
    A02,
    A03,
    B00,
    B01,
    B02,
    B03,
    DEFAULT;

    public static boolean isValid(AdmissionGroup admissionGroup){
        return switch (admissionGroup) {
            case A00, A01, A02, A03, B00, B01, B02, B03 -> true;
            default -> false;
        };
    }
};