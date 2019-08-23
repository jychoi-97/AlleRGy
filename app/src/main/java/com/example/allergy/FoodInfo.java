package com.example.allergy;

import java.util.ArrayList;

public class FoodInfo {
    ArrayList<String> foodName;
    ArrayList<String> foodIngredient;

    public FoodInfo(ArrayList<String> foodName, ArrayList<String> foodIngredient) {
        this.foodName = foodName;
        this.foodIngredient = foodIngredient;
    }

    public ArrayList<String> getFoodName() {
        return foodName;
    }

    public ArrayList<String> getFoodIngredient() {
        return foodIngredient;
    }

    public void setFoodName(ArrayList<String> foodName) {
        this.foodName = foodName;
    }

    public void setFoodIngredient(ArrayList<String> foodIngredient) {
        this.foodIngredient = foodIngredient;
    }
}
