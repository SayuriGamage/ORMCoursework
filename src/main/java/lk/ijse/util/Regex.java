package lk.ijse.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text){
        String field = "";

        switch (textField) {
            case  DESCRIPTION:
                field="^[A-Za-z0-9 .,!?'-]+$\n";
                break;
            case NAME:
                field = "^[A-Za-z\\s]{4,}$";
                break;
            case MOBILE:
                field = "^[0-9]{10}$";
                break;
            case ADDRESS:
                field = "^[A-Za-z\\s]{4,}$";
                break;
            case PASSWORD:
                field = "^[A-Za-z\\s]{4,}$";
                break;
                case EMAIL:
                    field = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
                    break;
            case FEE:
                field = "^[0-9]{4,}$";
                break;
        }


        Pattern pattern = Pattern.compile(field);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField){
        if (Regex.isTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-text-box-border: green;");


            return true;
        }else {
            textField.setStyle("-fx-text-box-border: red;");

            return false;
        }
    }

}