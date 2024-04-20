package com.example.eventmanagerapp.controller.util;

public class PrefillSMSUtility {

    // Validates SMS message and prefills category form if the SMS message is valid
//    public void prefillCategoryForm(String msg){
//        String attributes;
//        String categoryToken;
//        String nameToken = null;
//        String countToken = null;
//        String isActiveToken = null;
//        boolean isValidMessage = true;
//
//        try {
//            // first split occurs at colon ":"
//            StringTokenizer checkCategory = new StringTokenizer(msg, ":");
//            categoryToken = checkCategory.nextToken();
//
//            // rest of the attributes are tokenized with semicolon delimiter ";"
//            attributes = checkCategory.nextToken();
//            StringTokenizer attributesTokenizer = new StringTokenizer(attributes, ";");
//
//            // if there are an invalid number of entries, then we have an invalid message
//            int tokenNumber = attributesTokenizer.countTokens();
//            if (tokenNumber != 3){
//                isValidMessage = false;
//            }
//
//            // get token values
//            nameToken = attributesTokenizer.nextToken();
//            countToken = attributesTokenizer.nextToken();
//            isActiveToken = attributesTokenizer.nextToken();
//
//            // check if category tag is present
//            if (!categoryToken.equals("category")){
//                isValidMessage = false;
//            }
//
//            // check if count is a positive integer
//            try {
//                int check = Integer.parseInt(countToken);
//                if (check < 0){
//                    isValidMessage = false;
//                }
//            } catch (NumberFormatException e){
//                isValidMessage = false;
//            }
//
//            // check if isActiveToken is a valid boolean
//            if (!(isActiveToken.equalsIgnoreCase("TRUE") || isActiveToken.equalsIgnoreCase("FALSE"))){
//                isValidMessage = false;
//            }
//
//        } catch (Exception e){
//            isValidMessage = false;
//        }
//
//        // if the SMS message had passed all validations, then prefill form
//        if (isValidMessage){
//            categoryName.setText(nameToken);
//            categoryCount.setText(countToken);
//            categoryIsActive.setChecked(isActiveToken.equalsIgnoreCase("TRUE"));
//        }
//        // otherwise, show error toast message
//        else {
//            Toast.makeText(NewCategoryActivity.this, "SMS message had incorrect values or format", Toast.LENGTH_LONG).show();
//        }
//    }

    // Validates SMS message and prefills event form if the SMS message is valid
//    public void prefillEventForm(String msg){
//        String attributes;
//        String eventToken;
//        String nameToken = null;
//        String categoryIdToken = null;
//        String ticketToken = null;
//        String isActiveToken = null;
//        boolean isValidMessage = true;
//
//        try {
//            // first split occurs at colon ":"
//            StringTokenizer checkEvent= new StringTokenizer(msg, ":");
//            eventToken = checkEvent.nextToken();
//
//            // rest of the attributes are tokenized with semicolon delimiter ";"
//            attributes = checkEvent.nextToken();
//            StringTokenizer sT = new StringTokenizer(attributes, ";");
//
//            // if there are an invalid number of entries, then we have an invalid message
//            int tokenNumber = sT.countTokens();
//            if (tokenNumber != 4){
//                isValidMessage = false;
//            }
//
//            // get token values
//            nameToken = sT.nextToken();
//            categoryIdToken = sT.nextToken();
//            ticketToken = sT.nextToken();
//            isActiveToken = sT.nextToken();
//
//            // check if event tag is present
//            if (!eventToken.equals("event")){
//                isValidMessage = false;
//            }
//
//            // check if tickets is a positive integer
//            try {
//                int check = Integer.parseInt(ticketToken);
//                if (check < 0){
//                    isValidMessage = false;
//                }
//            } catch (NumberFormatException e){
//                isValidMessage = false;
//            }
//
//            // check if isActiveToken is a valid boolean
//            if (!(isActiveToken.equalsIgnoreCase("TRUE") || isActiveToken.equalsIgnoreCase("FALSE"))){
//                isValidMessage = false;
//            }
//
//        } catch (Exception e){
//            isValidMessage = false;
//        }
//
//        // if the SMS message had passed all validations, then prefill form
//        if (isValidMessage){
//            eventName.setText(nameToken);
//            eventCategoryId.setText(categoryIdToken);
//            eventTickets.setText(ticketToken);
//            eventIsActive.setChecked(isActiveToken.equalsIgnoreCase("TRUE"));
//        }
//        // otherwise, show error toast message
//        else {
//            Toast.makeText(EventActivity.this, "Event SMS message had incorrect values or format", Toast.LENGTH_LONG).show();
//        }
//    }
}
