package server;
//TODO
public class InputValidation {
    //проверка кол-ва символов в accountId. по умолчанию 16 символов
    boolean validationAccountID(String accountId) {
        boolean resultValAccId;
        if (accountId.length() == 16) {
            resultValAccId = true;
        } else {
            resultValAccId = false;
        }
        return resultValAccId;
    }
}
