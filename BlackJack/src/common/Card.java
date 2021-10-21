package common;

import java.io.Serializable;

public class Card implements Serializable {
    public Card(int number, String suit){
        this.number = number;
        this.suit = suit;
    }
    public int number;
    public String suit;
}
